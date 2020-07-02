package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReminderListTest {
    public ReminderList reminderList;
    public Reminder r1;
    public Reminder r2;
    public Reminder r3;
    public Reminder r4;
    public Reminder r5;

    @BeforeEach
    void runBefore() {
        reminderList = new ReminderList();
        r1 = new Reminder("Food" , "Eat food", "adrian.lee@smus.ca", 5);
        r2 = new Reminder("Drink" , "Drink food", "adrian.lee@smus.ca", 5);
        r3 = new Reminder("Drink water" , "Drink water", "adrian.lee@smus.ca", 5);
        r4 = new Reminder("cat" , "Feed cat", "adrian.lee@smus.ca", 5);
        r5 = new Reminder("Dance lesson" , "Pick up anindya from dance lesson", "adrian.lee@smus.ca", 5);

    }
    @Test
    void testConstructor() {
        assertEquals(reminderList.getSize(), 0);
    }

    @Test
    void testRemove() {
        reminderList.add(r1);
        reminderList.add(r2);
        reminderList.add(r3);
        reminderList.add(r4);
        reminderList.add(r5);
        assertEquals(reminderList.getSize(), 5);
        reminderList.removeReminderAtIndex(0);
        reminderList.removeReminderAtIndex(3);
        assertEquals(reminderList.getSize(), 3);
       ;

    }

    @Test
    void testGetReminderToString() {
        reminderList.add(r1);
        reminderList.add(r2);
        reminderList.add(r3);
        String temp = reminderList.getReminderString(2);
        assertEquals(r2.toString(), temp);
        Reminder r0 = reminderList.getReminder(3);
        assertEquals(r0.getLabel(), "Drink water");
    }

    @Test
    void testDeleteAll() {
        reminderList.add(r1);
        reminderList.add(r2);
        reminderList.add(r3);
        reminderList.add(r4);
        reminderList.add(r5);
        reminderList.deleteAll();
        assertEquals(0, reminderList.getSize());

    }

    @Test
    void testChangeLabel() {
        r1.changeLabel("drink water");
        assertEquals("drink water", r1.getLabel());
    }

    @Test
    void testLastEmail() {
        reminderList.add(r1);
        assertEquals(reminderList.getLastEmail(), "adrian.lee@smus.ca");
    }

    @Test
    void testGetReminders() {
        reminderList.add(r1);
        reminderList.add(r2);
        reminderList.add(r3);
        ArrayList<Reminder> test = reminderList.returnReminders();
        assertEquals(3, test.size());
    }

    @Test
    void testRemoveReminder() {
        reminderList.add(r1);
        try {
            reminderList.removeReminder(r1);
        } catch (NoSuchReminderException e) {
            fail();
        }
        try {
            reminderList.removeReminder(r3);
            fail();
        } catch (NoSuchReminderException e) {
            e.printStackTrace();
        }
    }
}
