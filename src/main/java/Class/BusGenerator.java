package Class;

import java.util.Random;

public class BusGenerator {
    private static final Random random = new Random();
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String[] MODELS = {
            "Mercedes‑Benz Citaro",
            "Mercedes‑Benz Tourismo",
            "MAN Lion’s City",
            "MAN Lion’s Coach",
            "Setra S 517 HD",
            "Setra S 415 LE business",
            "Volvo 7900 Electric",
            "Volvo B8R",
            "Scania Citywide",
            "Scania Interlink",
            "Solaris Urbino 12",
            "Jelcz 043",
            "VDL Futura FHD2",
            "Bova Lexio",
            "Yutong ZK6128H",
            "Higer KLQ6119Q",
            "Golden Dragon XML6126",
            "Yutong TC9",
            "Hyundai Universe Space Luxury",
            "Daewoo BS106",
            "Hino Rainbow",
            "Isuzu Erga",
            "Tata Starbus",
            "Ashok Leyland Falcon",
            "Gillig Low Floor",
            "New Flyer Xcelsior",
            "Thomas Saf-T-Liner C2",
            "Prevost H3-45",
            "Nova Bus LFS",
            "Marcopolo Paradiso G8 1200",
            "Caio Millennium",
            "ЛиАЗ‑4292",
            "ПАЗ‑3205",
            "КАВЗ‑4270",
            "Volgabus‑5270",
            "МАЗ‑203",
            "БКМ 321",
            "Богдан А092",
            "Otokar Kent C",
            "BMC Procity",
            "Saipa S90",
            "Volgren Optimus"
    };

    public static Bus generateRandomBus() {
        String number = generateRandomBusNumber();
        String model = MODELS[random.nextInt(MODELS.length)];
        int mileage = random.nextInt(1, 999999 + 1);

        return Bus.builder()
                .setNumber(number)
                .setModel(model)
                .setMileage(mileage)
                .build();
    }

    private static String generateRandomBusNumber() {
        StringBuilder numberBuilder = new StringBuilder();

        // Добавляем две случайные буквы
        for (int i = 0; i < 2; i++) {
            numberBuilder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        // Добавляем три случайные цифры
        for (int i = 0; i < 3; i++) {
            numberBuilder.append(random.nextInt(10));
        }

        // Добавляем две случайные буквы
        for (int i = 0; i < 2; i++) {
            numberBuilder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return numberBuilder.toString();
    }

    public static Bus[] generateRandomBusArray(int size) {
        if (size < 0) throw new IllegalArgumentException("Array size cannot be negative");

        Bus[] result = new Bus[size];
        for (int i = 0; i < size; i++) {
            result[i] = generateRandomBus();
        }
        return result;
    }
}