package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application  {

    //runs program
    public static void main(String[] args) {
        State.init();
        launch(args);
    }

    //starts the first scene.
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DefaultPage.fxml"));
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Email Screen");
        stage.setScene(scene);
        stage.show();
    }




}
