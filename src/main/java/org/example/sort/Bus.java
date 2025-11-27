package org.example.sort;

import java.util.Objects;

public class Bus {
    private final Integer number;
    private final String model;
    private final Integer mileage;

    public Integer getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public Integer getMileage() {
        return mileage;
    }

    private Bus(Integer number, String model, Integer mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public static BusBuilder builder() {
        return new BusBuilder();
    }

    @Override
    public String toString() {
        return "Bus { " +
                "number=" + number +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus bus)) return false;
        return Objects.equals(number, bus.number)
                && Objects.equals(model, bus.model)
                && Objects.equals(mileage, bus.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, mileage);
    }

    public static class BusBuilder {
        private Integer number;
        private String model;
        private Integer mileage;

        private BusBuilder() {}

        public BusBuilder setNumber(Integer number) {
            if (number < 0)
                throw new IllegalArgumentException("The bus number cannot be less than zero");

            this.number = number;
            return this;
        }

        public BusBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder setMileage(Integer mileage) {
            if (mileage < 0)
                throw new IllegalArgumentException("The bus mileage cannot be less than zero");

            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            if (number == null || model == null || mileage == null)
                throw new IllegalStateException("All fields must be initialized");

            return new Bus(number, model, mileage);
        }
    }
}
