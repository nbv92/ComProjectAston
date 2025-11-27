package org.example.sort.test;

import org.example.sort.Bus;
import org.example.sort.utils.BusGenerator;

public class BusGeneratorTest {
    public static void main(String[] args) {
        testGenerateRandomBusNamedModels();
        testGenerateRandomBusNumericModels();
        testGenerateRandomBusArrayNamedModels();
        testGenerateRandomBusArrayNumericModels();
        testInvalidArguments();
        System.out.println("All BusGenerator tests passed!");
    }

    private static void testGenerateRandomBusNamedModels() {
        String[] models = {"Mercedes", "Volvo", "Scania"};
        Bus bus = BusGenerator.generateRandomBus(1000, 2000, models, 5000, 10000);

        assert bus.getNumber() >= 1000 && bus.getNumber() <= 2000 : "Number out of range";
        assert bus.getMileage() >= 5000 && bus.getMileage() <= 10000 : "Mileage out of range";

        boolean validModel = false;
        for (String m : models) if (bus.getModel().equals(m)) validModel = true;
        assert validModel : "Model is not in the provided array";
    }

    private static void testGenerateRandomBusNumericModels() {
        Bus bus = BusGenerator.generateRandomBus(1000, 2000, 1, 3, 5000, 10000);

        assert bus.getNumber() >= 1000 && bus.getNumber() <= 2000 : "Number out of range";
        assert bus.getMileage() >= 5000 && bus.getMileage() <= 10000 : "Mileage out of range";
        assert bus.getModel().startsWith("Model_") : "Numeric model format invalid";
    }

    private static void testGenerateRandomBusArrayNamedModels() {
        String[] models = {"A", "B"};
        Bus[] buses = BusGenerator.generateRandomBusArray(5, 1, 10, models, 0, 100);
        assert buses.length == 5 : "Array size mismatch";
        for (Bus bus : buses) {
            assert bus.getNumber() >= 1 && bus.getNumber() <= 10;
        }
    }

    private static void testGenerateRandomBusArrayNumericModels() {
        Bus[] buses = BusGenerator.generateRandomBusArray(5, 1, 10, 1, 3, 0, 100);
        assert buses.length == 5 : "Array size mismatch";
        for (Bus bus : buses) {
            assert bus.getNumber() >= 1 && bus.getNumber() <= 10;
            assert bus.getModel().startsWith("Model_");
        }
    }

    private static void testInvalidArguments() {
        boolean thrown;

        // min > max for number
        thrown = false;
        try { BusGenerator.generateRandomBus(10, 5, new String[]{"A"}, 0, 10); }
        catch (IllegalArgumentException e) { thrown = true; }
        assert thrown : "Invalid number range should throw";

        // min > max for mileage
        thrown = false;
        try { BusGenerator.generateRandomBus(0, 10, new String[]{"A"}, 10, 5); }
        catch (IllegalArgumentException e) { thrown = true; }
        assert thrown : "Invalid mileage range should throw";

        // empty models array
        thrown = false;
        try { BusGenerator.generateRandomBus(0, 10, new String[]{}, 0, 10); }
        catch (IllegalArgumentException e) { thrown = true; }
        assert thrown : "Empty models array should throw";

        // negative size for array
        thrown = false;
        try { BusGenerator.generateRandomBusArray(-1, 0, 10, new String[]{"A"}, 0, 10); }
        catch (IllegalArgumentException e) { thrown = true; }
        assert thrown : "Negative array size should throw";
    }
}
