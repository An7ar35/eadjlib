package eadjlib.datastructure;

/**
 * Generic interface for printing contents of data-structures
 */
public interface IPrintFunction {
    /**
     * Caller function for general printing
     * @param objects Argument used for passing items to print from the data-structure
     */
    public void call( Object... objects );

    /**
     * Caller function for Key-Value pair printing
     * @param key Key
     * @param value Value
     */
    public void call( Object key, Object value );
}
