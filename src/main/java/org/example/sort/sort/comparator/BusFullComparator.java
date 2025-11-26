package org.example.sort.sort.comparator;

import org.example.sort.Bus;

import java.util.Comparator;

public class BusFullComparator implements Comparator<Bus> {
    @Override
    public int compare(Bus o1, Bus o2) {
        int cmp = o1.getNumber().compareTo(o2.getNumber());
        if (cmp != 0) return cmp;

        cmp = o1.getModel().compareToIgnoreCase(o2.getModel());
        if (cmp != 0) return cmp;

        return o1.getMileage().compareTo(o2.getMileage());
    }
}