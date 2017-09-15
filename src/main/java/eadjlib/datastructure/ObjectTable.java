package eadjlib.datastructure;

import eadjlib.logger.Logger;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ObjectTable {
    private final Logger log = Logger.getLoggerInstance(ObjectTable.class.getName());

    class Cursor {
        int column = 0;
        int row = 0;
    }

    class Row {
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
            }
            return Time.class.cast(data.get(column - 1));
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
                throw new IndexOutOfBoundsException("Column index not within table's columns");
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
     */
    public ObjectTable(String... headings) {
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
        if (cursor.column == 0 && cursor.row == 0) { //new table
            this.table.add(new Row(1));
            cursor.row = 1;
            cursor.column = 1;
        } else if (cursor.column == this.headings.size()) { //new data
            this.table.add(new Row(this.table.size() + 1));
            cursor.row++;
            cursor.column = 1;
        } else {
            cursor.column++;
        }
        this.table.get(cursor.row - 1).add(e);
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
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
            throw new IndexOutOfBoundsException("Row index not within table's rows");
        }
        return table.get(row - 1).getTimestamp(column);
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
