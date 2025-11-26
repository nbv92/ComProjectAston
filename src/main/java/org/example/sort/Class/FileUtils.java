package org.example.sort.Class;

import org.example.sort.CustomList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
    public static CustomList<Bus> readBusesFromFile(String filePath) {
        CustomList<Bus> list = new CustomList<>();

        Pattern pattern = Pattern.compile(
                "Bus \\{ number=(\\d+), model='([^']+)', mileage=(\\d+) }"
        );

        try {
            Files.lines(Path.of(filePath)).forEach(line -> {
                Matcher m = pattern.matcher(line.trim());
                if (!m.matches()) {
                    System.err.println("Incorrect line: " + line);
                    return;
                }

                try {
                    int number = Integer.parseInt(m.group(1));
                    String model = m.group(2);
                    int mileage = Integer.parseInt(m.group(3));

                    Bus bus = Bus.builder()
                            .setNumber(number)
                            .setModel(model)
                            .setMileage(mileage)
                            .build();

                    BusValidator.validateBus(bus);
                    list.add(bus);

                } catch (Exception e) {
                    System.err.println("Error processing string: " + line + " " + e.getMessage());
                }
            });
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
