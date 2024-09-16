import java.util.ArrayList;
import java.util.List;

public class UniversalNode {
    private String type;
    private String value;
    private List<UniversalNode> children = new ArrayList<>();

    public UniversalNode(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public List<UniversalNode> getChildren() {
        return children;
    }

    public void addChild(UniversalNode child) {
        children.add(child);
    }

    // Method to insert universal nodes and print them
    public void insertUniversalNodes() {
        if (isUniversalNode(this)) {
            printUniversalNode(this, 0);
        }

        for (UniversalNode child : children) {
            child.insertUniversalNodes();
        }
    }

    // Helper method to print nodes with indentation
    private void printUniversalNode(UniversalNode node, int level) {
        String indent = " ".repeat(level * 2);
        System.out.println(indent + "Universal Node: Type='" + node.getType() + "', Value='" + node.getValue() + "'");

        for (UniversalNode child : node.getChildren()) {
            printUniversalNode(child, level + 1);
        }
    }

    // Define the universal nodes based on their type
    private boolean isUniversalNode(UniversalNode node) {
        return node.getType().equals("LOOP_STATEMENT") ||
               node.getType().equals("CONDITION") ||
               node.getType().equals("KEYWORD") ||
               node.getType().equals("OPERATOR") ||
               node.getType().equals("NAME") ||
               node.getType().equals("CONST");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UniversalNode{type='").append(type).append("', value='").append(value).append("', children=[\n");
        for (UniversalNode child : children) {
            sb.append("  ").append(child.toString()).append(",\n");
        }
        sb.append("]}");
        return sb.toString();
    }
}
