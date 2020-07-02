package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReminderTest {
    public Reminder r;

    @BeforeEach
    void runBefore() {
        r = new Reminder("Water", "Drink water", "adrian.lee@ubc.ca", 1);
    }

    @Test
    void testReminderConstructor() {
        Reminder m1 = new Reminder("Groceries", "Pick up groceries", "adrian.lee@smus.ca", 5);
        assertEquals(m1.getDue(), 5);
        assertEquals(m1.getEmail(), "adrian.lee@smus.ca");
        assertEquals(m1.getMessage(), "Pick up groceries");
    }

    @Test
    void testEditDue() {
        r.changeDue(10);
        assertEquals(r.getDue(), 10);
        r.changeDue(-1);
        assertEquals(r.getDue(), 10);
    }

    @Test
    void testEditMessage() {
        r.changeMessage("Cook Food");
        assertEquals(r.getMessage(), "Cook Food");
        r.changeMessage("Boil Water");
        assertEquals(r.getMessage(), "Boil Water");
    }

    @Test
    void returnLabel() {
        assertEquals(r.getLabel(), "Water");
    }
    @Test
    void testEditEmail() {
        r.changeEmail("liang.liu@ubc.ca");
        assertEquals(r.getEmail(), "liang.liu@ubc.ca");
        r.changeEmail("");
        assertEquals(r.getEmail(), "");
    }
}