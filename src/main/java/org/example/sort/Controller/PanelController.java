package org.example.sort.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.sort.Class.Bus;
import org.example.sort.Class.BusGenerator;

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

                Bus[] buses = BusGenerator.generateRandomBusArray(
                        numberOfBuses,
                        minNumber, maxNumber,
                        minModel, maxModel,
                        minMileage, maxMileage
                );

                // Создание таблицы
                TableView<Bus> table = new TableView<>();

                TableColumn<Bus, Integer> colNum = new TableColumn<>("Номер");
                colNum.setCellValueFactory(new PropertyValueFactory<>("number"));

                TableColumn<Bus, Integer> colModel = new TableColumn<>("Модель");
                colModel.setCellValueFactory(new PropertyValueFactory<>("model"));

                TableColumn<Bus, Integer> colMileage = new TableColumn<>("Пробег");
                colMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));

                table.getColumns().addAll(colNum, colModel, colMileage);

                // Добавляем данные
                table.getItems().addAll(buses);

                // Показываем окно
                Stage tableStage = new Stage();
                tableStage.setTitle("Список автобусов");
                tableStage.setScene(new Scene(new StackPane(table), 820, 640));
                tableStage.show();

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