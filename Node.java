import java.util.*;

public class Node {
    private String value;
    private List<Node> children;

    public Node(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public String getValue() {
        return value;
    }

    public List<Node> getChildren() {
        return children;
    }
}
