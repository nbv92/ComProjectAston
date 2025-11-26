package org.example.sort.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.sort.Class.*;
import org.example.sort.CustomList;
import org.example.sort.sort.comparator.*;
import org.example.sort.sort.strategy.BubbleSortStrategy;
import org.example.sort.sort.strategy.EvenNumberSortStrategy;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class PanelController {
    @FXML private Label ButtonTextOne;
    @FXML private Label ButtonTextTwo;
    @FXML private Label ButtonTextThree;

    @FXML private TableView<Bus> busTable;
    private CustomList<Bus> busList = new CustomList<>();

    @FXML
    protected void onOneButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл с автобусами");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));

        File file = fileChooser.showOpenDialog(ButtonTextOne.getScene().getWindow());
        if (file == null) return;

        try {
            CustomList<Bus> loaded = FileUtils.readBusesFromFile(file.getAbsolutePath());

            if (loaded.size() == 0) {
                showError("Файл пустой или в нём нет корректных автобусов.");
                return;
            }

            busList = loaded;

            showBusTable();

        } catch (Exception e) {
            showError("Ошибка чтения файла: " + e.getMessage());
        }
    }

    @FXML
    protected void onTwoButtonClick() {
        Stage inputStage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label numberLabel = new Label("Номер:");
        TextField numberField = new TextField();
        Label modelLabel = new Label("Модель:");
        TextField modelField = new TextField();
        Label mileageLabel = new Label("Пробег:");
        TextField mileageField = new TextField();

        Button addButton = new Button("Добавить автобус");
        vbox.getChildren().addAll(numberLabel, numberField, modelLabel, modelField, mileageLabel, mileageField, addButton);
        inputStage.setScene(new Scene(vbox, 300, 300));
        inputStage.setTitle("Добавление автобусов вручную");
        inputStage.show();

        addButton.setOnAction(e -> {
            try {
                Bus bus = Bus.builder()
                        .setNumber(Integer.parseInt(numberField.getText()))
                        .setModel(modelField.getText().trim())
                        .setMileage(Integer.parseInt(mileageField.getText()))
                        .build();

                BusValidator.validateBus(bus);

                Stream.of(bus).forEach(busList::add);

                if (busTable != null) {
                    busTable.setItems(FXCollections.observableArrayList(listToArray()));
                }

                numberField.clear();
                modelField.clear();
                mileageField.clear();
            } catch (NumberFormatException ex) {
                showError("Номер и пробег должны быть числами.");
            } catch (Exception ex) {
                showError("Ошибка: " + ex.getMessage());
            }
        });

        showBusTable();
    }


    @FXML
    protected void onThreeButtonClick() {
        Stage inputStage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label label = new Label("Введите количество автобусов:");
        TextField inputField = new TextField();
        Button okButton = new Button("OK");
        vbox.getChildren().addAll(label, inputField, okButton);

        inputStage.setScene(new Scene(vbox, 300, 200));
        inputStage.setTitle("Random Data Input");

        okButton.setOnAction(event -> {
            try {
                int count = Integer.parseInt(inputField.getText());
                if (count <= 0) throw new NumberFormatException();

                // Стрим для заполнения кастомного списка
                busList = new CustomList<>();
                Bus[] buses = BusGenerator.generateRandomBusArray(count, 1000, 9999, 1, 3, 0, 500_000);
                Arrays.stream(buses).forEach(busList::add);

                showBusTable();
                inputStage.close();
            } catch (NumberFormatException e) {
                showError("Введите корректное положительное число.");
            }
        });

        inputStage.show();
    }

    private void showInfoStage(String text) {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().add(new Label("You are now in the " + text));
        stage.setScene(new Scene(root, 820, 640));
        stage.setTitle(text);
        stage.show();

        Stage current = (Stage) ButtonTextOne.getScene().getWindow();
        current.close();
    }

    private void showBusTable() {
        busTable = new TableView<>();

        TableColumn<Bus, Integer> colNum = new TableColumn<>("Номер");
        colNum.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Bus, String> colModel = new TableColumn<>("Модель");
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Bus, Integer> colMileage = new TableColumn<>("Пробег");
        colMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        busTable.getColumns().addAll(colNum, colModel, colMileage);
        busTable.setItems(FXCollections.observableArrayList(listToArray()));

        Button sortButton = new Button("Сортировать");
        Button saveButton = new Button("Сохранить в файл");
        Button countButton = new Button("Подсчёт вхождений");
        Button clearButton = new Button("Очистить");

        VBox root = new VBox(10, busTable, sortButton, saveButton, countButton, clearButton);
        root.setPadding(new Insets(10));

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 820, 700));
        stage.setTitle("Список автобусов");
        stage.show();

        sortButton.setOnAction(e -> showSortDialog(busTable));
        saveButton.setOnAction(e -> saveToFile());
        countButton.setOnAction(e -> {
            Bus selectedBus = busTable.getSelectionModel().getSelectedItem();
            if (selectedBus == null) {
                showError("Пожалуйста, выберите автобус в таблице.");
                return;
            }

            new Thread(() -> {
                int result = MultiThreadCounter.countOccurrences(busList, selectedBus, 4);
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Результат подсчёта");
                    alert.setContentText(
                            "Автобус:\n" +
                                    "Номер: " + selectedBus.getNumber() + "\n" +
                                    "Модель: " + selectedBus.getModel() + "\n" +
                                    "Пробег: " + selectedBus.getMileage() + "\n\n" +
                                    "Количество в списке: " + result
                    );
                    alert.showAndWait();
                });
            }).start();
        });

        clearButton.setOnAction(e -> {
            busList.clear();
            busTable.getItems().clear();
        });
    }


    private Bus[] listToArray() {
        Bus[] arr = new Bus[busList.size()];
        for (int i = 0; i < busList.size(); i++) arr[i] = busList.get(i);
        return arr;
    }

    private void showSortDialog(TableView<Bus> table) {
        Stage sortStage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label label = new Label("Выберите поле для сортировки:");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Номер", "Модель", "Пробег", "Все поля", "Чётные номера");
        Button sortBtn = new Button("Сортировать");

        vbox.getChildren().addAll(label, comboBox, sortBtn);
        sortStage.setScene(new Scene(vbox, 300, 200));
        sortStage.setTitle("Сортировка");

        sortBtn.setOnAction(event -> {
            String field = comboBox.getValue();
            if (field == null) return;

            if (field.equals("Чётные номера")) {
                EvenNumberSortStrategy sorter = new EvenNumberSortStrategy();
                sorter.sort(busList, new BusNumberComparator());
            } else {
                BubbleSortStrategy<Bus> sorter = new BubbleSortStrategy<>();
                Comparator<Bus> comparator;

                switch (field) {
                    case "Номер" -> comparator = new BusNumberComparator();
                    case "Модель" -> comparator = new BusModelComparator();
                    case "Пробег" -> comparator = new BusMileageComparator();
                    case "Все поля" -> comparator = new BusFullComparator();
                    default -> throw new IllegalStateException("Unexpected value: " + field);
                }

                sorter.sort(busList, comparator);
            }

            busTable.setItems(FXCollections.observableArrayList(listToArray()));
            sortStage.close();
        });

        sortStage.show();
    }


    private void showCountDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Подсчёт вхождений");
        dialog.setHeaderText(null);
        dialog.setContentText("Введите номер автобуса для подсчёта:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int number = Integer.parseInt(input);
                new Thread(() -> {
                    int count = 0;
                    for (int i = 0; i < busList.size(); i++)
                        if (busList.get(i).getNumber() == number) count++;
                    System.out.println("Количество автобусов с номером " + number + ": " + count);
                }).start();
            } catch (NumberFormatException e) {
                showError("Введите корректное число.");
            }
        });
    }

    private void saveToFile() {
        try {
            FileUtils.writeToFile(busList, "buses_output.txt");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Список автобусов успешно сохранен!");
            alert.showAndWait();
        } catch (Exception ex) {
            showError("Ошибка при сохранении файла: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
