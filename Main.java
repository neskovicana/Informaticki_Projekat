import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) throws Exception {
        // Učitajte test.mu fajl
        CharStream input = CharStreams.fromFileName("test.mu");

        // Kreirajte lexer
        MuLexer lexer = new MuLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Kreirajte parser
        MuParser parser = new MuParser(tokens);

        // Generišite parse tree
        ParseTree tree = parser.parse();

        // Inicijalizuj visitor
        CSTVisitor visitor = new CSTVisitor();

        // Kreirajte visitor i posetite stablo
        visitor.visit(tree);  // Obradite stablo

        // Ispis modifikovanog stabla
        System.out.println("CST visitor:");
        System.out.println(tree.toStringTree(parser));  // Prikazivanje originalnog stabla

        System.out.println("Novo stablo:");
        System.out.println(visitor.getEnrichedTree().toStringTree(parser));  // Prikazivanje novog stabla
    }
}
