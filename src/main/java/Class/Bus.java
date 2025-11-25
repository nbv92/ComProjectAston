package Class;

public class Bus {
    private final String number;
    private final String model;
    private final int mileage;

    private Bus(BusBuilder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public static BusBuilder builder() {
        return new BusBuilder();
    }

    public static class BusBuilder {
        private String number;
        private String model;
        private int mileage;

        public BusBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public BusBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
