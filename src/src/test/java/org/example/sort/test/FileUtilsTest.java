package org.example.sort.test;

import org.example.sort.Bus;
import org.example.sort.CustomList;
import org.example.sort.utils.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtilsTest {
    public static void main(String[] args) throws Exception {
        testWriteAndRead();
        System.out.println("All FileUtils tests passed!");
    }

    private static void testWriteAndRead() throws Exception {
        CustomList<Bus> buses = new CustomList<>();
        buses.add(Bus.builder().setNumber(101).setModel("Volvo").setMileage(5000).build());
        buses.add(Bus.builder().setNumber(102).setModel("Mercedes").setMileage(3000).build());

        String testFile = "test_buses.txt";

        // Delete the file if it exists
        File f = new File(testFile);
        if (f.exists()) f.delete();

        // Writing
        FileUtils.writeToFile(buses, testFile);

        // We check that the file has been created and the lines are there.
        if (!Files.exists(Path.of(testFile))) throw new AssertionError("File was not created");
        long lineCount = Files.lines(Path.of(testFile)).count();
        if (lineCount != buses.size()) throw new AssertionError("File line count mismatch");

        // Add another bus, check append mode
        buses.add(Bus.builder().setNumber(103).setModel("Scania").setMileage(7000).build());
        FileUtils.writeToFile(new CustomList<Bus>() {{ add(buses.get(2)); }}, testFile);

        lineCount = Files.lines(Path.of(testFile)).count();
        if (lineCount != 3) throw new AssertionError("Append failed");

        // Reading back
        CustomList<Bus> readBuses = FileUtils.readBusesFromFile(testFile);
        if (readBuses.size() != 3) throw new AssertionError("Read size mismatch");

        assert readBuses.get(0).equals(buses.get(0));
        assert readBuses.get(1).equals(buses.get(1));
        assert readBuses.get(2).equals(buses.get(2));

        // We remove the file after the test
        f.delete();
    }
}
