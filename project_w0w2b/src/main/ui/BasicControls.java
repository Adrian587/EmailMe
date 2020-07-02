package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class BasicControls {
//Contains the common button control in all scenes.

    //Switches scene to page using button.
    public void goTo(Button button, String page) {
        try {
            Pane mainPage = (Pane) FXMLLoader.load(getClass().getResource(page + ".fxml"));
            Stage window = (Stage) button.getScene().getWindow();
            window.setScene(new Scene(mainPage));
            window.show();
        } catch (IOException ie) {
            System.out.println("IOException");
        }
    }

}
