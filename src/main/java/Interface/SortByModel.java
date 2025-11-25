package Interface;

import Class.Bus;

import java.util.List;
public class SortByModel implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        buses.sort((b1, b2) -> b1.getModel().compareTo(b2.getModel()));
    }
}
