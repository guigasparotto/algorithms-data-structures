package utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Utils {

    public static Integer[] getTestArray() {
        return new Integer[]{2, 4, 1, 0, 8, 9, 11, 2, 4, 6, 22, 14, 16, 10, 7, 14, 88, -3, -2, -50, 31};
    }

    // By using the swap and isLess methods, we can add operations to count the number of
    // swaps and comparisons. This is useful to compare different algorithms and understand
    // how many operations each one of them does. For algorithms that do not swap elements
    // in this way, we can also count array accesses.

    public static <T extends Comparable<? super T>> void swap(T[] arr, int i, int j) {
        T swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static <T extends Comparable<? super T>> boolean isLess(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public static <T extends Comparable<? super T>> void runWithTime(Consumer<T[]> method, T[] arr) {
        long before = System.nanoTime();
        method.accept(arr);
        long duration = System.nanoTime() - before;
        System.out.println("Time in nanos: " + duration);
        System.out.println("Time in millis: " + TimeUnit.NANOSECONDS.toMillis(duration));

    }

    public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] createRandomArray(int size, int min, int max) {
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(min, max))
                .limit(size)
                .toArray(Integer[]::new);
    }
}
