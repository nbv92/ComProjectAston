package org.example.sort;

//import com.almasb.fxgl.app.PrimaryStageWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import Class.*;
import Controller.BusController;
import Interface.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort extends Application {

    private BusController busController = new BusController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bus Sorting Application");

        Button enterFromFileButton = new Button("Enter from File");
        Button enterFromKeyboardButton = new Button("Enter from Keyboard");
        Button enterRandomDataButton = new Button("Random Data");

        enterFromFileButton.setOnAction(e -> showInputFromFile(primaryStage));
        enterFromKeyboardButton.setOnAction(e -> showInputFields(primaryStage));
        enterRandomDataButton.setOnAction(e -> showRandomDates(primaryStage));

        VBox vbox = new VBox(enterFromFileButton, enterFromKeyboardButton, enterRandomDataButton);
        Scene scene = new Scene(vbox, 820, 640);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showInputFromFile(Stage currentStage){
        StackPane root3 = new StackPane();
        Label label = new Label("You are now in the enter from File");
        root3.getChildren().add(label);
        Scene secondScene = new Scene(root3, 820, 640);

        Stage thirdStage = new Stage();
        thirdStage.setScene(secondScene);
        thirdStage.setTitle("Enter from File");
        thirdStage.show();

        currentStage.hide();

        Button chooseButton = new Button("Choose to the File");
        Button sortButton = new Button("Sort Buses");
        Button backButton = new Button("Назад");

        ComboBox<String> sortingOptions = new ComboBox<>();
        sortingOptions.getItems().addAll("Sort by Number", "Sort by Model", "Sort by Mileage", "SortByMultipleFields", "SortByEvenOddField");



        sortButton.setOnAction(e -> {
            String selectedOption = sortingOptions.getValue();

            // Проверка на выбранный элемент
            if (selectedOption == null) {
                System.out.println("Пожалуйста, выберите опцию сортировки.");
                return;
            }

            switch (selectedOption) {
                case "Sort by Number":
                    busController.setSortingStrategy(new SortByNumber());
                    break;
                case "Sort by Model":
                    busController.setSortingStrategy(new SortByModel());
                    break;
                case "Sort by Mileage":
                    busController.setSortingStrategy(new SortByMileage());
                    break;
                case "SortByMultipleFields":
                    busController.setSortingStrategy(new SortByMultipleFields());
                    break;
                case "SortByEvenOddField":
                    busController.setSortingStrategy(new SortByEvenOddField("mileage"));
                    break;
            }
            busController.sortBuses();
            // Вывод отсортированных автобусов в новом окне
            showSortedBuses(busController.getBuses());
        });
        chooseButton.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Bus Data File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(", ");
                        if (parts.length == 3) {
                            String number = parts[0];
                            String model = parts[1];
                            double mileage = Double.parseDouble(parts[2]);
                            Bus bus = new Bus.BusBuilder()
                                    .setNumber(number)
                                    .setModel(model)
                                    .setMileage((int)mileage)
                                    .build();

                            busController.addBus(bus); // Добавление автобуса в контроллер
                        }
                    }
                    System.out.println("Данные успешно загружены из файла.");
                    // Отображение загруженных автобусов
                    showSortedBuses(busController.getBuses());
                } catch (IOException ex) {
                    System.out.println("Ошибка при загрузке файла: " + ex.getMessage());
                } catch (NumberFormatException ex) {
                    System.out.println("Ошибка формата данных: " + ex.getMessage());
                }
            }
        } );
        backButton.setOnAction(e -> {
            thirdStage.close(); // Закрыть текущее окно
            currentStage.show();// Вернуться к главному окну
        });
        VBox inputVBox = new VBox( chooseButton, sortingOptions, sortButton, backButton);

        // Добавляем поля ввода в StackPane
        root3.getChildren().add(inputVBox);
    };

    private void showInputFields(Stage currentStage) {
        StackPane root2 = new StackPane();
        Label label = new Label("You are now in the enter from keyboard");
        root2.getChildren().add(label);
        Scene secondScene = new Scene(root2, 820, 640);

        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Enter from Keyboard");
        secondStage.show();

        currentStage.hide();

        TextField numberField = new TextField();
        numberField.setPromptText("Enter bus number");

        TextField modelField = new TextField();
        modelField.setPromptText("Enter bus model");

        TextField mileageField = new TextField();
        mileageField.setPromptText("Enter bus mileage");

        Button addButton = new Button("Add Bus");
        Button sortButton = new Button("Sort Buses");
        Button backButton = new Button("Назад");

        ComboBox<String> sortingOptions = new ComboBox<>();
        sortingOptions.getItems().addAll("Sort by Number", "Sort by Model", "Sort by Mileage", "SortByMultipleFields", "SortByEvenOddField");

        addButton.setOnAction(e -> {
            String number = numberField.getText();
            String model = modelField.getText();
            String mileageText = mileageField.getText();

            // Проверка на пустые поля
            if (number.isEmpty() || model.isEmpty() || mileageText.isEmpty()) {
                System.out.println("Все поля должны быть заполнены.");
                return;
            }

            // Проверка корректности ввода пробега
            int mileage;
            try {
                mileage = Integer.parseInt(mileageText);
            } catch (NumberFormatException ex) {
                System.out.println("Пожалуйста, введите корректное число для пробега.");
                return;
            }

            Bus bus = new Bus.BusBuilder().setNumber(number).setModel(model).setMileage(mileage).build();
            busController.addBus(bus);
            numberField.clear();
            modelField.clear();
            mileageField.clear();
        });

        sortButton.setOnAction(e -> {
            String selectedOption = sortingOptions.getValue();

            // Проверка на выбранный элемент
            if (selectedOption == null) {
                System.out.println("Пожалуйста, выберите опцию сортировки.");
                return;
            }

            // Установка стратегии сортировки в зависимости от выбранной опции
            switch (selectedOption) {
                case "Sort by Number":
                    busController.setSortingStrategy(new SortByNumber());
                    break;
                case "Sort by Model":
                    busController.setSortingStrategy(new SortByModel());
                    break;
                case "Sort by Mileage":
                    busController.setSortingStrategy(new SortByMileage());
                    break;
                case "SortByMultipleFields":
                    busController.setSortingStrategy(new SortByMultipleFields());
                    break;
                case "SortByEvenOddField":
                    busController.setSortingStrategy(new SortByEvenOddField("mileage"));
                    break;
                default:
                    System.out.println("Неизвестная опция сортировки.");
                    return;
            }

            // Сортировка автобусов
            busController.sortBuses(); // Передаем список автобусов для сортировки

            // Вывод отсортированных автобусов в новом окне
            showSortedBuses(busController.getBuses());
        });

        backButton.setOnAction(e -> {
            secondStage.close(); // Закрыть текущее окно
            currentStage.show();// Вернуться к главному окну
        });

// Создание VBox для ввода данных
        VBox inputVBox = new VBox(numberField, modelField, mileageField, addButton, sortingOptions, sortButton, backButton);

// Добавляем поля ввода в StackPane
        root2.getChildren().add(inputVBox);

    }

    private void showRandomDates(Stage currentStage){
        StackPane root4 = new StackPane();
        Label label = new Label("You are now in the enter from Random Dates");
        root4.getChildren().add(label);
        Scene fourthScene = new Scene(root4, 820, 640);

        Stage fourtStage = new Stage();
        fourtStage.setScene(fourthScene);
        fourtStage.setTitle("Enter from Keyboard");
        fourtStage.show();

        currentStage.hide();

        TextField randomField = new TextField();
        randomField.setPromptText("Enter the number of random buses");

        Button randomButton = new Button("Make random Buses");
        Button sortButton = new Button("Sort Buses");
        Button backButton = new Button("Назад");

        ComboBox<String> sortingOptions = new ComboBox<>();
        sortingOptions.getItems().addAll("Sort by Number", "Sort by Model", "Sort by Mileage", "SortByMultipleFields", "SortByEvenOddField");

        List<Bus> randomBuses = new ArrayList<>(); // Коллекция для хранения случайных автобусов

        randomButton.setOnAction(e -> {
            String input = randomField.getText();

            // Проверка на пустые поля
            if (input == null || input.isEmpty()) {
                System.out.println("Введите количество автобусов.");
                return;
            }

            try {
                int number = Integer.parseInt(input);
                randomBuses.clear(); // Очищаем предыдущие автобусы
                Bus[] buses = BusGenerator.generateRandomBusArray(number);
                Collections.addAll(randomBuses, buses); // Добавляем новые автобусы в коллекцию

                randomField.clear(); // Очищаем поле ввода
                System.out.println("Случайные автобусы созданы: " + number);
            } catch (NumberFormatException ex) {
                System.out.println("Введите корректное число.");
            }
        });

        sortButton.setOnAction(e -> {
            String selectedOption = sortingOptions.getValue();

            // Проверка на выбранный элемент
            if (selectedOption == null) {
                System.out.println("Пожалуйста, выберите опцию сортировки.");
                return;
            }

            switch (selectedOption) {
                case "Sort by Number":
                    busController.setSortingStrategy(new SortByNumber());
                    break;
                case "Sort by Model":
                    busController.setSortingStrategy(new SortByModel());
                    break;
                case "Sort by Mileage":
                    busController.setSortingStrategy(new SortByMileage());
                    break;
                case "SortByMultipleFields":
                    busController.setSortingStrategy(new SortByMultipleFields());
                    break;
                case "SortByEvenOddField":
                    busController.setSortingStrategy(new SortByEvenOddField("mileage"));
                    break;
            }

            busController.sortBuses(); // Сортируем случайные автобусы
            // Вывод отсортированных автобусов в новом окне
            showSortedBuses(randomBuses);
        });

        backButton.setOnAction(e -> {
            fourtStage.close(); // Закрыть текущее окно
            currentStage.show();// Вернуться к главному окну
        });

        VBox inputVBox = new VBox(randomField, randomButton, sortingOptions, sortButton, backButton);

        // Добавляем поля ввода в StackPane
        root4.getChildren().add(inputVBox);
    };

    private void showSortedBuses(List<Bus> sortedBuses) {
        Stage resultStage = new Stage();
        VBox resultVBox = new VBox();

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false); // Делаем текстовое поле только для чтения
        sortedBuses.forEach(bus ->
                resultArea.appendText(bus.getNumber() + ", " + bus.getModel() + ", " + bus.getMileage() + "\n")
        );

        Button saveButton = new Button("Save to File");
        saveButton.setOnAction(e -> saveToFile(sortedBuses));

        resultVBox.getChildren().addAll(resultArea, saveButton);
        Scene resultScene = new Scene(resultVBox, 400, 300);
        resultStage.setScene(resultScene);
        resultStage.setTitle("Sorted Buses");
        resultStage.show();
    }

    private void saveToFile(List<Bus> sortedBuses) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Bus Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // true для добавления данных
                for (Bus bus : sortedBuses) {
                    writer.write(bus.getNumber() + ", " + bus.getModel() + ", " + bus.getMileage());
                    writer.newLine();
                }
                System.out.println("Данные успешно сохранены в файл.");
            } catch (IOException ex) {
                System.out.println("Ошибка при сохранении файла: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}