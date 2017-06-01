package eadjlib.datastructure;

import eadjlib.exception.UndefinedException;
import eadjlib.logger.Logger;

import java.awt.*;

import static eadjlib.datastructure.AVLTree.Branch.*;

public class AVLTree<T extends Comparable> extends Container {
    private final Logger log = Logger.getLoggerInstance(AVLTree.class.getName());
    private Integer node_count = 0;
    private Integer tree_height = 0;
    private AVLTreeNode<T> root;

    protected enum Branch {ROOT, LEFT, RIGHT}

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
    private boolean search(T key, AVLTreeNode<T> node) {
        int comparison = key.compareTo(node.key);
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
     * Helper function to remove a node matching given key
     *
     * @param key    Key to look for and remove
     * @param parent Parent of current node
     * @param branch Branch taken
     * @param node   Current node
     * @return Success
     * @throws UndefinedException when corruption is detected during the balancing of the parent
     */
    private boolean remove(T key, AVLTreeNode<T> parent, Branch branch, AVLTreeNode<T> node) throws UndefinedException {
        try {
            int comparison = node.key.compareTo(key);
            if (comparison == 0) {
                if (node.right != null) {
                    T replacement_key = removeSmallest(node, node.right);
                    node.key = replacement_key;
                } else if (node.left != null) {
                    T replacement_key = removeLargest(node, node.left);
                    node.key = replacement_key;
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
                if (comparison < 0 && node.left != null) {
                    return remove(key, node, LEFT, node.left);
                }
                if (comparison > 0 && node.right != null) {
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
    private T removeSmallest(AVLTreeNode<T> parent, AVLTreeNode<T> node) throws UndefinedException {
        if (node.left != null) {
            return removeSmallest(node, node.left);
        } else {
            T key = node.key;
            if (parent.left == node) {
                parent.left = null;
            } else { //parent.right == node
                parent.right = node.right;
                node.right.parent = parent;
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
    private T removeLargest(AVLTreeNode<T> parent, AVLTreeNode<T> node) throws UndefinedException {
        if (node.right != null) {
            return removeLargest(node, node.right);
        } else {
            T key = node.key;
            if (parent.right == node) {
                parent.right = null;
            } else { //parent.left == node
                parent.left = node.left;
                node.left.parent = parent;
            }
            balance(parent);
            return key;
        }
    }

    /**
     * Balances the nodes in the tree in reverse (leaf to root)
     *
     * @param node Root of the balance
     */
    private void balance(AVLTreeNode<T> node) throws UndefinedException {
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
    private void rotateRR(AVLTreeNode<T> parent, Branch branch) throws UndefinedException {
        if (getChild(parent, branch).right.getBalanceFactor() < 0) {
            rotateRL(parent, branch);
        } else {
            AVLTreeNode<T> b = detach(getChild(parent, branch), LEFT); // a<-b
            AVLTreeNode<T> c = detach(parent, branch); // c
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
    private void rotateLL(AVLTreeNode<T> parent, Branch branch) throws UndefinedException {
        if (getChild(parent, branch).left.getBalanceFactor() > 0) {
            rotateLR(parent, branch);
        } else {
            AVLTreeNode<T> b = detach(getChild(parent, branch), RIGHT); // b->c
            AVLTreeNode<T> a = detach(parent, branch); // a
            a.right = b.left;
            attach(parent, branch, b);
            attach(getChild(parent, branch), LEFT, a);
        }
    }

    /**
     * Left-Right rotation
     *
     * @param parent Pointer to the m_parent of the rotation
     * @param branch Branch on which to do the rotation
     * @throws UndefinedException when parent is null
     */
    private void rotateLR(AVLTreeNode<T> parent, Branch branch) throws UndefinedException {
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
    private void rotateRL(AVLTreeNode<T> parent, Branch branch) throws UndefinedException {
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
    private AVLTreeNode<T> detach(AVLTreeNode<T> parent, Branch branch) throws UndefinedException {
        if (parent == null && branch != ROOT) {
            throw new UndefinedException("Parent is undefined (null).");
        }
        switch (branch) {
            case LEFT:
                if (parent.left == null) return new AVLTreeNode<>( null );
                return parent.left;
            case RIGHT:
                if (parent.right == null) return new AVLTreeNode<>( null );
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
    private void attach(AVLTreeNode<T> parent, Branch branch, AVLTreeNode<T> orphan_branch) throws UndefinedException {
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
    private AVLTreeNode<T> getChild(AVLTreeNode<T> parent, Branch branch) throws UndefinedException {
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
    private Branch getBranch(AVLTreeNode<T> node) throws UndefinedException {
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
    private Integer getHeightDifference(AVLTreeNode<T> node) {
        return node.left.height() - node.right.height();
    }


    //==================================================================================================================
    // Protected methods
    //==================================================================================================================

//    /**
//     * [PROTECTED] Pre-Order BinaryTree transversal
//     * @param output_function Function to process output of the content of the node
//     * @param node AVLTreeNode to process
//     */
//    template<class T> void AVLTree<T>::preOrder( void(*output_function)( T &content ), const std::unique_ptr<AVLTreeNode<T>> &node ) const {
//        if( node ) {
//            output_function( node->_key );
//            preOrder( output_function, node->m_left );
//            preOrder( output_function, node->m_right );
//        }
//    }
//
//    /**
//     * [PROTECTED] In-Order BinaryTree transversal
//     * @param output_function Function to process output of the content of the node
//     * @param node AVLTreeNode to process
//     */
//    template<class T> void AVLTree<T>::inOrder( void(*output_function)( T &content ), const std::unique_ptr<AVLTreeNode<T>> &node ) const {
//        if( node ) {
//            inOrder( output_function, node->m_left );
//            output_function( node->_key );
//            inOrder( output_function, node->m_right );
//        }
//    }
//
//    /**
//     * [PROTECTED] Post-Order BinaryTree transversal
//     * @param output_function Function to process output of the content of the node
//     * @param node AVLTreeNode to process
//     */
//    template<class T> void AVLTree<T>::postOrder( void(*output_function)( T &content ), const std::unique_ptr<AVLTreeNode<T>> &node ) const {
//        if( node ) {
//            postOrder( output_function, node->m_left );
//            postOrder( output_function, node->m_right );
//            output_function( node->_key );
//        }
//    }
//
//    /**
//     * [PROTECTED] Level-Order BinaryTree transversal
//     * @param output_function Function to process output of the content of the node
//     * @param node AVLTreeNode to process
//     */
//    template<class T> void AVLTree<T>::levelOrder( void(*output_function)( T &content ), const std::unique_ptr<AVLTreeNode<T>> &node ) const {
//        if( node.get() == nullptr ) return;
//        eadlib::LinkedList<AVLTreeNode<T> *> queue;
//        queue.addToHead( node.get() );
//        while( !queue.isEmpty() ) {
//            AVLTreeNode<T> *current = queue.removeTail();
//            output_function( current->_key );
//            if( current->m_left ) queue.addToHead( current->m_left.get() );
//            if( current->m_right ) queue.addToHead( current->m_right.get() );
//        }
//    }
    protected void levelOrder( )

    //==================================================================================================================
    // Public methods
    //==================================================================================================================

//    public AVLTree( const std::initializer_list<T> list );
//    AVLTree( const LinearList<T> list );
//    AVLTree( const LinkedList<T> list );
//        ~AVLTree() {};
//    //Manipulation functions
//    void add( const T &key );
//    bool remove( const T &key );
//    void clear();
//    //Properties functions
//    bool isEmpty() const;
//    int size() const;
//    int height() const;
//    bool isFull() const;
//    bool isComplete() const;
//    //Transversal & Search
//    bool search( const T &key ) const;
//    void preOrder( void(*output_function)( T &content ) ) const;
//    void inOrder( void(*output_function)( T &content ) ) const;
//    void postOrder( void(*output_function)( T &content ) ) const;
//    void levelOrder( void(*output_function)( T &content ) ) const;
}
