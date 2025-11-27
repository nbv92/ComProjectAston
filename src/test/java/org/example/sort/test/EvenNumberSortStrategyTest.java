package org.example.sort.test;

import org.example.sort.Bus;
import org.example.sort.CustomList;
import org.example.sort.sort.strategy.EvenNumberSortStrategy;
import org.example.sort.sort.comparator.BusNumberComparator;

public class EvenNumberSortStrategyTest {
    public static void main(String[] args) {
        testEvenNumberSort();
        System.out.println("All EvenNumberSortStrategy tests passed!");
    }

    private static void testEvenNumberSort() {
        CustomList<Bus> list = new CustomList<>();

        list.add(Bus.builder().setNumber(5).setModel("X").setMileage(100).build());
        list.add(Bus.builder().setNumber(6).setModel("B").setMileage(50).build());
        list.add(Bus.builder().setNumber(4).setModel("Z").setMileage(300).build());
        list.add(Bus.builder().setNumber(2).setModel("Y").setMileage(200).build());
        list.add(Bus.builder().setNumber(3).setModel("A").setMileage(150).build());

        EvenNumberSortStrategy sorter = new EvenNumberSortStrategy();
        sorter.sort(list, new BusNumberComparator());

        assert list.get(0).getNumber() == 5 : "The odd element 5 must remain in place";
        assert list.get(1).getNumber() == 2 : "Even numbers should be sorted: 2";
        assert list.get(2).getNumber() == 4 : "Even numbers should be sorted: 4";
        assert list.get(3).getNumber() == 3 : "The odd element 3 must remain in place";
        assert list.get(4).getNumber() == 6 : "Even numbers should be sorted: 6";

        CustomList<Bus> allEven = new CustomList<>();
        allEven.add(Bus.builder().setNumber(8).setModel("X").setMileage(0).build());
        allEven.add(Bus.builder().setNumber(2).setModel("Y").setMileage(0).build());
        allEven.add(Bus.builder().setNumber(6).setModel("Z").setMileage(0).build());

        sorter.sort(allEven, new BusNumberComparator());
        assert allEven.get(0).getNumber() == 2;
        assert allEven.get(1).getNumber() == 6;
        assert allEven.get(2).getNumber() == 8;
    }
}
