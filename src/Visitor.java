import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Visitor extends labBaseVisitor<Void> {
    VisitorInfo info;
    SymbolTable symbolTable;

    public Visitor(String filename) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream(filename)));
        info = new VisitorInfo();
        symbolTable = new SymbolTable();
    }

    @Override
    public Void visitFuncDef(labParser.FuncDefContext ctx) {
        System.out.print("define dso_local i32 @main() ");
        return super.visitFuncDef(ctx);
    }

    @Override
    public Void visitBlock(labParser.BlockContext ctx) {
        System.out.println("{");
        symbolTable.createInnerTable();
        super.visitBlock(ctx);
        symbolTable.exitCurrentTable();
        System.out.println("}");
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
            System.out.printf("\t%s = alloca i32\n", item.getRegisterString());
            if (ctx.initVal() != null) {
                visit(ctx.initVal());
                System.out.printf("\tstore i32 %s, i32* %s\n", info, item.getRegisterString());
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
            SymbolTableItem item = symbolTable.newVariableInteger(name);
            System.out.printf("\t%s = alloca i32\n", item.getRegisterString());
            visit(ctx.constInitVal());
            if (info.isRegister()) {
                System.out.print("constInitVal should be const value\n");
                System.exit(1);
            }
            System.out.printf("\tstore i32 %s, i32* %s\n", info, item.getRegisterString());
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
            VisitorInfo rhs;
            visit(ctx.exp()); rhs = new VisitorInfo(info);
            System.out.printf("\tstore i32 %s, i32* %s\n", rhs, symbolTable.getSymbolByName(ctx.lval().getText()).getRegisterString());
        } else {
            super.visitStmt(ctx);
        }
        return null;
    }

    @Override
    public Void visitExp(labParser.ExpContext ctx) {
        return super.visitExp(ctx);
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
        }


        return null;
    }

    @Override
    public Void visitLval(labParser.LvalContext ctx) {
        SymbolTableItem var = symbolTable.getSymbolByName(ctx.getText());
        if (var != null) {
            SymbolTableItem item = symbolTable.newRegister();
            System.out.printf("\t%s = load i32, i32* %s\n", item.getRegisterString(), var.getRegisterString());
            info.setSymbol(item);
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
