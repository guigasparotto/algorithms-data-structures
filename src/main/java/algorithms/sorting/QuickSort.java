package algorithms.sorting;

import utils.Utils;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Very popular algorithm, given it is easy to implement and very fast - probably used more
 * than any other sorting algorithm.
 *  - easy to implement
 *  - works well for a variety of different kinds of input
 *  - substantially faster than any other sorting method in typical applications
 *  - sorts in-place
 *  - complexity is N log N on average
 * <p>
 *  These three conditions should be true after sorting each partition:
 *      - The entry arr[pivot] is in its final place in the array for some pivot
 *      - No entry in arr[left] through arr[mid - 1] is greater than arr[pivot]
 *      - No entry in arr[mid + 1] through arr[end] is less than arr[pivot]
 */
public class QuickSort {
    public static void main(String[] args) {
        var minBound = 0;
        var maxBound = Integer.MAX_VALUE;
        var arr = Utils.createMultipleIdenticalArrays(3, 10_000_000, minBound, maxBound);

        Utils.runWithTime(QuickSort::sort, arr[0]);
        System.out.println("Sorted: " + Utils.isSorted(arr[0]));

        System.out.println("\nParallel sort:");
        Utils.runWithTime(QuickSort::parallelSort, arr[1]);
        System.out.println("Sorted: " + Utils.isSorted(arr[1]));

        System.out.println("\nNative Arrays Parallel sort:");
        Utils.runWithTime(Arrays::parallelSort, arr[2]);
        System.out.println("Sorted: " + Utils.isSorted(arr[2]));
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        sort(arr, 0, arr.length);
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr, int start, int end) {
        if (end - start <= 1) return;

        // start two pointers at the first and last index
        // select a pivot, in this case will be the first element
        // Note: I was previously using the mid element, but it
        // doesn't make a difference
        int left = start, right = end - 1;
        T pivot = arr[start];

        // iterate until the pointers cross
        // compare each element with the pivot, and then:
        //  - save the left index when the value is higher than the pivot
        //  - save the right index when the value is lower than the pivot
        while (left <= right) {
            while (Utils.isLess(arr[left], pivot)) left++;
            while (Utils.isLess(pivot, arr[right])) right--;

            if (left <= right) {
                Utils.swap(arr, left++, right--);
            }
        }

        // quick sort tends to be slower than other sorting solutions for small
        // small arrays, so adjusting it to call a different sort algorithm like
        // insertion or selection sort, for arrays of 15 elements or less, tends
        // to improve performance
        sort(arr, start, right + 1);
        sort(arr, left, end);
    }

    public static <T extends Comparable<? super T>> void parallelSort(T[] arr) {
        var start = 0;
        var end = arr.length;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            Future<?> future1 = executorService.submit(() -> sort(arr, start, end / 2));
            Future<?> future2 = executorService.submit(() -> sort(arr, end / 2, end));

            // Wait for the tasks to complete
            future1.get();
            future2.get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        // Merge the two sorted halves
        merge(arr, start, end / 2, end);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void merge(T[] arr, int start, int mid, int end) {
        int lenghtL = mid - start;
        int lenghtR = end - mid;

        T[] left = (T[]) new Comparable[lenghtL];
        T[] right = (T[]) new Comparable[lenghtR];

        // Copy data to temporary arrays
        System.arraycopy(arr, start, left, 0, lenghtL);
        System.arraycopy(arr, mid, right, 0, lenghtR);

        // Merge the temporary arrays back into the original array arr
        int i = 0, j = 0, k = start;
        while (i < lenghtL && j < lenghtR) {
            if (Utils.isLess(left[i], right[j])) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < lenghtL) {
            arr[k++] = left[i++];
        }

        while (j < lenghtR) {
            arr[k++] = right[j++];
        }
    }
}
