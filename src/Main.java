import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(args[0])));
        StringBuilder source = new StringBuilder();
        while (scanner.hasNextLine())
            source.append(scanner.nextLine()).append("\n");

        System.out.println(source.toString());

        CharStream inputStream = CharStreams.fromString(source.toString());
        labLexer lexer = new labLexer(inputStream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new Error());

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        labParser parser = new labParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(new Error());

        ParseTree tree = parser.compUnit();
        Visitor visitor = new Visitor(args[1]);
        visitor.visit(tree);
    }
}
