package eadjlib.datastructure;

import eadjlib.datatype.Cursor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ObjectTableTest {
    private ObjectTable table;
    private Instant instant;

    @Before
    public void setUp() throws Exception {
        instant = Instant.now();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void clear() throws Exception {
        ObjectTable table = new ObjectTable("Integer");
        Assert.assertTrue(table.isEmpty());
        for (int i = 0; i < 10; i++) {
            table.add(i);
        }
        Assert.assertFalse(table.isEmpty());
        table.clear();
        Assert.assertTrue(table.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 0; i < 10; i++) {
            Assert.assertFalse(table.contains(i));
            Assert.assertFalse(table.contains("Value " + String.valueOf(i)));
        }
        for (int i = 0; i < 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(table.contains(i));
            Assert.assertTrue(table.contains("Value " + String.valueOf(i)));
        }
    }

    @Test
    public void containsInRow() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        for (int i = 1; i <= 10; i++) {
            Assert.assertTrue(table.containsInRow(i, i));
            Assert.assertTrue(table.containsInRow("Value " + String.valueOf(i), i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void containsInRow_IndexOutOfBoundsException() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.containsInRow(1, 11);
    }

    @Test
    public void containsColumn() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        Assert.assertTrue(table.containsColumn("Integer"));
        Assert.assertTrue(table.containsColumn("String"));
        Assert.assertFalse(table.containsColumn("Float"));
    }

    @Test
    public void containsInColumn_byIndex() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        Assert.assertTrue(table.containsInColumn(3, 1));
        Assert.assertTrue(table.containsInColumn("Value 3", 2));
        Assert.assertFalse(table.containsInColumn(3, 2));
        Assert.assertFalse(table.containsInColumn("Value 3", 1));
        Assert.assertFalse(table.containsInColumn(11, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void containsInColumn_byIndex_IndexOutOfBoundsException() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.containsInColumn(3, 3);
    }

    @Test
    public void containsInColumn_byName() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        Assert.assertTrue(table.containsInColumn(3, "Integer"));
        Assert.assertTrue(table.containsInColumn("Value 3", "String"));
        Assert.assertFalse(table.containsInColumn(3, "String"));
        Assert.assertFalse(table.containsInColumn("Value 3", "Integer"));
        Assert.assertFalse(table.containsInColumn(11, "Integer"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void containsInColumn_byName_IndexOutOfBoundsException() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.containsInColumn(3, "InvalidHeading");
    }

    @Test
    public void indexOfInRow() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        for (int i = 1; i <= 10; i++) {
            Assert.assertEquals(new Cursor(1, i), table.indexOfInRow(i, i));
            Assert.assertEquals(new Cursor(2, i), table.indexOfInRow("Value " + String.valueOf(i), i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfInRow_not_found() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.indexOfInRow("Value 3", 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfInRow_invalid_row() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.indexOfInRow("Value 3", 11);
    }

    @Test
    public void indexOfInColumn_byIndex() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        for (int i = 1; i <= 10; i++) {
            Assert.assertEquals(new Cursor(1, i), table.indexOfInColumn(i, 1));
            Assert.assertEquals(new Cursor(2, i), table.indexOfInColumn("Value " + String.valueOf(i), 2));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfInColumn_byIndex_invalid_column() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.indexOfInColumn("Value 3", 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfInColumn_byIndex_not_found() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.indexOfInColumn("Value 11", 2);
    }

    @Test
    public void indexOfInColumn_byName() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        for (int i = 1; i <= 10; i++) {
            Assert.assertEquals(new Cursor(1, i), table.indexOfInColumn(i, "Integer"));
            Assert.assertEquals(new Cursor(2, i), table.indexOfInColumn("Value " + String.valueOf(i), "String"));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfInColumn_byName_invalid_heading() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.indexOfInColumn("Value 3", "InvalidHeading");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfInColumn_byName_not_found() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.indexOfInColumn("Value 11", "String");
    }

    @Test
    public void indexOf() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        for (int i = 1; i <= 10; i++) {
            Assert.assertEquals(new Cursor(1, i), table.indexOf(i));
            Assert.assertEquals(new Cursor(2, i), table.indexOf("Value " + String.valueOf(i)));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOf_IndexOutOfBoundsException() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        table.indexOf("Value 11");
    }

    @Test
    public void isEmpty() throws Exception {
        table = new ObjectTable("Integers");
        Assert.assertTrue(table.isEmpty());
        table.add(1);
        Assert.assertFalse(table.isEmpty());
        table.clear();
        Assert.assertTrue(table.isEmpty());
    }

    @Test
    public void add() throws Exception {
        table = new ObjectTable(
                "Integers", "Float", "Double", "Short", "Long", "Bool",
                "Strings", "Dates", "Time", "Timestamps");
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i, this.table.rowCount());
            this.table.add(i);
            this.table.add((float) i);
            this.table.add((double) i);
            this.table.add((short) i);
            this.table.add((long) i);
            this.table.add(i % 2 == 0);
            this.table.add("number " + i);
            this.table.add(Date.from(this.instant));
            this.table.add(Time.from(this.instant));
            this.table.add(Timestamp.from(instant));
            this.instant = instant.plus(1, ChronoUnit.HOURS);
        }
        Assert.assertEquals(10, this.table.columnCount());
    }

    @Test
    public void getBoolean() throws Exception {
        table = new ObjectTable("Boolean");
        table.add(true);
        table.add(false);
        Assert.assertEquals(true, table.getBoolean(1, 1));
        Assert.assertEquals(false, table.getBoolean(1, 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getBoolean_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Boolean");
        table.add(true);
        table.getBoolean(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getBoolean_ClassCastException() throws Exception {
        table = new ObjectTable("Boolean");
        table.add(1);
        table.add("false");
        table.getBoolean(1, 1);
        table.getBoolean(1, 2);
    }

    @Test
    public void getDate() throws Exception {
        table = new ObjectTable("Date");
        table.add(Date.from(this.instant));
        this.table.add(Date.from((this.instant.plus(1, ChronoUnit.DAYS))));
        this.table.add(Date.from((this.instant.plus(1, ChronoUnit.DAYS))));
        Assert.assertEquals(Date.from(this.instant), table.getDate(1, 1));
        Assert.assertEquals(Date.from(this.instant.plus(1, ChronoUnit.DAYS)), table.getDate(1, 2));
        Assert.assertEquals(Date.from(this.instant.plus(1, ChronoUnit.DAYS)), table.getDate(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getDate_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Date");
        this.table.add(Date.from(this.instant));
        table.getTimestamp(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getDate_ClassCastException() throws Exception {
        table = new ObjectTable("Date");
        this.table.add("01/01/2000");
        table.getDate(1, 1);
    }

    @Test
    public void getDouble() throws Exception {
        table = new ObjectTable("Double");
        table.add(1.2345);
        table.add(0.0);
        Assert.assertEquals(1.2345, table.getDouble(1, 1), 4);
        Assert.assertEquals(0, table.getDouble(1, 2), 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getDouble_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Double");
        table.add(1.2345);
        table.getBoolean(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getDouble_ClassCastException() throws Exception {
        table = new ObjectTable("Double");
        table.add("1.2345");
        table.add("0.0");
        table.getDouble(1, 1);
        table.getDouble(1, 2);
    }

    @Test
    public void getInteger() throws Exception {
        table = new ObjectTable("Boolean");
        table.add(-1);
        table.add(0);
        table.add(1);
        Assert.assertEquals(-1, table.getInteger(1, 1));
        Assert.assertEquals(0, table.getInteger(1, 2));
        Assert.assertEquals(1, table.getInteger(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getInteger_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Integer");
        table.add(1);
        table.getInteger(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getInteger_ClassCastException() throws Exception {
        table = new ObjectTable("Integer");
        table.add("1");
        table.getInteger(1, 1);
    }

    @Test
    public void getLong() throws Exception {
        table = new ObjectTable("Long");
        table.add(new Long(-1));
        table.add(new Long(0));
        table.add(new Long(1));
        Assert.assertEquals(new Long(-1), table.getLong(1, 1));
        Assert.assertEquals(new Long(0), table.getLong(1, 2));
        Assert.assertEquals(new Long(1), table.getLong(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLong_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Long");
        table.add(1);
        table.getLong(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getLong_ClassCastException() throws Exception {
        table = new ObjectTable("Long");
        table.add(new Long(1));
        table.getInteger(1, 1);
    }

    @Test
    public void getObject() throws Exception {
        table = new ObjectTable("Object");
        table.add("some object");
        table.add(1);
        table.add(1.2345);
        Assert.assertEquals("some object", table.getObject(1, 1));
        Assert.assertEquals(1, table.getObject(1, 2));
        Assert.assertEquals(1.2345, table.getObject(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getObject_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Object");
        table.add(1);
        table.getObject(1, 2);
    }

    @Test
    public void getShort() throws Exception {
        table = new ObjectTable("Short");
        table.add(new Short((short) -1));
        table.add(new Short((short) 0));
        table.add(new Short((short) 1));
        Assert.assertEquals(new Short((short) -1), table.getShort(1, 1));
        Assert.assertEquals(new Short((short) 0), table.getShort(1, 2));
        Assert.assertEquals(new Short((short) 1), table.getShort(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getShort_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Short");
        table.add(new Short((short) 1));
        table.getShort(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getShort_ClassCastException() throws Exception {
        table = new ObjectTable("Short");
        table.add(1);
        table.getShort(1, 1);
    }

    @Test
    public void getString() throws Exception {
        table = new ObjectTable("Boolean");
        table.add("");
        table.add("string");
        table.add("1234");
        Assert.assertEquals("", table.getString(1, 1));
        Assert.assertEquals("string", table.getString(1, 2));
        Assert.assertEquals("1234", table.getString(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getString_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("String");
        table.add("string");
        table.getString(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getString_ClassCastException() throws Exception {
        table = new ObjectTable("String");
        table.add(1);
        table.getString(1, 1);
    }

    @Test
    public void getTime() throws Exception {
        table = new ObjectTable("Time");
        this.table.add(Time.from(this.instant));
        this.table.add(Time.from((this.instant.plus(1, ChronoUnit.HOURS))));
        this.table.add(Time.from((this.instant.plus(1, ChronoUnit.HOURS))));
        Assert.assertEquals(Time.from(this.instant), table.getTime(1, 1));
        Assert.assertEquals(Time.from(this.instant.plus(1, ChronoUnit.HOURS)), table.getTime(1, 2));
        Assert.assertEquals(Time.from(this.instant.plus(1, ChronoUnit.HOURS)), table.getTime(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getTime_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Time");
        this.table.add(Time.from(this.instant));
        table.getTime(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getTime_ClassCastException() throws Exception {
        table = new ObjectTable("Time");
        this.table.add("01/01/2000");
        table.getTime(1, 1);
    }

    @Test
    public void getTimestamp() throws Exception {
        table = new ObjectTable("Timestamp");
        this.table.add(Timestamp.from(instant));
        this.table.add(Timestamp.from((this.instant.plus(1, ChronoUnit.HOURS))));
        this.table.add(Timestamp.from((this.instant.plus(1, ChronoUnit.HOURS))));
        Assert.assertEquals(Timestamp.from(this.instant), table.getTimestamp(1, 1));
        Assert.assertEquals(Timestamp.from(this.instant.plus(1, ChronoUnit.HOURS)), table.getTimestamp(1, 2));
        Assert.assertEquals(Timestamp.from(this.instant.plus(1, ChronoUnit.HOURS)), table.getTimestamp(1, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getTimestamp_IndexOutOfBoundsException() throws Exception {
        table = new ObjectTable("Timestamp");
        this.table.add(Timestamp.from(this.instant));
        table.getTimestamp(1, 2);
    }

    @Test(expected = ClassCastException.class)
    public void getTimestamp_ClassCastException() throws Exception {
        table = new ObjectTable("Timestamp");
        this.table.add("01/01/2000");
        table.getTimestamp(1, 1);
    }

    @Test
    public void indexOfHeading() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        Assert.assertEquals(1, table.indexOfHeading("Integer"));
        Assert.assertEquals(2, table.indexOfHeading("String"));
    }

    @Test(expected = InvalidParameterException.class)
    public void indexOfHeading_with_invalid_heading() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        Assert.assertEquals(1, table.indexOfHeading("invalid_heading"));
    }

    @Test
    public void getRow() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "String");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add("Value " + String.valueOf(i));
        }
        HashMap<String, Object> map = table.getRow(3);
        Assert.assertEquals(2, map.size());
        Assert.assertEquals(3, map.get("Integer"));
        Assert.assertEquals("Value 3", map.get("String"));
    }

    @Test
    public void getRow_with_heading_clash() throws Exception {
        ObjectTable table = new ObjectTable("Integer", "Integer", "Integer");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
            table.add(i);
            table.add(i);
        }
        HashMap<String, Object> map = table.getRow(3);
        Assert.assertEquals(3, map.size());
        Assert.assertEquals(3, map.get("Integer"));
        Assert.assertEquals(3, map.get("Integer_1"));
        Assert.assertEquals(3, map.get("Integer_2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getRow_invalid_row() throws Exception {
        ObjectTable table = new ObjectTable("Integer");
        for (int i = 1; i <= 10; i++) {
            table.add(i);
        }
        HashMap<String, Object> map = table.getRow(11);
    }
}