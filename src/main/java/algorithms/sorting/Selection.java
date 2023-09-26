package algorithms.sorting;

import utils.Utils;

import java.util.Arrays;

/**
 * At each iteration, selects the lowest value and exchange it with the current index.
 *  - iterarion 1: find the smallest number and exchange it with the value at pos 0
 *  - iteration 2: find the next smallest number and exchange it with the value at pos 1
 *  - ... so forth until reaching the end of the array
 */
public class Selection {
    public static final String name = "Selection Sort";

    public static void main(String[] args) {
        var arr = Utils.getTestArray();
        sort(arr);
        System.out.println(Arrays.toString(arr));

        var strings = new String[]{"Guilherme", "Alissa", "Teresa", "Alana"};
        sort(strings);
        System.out.println(Arrays.toString(strings));
    }

    public static void sort(int[] arr) {
        for (var i = 0; i < arr.length; i++) {
            var min = i;
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            // is this if useful for optimisation?
            if (arr[min] < arr[i]) {
                var tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
    }

    // generified version
    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (Utils.isLess(arr[j], arr[min])) {
                    min = j;
                }
            }

            Utils.swap(arr, i, min);
        }
    }
}
