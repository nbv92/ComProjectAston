package Interface;
import java.util.List;
import Class.Bus;

public class SortByNumber implements SortingStrategy {

    @Override
    public void sort(List<Bus> buses) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - i - 1; j++) {

                int num1 = Integer.parseInt(buses.get(j).getNumber());
                int num2 = Integer.parseInt(buses.get(j + 1).getNumber());

                if (num1 > num2) {
                    Bus temp = buses.get(j);
                    buses.set(j, buses.get(j + 1));
                    buses.set(j + 1, temp);
                }
            }
        }
    }
}
