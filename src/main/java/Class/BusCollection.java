package Class;

import java.util.*;
import java.util.stream.Stream;

public class BusCollection implements Collection<Bus> {
    private List<Bus> buses = new ArrayList<>();

    @Override
    public boolean add(Bus bus) {
        return buses.add(bus);
    }

    @Override
    public boolean remove(Object o) {
        return buses.remove(o);
    }

    @Override
    public int size() {
        return buses.size();
    }

    @Override
    public boolean isEmpty() {
        return buses.isEmpty();  // Реализация метода isEmpty
    }

    @Override
    public Iterator<Bus> iterator() {
        return buses.iterator();
    }

    @Override
    public Object[] toArray() {
        return buses.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return buses.toArray(a);
    }

    @Override
    public boolean contains(Object o) {
        return buses.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return buses.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Bus> c) {
        return buses.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return buses.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return buses.retainAll(c);
    }

    @Override
    public void clear() {
        buses.clear();
    }

    // Метод для получения списка автобусов
    public List<Bus> getBuses() {
        return new ArrayList<>(buses);
    }

    // Метод для заполнения коллекции из стрима
    public void fillFromStream(Stream<Bus> busStream) {
        busStream.forEach(this::add);
    }
}
