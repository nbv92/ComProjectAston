package org.example.sort.sort.strategy;

import org.example.sort.Bus;

import java.util.List;

public class SortByMileage implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - i - 1; j++) {
                int m1 = buses.get(j).getMileage();
                int m2 = buses.get(j + 1).getMileage();
                if (m1 > m2) {
                    Bus temp = buses.get(j);
                    buses.set(j, buses.get(j + 1));
                    buses.set(j + 1, temp);
                }
            }
        }
    }
}
