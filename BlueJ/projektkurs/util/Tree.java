package projektkurs.util;

public class Tree<T> {

    private TreeNode<T> root, current;

    public Tree() {
        // NO-OP
    }

    public Tree(T content) {
        setRoot(new TreeNode<T>(content));
        toRoot();
    }

    public void add(boolean b, T content) throws UnsupportedOperationException {
        if (isEmpty()) {
            setRoot(new TreeNode<T>(content));
            toRoot();
        } else {
            if (b) {
                if (current.getTrueChild() != null) {
                    throw new UnsupportedOperationException();
                }
                current.setTrueChild(new TreeNode<T>(content));
            } else {
                if (current.getFalseChild() != null) {
                    throw new UnsupportedOperationException();
                }
                current.setFalseChild(new TreeNode<T>(content));
            }
        }
    }

    public void forceAdd(boolean b, T content) {
        if (b) {
            current.setTrueChild(new TreeNode<T>(content));
        } else {
            current.setFalseChild(new TreeNode<T>(content));
        }
    }

    public TreeNode<T> getCurrent() {
        return current;
    }

    public T getCurrentContent() {
        return current.get();
    }

    public TreeNode<T> getCurrentFalseChild() {
        return current.getFalseChild();
    }

    public TreeNode<T> getCurrentTrueChild() {
        return current.getTrueChild();
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public boolean hasCurrentFalseChild() {
        return current.getFalseChild() != null;
    }

    public boolean hasCurrentTrueChild() {
        return current.getTrueChild() != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean isLeaf() {
        return current.getTrueChild() == null && current.getFalseChild() == null;
    }

    public void next(boolean b) {
        if (isLeaf()) {
            throw new NullPointerException("Current Node is a leaf");
        }
        if (b) {
            if (!hasCurrentTrueChild()) {
                throw new NullPointerException("True Child doesn't exist");
            }
            current = current.getTrueChild();
        } else {
            if (!hasCurrentFalseChild()) {
                throw new NullPointerException("False Child doesn't exist");
            }
            current = current.getFalseChild();
        }
    }

    public void remove(boolean b) {
        if (b) {
            current.setTrueChild(null);
        } else {
            current.setFalseChild(null);
        }
    }

    public void setCurrent(TreeNode<T> current) {
        this.current = current;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public void toRoot() {
        current = root;
    }
}
