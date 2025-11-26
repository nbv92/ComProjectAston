package Interface;

import java.util.List;
import Class.Bus;
public class SortByNumber implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        buses.sort((b1, b2) -> b1.getNumber().compareTo(b2.getNumber()));
    }
}
