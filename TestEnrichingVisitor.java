import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;

public class TestEnrichingVisitor {
    public static void main(String[] args) throws IOException {
        
        CharStream input = CharStreams.fromFileName("test.mu");
        
        MuLexer lexer = new MuLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MuParser parser = new MuParser(tokens);

        ParseTree tree = parser.parse();
        
        EnrichingVisitor visitor = new EnrichingVisitor();
        
        Node result = visitor.visit(tree);

        printNode(result, 0);
    }

    private static void printNode(Node node, int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + node.getValue());
        for (Node child : node.getChildren()) {
            printNode(child, depth + 1);
        }
    }
}
