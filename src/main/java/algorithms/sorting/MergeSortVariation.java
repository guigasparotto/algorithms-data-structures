package algorithms.sorting;

import utils.Utils;

import java.util.Arrays;
import java.util.function.Consumer;

public class MergeSortVariation {

    public static void main(String[] args) {
        var arr = Arrays.stream(Utils.createRandomArray(
                10_000_000, 0, 1000)).mapToInt(e -> e).toArray();
        var arr1 = Arrays.copyOfRange(arr, 0, arr.length);

        Utils.runWithTime(MergeSortVariation::sort, arr);
        System.out.println("Sorted: " + Utils.isSorted(arr));

        // Optimised
        System.out.println();
        Utils.runWithTime(MergeSortVariation::sortOptimised, arr1);
        System.out.println("Sorted: " + Utils.isSorted(arr1));
    }

    public static void sort(int[] arr) {
        // base test
        if (arr.length < 2) return;

        // create left and right arrays
        int mid = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // sort each side
        sort(left);
        sort(right);

        // merge
        merge(arr, left, right);
    }

    public static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (j < left.length && k < right.length) {
            if (left[j] < right[k]) {
                result[i++] = left[j++];
            } else {
                result[i++] = right[k++];
            }
        }

        while (j < left.length) {
            result[i++] = left[j++];
        }

        while (k < right.length) {
            result[i++] = right[k++];
        }
    }

    // With 2 optimisations:
    //  - Use selection sort if array is smaller than 100 elements - to reduce memory
    //  use and unnecessary additional merges
    //  - If the last element of left array is smaller than first element of the second
    //  array, it means they are in the correct order, so just perform a simple copy
    public static void sortOptimised(int[] arr) {
        // base test
        if (arr.length < 2) return;

        // create left and right arrays
        int mid = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // sort each side, if the array is smaller than 15 elements, use
        // selection sort - it should improve performance for small arrays
        // Checking just one side because the difference between arrays wil
        // be 1 element at most
        if (left.length < 100) {
            Selection.sort(left);
            Selection.sort(right);
        } else {
            sort(left);
            sort(right);
        }

        // If the last element of the left array is smaller than the first element
        // of the right array, it is already in order
        if (left[left.length - 1] < right[0]) {
            // Directly copy the elements of left and right back into arr
            System.arraycopy(left, 0, arr, 0, left.length);
            System.arraycopy(right, 0, arr, mid, right.length);
        } else {
            // merge
            merge(arr, left, right);
        }
    }
}
