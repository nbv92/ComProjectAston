package org.example.sort.test;

import org.example.sort.CustomList;
import org.example.sort.utils.MultiThreadCounter;

public class MultiThreadCounterTest {
    public static void main(String[] args) {
        CustomList<Integer> list = new CustomList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(5);

        int result = MultiThreadCounter.countOccurrences(list, 2, 3);
        assert result == 3 : "Expected 3, received " + result;

        CustomList<String> list2 = new CustomList<>();
        list2.add("a");
        list2.add("b");

        int result2 = MultiThreadCounter.countOccurrences(list2, "z", 4);
        assert result2 == 0 : "Expected 0, got 0 " + result2;

        CustomList<Integer> list3 = new CustomList<>();
        for (int i = 0; i < 1000; i++) list3.add(7);

        int result3 = MultiThreadCounter.countOccurrences(list3, 7, 10);
        assert result3 == 1000 : "Expected 1000, received " + result3;

        CustomList<Integer> list4 = new CustomList<>();
        list4.add(1);
        list4.add(1);

        int result4 = MultiThreadCounter.countOccurrences(list4, 1, 100);
        assert result4 == 2 : "Expected 2, received " + result4;

        System.out.println("All tests were successful!");
    }
}
