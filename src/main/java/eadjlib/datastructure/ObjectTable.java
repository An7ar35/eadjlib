package eadjlib.datastructure;

import eadjlib.datatype.Cursor;
import eadjlib.logger.Logger;

import java.security.InvalidParameterException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class ObjectTable {
    private final Logger log = Logger.getLoggerInstance(ObjectTable.class.getName());

    public class Row {
        private int row_number;
        private ArrayList<Object> data;

        /**
         * Constructor
         *
         * @param data Row number
         */
        Row(int data) {
            this.data = new ArrayList<>();
            this.row_number = data;
        }

        /**
         * Adds an element to the end of the data
         *
         * @param e Element to add
         */
        void add(Object e) {
            data.add(e);
        }

        public int row() {
            return this.row_number;
        }

        /**
         * Gets the Boolean value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public Boolean getBoolean(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return Boolean.class.cast(data.get(column - 1));
        }

        /**
         * Gets the Date value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public Date getDate(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return Date.class.cast(data.get(column - 1));
        }

        /**
         * Gets the Double value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public Double getDouble(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return Double.class.cast(data.get(column - 1));
        }

        /**
         * Gets the Integer value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public int getInt(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return Integer.class.cast(data.get(column - 1));
        }

        /**
         * Gets the Long value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public Long getLong(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return Long.class.cast(data.get(column - 1));
        }

        /**
         * Gets the Object of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         */
        public Object getObject(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return data.get(column - 1);
        }

        /**
         * Gets the Short value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public Short getShort(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return Short.class.cast(data.get(column - 1));
        }

        /**
         * Gets the String value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public String getString(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return String.class.cast(data.get(column - 1));
        }

        /**
         * Gets the Time value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public Time getTime(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return new Time(Date.class.cast(data.get(column - 1)).getTime());
        }

        /**
         * Gets the Timestamp value of a cell
         *
         * @param column Column in row
         * @return Cell value
         * @throws IndexOutOfBoundsException when column index fall outside the row
         * @throws ClassCastException        when value cannot be cast to type
         */
        public Timestamp getTimestamp(int column) throws IndexOutOfBoundsException, ClassCastException {
            if (!(column > 0 && column <= data.size())) {
                log.log_Error("Column index (", column, ") not within Row (#", row(), ")'s size.");
                throw new IndexOutOfBoundsException("Column index '" + column + "' not within table's columns");
            }
            return Timestamp.class.cast(data.get(column - 1));
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(row_number);
            stringBuilder.append("] \t");
            for (Object e : this.data) {
                stringBuilder.append(e);
                stringBuilder.append(" | ");
            }
            return stringBuilder.toString();
        }
    }

    private ArrayList<String> headings;
    private ArrayList<Row> table;
    private Cursor cursor;

    /**
     * Constructor
     *
     * @param headings Column headings
     * @throws RuntimeException when no headings are given
     */
    public ObjectTable(String... headings) throws RuntimeException {
        int heading_count = 0;
        this.headings = new ArrayList<String>();
        for (String h : headings) {
            this.headings.add(h);
            heading_count++;
        }
        if (heading_count == 0) {
            log.log_Error("ObjectTable was instantiated with no headings.");
            throw new RuntimeException("ObjectTable was instantiated with no headings.");
        } else {
            this.table = new ArrayList<Row>();
            this.cursor = new Cursor();
        }
    }

    /**
     * Constructor
     *
     * @param headings Column headings
     * @throws RuntimeException when no headings are given
     */
    public ObjectTable(List<String> headings) throws RuntimeException {
        int heading_count = 0;
        this.headings = new ArrayList<String>();
        for (String h : headings) {
            this.headings.add(h);
            heading_count++;
        }
        if (heading_count == 0) {
            log.log_Error("ObjectTable was instantiated with no headings.");
            throw new RuntimeException("ObjectTable was instantiated with no headings.");
        } else {
            this.table = new ArrayList<Row>();
            this.cursor = new Cursor();
        }
    }

    /**
     * Adds an object in the table after the last insertion (left to right column, data by data)
     *
     * @param e Element to add to table
     */
    public void add(Object e) {
        if (cursor.column() == 0 && cursor.row() == 0) { //new table
            this.table.add(new Row(1));
            cursor.set(1, 1);
        } else if (cursor.column() == this.headings.size()) { //new data
            this.table.add(new Row(this.table.size() + 1));
            cursor.row++;
            cursor.setColumn(1);
        } else {
            cursor.column++;
        }
        this.table.get(cursor.row() - 1).add(e);
    }

    /**
     * Removes all elements in table
     */
    public void clear() {
        table.clear();
    }

    /**
     * Gets the number of rows currently in the table
     *
     * @return Number of rows
     */
    public int rowCount() {
        return this.table.size();
    }

    /**
     * Gets the number of columns in the table
     *
     * @return Number of columns
     */
    public int columnCount() {
        return this.headings.size();
    }

    /**
     * Gets the Boolean value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public Boolean getBoolean(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getBoolean(column);
    }

    /**
     * Gets the Date value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public Date getDate(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getDate(column);
    }

    /**
     * Gets the Double value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public Double getDouble(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getDouble(column);
    }

    /**
     * Gets the Integer value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public int getInteger(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getInt(column);
    }

    /**
     * Gets the Long value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public Long getLong(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getLong(column);
    }

    /**
     * Gets the Object in a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     */
    public Object getObject(int column, int row) throws IndexOutOfBoundsException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getObject(column);
    }

    /**
     * Gets the Short value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public Short getShort(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getShort(column);
    }

    /**
     * Gets the String value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public String getString(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getString(column);
    }

    /**
     * Gets the Time value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public Time getTime(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getTime(column);
    }

    /**
     * Gets the Timestamp value of a cell
     *
     * @param column Column
     * @param row    Row
     * @return Cell value
     * @throws IndexOutOfBoundsException when row/column fall outside the table
     * @throws ClassCastException        when Object at cell cannot be cast to type
     */
    public Timestamp getTimestamp(int column, int row) throws IndexOutOfBoundsException, ClassCastException {
        if (!(row > 0 && row <= this.table.size())) {
            log.log_Error("Row index (", row, ") not within the table's rows (", this.table.size(), ").");
            throw new IndexOutOfBoundsException("Row index '" + row + "' not within table's rows");
        }
        return table.get(row - 1).getTimestamp(column);
    }

    /**
     * Returns true if this table contains the specified element.
     *
     * @param e Element to search for
     * @return Existence state
     */
    public boolean contains(Object e) {
        for (Row row : this.table) {
            if (row.data.contains(e))
                return true;
        }
        return false;
    }

    /**
     * Returns true if specified row contains the specified element.
     *
     * @param e   Element to find
     * @param row Row in which to look into
     * @return Found state
     * @throws IndexOutOfBoundsException when row specified is not within the bounds of the table
     */
    public boolean containsInRow(Object e, int row) throws IndexOutOfBoundsException {
        for (Object cell : this.table.get(row - 1).data) {
            if (cell.equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this table contains the specified column.
     *
     * @param column Column name
     * @return Existence state
     */
    public boolean containsColumn(String column) {
        return this.headings.contains(column);
    }

    /**
     * Returns true if specified column contains the specified element.
     *
     * @param e      Element to find
     * @param column Column in which to look into
     * @return Found state
     * @throws IndexOutOfBoundsException when column specified is not within the bounds of the table
     */
    public boolean containsInColumn(Object e, int column) throws IndexOutOfBoundsException {
        if (column < 1 || column > this.headings.size()) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }
        for (Row row : this.table) {
            if (row.data.get(column - 1).equals(e))
                return true;
        }
        return false;
    }

    /**
     * Returns true if specified column contains the specified element.
     *
     * @param e       Element to find
     * @param heading heading of column in which to look into
     * @return Found state
     * @throws IndexOutOfBoundsException when heading specified is not in the table
     */
    public boolean containsInColumn(Object e, String heading) throws IndexOutOfBoundsException {
        int column = 0;
        for (String h : this.headings) {
            column++;
            if (h.equals(heading)) {
                return containsInColumn(e, column);
            }
        }
        throw new IndexOutOfBoundsException("Heading '" + heading + "' not in table.");
    }

    /**
     * Returns the cursor of the first occurrence of the specified element in the specified row.
     *
     * @param e   Element to find index of
     * @param row Row in which to look into
     * @return Cursor of element
     * @throws IndexOutOfBoundsException when element does not exist in row
     */
    public Cursor indexOfInRow(Object e, int row) throws IndexOutOfBoundsException {
        Cursor cursor = new Cursor();
        for (Object cell : this.table.get(row - 1).data) {
            cursor.column++;
            if (cell.equals(e)) {
                cursor.setRow(row);
                return cursor;
            }
        }
        throw new IndexOutOfBoundsException("Element '" + e + "' not in row '" + row + "'.");
    }

    /**
     * Returns the cursor of the first occurrence of the specified element in the specified column.
     *
     * @param e      Element to find index of
     * @param column Column in which to look into
     * @return Cursor of element
     * @throws IndexOutOfBoundsException when column index is invalid or element does not exist in column
     */
    public Cursor indexOfInColumn(Object e, int column) throws IndexOutOfBoundsException {
        if (column < 1 || column > this.headings.size()) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }
        Cursor cursor = new Cursor(column, 0);
        for (Row row : this.table) {
            cursor.row++;
            if (row.data.get(column - 1).equals(e))
                return cursor;
        }
        throw new IndexOutOfBoundsException("Element '" + e + "' not in column '" + column + "'.");
    }

    /**
     * Returns the cursor of the first occurrence of the specified element in the specified column.
     *
     * @param e       Element to find index of
     * @param heading Heading of column in which to look into
     * @return Cursor of element
     * @throws IndexOutOfBoundsException when column heading is invalid or element does not exist in column
     */
    public Cursor indexOfInColumn(Object e, String heading) throws IndexOutOfBoundsException {
        int column = 0;
        for (String h : this.headings) {
            column++;
            if (h.equals(heading)) {
                return indexOfInColumn(e, column);
            }
        }
        throw new IndexOutOfBoundsException("Heading '" + heading + "' not in table.");
    }

    /**
     * Returns the cursor of the first occurrence of the specified element in this table.
     *
     * @param e Element to find index of
     * @return Cursor of element
     * @throws IndexOutOfBoundsException when element does not exist in table
     */
    public Cursor indexOf(Object e) throws IndexOutOfBoundsException {
        Cursor cursor = new Cursor();
        for (Row row : this.table) {
            cursor.row++;
            for (Object cell : row.data) {
                cursor.column++;
                if (cell.equals(e))
                    return cursor;
            }
            cursor.setColumn(0);
        }
        throw new IndexOutOfBoundsException("Element '" + e + "' not in table.");
    }

    /**
     * Gets the index of the first matching column heading
     *
     * @param heading Heading
     * @return Index of the column
     * @throws InvalidParameterException when heading does not match any in the table
     */
    public int indexOfHeading(String heading) throws InvalidParameterException {
        int column = 0;
        for (String h : this.headings) {
            column++;
            if (h.equals(heading)) {
                return column;
            }
        }
        throw new InvalidParameterException("Heading '" + heading + "' does not match any in table.");
    }

    /**
     * Gets a row as a HashMap with keys as column headings and values as the row objects
     *
     * @param row Row index
     * @return HashMap of column headings and row objects
     */
    public HashMap<String, Object> getRow(int row) throws IndexOutOfBoundsException {
        if (row < 1 || row > this.table.size()) {
            throw new IndexOutOfBoundsException("Row index '" + row + "' out of bounds of the table.");
        }
        try {
            HashMap<String, Object> map = new HashMap<>();
            int column = 0;
            for (String heading : this.headings) {
                column++;
                String mapKey = heading;
                if (map.containsKey(mapKey)) {
                    int count = 1;
                    mapKey = heading + "_" + String.valueOf(count);
                    log.log_Debug("HashMap already contains a key '" + heading + "' Trying with '" + mapKey + "'.");
                    while (map.containsKey(mapKey)) {
                        count++;
                        mapKey = heading + "_" + String.valueOf(count);
                        log.log_Debug("HashMap already contains a key '" + heading + "' Trying with '" + mapKey + "'.");
                    }
                }
                map.put(mapKey, this.getObject(column, row));
            }
            return map;
        } catch (IndexOutOfBoundsException e) {
            log.log_Error("Inconsistencies detected between row '", row, "' width (", table.get(row).data.size(), ") and number of columns (", columnCount(), ")");
            log.log_Exception(e);
            throw e;
        }
    }

    /**
     * Returns true if this table contains no elements.
     *
     * @return Empty state
     */
    public boolean isEmpty() {
        return this.table.isEmpty();
    }

    /**
     * Gets the table's rows as an iterable list
     *
     * @return Table's rows
     */
    public Iterable<Row> getTableRows() {
        return Collections.unmodifiableList(this.table);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t| ");
        for (String h : this.headings) {
            stringBuilder.append(h);
            stringBuilder.append(" | ");
        }
        for (Row r : this.table) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(r);
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
