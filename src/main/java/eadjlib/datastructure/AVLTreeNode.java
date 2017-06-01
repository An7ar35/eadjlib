package eadjlib.datastructure;

public class AVLTreeNode<T extends Comparable> implements Comparable<T> {
    private Integer balance_factor;
    AVLTreeNode<T> parent;
    AVLTreeNode<T> left;
    AVLTreeNode<T> right;
    public T key;

    /**
     * Constructor
     *
     * @param key Key
     */
    public AVLTreeNode(T key) {
        this.key = key;
    }

    /**
     * Gets the height of the node in the tree
     *
     * @return Height of node
     */
    public Integer height() {
        Integer l_height = 0;
        Integer r_height = 0;
        if (this.left != null)
            l_height = this.left.height();
        if (this.right != null)
            r_height = this.right.height();
        return Math.max(l_height, r_height) + 1;
    }

    /**
     * Refreshes and gets the balance status of the balance factor
     *
     * @return Balance status
     */
    public boolean isBalanced() {
        getBalanceFactor();
        return this.balance_factor > -2 && this.balance_factor < 2;
    }

    /**
     * Gets the balance factor
     *
     * @return Balance factor
     */
    public Integer getBalanceFactor() {
        int l_height = 0;
        int r_height = 0;
        if (this.left != null)
            l_height = this.left.height();
        if (this.right != null)
            r_height = this.right.height();
        this.balance_factor = l_height - r_height;
        return this.balance_factor;
    }

    /**
     * Compare against another AVLTreeNode
     *
     * @param node Node to compare with
     * @return Comparator result value
     * @throws NullPointerException when key is not set
     */
    public int compareTo(AVLTreeNode<T> node) throws NullPointerException {
        if (this.key == null) {
            throw new NullPointerException("Key of the node is null.");
        } else if (node.key == null) {
            throw new NullPointerException("Key of the node used for comparison is null.");
        }
        return this.key.compareTo(node.key);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException when key is not set
     */
    @Override
    public int compareTo(T key) throws NullPointerException {
        if (this.key == null) {
            throw new NullPointerException("Key is null.");
        } else if (key == null) {
            throw new NullPointerException("Key used for comparison is null.");
        } else {
            return this.key.compareTo(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.key.toString();
    }
}
