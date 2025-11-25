package Interface;

import Class.Bus;

import java.util.List;

public class SortByMultipleFields implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        int n = buses.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Bus b1 = buses.get(j);
                Bus b2 = buses.get(j + 1);
                if (compareBuses(b1, b2) > 0) {
                    // Меняем местами
                    buses.set(j, b2);
                    buses.set(j + 1, b1);
                }
            }
        }
    }

    private int compareBuses(Bus b1, Bus b2) {
        int numberComparison = b1.getNumber().compareTo(b2.getNumber());
        if (numberComparison != 0) return numberComparison;

        int modelComparison = b1.getModel().compareTo(b2.getModel());
        if (modelComparison != 0) return modelComparison;

        return Integer.compare(b1.getMileage(), b2.getMileage());
    }
}
