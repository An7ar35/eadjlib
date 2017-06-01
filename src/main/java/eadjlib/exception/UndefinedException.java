package eadjlib.exception;

public class UndefinedException extends Exception {
    public UndefinedException() {
        super();
    }

    public UndefinedException(String message) {
        super(message);
    }

    public UndefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UndefinedException(Throwable cause) {
        super(cause);
    }
}
