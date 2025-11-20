package org.example.sort;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class PanelController {
    @FXML
    private Label ButtonTextOne;

    @FXML
    private Label ButtonTextTwo;

    @FXML
    private Label ButtonTextThree;

    @FXML
    private Button button;


    @FXML
    protected void onOneButtonClick() {
        StackPane root2 = new StackPane();
        Label label = new Label("You are now in the enter from File");
        root2.getChildren().add(label);
        Scene secondScene = new Scene(root2, 820,640);

        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Enter from File");
        secondStage.show();

        // Закрываем первое окно
        Stage currentStage = (Stage) ButtonTextOne.getScene().getWindow();
        currentStage.close();;
    }

    @FXML
    protected void onTwoButtonClick() {
        StackPane root2 = new StackPane();
        Label label = new Label("You are now in the enter from keyboard");
        root2.getChildren().add(label);
        Scene secondScene = new Scene(root2, 820,640);

        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Enter from Keyboard");
        secondStage.show();

        // Закрываем первое окно
        Stage currentStage = (Stage) ButtonTextTwo.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    protected void onThreeButtonClick() { StackPane root2 = new StackPane();
        Label label = new Label("You are now in the random data");
        root2.getChildren().add(label);
        Scene secondScene = new Scene(root2, 820,640);

        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Random data");
        secondStage.show();

        // Закрываем первое окно
        Stage currentStage = (Stage) ButtonTextThree.getScene().getWindow();
        currentStage.close();}


}