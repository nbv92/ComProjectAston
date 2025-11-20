package org.example.sort;

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

    public static class BusBuilder {
        private Integer number;
        private String model;
        private Integer mileage;

        private BusBuilder() {}

        public BusBuilder setNumber(Integer number) {
            if (number < 0) throw new IllegalArgumentException("The bus number cannot be less than zero");
            this.number = number;
            return this;
        }

        public BusBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder setMileage(Integer mileage) {
            if (mileage < 0) throw new IllegalArgumentException("The bus mileage cannot be less than zero");
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(number, model, mileage);
        }
    }
}
