package projektkurs.util;

public class TreeNode<T> {

    private final T content;
    private TreeNode<T> trueChild, falseChild;

    public TreeNode(T content) {
        this(content, null, null);
    }

    public TreeNode(T content, TreeNode<T> trueChild, TreeNode<T> falseChild) {
        this.content = content;
        this.trueChild = trueChild;
        this.falseChild = falseChild;
    }

    public T get() {
        return content;
    }

    public TreeNode<T> getFalseChild() {
        return falseChild;
    }

    public TreeNode<T> getTrueChild() {
        return trueChild;
    }

    public void setFalseChild(TreeNode<T> falseChild) {
        this.falseChild = falseChild;
    }

    public void setTrueChild(TreeNode<T> trueChild) {
        this.trueChild = trueChild;
    }

}
