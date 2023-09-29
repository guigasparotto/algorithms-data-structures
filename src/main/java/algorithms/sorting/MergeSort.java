package algorithms.sorting;

import utils.Utils;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Merge sort works by splitting the collection into 2 parts recursively, until it can no longer
 * be divided, once it gets to this point, it sorts each of the individual parts then merge them.
 * It is much faster than more simple algorithms, however it takes significantly more space due
 * to the need for creating new objects. See a short description of the inner works below:
 *  - The sort method receives the collection that should be sorted
 *      - it then calls an overloaded version where initial and end positions are passed as params,
 *      this is not strictly necessary, but it removes the responsibility of passing seemingly
 *      unnecessary parameter from the method caller
 *      - the overloaded method is the one that will be called recursively, an initial base case
 *      is in place to ensure that the method returns straight away when the array has only 1
 *      element. The method then proceeds to:
 *          - calculate the middle index
 *          - call itself to sort the indexes from start to middle - left side of the array
 *          - call itself to sort the indexes from middle to end - right side of the array
 *          - create two copies of the array - left and right - with their respective start
 *          and end indexes
 *          - merge the two copies to a new array placing the items in their correct positions
 *      - because of the recursive nature of the sort method, it will keep calling sort on the
 *      left side of each sub-array until it end (index) - start (index) <= 1 - this indicates
 *      that the current sub-array contains only 1 element
 *      - when this happens, the control will be returned to the caller of the last method,
 *      which will perform the sort of the array's right side
 *      - with both arrays at hand, we can merge them, iterating through a result array, that
 *      is created in the merge method, and cherry-picking the next element from left or right
 *      (based on order comparison) until all elements have been exhausted
 *      - lastly, the original array is modified, by copying the merged array to its correct
 *      place, based on start and end positions
 */
public class MergeSort {

    public static void main(String[] args) {
        var arr = Utils.createRandomArray(10,0, 20);
        Consumer<Integer[]> mergeSort = MergeSort::sort;
        Utils.runWithTime(mergeSort, arr);
        System.out.println("Sorted: " + Utils.isSorted(arr));
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        sort(arr, 0, arr.length);
    }

    // This is called top-bottom approach, meaning there is also a bottom-up approach
    // see page 277 of the Algorithms book
    public static <T extends Comparable<? super T>> void sort(T[] arr, int start, int end) {
        // base case for recursion, if the size of the subarray is 1 or 0
        if (end - start <= 1) return;

        // This is to ensure that the right part of the sort calculates the middle element
        // correctly, for example, for an array of size 10, start = 5, end = 10, we calculate
        // 5 + (10 - 5) / 2 = 7 (middle element of the right part)
        // left would be just: 0 + (5 - 0) / 2 = 2
        int middle = start + (end - start) / 2;

        // call sort for the left and right parts
        sort(arr, start, middle);
        sort(arr, middle, end);

        // create sub-arrays for merging
        T[] left = Arrays.copyOfRange(arr, start, middle);
        T[] right = Arrays.copyOfRange(arr, middle, end);

        // merge the parts
        T[] merged = merge(left, right);

        // copy the merged elements back into the original array
        System.arraycopy(merged, 0, arr, start, merged.length);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> T[] merge(T[] left, T[] right) {
        var lengthL = left.length;
        var lengthR = right.length;
        T[] result = (T[]) new Comparable[lengthL + lengthR];

        int leftIndex = 0;
        int rightIndex = 0;
        int i = 0;
        while (leftIndex < lengthL && rightIndex < lengthR) {
            if (Utils.isLess(left[leftIndex], right[rightIndex])) {
                result[i++] = left[leftIndex++];
            } else {
                result[i++] = right[rightIndex++];
            }
        }

        while (leftIndex < lengthL) {
            result[i++] = left[leftIndex++];
        }

        while (rightIndex < lengthR) {
            result[i++] = right[rightIndex++];
        }

        return result;
    }

    // This implementation sorts the items in place, so it requires less memory,
    // however it is more complicated, as it requires keeping track of multiple indexes
    public static <T extends Comparable<? super T>> void inPlace(T[] arr) {
        inPlace(arr, 0, arr.length);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void inPlace(T[] arr, int start, int end) {
        // base case for recursion, if the size of the subarray is 1 or 0
        if (end - start <= 1) return;

        int middle = start + (end - start) / 2;

        // call sort for the left and right parts
        sort(arr, start, middle);
        sort(arr, middle, end);

        // merge the parts in-place
        int leftIndex = start;
        int rightIndex = middle;
        int tempIndex = 0;
        T[] tempArray = (T[]) new Comparable[end - start];

        while (leftIndex < middle && rightIndex < end) {
            if (Utils.isLess(arr[leftIndex], arr[rightIndex])) {
                tempArray[tempIndex++] = arr[leftIndex++];
            } else {
                tempArray[tempIndex++] = arr[rightIndex++];
            }
        }

        while (leftIndex < middle) {
            tempArray[tempIndex++] = arr[leftIndex++];
        }

        while (rightIndex < end) {
            tempArray[tempIndex++] = arr[rightIndex++];
        }

        System.arraycopy(tempArray, 0, arr, start, tempArray.length);
    }
}
