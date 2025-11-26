package org.example.sort.sort.strategy;

import org.example.sort.Class.Bus;
import org.example.sort.CustomList;
import org.example.sort.sort.SortStrategy;

import java.util.Comparator;

public class EvenNumberSortStrategy implements SortStrategy<Bus> {
    @Override
    public void sort(CustomList<Bus> list, Comparator<Bus> comparator) {
        int n = list.size();
        int[] evenIndexes = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (list.get(i).getNumber() % 2 == 0) {
                evenIndexes[count++] = i;
            }
        }

        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < count - 1; i++) {
                Bus a = list.get(evenIndexes[i]);
                Bus b = list.get(evenIndexes[i + 1]);

                if (comparator.compare(a, b) > 0) {
                    list.set(evenIndexes[i], b);
                    list.set(evenIndexes[i + 1], a);
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
