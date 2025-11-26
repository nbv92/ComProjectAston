package org.example.sort.Class;

import org.example.sort.CustomList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static CustomList<Bus> readBusesFromFile(String filePath) {
        CustomList<Bus> list = new CustomList<>();

        try {
            Files.lines(Path.of(filePath))
                    .map(String::trim)
                    .filter(line -> line.startsWith("Bus {") && line.endsWith("}"))
                    .map(line -> {
                        try {
                            String content = line.substring(5, line.length() - 1).trim();
                            String[] parts = content.split(", ");
                            int number = Integer.parseInt(parts[0].split("=")[1].trim());
                            String model = parts[1].split("=")[1].replace("'", "").trim();
                            int mileage = Integer.parseInt(parts[2].split("=")[1].trim());

                            Bus bus = Bus.builder()
                                    .setNumber(number)
                                    .setModel(model)
                                    .setMileage(mileage)
                                    .build();
                            BusValidator.validateBus(bus);
                            return bus;
                        } catch (Exception e) {
                            System.err.println("Error processing string: " + line + " " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(b -> b != null)
                    .forEach(list::add);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static <T> void writeToFile(CustomList<T> list, String filePath) {
        try (var writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath, true))) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
