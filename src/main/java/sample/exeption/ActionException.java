package sample.exeption;

public class ActionException extends Exception {
    public ActionException(String message) {
        super("Illegal action " + message);
    }

    public ActionException() {
        super("Illegal action ");
    }
}