package aston;
import java.util.*;

public class autobuss {
    public static void main(String[] args) {
        List<String> buss = new ArrayList<>(Arrays.asList(
                "model1", "model22", "model3", "model4444"
        ));

        // ручная пузырьковая сортировка по длине строки
        for (int i = 0; i < buss.size() - 1; i++) {
            for (int j = 0; j < buss.size() - 1 - i; j++) {
                if (buss.get(j).length() > buss.get(j + 1).length()) {
                    // меняем элементы местами
                    String tmp = buss.get(j);
                    buss.set(j, buss.get(j + 1));
                    buss.set(j + 1, tmp);
                }
            }
        }

        System.out.println(buss);
    }
}