import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class TestEnrichingListener {

    public static void main(String[] args) throws Exception {
        // Path to the file 'test.mu'
        String filePath = "test.mu"; // Update with the correct path

        // Read the contents of the file into a string
        String input = new String(Files.readAllBytes(Paths.get(filePath)));

        // Create a lexer that feeds off of input CharStream
        MuLexer lexer = new MuLexer(CharStreams.fromString(input));

        // Create a buffer of tokens between lexer and parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create the parser
        MuParser parser = new MuParser(tokens);

        // Parse the input code (start rule: parse)
        ParseTree tree = parser.parse();

        // Create the listener
        EnrichingListener listener = new EnrichingListener();

        // Walk the parse tree using the listener
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        // Print the enriched tree (your listener should have built this in the 'root'
        // TreeNode)
        System.out.println("Generated Tree:");
        printTree(listener.getRoot(), 0);
    }

    // Helper method to print the tree structure
    private static void printTree(TreeNode node, int level) {
        // Print current node with indentation
        for (int i = 0; i < level; i++) {
            System.out.print("  "); // Indent for readability
        }
        System.out.println(node.getValue());

        // Recursively print children
        for (TreeNode child : node.getChildren()) {
            printTree(child, level + 1);
        }
    }
}
