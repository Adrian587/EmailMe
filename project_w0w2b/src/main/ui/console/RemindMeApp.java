
package ui.console;

import model.Reminder;
import model.ReminderList;
import persistence.Loader;
import persistence.Saver;

import java.io.IOException;
import java.util.Scanner;
//RemindMeApp application
//Uses code from TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class RemindMeApp {
    private static final String USER_FILE = "./data/account.txt";
    private Scanner input;
    private ReminderList reminders;

    public RemindMeApp() {
        runApp();
        input = new Scanner(System.in);
        Reminder test = new Reminder("test", "test reminder", "adrian", 5);
        reminders.add(test);
    }


    //prints labels of reminder list
    private void printLabels() {
        int labelNum = 1;
        for (Reminder r : reminders.list) {
            System.out.println(labelNum + ": " + r.getLabel());
            labelNum++;
        }
    }

    //EFFECTS: runs the RemindMeApp
    private void runApp() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        reminders = new ReminderList();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                Scanner input = new Scanner(System.in);
                System.out.println("Do you want to save your reminders? (y/n)");
                String checkAutoSave = input.nextLine();
                if (checkAutoSave.equals("y")) {
                    saveReminders();
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nRemindMeApp:");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add reminder");
        System.out.println("\te -> edit a reminder");
        System.out.println("\td -> delete reminder");
        System.out.println("\tv -> view reminders");
        System.out.println("\tp -> delete all reminders");
        System.out.println("\ts -> save your reminders");
        System.out.println("\tl -> load your reminders");
        System.out.println("\tq -> quit");
    }
    // MODIFIES: this
    // EFFECTS: processes user command

    private void processCommand(String command) {
        input = new Scanner(System.in);
        if (command.equals("a")) {
            addReminder();
        } else if (command.equals("e")) {
            editAReminder();
        } else if (command.equals("d")) {
            deleteAReminder();
        } else if (command.equals("v")) {
            viewReminders();
        } else if (command.equals("p")) {
            System.out.println("Are you sure? (y/n)");
            String checkSure = input.nextLine();
            if (checkSure.equals("y")) {
                reminders.deleteAll();
                System.out.println("All reminders deleted!");
            }
        }  else if (command.equals("s")) {
            saveReminders();
        }  else if (command.equals("l")) {
            loadReminders();
        }  else {
            System.out.println("Selection not valid...");
        }
    }
    //REQUIRES: input parameter for dueInput must be an integer
    // MODIFIES: this
    // EFFECTS: adds a custom user reminder

    private void addReminder() {
        input = new Scanner(System.in);
        System.out.println("What is the name of this reminder?: ");
        String labelInput = input.nextLine();
        System.out.println("What is the reminder message?: ");
        String mesInput = input.nextLine();
        System.out.println("What is your email?: ");
        String emailInput = input.nextLine();
        System.out.println("How long until this reminder is due?: (hours) ");
        int dueInput = input.nextInt();
        Reminder temp = new Reminder(labelInput, mesInput, emailInput, dueInput);
        reminders.list.add(temp);

    }
    //REQUIRES: at least one reminder in reminder list.
    // MODIFIES: this
    // EFFECTS: edits a reminder from reminder list

    private void editAReminder() {
        printLabels();
        System.out.println("Which reminder would you like to edit? (number)");
        int labelIndex = input.nextInt();
        if (reminders.list.size() == 0) {
            return;
        }
        selectConditions(labelIndex);
        System.out.println("Reminder edited!");
    }
    //REQUIRES: at least one reminder in reminder list.
    // MODIFIES: this
    // EFFECTS: deletes a reminder from reminder list

    private void deleteAReminder() {
        input = new Scanner(System.in);
        printLabels();
        System.out.println("Which reminder would you like to delete? (number)");
        int labelIndex = input.nextInt();
        if (reminders.list.size() == 0) {
            return;
        }
        reminders.removeReminderAtIndex(labelIndex - 1);
        System.out.println("Reminder removed!");
    }

    //EFFECTS: prints reminders

    public void viewReminders() {
        returnList();
    }
    //REQUIRES: at least one reminder in reminderlist.
    // MODIFIES: this
    // EFFECTS: selects user preferences for editing a reminder.

    private void selectConditions(int labIndex) {
        input = new Scanner(System.in);
        System.out.println("Do you want to edit the label? (y/n)");
        String editLabel = input.nextLine();
        if (editLabel.equals("y")) {
            customizeLabel(labIndex);

        }
        System.out.println("Do you want to edit the message? (y/n)");
        String editMessage = input.nextLine();

        if (editMessage.equals("y")) {
            customizeMessage(labIndex);

        }

        System.out.println("Do you want to edit the email recipient? (y/n)");
        String editEmail = input.nextLine();
        if (editEmail.equals("y")) {
            customizeEmail(labIndex);

        }
        System.out.println("Do you want to edit the duration? (y/n)");
        String editDuration = input.nextLine();
        if (editDuration.equals("y")) {
            customizeDuration(labIndex);
        }





    }

    // MODIFIES: this
    // EFFECTS: changes label of a reminder

    private void customizeLabel(int i) {
        System.out.println("What is the new label name instead of: "
                + reminders.getReminder(i).getLabel());
        String newLabel = input.nextLine();
        reminders.getReminder(i).changeLabel(newLabel);
        System.out.println("Label changed!");
    }

    // MODIFIES: this
    // EFFECTS: changes message of a reminder
    private void customizeMessage(int i) {
        System.out.println("What is the new message instead of: "
                + reminders.getReminder(i).getMessage());
        String newMessage = input.nextLine();
        reminders.getReminder(i).changeMessage(newMessage);
        System.out.println("Message changed!");
    }
    // MODIFIES: this
    // EFFECTS: changes recipient of a reminder

    private void customizeEmail(int i) {
        System.out.println("What is the new email instead of: "
                + reminders.getReminder(i).getEmail());
        String newEmail = input.nextLine();
        reminders.getReminder(i).changeEmail(newEmail);
        System.out.println("Recipient changed!");
    }
    // MODIFIES: this
    // EFFECTS: changes duration of a reminder

    private void customizeDuration(int i) {
        System.out.println("What is the new duration (number) instead of: "
                + reminders.getReminder(i).getDue());
        int newDuration = input.nextInt();
        reminders.getReminder(i).changeDue(newDuration);
        System.out.println("Duration changed!");
    }

    //EFFECTS: prints out the reminders' labels list.
    public void returnList() {
        for (int i = 0; i < reminders.list.size(); i++) {
            String message = reminders.list.get(i).toString();
            String listNum = Integer.toString(i + 1);
            System.out.println(listNum + ":" + message);
        }
    }

    //EFFECTS: saves the reminder list to the user file.
    public void saveReminders() {
        try {
            Saver.saveFile(reminders, USER_FILE);
            System.out.println("Save Succesful!");
        } catch (IOException ie) {
            System.out.println("File not found!");

        }
    }

     //EFFECTS: loads the user's file.

    public void loadReminders() {
        try {
            ReminderList userReminders = Loader.loadFile(USER_FILE);

            for (int i = 0; i < userReminders.list.size(); i++) {
                String message = userReminders.list.get(i).toString();
                String listNum = Integer.toString(i + 1);
                System.out.println(listNum + ":" + message);
            }

        } catch (IOException | ClassNotFoundException ce) {
            System.out.println("Error!");
        }
    }



}
