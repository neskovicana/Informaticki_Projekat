import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class TestEnrichingListener {

    public static void main(String[] args) throws Exception {
        String filePath = "test.mu";

        String input = new String(Files.readAllBytes(Paths.get(filePath)));

        MuLexer lexer = new MuLexer(CharStreams.fromString(input));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MuParser parser = new MuParser(tokens);

        ParseTree tree = parser.parse();

        EnrichingListener listener = new EnrichingListener();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        System.out.println("Generated Tree:");
        printTree(listener.getRoot(), 0);
    }

    private static void printTree(TreeNode node, int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + node.getValue());
        for (TreeNode child : node.getChildren()) {
            printTree(child, depth + 1);
        }
    }
}
