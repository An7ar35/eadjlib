package eadjlib.datastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

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
        //TODO
    }

    @Test
    public void inOrder() throws Exception {
        //TODO
    }

    @Test
    public void postOrder() throws Exception {
        //TODO
    }

    @Test
    public void levelOrder() throws Exception {
        //TODO
    }

    @Test
    public void add() throws Exception {
        //TODO
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
    public void add_RRotation() {
        //TODO
    }

    @Test
    public void remove() throws Exception {
        //TODO
    }

    @Test
    public void remove_in_increasing_order() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for( int i = 0; i < 10; i++ ) {
            tree.add( i,"");
            Assert.assertTrue( tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for( int i = 0; i < 10; i++ ) {
            tree.remove( i );
            Assert.assertFalse( tree.search( i ));
            Assert.assertTrue( tree.size() == size-- );
        }
    }

    @Test
    public void remove_in_decreasing_order() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for( int i = 0; i < 10; i++ ) {
            tree.add( i,"");
            Assert.assertTrue( tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for( int i = 9; i >= 0; i-- ) {
            tree.remove( i );
            Assert.assertFalse( tree.search( i ));
            //System.out.println( tree.size() + " : " + size-- );
            //System.out.println( tree.toString_Debug() );
            Assert.assertTrue( tree.size() == size-- );
        }
    }

    @Test
    public void iterator() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "val_" + i);
        }
        Iterator iterator = tree.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals("", "[" + i + "]=val_" + i, iterator.next().toString());
            i++;
        }
    }

    @Test
    public void forEach() throws Exception {
        //TODO
    }

    @Test
    public void size() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for( int i = 0; i < 10; i++ ) {
            tree.add( i,"");
            Assert.assertTrue( tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for( int i = 0; i < 10; i++ ) {
            tree.remove( i );
            //System.out.println( tree.size() + " : " + size-- );
            //System.out.println( tree.toString_Debug() );
            Assert.assertTrue( tree.size() == size-- );
        }
    }

    @Test
    public void height() throws Exception {
        //TODO
    }

    @Test
    public void clear() throws Exception {
        //TODO
    }

    @Test
    public void isEmpty() throws Exception {
        //TODO
    }

    @Test
    public void isFull() throws Exception {
        //TODO
    }

    @Test
    public void isComplete() throws Exception {
        //TODO
    }

    @Test
    public void searchValue() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "val_" + i);
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(tree.search("val_" + i) == i);
        }
        Assert.assertTrue(tree.search("error") == null);
        Assert.assertTrue(tree.search("val_100") == null);
    }

    @Test
    public void searchKey() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "");
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(tree.search(i));
        }
        Assert.assertFalse(tree.search(-1));
        Assert.assertFalse(tree.search(10));
    }
}