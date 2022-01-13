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
    public Void visitNumber(labParser.NumberContext ctx) {
        if (ctx.getText().equals("0")) value = 0;
        else if (ctx.DecimalConst() != null) value = Integer.parseInt(ctx.getText());
        else if (ctx.OctalConst() != null) value = Integer.parseInt(ctx.getText().substring(1), 8);
        else if (ctx.HexadecimalConst() != null) value = Integer.parseInt(ctx.getText().substring(2), 16);
        else System.exit(1);
        return null;
    }
}
