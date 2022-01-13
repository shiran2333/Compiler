import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Visitor extends labBaseVisitor<Void> {
    int blockCount = 0;
    VisitorInfo info;
    SymbolTable symbolTable;

    public Visitor(String filename) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream(filename)));
        info = new VisitorInfo();
        symbolTable = new SymbolTable();
    }

    @Override
    public Void visitFuncDef(labParser.FuncDefContext ctx) {
        System.out.println("define dso_local i32 @main() {");
        super.visitFuncDef(ctx);
        System.out.println("}");
        return null;
    }

    @Override
    public Void visitBlock(labParser.BlockContext ctx) {
        symbolTable.createInnerTable();
        super.visitBlock(ctx);
        symbolTable.exitCurrentTable();
        return null;
    }

    @Override
    public Void visitVarDef(labParser.VarDefContext ctx) {
        String name = ctx.ident().getText();
        if (symbolTable.currentBlockHasName(name)) {
            System.out.printf("%s is already used\n", name);
            System.exit(1);
        }
        else {
            SymbolTableItem item = symbolTable.newVariableInteger(name);
            if (item.isGlobal()) {
                int value = 0;
                if (ctx.initVal() != null) {
                    visit(ctx.initVal());
                    if (info.isRegister()) {
                        System.out.print("Global variable should be static");
                        System.exit(1);
                    }
                    value = info.getValue();
                    System.out.printf("%s = global i32 %d\n", item.getRegisterString(), value);
                }
            }
            else {
                System.out.printf("\t%s = alloca i32\n", item.getRegisterString());
                if (ctx.initVal() != null) {
                    visit(ctx.initVal());
                    System.out.printf("\tstore i32 %s, i32* %s\n", info, item.getRegisterString());
                }
            }

        }
        return null;
    }

    @Override
    public Void visitConstDef(labParser.ConstDefContext ctx) {
        String name = ctx.ident().getText();
        if (symbolTable.currentBlockHasName(name)) {
            System.out.printf("%s is already used\n", name);
            System.exit(1);
        }
        else {
            SymbolTableItem item = symbolTable.newConstantInteger(name);
            if (item.isGlobal()) {
                int value = 0;
                if (ctx.constInitVal() != null) {
                    visit(ctx.constInitVal());
                    if (info.isRegister()) {
                        System.out.print("Global variable should be static");
                        System.exit(1);
                    }
                    value = info.getValue();
                    System.out.printf("%s = global i32 %d\n", item.getRegisterString(), value);
                }
                item.setIntValue(value);
            }
            else {
                System.out.printf("\t%s = alloca i32\n", item.getRegisterString());
                visit(ctx.constInitVal());
                if (info.isRegister()) {
                    System.out.print("constInitVal should be const value\n");
                    System.exit(1);
                }
                System.out.printf("\tstore i32 %s, i32* %s\n", info, item.getRegisterString());
                item.setIntValue(info.getValue());
            }
        }
        return null;
    }

    @Override
    public Void visitStmt(labParser.StmtContext ctx) {
        if (ctx.RETURN() != null) {
            super.visitStmt(ctx);
            System.out.printf("\tret i32 %s\n", info);
        }
        else if (ctx.lval() != null) {
            VisitorInfo rhs; SymbolTableItem lhs = symbolTable.getSymbolByName(ctx.lval().getText());
            if (lhs.isConstant()) {
                System.out.println("const value can not be assigned");
                System.exit(1);
            }
            visit(ctx.exp()); rhs = new VisitorInfo(info);
            System.out.printf("\tstore i32 %s, i32* %s\n", rhs, lhs.getRegisterString());
        }
        else if (ctx.IF() != null) {
            int trueBlock = ++blockCount;
            int falseBlock = ++blockCount;
            int nxtBlock = ++blockCount;
            visit(ctx.cond());
            System.out.printf("\nblock%d:\n", trueBlock);
            visit(ctx.stmt(0));
            System.out.printf("\tbr label %%block%d\n", nxtBlock);
            System.out.printf("\nblock%d:\n", falseBlock);
            if (ctx.ELSE() != null)
                visit(ctx.stmt(1));
            System.out.printf("\tbr label %%block%d\n", nxtBlock);
            System.out.printf("\nblock%d:\n", nxtBlock);
        }
        else {
            super.visitStmt(ctx);
        }
        return null;
    }

    @Override
    public Void visitCond(labParser.CondContext ctx) {
        super.visitCond(ctx); VisitorInfo val = new VisitorInfo(info); SymbolTableItem item = symbolTable.newRegister();
        System.out.printf("\t%s = icmp ne i32 %s, 0\n", item.getRegisterString(), info);
        System.out.printf("\tbr i1 %s, label %%block%d, label %%block%d\n", item.getRegisterString(), blockCount - 2, blockCount - 1);
        return null;
    }

    @Override
    public Void visitLorExp(labParser.LorExpContext ctx) {
        if (ctx.lorExp() == null) super.visitLorExp(ctx);
        else {
            VisitorInfo lhs, rhs;
            visit(ctx.lorExp()); lhs = new VisitorInfo(info);
            visit(ctx.landExp()); rhs = new VisitorInfo(info);
            SymbolTableItem item1 = symbolTable.newRegister(), item2 = symbolTable.newRegister();
            SymbolTableItem item3 = symbolTable.newRegister(), item4 = symbolTable.newRegister();
            System.out.printf("\t%s = icmp ne i32 %s, 0\n", item1.getRegisterString(), lhs);
            System.out.printf("\t%s = icmp ne i32 %s, 0\n", item2.getRegisterString(), rhs);
            System.out.printf("\t%s = or i1 %s, %s\n", item3.getRegisterString(), item1.getRegisterString(), item2.getRegisterString());
            System.out.printf("\t%s = zext i1 %s to i32\n", item4.getRegisterString(), item3.getRegisterString());
            info.setSymbol(item4);
        }
        return null;
    }

    @Override
    public Void visitLandExp(labParser.LandExpContext ctx) {
        if (ctx.landExp() == null) super.visitLandExp(ctx);
        else {
            VisitorInfo lhs, rhs;
            visit(ctx.landExp()); lhs = new VisitorInfo(info);
            visit(ctx.eqExp()); rhs = new VisitorInfo(info);
            SymbolTableItem item1 = symbolTable.newRegister(), item2 = symbolTable.newRegister();
            SymbolTableItem item3 = symbolTable.newRegister(), item4 = symbolTable.newRegister();
            System.out.printf("\t%s = icmp ne i32 %s, 0\n", item1.getRegisterString(), lhs);
            System.out.printf("\t%s = icmp ne i32 %s, 0\n", item2.getRegisterString(), rhs);
            System.out.printf("\t%s = and i1 %s, %s\n", item3.getRegisterString(), item1.getRegisterString(), item2.getRegisterString());
            System.out.printf("\t%s = zext i1 %s to i32\n", item4.getRegisterString(), item3.getRegisterString());
            info.setSymbol(item4);
        }
        return null;
    }

    @Override
    public Void visitEqExp(labParser.EqExpContext ctx) {
        if (ctx.eqExp() == null) super.visitEqExp(ctx);
        else {
            VisitorInfo lhs, rhs;
            visit(ctx.eqExp()); lhs = new VisitorInfo(info);
            visit(ctx.relExp()); rhs = new VisitorInfo(info);
            SymbolTableItem item1 = symbolTable.newRegister(), item2 = symbolTable.newRegister();
            if (ctx.EQ() != null) System.out.printf("\t%s = icmp eq i32 %s, %s\n", item1.getRegisterString(), lhs, rhs);
            if (ctx.NEQ() != null) System.out.printf("\t%s = icmp ne i32 %s, %s\n", item1.getRegisterString(), lhs, rhs);
            System.out.printf("\t%s = zext i1 %s to i32\n", item2.getRegisterString(), item1.getRegisterString());
            info.setSymbol(item2);
        }
        return null;
    }

    @Override
    public Void visitRelExp(labParser.RelExpContext ctx) {
        if (ctx.relExp() == null) super.visitRelExp(ctx);
        else {
            VisitorInfo lhs, rhs;
            visit(ctx.relExp()); lhs = new VisitorInfo(info);
            visit(ctx.addExp()); rhs = new VisitorInfo(info);
            SymbolTableItem item1 = symbolTable.newRegister(), item2 = symbolTable.newRegister();
            if (ctx.LT() != null) System.out.printf("\t%s = icmp slt i32 %s, %s\n", item1.getRegisterString(), lhs, rhs);
            if (ctx.LTEQ() != null) System.out.printf("\t%s = icmp sle i32 %s, %s\n", item1.getRegisterString(), lhs, rhs);
            if (ctx.GT() != null) System.out.printf("\t%s = icmp sgt i32 %s, %s\n", item1.getRegisterString(), lhs, rhs);
            if (ctx.GTEQ() != null) System.out.printf("\t%s = icmp sge i32 %s, %s\n", item1.getRegisterString(), lhs, rhs);
            System.out.printf("\t%s = zext i1 %s to i32\n", item2.getRegisterString(), item1.getRegisterString());
            info.setSymbol(item2);
        }
        return null;
    }

    @Override
    public Void visitAddExp(labParser.AddExpContext ctx) {
        if (ctx.addExp() != null) {
            VisitorInfo lhs, rhs;
            visit(ctx.addExp()); lhs = new VisitorInfo(info);
            visit(ctx.mulExp()); rhs = new VisitorInfo(info);
            if (lhs.isValue() && rhs.isValue()) {
                int value = -1;
                if (ctx.ADD() != null) value = lhs.getValue() + rhs.getValue();
                if (ctx.SUB() != null) value = lhs.getValue() - rhs.getValue();
                info.setValue(value);
            } else {
                SymbolTableItem item = symbolTable.newRegister();
                if (ctx.ADD() != null)
                    System.out.printf("\t%s = add i32 %s, %s\n", item.getRegisterString(), lhs, rhs);
                if (ctx.SUB() != null)
                    System.out.printf("\t%s = sub i32 %s, %s\n", item.getRegisterString(), lhs, rhs);
                info.setSymbol(item);
            }
        } else {
            super.visitAddExp(ctx);
        }
        return null;
    }

    @Override
    public Void visitMulExp(labParser.MulExpContext ctx) {
        if (ctx.mulExp() != null) {
            VisitorInfo lhs, rhs;
            visit(ctx.mulExp()); lhs = new VisitorInfo(info);
            visit(ctx.unaryExp()); rhs = new VisitorInfo(info);
            if (lhs.isValue() && rhs.isValue()) {
                int value = -1;
                if (ctx.MUL() != null) value = lhs.getValue() * rhs.getValue();
                if (ctx.MOD() != null) value = lhs.getValue() % rhs.getValue();
                if (ctx.DIV() != null) value = lhs.getValue() / rhs.getValue();
                info.setValue(value);
            } else {
                SymbolTableItem item = symbolTable.newRegister();
                if (ctx.MUL() != null)
                    System.out.printf("\t%s = mul i32 %s, %s\n", item.getRegisterString(), lhs, rhs);
                if (ctx.MOD() != null)
                    System.out.printf("\t%s = srem i32 %s, %s\n", item.getRegisterString(), lhs, rhs);
                if (ctx.DIV() != null)
                    System.out.printf("\t%s = sdiv i32 %s, %s\n", item.getRegisterString(), lhs, rhs);
                info.setSymbol(item);
            }
        } else {
            super.visitMulExp(ctx);
        }
        return null;
    }

    @Override
    public Void visitUnaryExp(labParser.UnaryExpContext ctx) {
        if (ctx.ident() != null) {
            String funcName = ctx.ident().getText();
            SymbolTableItem func = symbolTable.getSymbolByName(funcName);
            if (func == null || !func.isFunction()) {
                System.out.printf("function %s not found\n", funcName);
                System.exit(1);
            }
            int count1 = ctx.funcRParams() == null ? 0 : ctx.funcRParams().exp().size();
            int count2 = func.getParams().size();
            if (count1 != count2) {
                System.out.printf("function %s's params not match\n", funcName);
                System.exit(1);
            }
            ArrayList<VisitorInfo> params = new ArrayList<>();
            for (int i = 0;i < count1; ++i) {
                visit(ctx.funcRParams().exp(i));
                params.add(new VisitorInfo(info));
            }
            if (func.isINT()) {
                SymbolTableItem item = symbolTable.newRegister();
                System.out.printf("\t%s = call i32 %s(", item.getRegisterString(), func.getRegisterString());
                for (int i = 0;i < count1; ++i) {
                    if (i > 0) System.out.print(", ");
                    System.out.printf("i32 %s", params.get(i));
                }
                System.out.println(")");
                info.setSymbol(item);
            }
            if (func.isVOID()) {
                System.out.printf("\tcall void %s(", func.getRegisterString());
                for (int i = 0;i < count1; ++i) {
                    if (i > 0) System.out.print(", ");
                    System.out.printf("i32 %s", params.get(i));
                }
                System.out.println(")");
            }
        }
        else {
            super.visitUnaryExp(ctx);
            if (info.isValue()) {
                if (ctx.unaryOp() != null && ctx.unaryOp().SUB() != null)
                    info.setValue(-info.getValue());
            } else {
                if (ctx.unaryOp() != null && ctx.unaryOp().SUB() != null) {
                    SymbolTableItem item = symbolTable.newRegister();
                    System.out.printf("\t%s = sub i32 0, %s\n", item.getRegisterString(), info);
                    info.setSymbol(item);
                }
                if (ctx.unaryOp() != null && ctx.unaryOp().NOT() != null) {
                    SymbolTableItem item1 = symbolTable.newRegister(), item2 = symbolTable.newRegister();
                    System.out.printf("\t%s = icmp eq i32 %s, 0\n", item1.getRegisterString(), info);
                    System.out.printf("\t%s = zext i1 %s to i32\n", item2.getRegisterString(), item1.getRegisterString());
                    info.setSymbol(item2);
                }
            }
        }
        return null;
    }

    @Override
    public Void visitLval(labParser.LvalContext ctx) {
        SymbolTableItem var = symbolTable.getSymbolByName(ctx.getText());
        if (var != null) {
            if (var.isConstant()) {
                info.setValue(var.getIntValue());
            }
            else {
                SymbolTableItem item = symbolTable.newRegister();
                System.out.printf("\t%s = load i32, i32* %s\n", item.getRegisterString(), var.getRegisterString());
                info.setSymbol(item);
            }
        } else {
            System.out.printf("%s is not declared before using", ctx.getText());
            System.exit(1);
        }
        return null;
    }

    @Override
    public Void visitNumber(labParser.NumberContext ctx) {
        int value = 0;
        if (ctx.getText().equals("0")) value = 0;
        else if (ctx.DecimalConst() != null) value = Integer.parseInt(ctx.getText());
        else if (ctx.OctalConst() != null) value = Integer.parseInt(ctx.getText().substring(1), 8);
        else if (ctx.HexadecimalConst() != null) value = Integer.parseInt(ctx.getText().substring(2), 16);
        else System.exit(1);
        info.setValue(value);
        return null;
    }
}
