package org.example.sort.test;

import org.example.sort.CustomList;

public class CustomListTest {
    public static void main(String[] args) {

        // size() and add()
        CustomList<Integer> list = new CustomList<>();
        list.add(10);
        list.add(20);
        assert list.size() == 2 : "Expected size 2 but got " + list.size();
        assert list.get(0) == 10;
        assert list.get(1) == 20;

        list.add(1, 15);
        assert list.size() == 3;
        assert list.get(1) == 15 : "Expected 15 at index 1";

        // set
        list.set(1, 99);
        assert list.get(1) == 99 : "set() didn't change value";

        // remove()
        Integer removed = list.remove(1);
        assert removed == 99 : "Expected removed 99";
        assert list.size() == 2 : "Expected size 2";
        assert list.get(1) == 20 : "remove() shifted elements incorrectly";

        // get()
        try {
            list.get(100);
            assert false : "IndexOutOfBounds not thrown";
        } catch (ArrayIndexOutOfBoundsException ignored) {}

        // expandArray()
        CustomList<Integer> big = new CustomList<>();
        for (int i = 0; i < 20; i++) big.add(i);
        assert big.size() == 20;
        assert big.get(19) == 19;

        // addAll(T[])
        CustomList<String> s = new CustomList<>();
        String[] arr = {"a", "b", "c"};
        s.addAll(arr);
        assert s.size() == 3;
        assert s.get(2).equals("c");

        // addAll(Iterable)
        java.util.List<String> list2 = java.util.List.of("x", "y");
        s.addAll(list2);
        assert s.size() == 5;
        assert s.get(3).equals("x");

        // clear()
        s.clear();
        assert s.size() == 0;
        try {
            s.get(0);
            assert false : "clear() didn't reset to empty";
        } catch (ArrayIndexOutOfBoundsException ignored) {}

        // toString()
        CustomList<Integer> t = new CustomList<>();
        t.add(1);
        t.add(2);
        t.add(3);
        assert t.toString().equals("[1, 2, 3]") : "toString() is broken: " + t.toString();

        System.out.println("Все тесты CustomList прошли успешно!");
    }
}
