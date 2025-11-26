package Interface;

import java.util.List;
import Class.Bus;
public class SortByModel implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        buses.sort((b1, b2) -> b1.getModel().compareTo(b2.getModel()));
    }
}
