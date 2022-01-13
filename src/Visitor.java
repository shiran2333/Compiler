import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Visitor extends labBaseVisitor<Void> {
    int value = 0;

    public Visitor(String filename) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream(filename)));
    }

    @Override
    public Void visitFuncDef(labParser.FuncDefContext ctx) {
        System.out.print("define dso_local i32 @main() ");
        return super.visitFuncDef(ctx);
    }

    @Override
    public Void visitBlock(labParser.BlockContext ctx) {
        System.out.println("{");
        visit(ctx.stmt());
        System.out.println("}");
        return null;
    }

    @Override
    public Void visitStmt(labParser.StmtContext ctx) {
        System.out.print("    ret i32 ");
        visit(ctx.exp());
        System.out.println(value);
        return null;
    }

    @Override
    public Void visitExp(labParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Void visitAddExp(labParser.AddExpContext ctx) {
        if (ctx.addExp() != null) {
            int lhs, rhs;
            visit(ctx.addExp()); lhs = value;
            visit(ctx.mulExp()); rhs = value;
            if (ctx.ADD() != null) value = lhs + rhs;
            if (ctx.SUB() != null) value = lhs - rhs;
        } else {
            super.visitAddExp(ctx);
        }
        return null;
    }

    @Override
    public Void visitMulExp(labParser.MulExpContext ctx) {
        if (ctx.mulExp() != null) {
            int lhs, rhs;
            visit(ctx.mulExp()); lhs = value;
            visit(ctx.unaryExp()); rhs = value;
            if (ctx.MUL() != null) value = lhs * rhs;
            if (ctx.MOD() != null) value = lhs % rhs;
            if (ctx.DIV() != null) value = lhs / rhs;
        } else {
            super.visitMulExp(ctx);
        }
        return null;
    }

    @Override
    public Void visitUnaryExp(labParser.UnaryExpContext ctx) {
        super.visitUnaryExp(ctx);
        if (ctx.unaryOp() != null && ctx.unaryOp().SUB() != null) value = -value;
        return null;
    }

    @Override
    public Void visitNumber(labParser.NumberContext ctx) {
        if (ctx.getText().equals("0")) value = 0;
        else if (ctx.DecimalConst() != null) value = Integer.parseInt(ctx.getText());
        else if (ctx.OctalConst() != null) value = Integer.parseInt(ctx.getText().substring(1), 8);
        else if (ctx.HexadecimalConst() != null) value = Integer.parseInt(ctx.getText().substring(2), 16);
        else System.exit(1);
        return null;
    }
}
