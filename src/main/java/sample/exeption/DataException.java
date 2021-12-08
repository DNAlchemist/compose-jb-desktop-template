package sample.exeption;

public class DataException extends Exception {
    public DataException(String data) {
        super("Invalid data " + data);
    }

    public DataException() {
        super("Invalid data ");
    }
}
