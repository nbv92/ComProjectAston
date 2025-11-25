package org.example.sort.sort.strategy;

import org.example.sort.Class.Bus;
import org.example.sort.sort.MyList;
import org.example.sort.sort.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvenNumberSortStrategy implements SortStrategy<Bus> {

    private final Comparator<Bus> comparator;

    public EvenNumberSortStrategy(Comparator<Bus> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(MyList<Bus> list, Comparator<Bus> comparator) {
        int n = list.size();
        boolean swapped;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                Bus current = list.get(i);
                Bus next = list.get(i + 1);

                if (current.getNumber() % 2 == 0 && next.getNumber() % 2 == 0
                        && comparator.compare(current, next) > 0) {

                    list.set(i, next);
                    list.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
