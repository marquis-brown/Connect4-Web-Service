package edu.citytech.cst.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Connect4Main extends Application {

    public static void main(String[] args) {
        Application.launch(edu.citytech.cst.View.Connect4Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Connect4View.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Connect 4, Author: Marquis Brown");
        stage.setScene(scene);
        stage.show();
    }
}
