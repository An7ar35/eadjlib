package eadjlib.datastructure;

import eadjlib.exception.UndefinedException;
import eadjlib.logger.Logger;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Consumer;

import static eadjlib.datastructure.AVLTree.Branch.*;

public class AVLTree<K extends Comparable, V> extends AbstractCollection {
    private final Logger log = Logger.getLoggerInstance(AVLTree.class.getName());
    private Integer node_count = 0;
    private AVLTreeNode<K, V> root;

    //==================================================================================================================
    // Sub-classes
    //==================================================================================================================

    /**
     * Describes the branch type
     */
    protected enum Branch {
        ROOT, LEFT, RIGHT
    }

    /**
     * Iterator
     */
    public class AVLTreeIterator implements Iterator<AVLTreeNode<K, V>> {
        private AVLTreeNode<K, V> next = null;
        private Stack<AVLTreeNode<K, V>> stack = new Stack<>();

        /**
         * Adds all left nodes available from a starting node
         *
         * @param node Starting node
         */
        private void addAll(AVLTreeNode<K, V> node) {
            while (node != null) {
                this.stack.push(node);
                node = node.left;
            }
        }

        /**
         * Constructor
         *
         * @param root Starting node
         */
        public AVLTreeIterator(AVLTreeNode<K, V> root) {
            this.next = root;
            this.addAll(root);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        /**
         * {@inheritDoc}
         *
         * @return next AVLTreeNode
         */
        @Override
        public AVLTreeNode<K, V> next() {
            AVLTreeNode<K, V> current = stack.pop();
            this.addAll(current.right);
            return current;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Removal is not supported on the AVL tree iterator.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void forEachRemaining(Consumer action) {
            while (hasNext()) {
                action.accept(next());
            }
        }
    }

    //==================================================================================================================
    // Private methods
    //==================================================================================================================

    /**
     * Searches for the existence of a key in the specified node and below
     *
     * @param key  Key to search for
     * @param node Starting node
     * @return Found status
     */
    private boolean search(K key, AVLTreeNode<K, V> node) {
        int comparison = key.compareTo(node.key());
        if (comparison == 0) {
            return true;
        } else if (comparison < 0 && node.left != null) {
            return search(key, node.left);
        } else if (comparison > 0 && node.right != null) {
            return search(key, node.right);
        }
        return false;
    }

    /**
     * Searches for the existence of a value in the specified node and below
     *
     * @param value Value to search for
     * @param node  Starting node
     * @return Key of value or null if not found
     */
    private K search(V value, AVLTreeNode<K, V> node) {
        AVLTreeIterator iterator = new AVLTreeIterator(node);
        while (iterator.hasNext()) {
            AVLTreeNode<K, V> current = iterator.next();
            if (current.value().equals(value)) {
                return current.key;
            }
        }
        return null;
    }

    /**
     * Helper function to remove a node matching given key
     *
     * @param key    Key to look for and remove
     * @param parent Parent of current node
     * @param branch Branch taken
     * @param node   Current node
     * @return Success
     * @throws UndefinedException when corruption is detected during the balancing of the parent
     */
    private boolean remove(K key, AVLTreeNode<K, V> parent, Branch branch, AVLTreeNode<K, V> node) throws UndefinedException {
        try {
            log.log_Trace("Trying to remove K=", key, "; node is '", node.key, "'");
            int comparison = node.key().compareTo(key);
            if (comparison == 0) {
                if (node.right != null) {
                    removeSmallest(node, node.right);
                } else if (node.left != null) {
                    removeLargest(node, node.left);
                } else { //It's a leaf node
                    switch (branch) {
                        case LEFT:
                            parent.left = null;
                            break;
                        case RIGHT:
                            parent.right = null;
                            break;
                        case ROOT:
                            this.root = null;
                            break;
                    }
                }
                this.node_count--;
                balance(node);
                log.log_Debug("Removed '", key, "'.");
                return true;
            } else {
                if (comparison > 0 && node.left != null) {
                    return remove(key, node, LEFT, node.left);
                }
                if (comparison < 0 && node.right != null) {
                    return remove(key, node, RIGHT, node.right);
                }
                return false;
            }
        } catch (UndefinedException e) {
            log.log_Fatal("Corruption detected during removal of a node with key '", key, "'.");
            throw new UndefinedException("Corruption detected during removal of a node with key [" + key + "].", e);
        }
    }

    /**
     * Removes the smallest valued node in the tree
     *
     * @param parent Parent of node
     * @param node   Next node
     * @return Key value removed
     * @throws UndefinedException when corruption is detected during the balancing of the parent
     */
    private K removeSmallest(AVLTreeNode<K, V> parent, AVLTreeNode<K, V> node) throws UndefinedException {
        if (node.left != null) {
            return removeSmallest(node, node.left);
        } else {
            K key = node.key;
            if (parent.left == node) {
                parent.left = null;
            } else if (parent == root) {
                key = root.key;
                node.parent = null;
                root.right = null;
                root = node;
            } else { //parent.right == node
                key = parent.key;
                AVLTreeNode<K, V> grand_parent = parent.parent;
                grand_parent.left = node;
                node.parent = grand_parent;
            }
            balance(parent);
            return key;
        }
    }

    /**
     * Removes the largest valued node in the tree
     *
     * @param parent Parent of node
     * @param node   Next node
     * @return Key value removed
     * @throws UndefinedException when corruption is detected during the balancing of the parent
     */
    private K removeLargest(AVLTreeNode<K, V> parent, AVLTreeNode<K, V> node) throws UndefinedException {
        if (node.right != null) {
            return removeLargest(node, node.right);
        } else {
            K key = node.key;
            if (parent.right == node) {
                log.log_Debug( "A");
                parent.right = null;
            } else if (parent == root) {
                log.log_Debug( "B");
                key = root.key;
                node.parent = null;
                root.left = null;
                root = node;
            } else { //parent.left == node
                log.log_Debug( "C");
                key = parent.key;
                AVLTreeNode<K, V> grand_parent = parent.parent;
                grand_parent.right = node;
                node.parent = grand_parent;
            }
            balance(parent);
            log.log_Debug( "@removeLargest(..): removed '", key, "'");
            return key;
        }
    }

    /**
     * Balances the nodes in the tree in reverse (leaf to root)
     *
     * @param node Root of the balance
     */
    private void balance(AVLTreeNode<K, V> node) throws UndefinedException {
        try {
            if (node != null) {
                int factor = node.getBalanceFactor();
                if (factor > 1) { //right rotation
                    rotateRR(node.parent, getBranch(node));
                } else if (factor < -1) { //left rotation
                    rotateLL(node.parent, getBranch(node));
                }
                balance(node.parent);
            }
        } catch (UndefinedException e) {
            log.log_Fatal("Corruption detected in the AVL tree whilst balancing node [", node.key, "].");
            throw new UndefinedException("Corruption detected in the AVL tree whilst balancing node [" + node.key + "].", e);
        }
    }


    /**
     * Right rotation
     * Note: where a<-b<-c becomes a<-b->c
     *
     * @param parent parent of the rotation
     * @param branch Branch on which to do the rotation
     * @throws UndefinedException when parent is null
     */
    private void rotateRR(AVLTreeNode<K, V> parent, Branch branch) throws UndefinedException {
        AVLTreeNode<K, V> child = getChild(parent, branch);
        if (child.right != null && child.right.getBalanceFactor() < 0) {
            rotateRL(parent, branch);
        } else {
            AVLTreeNode<K, V> b = detach(getChild(parent, branch), LEFT); // a<-b
            AVLTreeNode<K, V> c = detach(parent, branch); // c
            c.left = b.right;
            attach(parent, branch, b);
            attach(getChild(parent, branch), RIGHT, c);
        }
    }

    /**
     * Left Rotation
     * Note: where a->b->c becomes a<-b->c
     *
     * @param parent Pointer to the m_parent of the rotation
     * @param branch Branch on which to do the rotation
     * @throws UndefinedException when parent is null
     */
    private void rotateLL(AVLTreeNode<K, V> parent, Branch branch) throws UndefinedException {
        AVLTreeNode<K, V> child = getChild(parent, branch);
        if (child != null) {
            if (child.left != null && child.left.getBalanceFactor() > 0) {
                rotateLR(parent, branch);
            } else {
                AVLTreeNode<K, V> b = detach(getChild(parent, branch), RIGHT); // b->c
                AVLTreeNode<K, V> a = detach(parent, branch); // a
                a.right = b.left;
                attach(parent, branch, b);
                attach(getChild(parent, branch), LEFT, a);
            }
        }
    }

    /**
     * Left-Right rotation
     *
     * @param parent Pointer to the m_parent of the rotation
     * @param branch Branch on which to do the rotation
     * @throws UndefinedException when parent is null
     */
    private void rotateLR(AVLTreeNode<K, V> parent, Branch branch) throws UndefinedException {
        rotateLL(getChild(parent, branch), LEFT);
        rotateRR(parent, branch);
    }

    /**
     * Right-Left rotation
     *
     * @param parent Pointer to the m_parent of the rotation
     * @param branch Branch on which to do the rotation
     * @throws UndefinedException when parent is null
     */
    private void rotateRL(AVLTreeNode<K, V> parent, Branch branch) throws UndefinedException {
        rotateRR(getChild(parent, branch), RIGHT);
        rotateLL(parent, branch);
    }

    /**
     * Detaches a branch from specified node
     *
     * @param parent Parent from which to detach branch
     * @param branch Branch to detach
     * @return Detached branch
     * @throws UndefinedException when the parent is null and branch is not ROOT
     */
    private AVLTreeNode<K, V> detach(AVLTreeNode<K, V> parent, Branch branch) throws UndefinedException {
        if (parent == null && branch != ROOT) {
            throw new UndefinedException("Parent is undefined (null).");
        }
        switch (branch) {
            case LEFT:
                if (parent.left == null) return new AVLTreeNode<>();
                return parent.left;
            case RIGHT:
                if (parent.right == null) return new AVLTreeNode<>();
                return parent.right;
            case ROOT:
                return this.root;
        }
        return null;
    }

    /**
     * Attaches an orphan branch to a adoptive m_parent node
     *
     * @param parent        Adoptive parent node
     * @param branch        Branch to attach to
     * @param orphan_branch Orphan branch to adopt
     * @throws UndefinedException when the parent is null and branch is not ROOT
     */
    private void attach(AVLTreeNode<K, V> parent, Branch branch, AVLTreeNode<K, V> orphan_branch) throws UndefinedException {
        if (parent == null && branch != ROOT) {
            throw new UndefinedException("Parent is undefined (null).");
        }
        switch (branch) {
            case LEFT:
                parent.left = orphan_branch;
                parent.left.parent = parent;
                break;
            case RIGHT:
                parent.right = orphan_branch;
                parent.right.parent = parent;
                break;
            case ROOT:
                this.root = orphan_branch;
                this.root.parent = null;
                break;
        }
    }

    /**
     * Gets the child of a parent in the branch specified
     *
     * @param parent Parent node
     * @param branch Branch to pluck the child from
     * @return Child node
     * @throws UndefinedException when the parent is null and branch is not ROOT
     */
    private AVLTreeNode<K, V> getChild(AVLTreeNode<K, V> parent, Branch branch) throws UndefinedException {
        if (parent == null && branch != ROOT) {
            throw new UndefinedException("Parent is undefined (i.e.: null).");
        }
        switch (branch) {
            case LEFT:
                if (parent.left != null) return parent.left;
                break;
            case RIGHT:
                if (parent.right != null) return parent.right;
                break;
            case ROOT:
                if (this.root != null) return this.root;
                break;
        }
        log.log_Error("Child doesn't exist.");
        return null;
    }

    /**
     * Gets the branch from which the node spawns from
     *
     * @param node Node to search the m_parent's branch of
     * @return Spawning branch
     * @throws UndefinedException when the node points to a parent that isn't his.
     */
    private Branch getBranch(AVLTreeNode<K, V> node) throws UndefinedException {
        if (node.parent == null) return ROOT;
        if (node.parent.left == node) return LEFT;
        if (node.parent.right == node) return RIGHT;
        throw new UndefinedException("Node [" + node.key + "] points to the wrong parent. Must have attachment issues or be confused.");
    }

    /**
     * Finds the balance factor of a node
     *
     * @param node AVLTreeNode to check
     * @return Balance factor
     */
    private Integer getHeightDifference(AVLTreeNode<K, V> node) {
        return node.left.height() - node.right.height();
    }

    /**
     * Pre-Order Binary Tree transversal
     *
     * @param printer Printer function
     * @param node    Node to process
     */
    private void preOrder(IPrintFunction printer, AVLTreeNode<K, V> node) {
        if (node != null) {
            printer.call(node.key(), node.value());
            preOrder(printer, node.left);
            preOrder(printer, node.right);
        }
    }

    /**
     * In-Order BinaryTree transversal
     *
     * @param printer Printer function
     * @param node    Node to process
     */
    private void inOrder(IPrintFunction printer, AVLTreeNode<K, V> node) {
        if (node != null) {
            inOrder(printer, node.left);
            printer.call(node.key(), node.value());
            inOrder(printer, node.right);
        }
    }

    /**
     * Post-Order BinaryTree transversal
     *
     * @param printer Printer function
     * @param node    Node to process
     */
    private void postOrder(IPrintFunction printer, AVLTreeNode<K, V> node) {
        if (node != null) {
            postOrder(printer, node.left);
            postOrder(printer, node.right);
            printer.call(node.key(), node.value());
        }
    }

    /**
     * Level-Order BinaryTree transversal
     *
     * @param printer Printer function
     * @param node    Node to process
     */
    private void levelOrder(IPrintFunction printer, AVLTreeNode<K, V> node) {
        if (node != null) {
            Queue<AVLTreeNode<K, V>> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                AVLTreeNode<K, V> current = queue.remove();
                printer.call(current.key(), current.value());
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
    }

    //==================================================================================================================
    // Public methods
    //==================================================================================================================

    /**
     * Constructor
     */
    public AVLTree() {
    }

    /**
     * Constructor
     *
     * @param list List of keys to initialise the tree with
     * @throws RuntimeException when corruption is detected during tree construction
     */
    public AVLTree(Collection<Pair<K, V>> list) throws RuntimeException {
        try {
            for (Pair<K, V> item : list) {
                this.add(item.getKey(), item.getValue());
                this.node_count++;
            }
        } catch (UndefinedException e) {
            log.log_Fatal("Corruption detected during additions of keys from AVLTree constructor list.");
            throw new RuntimeException("Corruption detected during additions of keys from AVLTree constructor list.", e);
        }
    }

    /**
     * Adds a key to the tree
     *
     * @param key   Key to add
     * @param value Value to add
     * @throws UndefinedException when corruption is detected during re-balancing
     */
    public void add(K key, V value) throws UndefinedException {
        try {
            log.log_Debug("Adding <", key, ", ", value, "> to tree.");
            if (this.node_count < 1) {
                this.root = new AVLTreeNode<>(null, key, value);
                this.node_count++;
            } else {
                AVLTreeNode<K, V> ptr = this.root;
                while (true) {
                    if (key.compareTo(ptr.key) < 0) {
                        if (ptr.left != null) {
                            ptr = ptr.left;
                        } else {
                            ptr.left = new AVLTreeNode<>(ptr, key, value);
                            this.node_count++;
                            balance(ptr);
                            break;
                        }
                    } else {
                        if (ptr.right != null) {
                            ptr = ptr.right;
                        } else {
                            ptr.right = new AVLTreeNode<>(ptr, key, value);
                            this.node_count++;
                            balance(ptr);
                            break;
                        }
                    }
                }
            }
        } catch (UndefinedException e) {
            throw new UndefinedException("Balancing failed whilst adding key [" + key + "] to tree.", e);
        }
    }

    /**
     * Removes key in tree matching given key
     *
     * @param key Key to remove
     * @return Success
     * @throws UndefinedException when corruption is detected in the tree.
     */
    public boolean remove(K key) throws UndefinedException {
        if (this.size() < 1) {
            return false;
        } else {
            return remove(key, null, ROOT, this.root);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator iterator() {
        return new AVLTreeIterator(this.root);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer action) {
        AVLTreeIterator iterator = new AVLTreeIterator(this.root);
        while (iterator.hasNext()) {
            action.accept(iterator.next().value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.node_count;
    }

    /**
     * Gets the height of the tree
     *
     * @return Tree height
     */
    public int height() {
        if( this.root == null )
            return 0;
        else
            return this.root.height();
    }

    /**
     * Clears everything from the tree
     */
    public void clear() {
        this.root = null;
        this.node_count = 0;
    }

    /**
     * Checks if tree is empty
     *
     * @return Empty state of tree
     */
    public boolean isEmpty() {
        return this.node_count == 0;
    }

    /**
     * Checks if the tree is full
     *
     * @return Full state of tree
     */
    public boolean isFull() {
        if (this.node_count == 0) return true;
        boolean flag = false;
        Queue<AVLTreeNode<K, V>> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            AVLTreeNode<K, V> current = queue.remove();
            if (current.left != null) {
                if (flag) return false;
                queue.add(current.left);
            } else {
                flag = true;
            }
            if (current.right != null) {
                if (flag) return false;
                queue.add(current.right);
            } else {
                flag = true;
            }
        }
        return true;
    }

    /**
     * Checks if the tree is complete
     *
     * @return Complete state of tree
     */
    public boolean isComplete() {
        return (Math.pow(2, this.height()) - 1) == this.node_count;
    }

    /**
     * Looks for the existence of a key in the tree
     *
     * @param key Key to look for
     * @return Key existence state
     */
    public boolean search(K key) {
        if (this.size() < 1) {
            return false;
        } else {
            return search(key, this.root);
        }
    }

    /**
     * Searches for key of a value
     *
     * @param value Value to search for
     * @return Key of value or null if not found
     */
    public K search(V value) {
        log.log_Debug("Searching for value '", value, "'");
        return search(value, this.root);
    }

    /**
     * Print the tree in Pre-Order
     *
     * @param printer Printing function
     */
    public void preOrder(IPrintFunction printer) {
        this.preOrder(printer, this.root);
    }

    /**
     * Print the tree in In-Order
     *
     * @param printer Printing function
     */
    public void inOrder(IPrintFunction printer) {
        this.inOrder(printer, this.root);
    }

    /**
     * Print the tree in Post-Order
     *
     * @param printer Printing function
     */
    public void postOrder(IPrintFunction printer) {
        this.postOrder(printer, this.root);
    }

    /**
     * Print the tree in Level-Order
     *
     * @param printer Printing function
     */
    public void levelOrder(IPrintFunction printer) {
        this.levelOrder(printer, this.root);
    }

    /**
     * toString method
     *
     * @return Summary of the AVLTree data-structure as a string
     */
    public String toString() {
        return "AVLTree<K>( nodes = " + this.node_count + ", height = " + this.height() + " )";
    }

    /**
     * Prints the all nodes and their children in rows
     *
     * @return String containing the nodes and their children connected
     */
    public String toString_Debug() {
        AVLTreeNode<K, V> node = this.root;
        String s = "";
        if (node != null) {
            Queue<AVLTreeNode<K, V>> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                AVLTreeNode<K, V> current = queue.remove();
                String l = current.left != null ? "(" + current.left.key + ")" : "()";
                String r = current.right != null ? "(" + current.right.key + ")" : "()";
                s += l + "<-(" + current.key + ")->" + r + "\n";
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return s;
    }

}
