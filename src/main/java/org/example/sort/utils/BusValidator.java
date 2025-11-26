package org.example.sort.utils;

import org.example.sort.Bus;

public class BusValidator {
    public static void validateNumber(Integer number) {
        if (number == null || number < 0) {
            throw new IllegalArgumentException("The bus number cannot be less than zero.");
        }
    }

    public static void validateModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("The bus model cannot be empty.");
        }
    }

    public static void validateMileage(Integer mileage) {
        if (mileage == null || mileage < 0) {
            throw new IllegalArgumentException("The bus mileage cannot be less than zero.");
        }
    }

    public static void validateBus(Bus bus) {
        validateNumber(bus.getNumber());
        validateModel(bus.getModel());
        validateMileage(bus.getMileage());
    }
}
