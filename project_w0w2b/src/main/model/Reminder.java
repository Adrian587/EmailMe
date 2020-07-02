package model;

import java.io.Serializable;

public class Reminder implements Serializable {
    //Reminder class, implementation of a reminder
    private String label;
    private String message;
    private String email;
    private int due; // in hours from now

    public Reminder(String lab, String mes, String mail, int date) {
        this.label = lab;
        this.message = mes;
        this.email = mail;
        this.due = date; //number of days from now
    }
    //MODIFIES: this
    //EFFECTS: changes the due date of a reminder

    public void changeDue(int newDue) {
        if (newDue <= 0) {
            return;
        }
        this.due = newDue;
    }
    //MODIFIES: this
    //EFFECTS: changes the message of this reminder

    public void changeMessage(String newMessage) {
        this.message = newMessage;
    }
    //MODIFIES: this
    //EFFECTS: changes the email recipient of this reminder

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public void changeLabel(String newLabel) {
        this.label = newLabel;
    }

    public  int getDue() {
        return this.due;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMessage() {
        return this.message;
    }

    public String getLabel() {
        return this.label;
    }

    //EFFECTS: turns a reminder into a readable string.

    public String toString() {
        return "[" + label + ", " + message + ", Email: " + email + ", "
                + "Due in : " + due + " hours" + "]";
    }




}
