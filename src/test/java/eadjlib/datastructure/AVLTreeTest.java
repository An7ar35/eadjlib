package eadjlib.datastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AVLTreeTest {
    private AVLTree<Integer, String> tree;

    class Printer implements IPrintFunction {
        @Override
        public void call(Object... objects) {
            for (Object o : objects) {
                System.out.print(o + " ");
            }
            System.out.print(System.lineSeparator());
        }

        @Override
        public void call(Object key, Object value) {
            System.out.print("[" + key + "]=" + value + System.lineSeparator());
        }
    }

    class PrintToCollection implements IPrintFunction {
        ArrayList<Integer> key_store = new ArrayList<>();
        ArrayList<String> value_store = new ArrayList<>();

        @Override
        public void call(Object... objects) {
            //void
        }

        @Override
        public void call(Object key, Object value) {
            this.key_store.add((Integer) key);
            this.value_store.add((String) value);
        }

        public ArrayList<Integer> getKeyStore() {
            return this.key_store;
        }

        public ArrayList<String> getValueStore() {
            return this.value_store;
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
        for (int i = 0; i < 15; i++) {
            tree.add(i, "");
        }
        Assert.assertTrue(tree.size() == 15);
        Assert.assertTrue(tree.height() == 4);
        PrintToCollection printer = new PrintToCollection();
        tree.preOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(7, 3, 1, 0, 2, 5, 4, 6, 11, 9, 8, 10, 13, 12, 14));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    @Test
    public void inOrder() throws Exception {
        for (int i = 0; i < 15; i++) {
            tree.add(i, "");
        }
        Assert.assertTrue(tree.size() == 15);
        Assert.assertTrue(tree.height() == 4);
        PrintToCollection printer = new PrintToCollection();
        tree.inOrder(printer);

        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(tree.size() == result.size());
        Iterator iterator_res = result.iterator();
        int count = 0;
        while (iterator_res.hasNext()) {
            Assert.assertEquals(count, iterator_res.next());
            count++;
        }
    }

    @Test
    public void postOrder() throws Exception {
        for (int i = 0; i < 15; i++) {
            tree.add(i, "");
        }
        Assert.assertTrue(tree.size() == 15);
        Assert.assertTrue(tree.height() == 4);
        PrintToCollection printer = new PrintToCollection();
        tree.postOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 2, 1, 4, 6, 5, 3, 8, 10, 9, 12, 14, 13, 11, 7));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    @Test
    public void levelOrder() throws Exception {
        for (int i = 0; i < 15; i++) {
            tree.add(i, "");
        }
        Assert.assertTrue(tree.size() == 15);
        Assert.assertTrue(tree.height() == 4);
        PrintToCollection printer = new PrintToCollection();
        tree.levelOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    @Test
    public void add() throws Exception {
        tree.add(6, "");
        tree.add(7, "");
        tree.add(4, "");
        tree.add(5, "");
        tree.add(3, "");
        tree.add(2, "");

        Assert.assertTrue(tree.size() == 6);
        Assert.assertTrue(tree.height() == 3);
        PrintToCollection printer = new PrintToCollection();
        tree.levelOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(4, 3, 6, 2, 5, 7));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    @Test
    public void add_in_ascending_order() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "");
        }
        Assert.assertTrue(tree.size() == 10);
        Assert.assertTrue(tree.height() == 4);
        PrintToCollection printer = new PrintToCollection();
        tree.levelOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 1, 7, 0, 2, 5, 8, 4, 6, 9));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    @Test
    public void add_in_descending_order() throws Exception {
        for (int i = 9; i >= 0; i--) {
            tree.add(i, "");
            System.out.println(tree.toString_Debug());
            System.out.println("-------------------");
        }
        Assert.assertTrue(tree.size() == 10);
        Assert.assertTrue(tree.height() == 4);
        PrintToCollection printer = new PrintToCollection();
        tree.levelOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(6, 2, 8, 1, 4, 7, 9, 0, 3, 5));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    @Test
    public void remove_in_increasing_order() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for (int i = 0; i < 10; i++) {
            tree.add(i, "");
            Assert.assertTrue(tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for (int i = 0; i < 10; i++) {
            tree.remove(i);
            Assert.assertFalse(tree.search(i));
            Assert.assertTrue(tree.size() == size--);
            System.out.println(tree.toString_Debug());
        }
    }

    @Test
    public void remove_in_decreasing_order() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for (int i = 0; i < 10; i++) {
            tree.add(i, "");
            Assert.assertTrue(tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for (int i = 9; i >= 0; i--) {
            tree.remove(i);
            Assert.assertFalse(tree.search(i));
            //System.out.println( tree.toString_Debug() );
            Assert.assertTrue(tree.size() == size--);
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
    public void size() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for (int i = 0; i < 10; i++) {
            tree.add(i, "");
            Assert.assertTrue(tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for (int i = 0; i < 10; i++) {
            tree.remove(i);
            Assert.assertTrue(tree.size() == size--);
        }
    }

    @Test
    public void height() throws Exception {
        for (int i = 0; i < 33; i++) {
            if (i == 0) Assert.assertEquals(0, tree.height());
            if (i == 1) Assert.assertEquals(1, tree.height());
            if (i >= 2 && i < 4) Assert.assertEquals(2, tree.height());
            if (i >= 4 && i < 8) Assert.assertEquals(3, tree.height());
            if (i >= 8 && i < 16) Assert.assertEquals(4, tree.height());
            if (i >= 16 && i < 32) Assert.assertEquals(5, tree.height());
            if (i >= 32) Assert.assertEquals(6, tree.height());
            tree.add(i, "");
        }
    }

    @Test
    public void clear() throws Exception {
        Assert.assertTrue(tree.isEmpty() && tree.size() == 0);
        for (int i = 0; i < 10; i++) {
            tree.add(i, "val_" + i);
        }
        Assert.assertTrue(tree.size() == 10);
        tree.clear();
        Assert.assertTrue(tree.isEmpty() && tree.size() == 0);
    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertTrue(tree.isEmpty());
        tree.add(1, "One");
        Assert.assertFalse(tree.isEmpty());
        tree.remove(1);
        Assert.assertTrue(tree.isEmpty());
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