import java.util.ArrayList;
import java.util.List;

public class Node {
    private String type; // Tip čvora, npr. "NUMBER", "ID", "ASSIGNMENT"
    private String value; // Vrednost čvora, npr. "5", "x", "+"
    private List<Node> children; // Lista dece čvorova
    private Node parent; // Roditeljski čvor

    // Konstruktor za čvor sa tipom
    public Node(String type) {
        this.type = type;
        this.value = null;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    // Konstruktor za čvor sa tipom i vrednošću
    public Node(String type, String value) {
        this.type = type;
        this.value = value;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    // Dodavanje deteta
    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    // Postavljanje roditeljskog čvora
    public void setParent(Node parent) {
        this.parent = parent;
    }

    // Dobijanje tipa čvora
    public String getType() {
        return type;
    }

    // Dobijanje vrednosti čvora
    public String getValue() {
        return value;
    }

    // Dobijanje dece
    public List<Node> getChildren() {
        return children;
    }

    // Dobijanje roditeljskog čvora
    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", children=" + children +
                '}';
    }
}
