package org.example.sort.test;

import org.example.sort.Bus;
import org.example.sort.utils.BusValidator;

public class BusValidatorTest {
    public static void main(String[] args) {
        testValidBus();
        testInvalidNumber();
        testInvalidModel();
        testInvalidMileage();
        System.out.println("All BusValidator tests passed!");
    }

    private static void testValidBus() {
        Bus bus = Bus.builder().setNumber(100).setModel("Volvo").setMileage(5000).build();
        BusValidator.validateBus(bus); // не должно выкинуть исключение
    }

    private static void testInvalidNumber() {
        boolean thrown = false;
        try {
            Bus bus = Bus.builder().setNumber(-1).setModel("Volvo").setMileage(5000).build();
            BusValidator.validateBus(bus);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Negative number should throw exception";

        thrown = false;
        try {
            BusValidator.validateNumber(null);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Null number should throw exception";
    }

    private static void testInvalidModel() {
        boolean thrown = false;
        try {
            Bus bus = Bus.builder().setNumber(100).setModel("").setMileage(5000).build();
            BusValidator.validateBus(bus);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Empty model should throw exception";

        thrown = false;
        try {
            BusValidator.validateModel(null);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Null model should throw exception";
    }

    private static void testInvalidMileage() {
        boolean thrown = false;
        try {
            Bus bus = Bus.builder().setNumber(100).setModel("Volvo").setMileage(-5).build();
            BusValidator.validateBus(bus);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Negative mileage should throw exception";

        thrown = false;
        try {
            BusValidator.validateMileage(null);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Null mileage should throw exception";
    }
}
