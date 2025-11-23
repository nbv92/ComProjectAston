package org.example.sort.Class;

import java.util.Random;

public class BusGenerator {
    private static final Random random = new Random();

    /**
     * Generates a random {@link Bus} using a predefined array of model names.
     *
     * @param minNumber  inclusive lower bound for the bus number
     * @param maxNumber  inclusive upper bound for the bus number
     * @param models     array of possible model names (one will be selected randomly)
     * @param minMileage inclusive lower bound for the mileage
     * @param maxMileage inclusive upper bound for the mileage
     * @return a randomly generated {@link Bus} instance
     */
    public static Bus generateRandomBus(int minNumber, int maxNumber, String[] models, int minMileage, int maxMileage) {
        validateRange(minNumber, maxNumber);
        validateRange(minMileage, maxMileage);

        if (models == null || models.length == 0) {
            throw new IllegalArgumentException("Models array must not be null or empty");
        }

        return Bus.builder()
                .setNumber(random.nextInt(minNumber, maxNumber + 1))
                .setModel(models[random.nextInt(models.length)])
                .setMileage(random.nextInt(minMileage, maxMileage + 1))
                .build();
    }

    /**
     * Generates a random {@link Bus} using numeric model identifiers in the format:
     * <pre>Model_&lt;number&gt;</pre>
     *
     * @param minNumber  inclusive lower bound for the bus number
     * @param maxNumber  inclusive upper bound for the bus number
     * @param minModel   inclusive lower bound for the model number
     * @param maxModel   inclusive upper bound for the model number
     * @param minMileage inclusive lower bound for the mileage
     * @param maxMileage inclusive upper bound for the mileage
     * @return a randomly generated {@link Bus} instance
     */
    public static Bus generateRandomBus(int minNumber, int maxNumber, int minModel, int maxModel, int minMileage, int maxMileage) {
        validateRange(minNumber, maxNumber);
        validateRange(minModel, maxModel);
        validateRange(minMileage, maxMileage);

        return Bus.builder()
                .setNumber(random.nextInt(minNumber, maxNumber + 1))
                .setModel("Model_" + random.nextInt(minModel, maxModel + 1))
                .setMileage(random.nextInt(minMileage, maxMileage + 1))
                .build();
    }

    /**
     * Generates an array of random buses using predefined model names.
     *
     * @param size       number of buses to generate
     * @param minNumber  inclusive lower bound for the bus number
     * @param maxNumber  inclusive upper bound for the bus number
     * @param models     array of possible model names
     * @param minMileage inclusive lower bound for the mileage
     * @param maxMileage inclusive upper bound for the mileage
     * @return an array of randomly generated {@link Bus} class objects
     */
    public static Bus[] generateRandomBusArray(int size, int minNumber, int maxNumber, String[] models, int minMileage, int maxMileage) {
        if (size < 0) throw new IllegalArgumentException("Array size cannot be negative");

        Bus[] result = new Bus[size];
        for (int i = 0; i < size; i++) {
            result[i] = generateRandomBus(minNumber, maxNumber, models, minMileage, maxMileage);
        }
        return result;
    }

    /**
     * Generates an array of random buses using numeric model identifiers.
     *
     * @param size       number of buses to generate
     * @param minNumber  inclusive lower bound for the bus number
     * @param maxNumber  inclusive upper bound for the bus number
     * @param minModel   inclusive lower bound for the model number
     * @param maxModel   inclusive upper bound for the model number
     * @param minMileage inclusive lower bound for the mileage
     * @param maxMileage inclusive upper bound for the mileage
     * @return an array of randomly generated {@link Bus} class objects
     */
    public static Bus[] generateRandomBusArray(int size, int minNumber, int maxNumber, int minModel, int maxModel, int minMileage, int maxMileage) {
        if (size < 0) throw new IllegalArgumentException("Array size cannot be negative");

        Bus[] result = new Bus[size];
        for (int i = 0; i < size; i++) {
            result[i] = generateRandomBus(minNumber, maxNumber, minModel, maxModel, minMileage, maxMileage);
        }
        return result;
    }

    private static void validateRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value");
        }
    }
}
