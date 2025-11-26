package org.example.sort.Class;

import org.example.sort.CustomList;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCounter {
    public static <T> int countOccurrences(CustomList<T> list, T target, int threads) {
        AtomicInteger totalCount = new AtomicInteger(0);
        int size = list.size();
        Thread[] workers = new Thread[threads];

        int chunkSize = (size + threads - 1) / threads;

        for (int t = 0; t < threads; t++) {
            int start = t * chunkSize;
            int end = Math.min(start + chunkSize, size);

            workers[t] = new Thread(() -> {
                int localCount = 0;
                for (int i = start; i < end; i++) {
                    if (list.get(i).equals(target)) {
                        localCount++;
                    }
                }
                totalCount.addAndGet(localCount);
            });

            workers[t].start();
        }

        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return totalCount.get();
    }
}
