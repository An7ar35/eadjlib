package eadjlib.datastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
    public void add_duplicate_fail() throws Exception {
        Assert.assertTrue(tree.add(1, ""));
        Assert.assertFalse(tree.add(1, ""));
    }

    @Test
    public void add1() throws Exception {
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
    public void add2() throws Exception {
        tree.add(408, "");
        tree.add(68, "");
        tree.add(453, "");
        tree.add(418, "");
        tree.add(64, "");
        tree.add(485, "");
        tree.add(457, "");
        tree.add(111, "");
        tree.add(317, "");
        tree.add(155, "");
        tree.add(49, "");
        tree.add(274, "");
        tree.add(140, "");
        tree.add(95, "");
        tree.add(497, "");
        tree.add(479, "");
        tree.add(499, "");
        tree.add(483, "");
        tree.add(484, "");
        Assert.assertEquals(19, tree.size());
        Assert.assertEquals(5, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(408, 68, 457, 64, 155, 453, 485, 49, 111, 317, 418, 483, 497, 95, 140, 274, 479, 484, 499));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
        //In order
        PrintToCollection printer2 = new PrintToCollection();
        tree.inOrder(printer2);
        ArrayList<Integer> result2 = printer2.getKeyStore();
        ArrayList<Integer> expected2 = new ArrayList<>(Arrays.asList(49, 64, 68, 95, 111, 140, 155, 274, 317, 408, 418, 453, 457, 479, 483, 484, 485, 497, 499));
        Assert.assertEquals(result2.size(), expected2.size());
        Iterator iterator_exp2 = expected2.iterator();
        Iterator iterator_res2 = result2.iterator();
        while (iterator_res2.hasNext() && iterator_exp2.hasNext()) {
            Assert.assertEquals(iterator_exp2.next(), iterator_res2.next());
        }
    }

    @Test
    public void add3() throws Exception {
        tree.add(3771, "");
        tree.add(2685, "");
        tree.add(310, "");
        tree.add(4826, "");
        tree.add(1324, "");
        tree.add(4428, "");
        tree.add(2163, "");
        tree.add(691, "");
        tree.add(4946, "");
        tree.add(2937, "");
        Assert.assertEquals(10, tree.size());
        Assert.assertEquals(4, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(2685, 1324, 4428, 310, 2163, 3771, 4826, 691, 2937, 4946));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
        //In order
        PrintToCollection printer2 = new PrintToCollection();
        tree.inOrder(printer2);
        ArrayList<Integer> result2 = printer2.getKeyStore();
        ArrayList<Integer> expected2 = new ArrayList<>(Arrays.asList(310, 691, 1324, 2163, 2685, 2937, 3771, 4428, 4826, 4946));
        Assert.assertEquals(result2.size(), expected2.size());
        Iterator iterator_exp2 = expected2.iterator();
        Iterator iterator_res2 = result2.iterator();
        while (iterator_res2.hasNext() && iterator_exp2.hasNext()) {
            Assert.assertEquals(iterator_exp2.next(), iterator_res2.next());
        }
    }

    @Test
    public void add_fuzz_random() throws Exception {
        Random random = new Random();
        ArrayList<Integer> expected = new ArrayList<>();
        for( int i = 0; i< 2500; i++ ) {
            Integer number = random.nextInt(5000);
            if( tree.add( number, ""))
                expected.add(number);
        }
        expected.sort((Integer a, Integer b) -> (a.compareTo(b)));
        //In order
        PrintToCollection printer = new PrintToCollection();
        tree.inOrder(printer);
        ArrayList<Integer> result = printer.getKeyStore();
        Assert.assertEquals(result.size(), expected.size());
        Iterator iterator_exp2 = expected.iterator();
        Iterator iterator_res2 = result.iterator();
        while (iterator_res2.hasNext() && iterator_exp2.hasNext()) {
            Assert.assertEquals(iterator_exp2.next(), iterator_res2.next());
        }
    }

    /**
     *    5
     *     \
     *      10
     *     /
     *    6
     */
    @Test
    public void add_zig_zag1() throws Exception {
        tree.add(5, "");
        tree.add(10, "");
        tree.add(6, "");

        Assert.assertEquals(3, tree.size());
        Assert.assertEquals(2, tree.height());
        PrintToCollection printer = new PrintToCollection();
        tree.levelOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(6, 5, 10));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    /**
     *    14
     *   /  \
     *  13  23
     *        \
     *        64
     *        /
     *       31
     */
    @Test
    public void add_zig_zag2() throws Exception {
        tree.add( 14, "" );
        tree.add( 13, "" );
        tree.add( 23, "" );
        tree.add( 64, "" );
        tree.add( 31, "" );
        Assert.assertEquals(5, tree.size());
        Assert.assertEquals(3, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(14, 13, 31, 23, 64));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
    }

    /**
     *    10
     *    /
     *   5
     *    \
     *     6
     */
    @Test
    public void add_zag_zig1() throws Exception {
        tree.add(10, "");
        tree.add(5, "");
        tree.add(6, "");

        Assert.assertTrue(tree.size() == 3);
        Assert.assertTrue(tree.height() == 2);
        PrintToCollection printer = new PrintToCollection();
        tree.levelOrder(printer);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(6, 5, 10));
        ArrayList<Integer> result = printer.getKeyStore();

        Assert.assertTrue(expected.size() == result.size());
        Iterator iterator_exp = expected.iterator();
        Iterator iterator_res = result.iterator();
        while (iterator_res.hasNext() && iterator_exp.hasNext()) {
            Assert.assertEquals(iterator_exp.next(), iterator_res.next());
        }
    }

    @Test
    public void add_in_increasing_order1() throws Exception {
        tree.add(0, "");
        tree.add(1, "");
        tree.add(2, "");
        tree.add(3, "");
        Assert.assertEquals(4, tree.size());
        Assert.assertEquals(3, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 0, 2, 3));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
    }

    @Test
    public void add_in_decreasing_order1() throws Exception {
        tree.add(3, "");
        tree.add(2, "");
        tree.add(1, "");
        tree.add(0, "");
        Assert.assertEquals(4, tree.size());
        Assert.assertEquals(3, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(2, 1, 3, 0));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
    }

    @Test
    public void add_zig_zag3() throws Exception {
        //6, 4, 7, 3, 8, 2, 9, 1, 10, 0
        for( int i = 1, val = 5; i <= 5; i++ ) {
            tree.add( val + i, "val_" + ( val + i ) );
            tree.add( val - i, "val_" + ( val - i ) );
        }
        Assert.assertEquals(10, tree.size());
        Assert.assertEquals(4, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(6, 3, 8, 1, 4, 7, 9, 0, 2, 10));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
    }

    @Test
    public void add_zag_zig2() throws Exception {
        //4, 6, 3, 7, 2, 8, 1, 9, 0, 10
        for( int i = 1, val = 5; i <= 5; i++ ) {
            tree.add( val - i, "val_" + ( val - i) );
            tree.add( val + i, "val_" + ( val + i ) );
        }
        Assert.assertEquals(10, tree.size());
        Assert.assertEquals(4, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(4, 2, 7, 1, 3, 6, 9, 0, 8, 10));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
    }

    @Test
    public void add_in_increasing_order2() throws Exception {
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
    public void add_in_descending_order2() throws Exception {
        for (int i = 9; i >= 0; i--) {
            tree.add(i, "");
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
    public void remove1() throws Exception {
        for( int i = 0; i <= 10; i++) {
            tree.add( i, "val_" + i );
            Assert.assertEquals( "val_" + i, tree.getValue(i));
            Assert.assertEquals(i+1,tree.size());
        }
        tree.remove(7);
        tree.remove(4);
        tree.remove(1);
        tree.remove(2);
        Assert.assertEquals(7, tree.size());
        Assert.assertEquals(3, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(6, 3, 9, 0, 5, 8, 10));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
    }

    @Test
    public void remove2() throws Exception {
        tree.add( 6, "val_" + 6 );
        tree.add( 4, "val_" + 4 );
        tree.add( 7, "val_" + 7 );
        tree.add( 1, "val_" + 1 );
        tree.add( 5, "val_" + 5 );
        tree.add( 8, "val_" + 8 );
        tree.add( 0, "val_" + 0 );
        tree.add( 2, "val_" + 2 );
        tree.add( 3, "val_" + 3 );
        tree.remove(7);
        tree.remove(5);
        tree.remove(2);
        Assert.assertEquals(6, tree.size());
        Assert.assertEquals(3, tree.height());
        //Level order
        PrintToCollection printer1 = new PrintToCollection();
        tree.levelOrder(printer1);
        ArrayList<Integer> result = printer1.getKeyStore();
        ArrayList<Integer> expected1 = new ArrayList<>(Arrays.asList(4, 1, 6, 0, 3, 8));
        Assert.assertEquals(result.size(), expected1.size());
        Iterator iterator_exp1 = expected1.iterator();
        Iterator iterator_res1 = result.iterator();
        while (iterator_res1.hasNext() && iterator_exp1.hasNext()) {
            Assert.assertEquals(iterator_exp1.next(), iterator_res1.next());
        }
    }

    @Test
    public void remove_in_increasing_order() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for (int i = 0; i < 100; i++) {
            tree.add(i, "");
            Assert.assertTrue(tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for (int i = 0; i < 100; i++) {
            tree.remove(i);
            Assert.assertFalse(tree.search(i));
            Assert.assertTrue(tree.size() == size--);
        }
    }

    @Test
    public void remove_in_decreasing_order() throws Exception {
        Assert.assertTrue(tree.size() == 0);
        for (int i = 0; i < 100; i++) {
            tree.add(i, "");
            Assert.assertTrue(tree.size() == i + 1);
        }
        int size = tree.size() - 1;
        for (int i = 99; i >= 0; i--) {
            tree.remove(i);
            Assert.assertFalse(tree.search(i));
            Assert.assertTrue(tree.size() == size--);
        }
    }

    @Test
    public void add_remove_add() throws Exception {
        Assert.assertTrue(tree.isEmpty());
        Assert.assertTrue(tree.add(1, "one"));
        Assert.assertEquals(1, tree.size());
        Assert.assertEquals("one", tree.getValue(1));
        Assert.assertTrue(tree.remove(1));

        Assert.assertTrue(tree.isEmpty());
        Assert.assertTrue(tree.add(2, "two"));
        Assert.assertEquals(1, tree.size());
        Assert.assertEquals("two", tree.getValue(2));

        Assert.assertTrue(tree.add(1, "one"));
        Assert.assertEquals(2, tree.size());
        Assert.assertEquals("one", tree.getValue(1));
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
    public void getValue() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "val_" + i);
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals("val_" + i, tree.getValue(i));
        }
    }

    @Test
    public void apply() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "val_" + i);
        }
        for (int i = 0; i < 10; i++) {
            String value = tree.apply(i, (String s) -> (s += "_appended"));
            Assert.assertEquals("Returned value on apply(..) incorrect.", "val_" + i + "_appended", value);
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals("Stored value incorrect.", "val_" + i + "_appended", tree.getValue(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void apply_fail() throws Exception {
        for (int i = 0; i < 10; i++) {
            tree.add(i, "val_" + i);
        }
        tree.getValue(11);
    }

    @Test(expected = NullPointerException.class)
    public void getValue_fail() throws Exception {
        tree.getValue(1);
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

    @Test
    public void searchKeys() throws Exception {
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < 100; i++) {
            avlTree.add(i, i * 10);
        }
        Collection<Integer> result = avlTree.searchKeys((i) -> (i >= 50 && i < 100));
        for (Integer r : result) {
            Assert.assertTrue(r >= 500 && r < 1000);
        }
    }

    @Test
    public void searchValues() throws Exception {
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < 100; i++) {
            avlTree.add(i, i * 10);
        }
        Collection<Integer> result = avlTree.searchValues((i) -> (i >= 500 && i < 1000));
        for (Integer r : result) {
            Assert.assertTrue(r >= 500 && r < 1000);
        }
    }
}