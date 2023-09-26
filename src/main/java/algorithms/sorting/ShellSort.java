package algorithms.sorting;

import utils.Utils;

import java.util.Arrays;

/**
 * Shell sort is a generalisation of insertion sort that allows the exchange of items that are far apart.
 * While the insertion sort moves item by item inserting them into their place in relation to the sorted
 * area of the array, the shell sort uses a formula to exchange items based on a given gap, starting from
 * elements that are very far apart and reducing the gap at every stage. After sorting, we say that elements
 * are h-sorted (we swap every hth elements, where h is the size of the gap). This is very efficient in
 * comparison to normal insertion sort, see the comparison below.
 */
public class ShellSort {
    public static final String name = "Shell Sort";

    public static void main(String[] args) {
        var arr2 = Utils.getTestArray();

        var before = System.nanoTime();
        sort(arr2);
        System.out.println("After shell sort: " + (System.nanoTime() - before));
        System.out.println(Arrays.toString(arr2));
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        var length = arr.length;
        var h = 1;
        while (h < length / 3) {
            h = (3 * h) + 1;
        }

        // the while loop keeps track of the value of h and decreases it as required
        // i starts from h and iterates one by one going forward, values that are less
        // than it will be dealt with in the subsequent iterations, given the value of
        // h decreases. The inner loop is responsible to swap elements at the distance
        // of h, or, the hth element, if they are not in the correct order

        while (h >= 1) {
            for (var i = h; i < length; i++) {
                for (var j = i; j >= h && Utils.isLess(arr[j], arr[j-h]); j -= h) {
                    Utils.swap(arr, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
