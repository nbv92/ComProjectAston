package org.example.sort;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label ButtonTextOne;

    @FXML
    private Label ButtonTextTwo;

    @FXML
    private Label ButtonTextThree;

    @FXML
    protected void onOneButtonClick() {
        ButtonTextOne.setText("Ввод из файла");
    }

    @FXML
    protected void onTwoButtonClick() {
        ButtonTextTwo.setText("Ввод из клавиатуры");
    }

    @FXML
    protected void onThreeButtonClick() { ButtonTextThree.setText("Рандомные данные");}
}