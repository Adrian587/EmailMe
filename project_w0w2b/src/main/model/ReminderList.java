package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ReminderList implements Serializable {
    //ReminderList class, implementation of reminder list
    public ArrayList<Reminder> list;


    public ReminderList() {
        list = new ArrayList<Reminder>();
    }
    //modifies: this
    //adds a reminder to the reminderlist.

    public void add(Reminder r) {
        list.add(r);
    }
    //modifies: this
    //removes a reminder from the reminderlist

    public void removeReminder(Reminder r) throws NoSuchReminderException {
        if (list.contains(r)) {
            list.remove(r);
        } else {
            throw new NoSuchReminderException("Reminder Not Found");
        }

    }

    // returns the list of reminders
    public ArrayList<Reminder> returnReminders() {
        return list;
    }

    //MODIFIES: this
    //removes reminder at index k
    public void removeReminderAtIndex(int k) {
        list.remove(k);
    }



    // gets reminder at index i - 1
    public Reminder getReminder(int i) {
        return list.get(i - 1);
    }

    //returns a reminder message from the reminderlist at given index.
    public String getReminderString(int i) {
        return list.get(i - 1).toString();
    }

    //returns size of reminderlist.
    public int getSize() {
        return list.size();
    }

    //modifies: this
    //deletes all reminders from this list.
    public void deleteAll() {
        list = new ArrayList<Reminder>();
    }

    //returns the last email in the list.
    public String getLastEmail() {
        return list.get(list.size() - 1).getEmail();
    }











}
