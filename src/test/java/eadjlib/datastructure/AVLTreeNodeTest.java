package eadjlib.datastructure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AVLTreeNodeTest {
    @Test
    public void height() throws Exception {
        AVLTreeNode<Integer, String> a = new AVLTreeNode<>(null, 1, "");
        AVLTreeNode<Integer, String> b = new AVLTreeNode<>(null, 2, "");
        AVLTreeNode<Integer, String> c = new AVLTreeNode<>(null, 3, "");
        AVLTreeNode<Integer, String> d = new AVLTreeNode<>(null, 4, "");
        AVLTreeNode<Integer, String> e = new AVLTreeNode<>(null, 5, "");

        Assert.assertTrue(a.height() == 1);
        a.left = b;
        Assert.assertTrue(a.height() == 2);
        a.right = c;
        Assert.assertTrue(a.height() == 2);
        b.left = d;
        Assert.assertTrue(a.height() == 3);
        d.right = e;
        Assert.assertTrue(a.height() == 4);
    }

    @Test
    public void isBalanced() throws Exception {
        AVLTreeNode<Integer, String> a = new AVLTreeNode<>(null, 1, "");
        AVLTreeNode<Integer, String> b = new AVLTreeNode<>(null, 2, "");
        AVLTreeNode<Integer, String> c = new AVLTreeNode<>(null, 3, "");
        AVLTreeNode<Integer, String> d = new AVLTreeNode<>(null, 4, "");
        AVLTreeNode<Integer, String> e = new AVLTreeNode<>(null, 5, "");

        Assert.assertTrue(a.isBalanced());
        a.left = b;
        Assert.assertTrue(a.isBalanced());
        b.right = c;
        Assert.assertFalse(a.isBalanced());
        a.right = d;
        Assert.assertTrue(a.isBalanced());
        c.left = e;
        Assert.assertFalse(a.isBalanced());
    }

    @Test
    public void getBalanceFactor() throws Exception {
        AVLTreeNode<Integer, String> a = new AVLTreeNode<>(null, 1, "");
        AVLTreeNode<Integer, String> b = new AVLTreeNode<>(null, 2, "");
        AVLTreeNode<Integer, String> c = new AVLTreeNode<>(null, 3, "");
        AVLTreeNode<Integer, String> d = new AVLTreeNode<>(null, 4, "");
        AVLTreeNode<Integer, String> e = new AVLTreeNode<>(null, 5, "");

        Assert.assertTrue(a.getBalanceFactor() == 0);
        a.left = b;
        Assert.assertTrue(a.getBalanceFactor() == 1);
        a.right = c;
        Assert.assertTrue(a.getBalanceFactor() == 0);
        b.left = d;
        Assert.assertTrue(a.getBalanceFactor() == 1);
        d.right = e;
        Assert.assertTrue(a.getBalanceFactor() == 2);
    }

    @Test
    public void compareTo() throws Exception {
        AVLTreeNode<Integer, String> a = new AVLTreeNode<>(null, 0, "");
        AVLTreeNode<Integer, String> b = new AVLTreeNode<>(null, 0, "");
        AVLTreeNode<Integer, String> c = new AVLTreeNode<>(null, 10, "");
        AVLTreeNode<Integer, String> d = new AVLTreeNode<>(null, -10, "");

        Assert.assertTrue(a.compareTo(b) == 0);
        Assert.assertTrue(a.compareTo(c) == -1);
        Assert.assertTrue(a.compareTo(d) == 1);

    }

}