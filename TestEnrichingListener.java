// import org.antlr.v4.runtime.*;
// import org.antlr.v4.runtime.tree.*;

// public class TestEnrichingListener {

//     public static void main(String[] args) throws Exception {
//         // Load the input file
//         CharStream input = CharStreams.fromFileName("test.mu");

//         // Create lexer and parser
//         MuLexer lexer = new MuLexer(input);
//         CommonTokenStream tokens = new CommonTokenStream(lexer);
//         MuParser parser = new MuParser(tokens);

//         // Parse the input file
//         ParseTree tree = parser.parse(); // Adjust according to your entry point

//         // Create and attach your listener
//         EnrichingListener listener = new EnrichingListener();
//         ParseTreeWalker walker = new ParseTreeWalker();
//         walker.walk(listener, tree);

//         // Retrieve and print the root of the CST
//         Node root = listener.getRoot();
//         printTree(root, "");
//     }

//     private static void printTree(Node node, String indent) {
//         System.out.println(indent + node.getValue());
//         for (Node child : node.getChildren()) {
//             printTree(child, indent + "  ");
//         }
//     }
// }
