package eadjlib.datastructure;

import eadjlib.logger.Logger;

public class AVLTreeNode<K extends Comparable, V> implements Comparable<K> {
    private final Logger log = Logger.getLoggerInstance(AVLTreeNode.class.getName());
    private Integer balance_factor;
    K key;
    V value;
    AVLTreeNode<K, V> parent;
    AVLTreeNode<K, V> left;
    AVLTreeNode<K, V> right;

    public AVLTreeNode() {
        this.parent = null;
        this.key = null;
        this.value = null;
    }

    /**
     * Constructor
     *
     * @param parent Parent of the node
     * @param key    Key
     * @param value  Value
     */
    public AVLTreeNode(AVLTreeNode<K, V> parent, K key, V value) {
        this.parent = parent;
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the height of the node in the tree
     *
     * @return Height of node
     */
    public int height() {
        int l_height = 0;
        int r_height = 0;
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
    public int getBalanceFactor() {
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
    public int compareTo(AVLTreeNode<K, V> node) throws NullPointerException {
        if (this.key == null) {
            throw new NullPointerException("Key of the node is null.");
        } else if (node.key == null) {
            throw new NullPointerException("Key of the node used for comparison is null.");
        }
        return this.key.compareTo(node.key);
    }

    /**
     * Gets the node's key
     *
     * @return Key
     */
    public K key() {
        return this.key;
    }

    /**
     * Gets the node's value
     *
     * @return Value
     */
    public V value() {
        return this.value;
    }

    /**
     * Sets the node's value
     *
     * @param value Value to set the node to
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException when key is not set
     */
    @Override
    public int compareTo(K key) throws NullPointerException {
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
        return "[" + this.key.toString() + "]=" + this.value.toString();
    }
}
