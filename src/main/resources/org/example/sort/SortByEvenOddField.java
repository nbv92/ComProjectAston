package Interface;

import java.util.List;

import Class.Bus;

public class SortByEvenOddField implements SortingStrategy {
    private final String field;

    public SortByEvenOddField(String field) {
        this.field = field;
    }

    @Override
    public void sort(List<Bus> buses) {
        int n = buses.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Bus b1 = buses.get(j);
                Bus b2 = buses.get(j + 1);
                if (compareByEvenOdd(b1, b2) > 0) {
                    // Меняем местами
                    buses.set(j, b2);
                    buses.set(j + 1, b1);
                }
            }
        }
    }

    private int compareByEvenOdd(Bus b1, Bus b2) {
        int value1 = getValue(b1);
        int value2 = getValue(b2);

        boolean isValue1Even = value1 % 2 == 0;
        boolean isValue2Even = value2 % 2 == 0;

        if (isValue1Even && isValue2Even) {
            return Integer.compare(value1, value2);
        } else if (isValue1Even) {
            return -1; // четное перед нечетным
        } else if (isValue2Even) {
            return 1; // четное перед нечетным
        } else {
            return 0; // оба нечетные остаются на месте
        }
    }


    private int getValue(Bus bus) {
        switch (field) {
            case "mileage":
                return bus.getMileage();
            // Добавьте другие поля, если нужно
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }
    }
}
