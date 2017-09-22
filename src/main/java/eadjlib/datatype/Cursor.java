package eadjlib.datatype;

public class Cursor {
    public int column;
    public int row;

    /**
     * Default Constructor
     */
    public Cursor() {
        this.column = 0;
        this.row = 0;
    }

    /**
     * Constructor
     *
     * @param column Column index
     * @param row    Row index
     */
    public Cursor(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Gets the column value
     *
     * @return Column value
     */
    public int column() {
        return this.column;
    }

    /**
     * Gets the row value
     *
     * @return Row value
     */
    public int row() {
        return this.row;
    }

    /**
     * Set the cursor column value
     *
     * @param column Column value
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Set the cursor row value
     *
     * @param row Row value
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Set the cursor values
     *
     * @param row    Row value
     * @param column Column value
     */
    public void set(int row, int column) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass().isAssignableFrom(this.getClass()) || o.getClass().equals(this.getClass())) {
            Cursor c = (Cursor) o;
            return this.row() == c.row() && this.column() == c.column();
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + row() + ", " + column() + ")";
    }
}