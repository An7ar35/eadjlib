package eadjlib.datastructure;

/**
 * Generic interface for printing contents of data-structures
 */
public interface IPrintFunction {
    /**
     * Caller function for the printing
     * @param objects Argument used for passing items to print from the data-structure
     */
    public void call( Object... objects );
}
