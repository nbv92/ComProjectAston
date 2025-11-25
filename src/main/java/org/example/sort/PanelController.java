package org.example.sort;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PanelController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}