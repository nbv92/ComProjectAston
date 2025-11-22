package org.example.sort.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;




import java.awt.*;

import org.example.sort.Class.*;

/*Этот контроллер отвечает за кнопки в графическом приложении*/
public class PanelController {
    @FXML
    private Label ButtonTextOne;

    @FXML
    private Label ButtonTextTwo;

    @FXML
    private Label ButtonTextThree;


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
        // Создаем новое окно
        Stage secondStage = new Stage();

        // Создаем VBox для размещения элементов
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        // Создаем Label и TextField для ввода числа
        Label inputLabel = new Label("Введите количество автобусов:");
        TextField inputField = new TextField();
        Button okButton = new Button("OK");

        // Добавляем элементы в VBox
        vbox.getChildren().addAll(inputLabel, inputField, okButton);

        // Создаем сцену и устанавливаем ее на окно
        Scene secondScene = new Scene(vbox, 300, 200);
        secondStage.setScene(secondScene);
        secondStage.setTitle("Random Data Input");

        // Обработчик нажатия кнопки OK
        okButton.setOnAction(event -> {
            int numberOfBuses;

            try {
                numberOfBuses = Integer.parseInt(inputField.getText());

                // Определяем диапазоны для генерации автобусов
                int minNumber = 1000; // Минимальный номер автобуса
                int maxNumber = 9999; // Максимальный номер автобуса
                int minModel = 1; // Минимальный номер модели
                int maxModel = 100; // Максимальный номер модели (предполагаем 3 модели)
                int minMileage = 0; // Минимальный пробег
                int maxMileage = 500000; // Максимальный пробег

                List<Bus> buses = new ArrayList<>();
                for (int i = 0; i < numberOfBuses; i++) {
                    buses.add(BusGenerator.generateRandomBus(minNumber, maxNumber, minModel, maxModel, minMileage, maxMileage));
                }

                // Создаем новое окно для отображения списка автобусов
                StackPane root3 = new StackPane();
                VBox busListVBox = new VBox(10);
                for (Bus bus : buses) {
                    busListVBox.getChildren().add(new Label(bus.toString()));
                }
                root3.getChildren().add(busListVBox);

                Scene busListScene = new Scene(root2, 820, 640);
                Stage busListStage = new Stage();
                busListStage.setScene(busListScene);
                busListStage.setTitle("Список автобусов");
                busListStage.show();

                // Закрываем текущее окно
                secondStage.close();
            } catch (NumberFormatException e) {
                // Обработка ошибки ввода (нечисловое значение)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка ввода");
                alert.setHeaderText(null);
                alert.setContentText("Пожалуйста, введите корректное число.");
                alert.showAndWait();
            }
        });

        secondStage.show();
    }


}