package algorithms.sorting;

import utils.Utils;

/**
 * At each iteration, insert the current element in its proper position in relation to elements already known
 */
public class Insertion {
    public static final String name = "Insertion Sort";

    // Correct implementation:
    //  - start from index 1, as at index 0 there will be no other indexes to compare
    //  - the inner loop starts with j = i and we iterate back, comparing j with j - 1,
    //  then swap elements and decrement j until the condition is satisfied
    //  - this means that, at every iteration, we swap the current element with the one
    //  at the previous position, should it be higher
    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        for (var i = 1; i < arr.length; i++) {
            for (var j = i; j > 0 && Utils.isLess(arr[j], arr[j -1]); j--) {
                Utils.swap(arr, j, j - 1);
            }
        }
    }

    // This is a variation of the insertion sort, which uses a shift right approach to
    // avoid swapping elements unnecessarily - it does that by comparing the current index
    // (i) with each of the previous elements, then inserting the value of the current
    // index only when the correct position in relation to the known part of the array is
    // found. See a detailed explanation below. Given the following array:
    //  - [5, 2, 1, 8, 9]
    //  - apply the following steps
    //  - skip 5 at is is already in the right position - at the time i is the first index
    //  - i = 1, so save arr[i] in tmp, and the index number to j
    //  - compare the value with the current index: 2 < 5
    //  - "shift" 5 to the right: [5, 5, 1, 8, 9]
    //  - because we are moving backwards, there are no other numbers to compare, so
    //  tmp is assigned to arr[j]: [2, 5, 1, 8, 9]
    //  - next value is 1, so the sequence is shown below
    //      - save 1 to tmp e 2 (current index) to j
    //      - [2, 5, 5, 8, 9], j-- (1)
    //      - [2, 2, 5, 8, 9], j-- (0)
    //      - [1, 2, 5, 8, 9] -> tmp is assigned to arr[j]
    public static <T extends Comparable<? super T>> void sortShift(T[] arr) {
        var length = arr.length;

        for (var i = 1; i < length; i++) {
            T tmp = arr[i];
            var j = i;

            while (j > 0 && Utils.isLess(tmp, arr[j - 1])) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }
}
