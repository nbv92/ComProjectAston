package org.example.sort.sort.comparator;

import org.example.sort.Class.Bus;

import java.util.Comparator;

public class BusModelComparator implements Comparator<Bus> {
    @Override
    public int compare(Bus o1, Bus o2) {
        return o1.getModel().compareToIgnoreCase(o2.getModel());
    }
}