package model;

public class NoSuchReminderException extends Exception {
// thrown if access to a reminder that does not exist is attempted
    public NoSuchReminderException(String msg) {
        super(msg);
    }
}
