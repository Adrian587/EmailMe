package persistence;

import model.ReminderList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Loader {
    // loading class

    public Loader() {

    }

    // loads data from user file
    public static ReminderList loadFile(String file) throws IOException, ClassNotFoundException {

        ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
        ReminderList loadedReminders = (ReminderList) input.readObject();
        input.close();
        return loadedReminders;

    }

}
