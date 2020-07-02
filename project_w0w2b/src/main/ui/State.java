package ui;

import model.Reminder;
import model.ReminderList;
import persistence.Loader;
import persistence.Saver;

import java.io.IOException;

public class State  {
    //Singleton class to control the state of the application.
    private ReminderList reminders;
    private static final String USER_FILE = "./data/account.txt";
    private static State instance;

    public static void init() {
        instance = new State();
    }

    public static State getState() {
        return instance;
    }

    private State() {
        reminders = new ReminderList();
    }

    //returns the reminders from this instance
    public ReminderList getReminders() {
        return reminders;
    }

    // returns the user's .txt file.
    public String getUserFile() {
        return USER_FILE;
    }

    //sets reminders to user's reminders.
    public void setReminders() throws IOException, ClassNotFoundException {
        ReminderList loadedReminders = null;
        loadedReminders = Loader.loadFile(USER_FILE);

        for (Reminder r: loadedReminders.returnReminders()) {
            reminders.add(r);
        }

    }

    public void saveReminders() {
        try {
            Saver.saveFile(getReminders(), getUserFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReminder(String to, String msg, String from, int dur) {
        reminders.add(new Reminder(to, msg, from, dur));
    }
}

