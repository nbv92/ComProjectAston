package org.example.sort.test;

import org.example.sort.Bus;

public class BusTest {
    public static void main(String[] args) {
        testBuilderAndGetters();
        testEqualsAndHashCode();
        testInvalidValues();
        System.out.println("All Bus tests passed!");
    }

    private static void testBuilderAndGetters() {
        Bus bus = Bus.builder()
                .setNumber(123)
                .setModel("Model")
                .setMileage(5000)
                .build();

        assert bus.getNumber() == 123 : "Number getter failed";
        assert bus.getModel().equals("Model") : "Model getter failed";
        assert bus.getMileage() == 5000 : "Mileage getter failed";

        String str = bus.toString();
        assert str.contains("123") && str.contains("Model") && str.contains("5000") : "toString failed";
    }

    private static void testEqualsAndHashCode() {
        Bus bus1 = Bus.builder().setNumber(1).setModel("A").setMileage(100).build();
        Bus bus2 = Bus.builder().setNumber(1).setModel("A").setMileage(100).build();
        Bus bus3 = Bus.builder().setNumber(2).setModel("B").setMileage(200).build();

        assert bus1.equals(bus2) : "Equals failed for equal buses";
        assert !bus1.equals(bus3) : "Equals failed for different buses";
        assert bus1.hashCode() == bus2.hashCode() : "HashCode failed for equal buses";
    }

    private static void testInvalidValues() {
        boolean thrown;

        // Number < 0
        thrown = false;
        try {
            Bus.builder().setNumber(-1).setModel("X").setMileage(10).build();
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Negative number should throw exception";

        // Mileage < 0
        thrown = false;
        try {
            Bus.builder().setNumber(1).setModel("X").setMileage(-5).build();
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert thrown : "Negative mileage should throw exception";

        // Null fields
        thrown = false;
        try {
            Bus.builder().setNumber(1).setModel(null).setMileage(5).build();
        } catch (IllegalStateException e) {
            thrown = true;
        }
        assert thrown : "Null model should throw exception";
    }
}
