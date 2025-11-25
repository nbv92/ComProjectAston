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
                    .forEach(line -> {
                        String[] parts = line.split(", ");
                        if (parts.length != 3) {
                            System.err.println("Пропущена строка (неверный формат): " + line);
                            return;
                        }
                        try {
                            Bus bus = Bus.builder()
                                    .setNumber(Integer.parseInt(parts[0].trim()))
                                    .setModel(parts[1].trim())
                                    .setMileage(Integer.parseInt(parts[2].trim()))
                                    .build();
                            BusValidator.validateBus(bus);
                            list.add(bus);
                        } catch (Exception e) {
                            System.err.println("Ошибка при чтении строки: " + line + " → " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
