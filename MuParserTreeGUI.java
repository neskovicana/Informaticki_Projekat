import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;

import javax.swing.*;
import java.util.Arrays;

public class MuParserTreeGUI {
    public static void main(String[] args) throws Exception {
        // Učitaj ulazni fajl ili tekst
        String input = "while (x > 0) { x = x - 1; }";  // Ovde unesi svoj kod
        
        // Kreiraj Lexer i Parser
        CharStream charStream = CharStreams.fromString(input);
        MuLexer lexer = new MuLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MuParser parser = new MuParser(tokens);
        
        // Generiši ParseTree (drvo parsiranja) koristeći početno pravilo "parse"
        ParseTree tree = parser.parse();  // Pokreni parsing od glavne grammar rule
        
        // Prikaz u GUI koristeći ANTLR TreeViewer
        JFrame frame = new JFrame("Parse Tree");
        JPanel panel = new JPanel();
        
        // Koristi ANTLR TreeViewer da kreiraš grafičko stablo
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.5);  // Skaliraj veličinu stabla ako je potrebno
        
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
