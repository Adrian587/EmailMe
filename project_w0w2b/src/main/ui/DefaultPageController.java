package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;

public class DefaultPageController extends BasicControls {
//Contains the controls for the main screen. Used code from https://www.youtube.com/watch?v=zvgWgpGZVKc&t=4s

    protected ArrayList<TextField> textFields = new ArrayList<>();


    @FXML
    private TextField recipientEmail;

    @FXML
    private TextField senderAddress;

    @FXML
    private Button sendButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button viewMessagesButton;

    @FXML
    private Button musicButton;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField messageOne;

    @FXML
    private TextField messageTwo;

    @FXML
    private TextField messageThree;

    @FXML
    private TextField messageFour;

    @FXML
    private TextField messageFive;

    @FXML
    private TextField messageSix;

    @FXML
    private TextField messageSeven;

    @FXML
    private TextField messageEight;

    @FXML
    private TextField messageNine;

    @FXML
    private TextField messageTen;


    //sends email to recipient (not yet) and adds it to message screen.
    @FXML
    void sendMessage(ActionEvent event) {
        createMessage();
        displayOnMessageBoard();

    }

    //goes to the message screen.
    @FXML
    void viewMessages() {
        goTo(viewMessagesButton, "MessageScreen");
    }

    //save all email messages to users file.
    @FXML
    void saveMessages() {
        State.getState().saveReminders();
    }

    //loads all email messages to message screen.
    @FXML
    void loadMessages() {
        try {
            State.getState().setReminders();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //plays water sound.
    @FXML
    void playMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("water.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

    //takes in users info text to create email message.
    private void createMessage() {
        initializeDefaultPage();
        String sender = senderAddress.getText();
        String messageText = messageArea.getText();
        String email = recipientEmail.getText();
        State.getState().addReminder(sender, messageText, email, 5);
    }

    //displays the contact history of email recipients user has sent to.
    private void displayOnMessageBoard() {
        for (TextField t : textFields) {
            if (t.getText().equals("")) {
                t.setText(State.getState().getReminders().getLastEmail());
                return;
            }
        }
    }

    //initializes the text fields for contact history.
    private void initializeDefaultPage() {
        if (textFields.isEmpty()) {
            textFields.add(messageOne);
            textFields.add(messageTwo);
            textFields.add(messageThree);
            textFields.add(messageFour);
            textFields.add(messageFive);
            textFields.add(messageSix);
            textFields.add(messageSeven);
            textFields.add(messageEight);
            textFields.add(messageNine);
            textFields.add(messageTen);
        }
    }


}

