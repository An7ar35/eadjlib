package eadjlib.datastructure;

import eadjlib.logger.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AVLTreeTest {
    private AVLTree<Integer, String> tree;

    class Printer implements IPrintFunction {
        @Override
        public void call(Object... objects) {
            for (Object o : objects) {
                System.out.print(o + " ");
            }
        }
    }

    @Before
    public void setUp() throws Exception {
        tree = new AVLTree<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void preOrder() throws Exception {
    }

    @Test
    public void inOrder() throws Exception {
    }

    @Test
    public void postOrder() throws Exception {
    }

    @Test
    public void levelOrder() throws Exception {
    }

    @Test
    public void add() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "");
        }

//        tree.add( 6, "" );
//        tree.add( 7, "" );
//        tree.add( 4, "" );
//        tree.add( 5, "" );
//        tree.add( 3, "" );
//        tree.add( 2, "" );

        tree.inOrder(new Printer());
        System.out.print(System.lineSeparator());
        System.out.print(tree.toString_Debug());
        System.out.println(tree.size());
        Assert.assertTrue(tree.size() == 10);
        System.out.println(tree.height());
        Assert.assertTrue(tree.height() == 4);
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void iterator() throws Exception {
    }

    @Test
    public void forEach() throws Exception {
    }

    @Test
    public void size() throws Exception {
        Assert.assertTrue(tree.size() == 0);
    }

    @Test
    public void height() throws Exception {
    }

    @Test
    public void clear() throws Exception {
    }

    @Test
    public void isEmpty() throws Exception {
    }

    @Test
    public void isFull() throws Exception {
    }

    @Test
    public void isComplete() throws Exception {
    }

    @Test
    public void search() throws Exception {
    }

    @Test
    public void preOrder1() throws Exception {
    }

    @Test
    public void inOrder1() throws Exception {
    }

    @Test
    public void postOrder1() throws Exception {
    }

    @Test
    public void levelOrder1() throws Exception {
    }

}