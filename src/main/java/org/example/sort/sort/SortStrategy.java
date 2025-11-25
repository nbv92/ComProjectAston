package org.example.sort.sort;

import org.example.sort.CustomList;

import java.util.Comparator;

public interface SortStrategy<T> {
    void sort(CustomList<T> list, Comparator<T> comparator);
}
