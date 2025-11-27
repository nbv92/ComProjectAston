package org.example.sort.test;

import org.example.sort.Bus;
import org.example.sort.CustomList;
import org.example.sort.sort.comparator.*;
import org.example.sort.sort.strategy.BubbleSortStrategy;

public class BubbleSortTest {

    public static void main(String[] args) {
        testSortByNumber();
        testSortByModel();
        testSortByMileage();
        testSortByFullComparator();
        System.out.println("All BubbleSort tests passed!");
    }

    private static CustomList<Bus> sampleList() {
        CustomList<Bus> list = new CustomList<>();
        list.add(Bus.builder().setNumber(12).setModel("Volvo").setMileage(300).build());
        list.add(Bus.builder().setNumber(5).setModel("Scania").setMileage(100).build());
        list.add(Bus.builder().setNumber(7).setModel("MAN").setMileage(200).build());
        return list;
    }

    private static void testSortByNumber() {
        CustomList<Bus> list = sampleList();
        BubbleSortStrategy<Bus> sorter = new BubbleSortStrategy<>();

        sorter.sort(list, new BusNumberComparator());

        assert list.get(0).getNumber() == 5 : "Sort by number failed (position 0)";
        assert list.get(1).getNumber() == 7 : "Sort by number failed (position 1)";
        assert list.get(2).getNumber() == 12 : "Sort by number failed (position 2)";
    }

    private static void testSortByModel() {
        CustomList<Bus> list = sampleList();
        BubbleSortStrategy<Bus> sorter = new BubbleSortStrategy<>();

        sorter.sort(list, new BusModelComparator());

        assert list.get(0).getModel().equals("MAN") : "Sort by model failed (position 0)";
        assert list.get(1).getModel().equals("Scania") : "Sort by model failed (position 1)";
        assert list.get(2).getModel().equals("Volvo") : "Sort by model failed (position 2)";
    }

    private static void testSortByMileage() {
        CustomList<Bus> list = sampleList();
        BubbleSortStrategy<Bus> sorter = new BubbleSortStrategy<>();

        sorter.sort(list, new BusMileageComparator());

        assert list.get(0).getMileage() == 100 : "Sort by mileage failed (position 0)";
        assert list.get(1).getMileage() == 200 : "Sort by mileage failed (position 1)";
        assert list.get(2).getMileage() == 300 : "Sort by mileage failed (position 2)";
    }

    private static void testSortByFullComparator() {
        CustomList<Bus> list = sampleList();
        BubbleSortStrategy<Bus> sorter = new BubbleSortStrategy<>();

        sorter.sort(list, new BusFullComparator());

        assert list.get(0).getNumber() == 5 : "Full comparator sort failed (position 0)";
        assert list.get(1).getNumber() == 7 : "Full comparator sort failed (position 1)";
        assert list.get(2).getNumber() == 12 : "Full comparator sort failed (position 2)";
    }
}
