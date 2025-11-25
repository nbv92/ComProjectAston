package Controller;

import java.util.ArrayList;
import java.util.List;

import Class.Bus;
import Interface.SortingStrategy;

public class BusController {
    private List<Bus> buses = new ArrayList<>();
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public void sortBuses() {
        if (sortingStrategy == null) {
            System.out.println("Сортировочная стратегия не установлена.");
            return;
        }
        if (buses.isEmpty()) {
            System.out.println("Нет автобусов для сортировки.");
            return;
        }
        sortingStrategy.sort(buses);
    }

    public List<Bus> getBuses() {
        return buses;
    }
}