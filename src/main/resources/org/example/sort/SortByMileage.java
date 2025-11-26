package Interface;

import java.util.List;
import Class.Bus;
public class SortByMileage implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        buses.sort((b1, b2) -> Integer.compare(b1.getMileage(), b2.getMileage()));
    }
}
