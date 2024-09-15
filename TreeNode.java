import java.util.ArrayList;
import java.util.List;

class TreeNode {
    String value;  // Vrednost čvora, npr. "=" za operator ili ime promenljive
    List<TreeNode> children;  // Lista dece ovog čvora

    public TreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public String getValue() {
        return this.value;
    }

    public List<TreeNode> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        return value;
    }
}
