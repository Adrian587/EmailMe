package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Loader;
import persistence.Saver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SaverLoaderTest {
    public ReminderList reminderList;
    public Reminder r1;
    public Reminder r2;
    public Reminder r3;
    public String destination;
    @BeforeEach
    void runBefore() {
        reminderList = new ReminderList();
        r1 = new Reminder("Food" , "Eat food", "adrian.lee@smus.ca", 5);
        r2 = new Reminder("Drink" , "Drink food", "adrian.lee@smus.ca", 5);
        r3 = new Reminder("Drink water" , "Drink water", "adrian.lee@smus.ca", 5);
        destination = "./data/reminders.txt";
    }

    @Test
    void testConstructor() {
        Saver saverTest = new Saver();
        assertEquals(0, saverTest.reminders.getSize());
        Loader loader = new Loader();

    }

    @Test
    void testSaveLoadSingleReminder() {
        reminderList.add(r1);
        try {
            Saver.saveFile(reminderList, destination);
            Reminder firstReminder = Loader.loadFile(destination).getReminder(1);
            assertEquals("Food", firstReminder.getLabel());
            reminderList.add(r2);
            Saver.saveFile(reminderList, destination);
            Reminder secondReminder = Loader.loadFile(destination).getReminder(2);
            assertEquals("Drink", secondReminder.getLabel());
        } catch (ClassNotFoundException | IOException ie) {
            fail();

        }



    }

    @Test
    void testSaveLoadWithException() {
        reminderList.add(r1);
        try {
            Saver.saveFile(reminderList, destination);
        } catch (IOException ie) {
            fail();
        }
        try {
            ReminderList exceptionTest = Loader.loadFile("./data/remind.txt");
            fail();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
