import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // Učitavanje sadržaja iz fajla
            MuLexer lexer = new MuLexer(CharStreams.fromFileName("test.mu"));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MuParser parser = new MuParser(tokens);

            // Parsiranje ulaza
            MuParser.ParseContext tree = parser.parse();

            // Kreiranje i dodavanje vašeg listener-a
            CSTListener listener = new CSTListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, tree);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
