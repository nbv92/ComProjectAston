package org.example.sort.sort.strategy;

import org.example.sort.Bus;

import java.util.List;

public class SortByNumber implements SortingStrategy {

    @Override
    public void sort(List<Bus> buses) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - i - 1; j++) {

                int num1 = buses.get(j).getNumber();
                int num2 = buses.get(j + 1).getNumber();

                if (num1 > num2) {
                    Bus temp = buses.get(j);
                    buses.set(j, buses.get(j + 1));
                    buses.set(j + 1, temp);
                }
            }
        }
    }
}
