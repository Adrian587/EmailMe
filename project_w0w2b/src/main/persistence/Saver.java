package persistence;

import model.ReminderList;

import java.io.*;
// used code from https://www.youtube.com/watch?v=YzwiuRDgSSY&feature=share&fbclid=IwAR2MRsAyCvZ2J3XOp4w4hdO1sJQk_k-Bg0j68ga4TMbe9anSD0IaxKvG_JQ

public class Saver {
    // saving class

    public ReminderList reminders;

    public Saver() {
        reminders = new ReminderList();
    }

    //saves data to user text file
    public static void saveFile(ReminderList reminders, String destination) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destination));
        out.writeObject(reminders);
        out.close();


    }



}
