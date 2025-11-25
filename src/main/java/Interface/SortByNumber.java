package Interface;

import Class.Bus;

import java.util.List;
public class SortByNumber implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        buses.sort((b1, b2) -> b1.getNumber().compareTo(b2.getNumber()));
    }
}
