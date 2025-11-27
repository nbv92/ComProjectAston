package Interface;

import java.util.List;
import Class.Bus;

public class SortByModel implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - i - 1; j++) {
                String a = buses.get(j).getModel();
                String b = buses.get(j + 1).getModel();

                int minLen = Math.min(a.length(), b.length());
                boolean greater = false;

                for (int k = 0; k < minLen; k++) {
                    if (a.charAt(k) > b.charAt(k)) {
                        greater = true;
                        break;
                    } else if (a.charAt(k) < b.charAt(k)) {
                        break;
                    }
                }

                if (!greater && a.length() > b.length() && a.startsWith(b)) {
                    greater = true;
                }

                if (greater) {
                    Bus temp = buses.get(j);
                    buses.set(j, buses.get(j + 1));
                    buses.set(j + 1, temp);
                }
            }
        }
    }
}
