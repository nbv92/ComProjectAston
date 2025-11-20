package org.example.sort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Sort extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("conf-panel.fxml"));
        primaryStage.setTitle("Sorting");
        primaryStage.setScene(new Scene(root,820,640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}