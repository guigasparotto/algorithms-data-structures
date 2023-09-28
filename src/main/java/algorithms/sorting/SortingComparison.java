package algorithms.sorting;

import algorithms.sorting.Insertion;
import utils.Utils;

import java.util.Arrays;
import java.util.function.Consumer;

public class SortingComparison {

    public static void main(String[] args) {
        var arrayNumber = 6;
        var size = 10_000;
        var minBound = 0;
        var maxBound = 500_000;

        var arrays = createMultipleIdenticalArrays(arrayNumber, size, minBound, maxBound);

        System.out.println("Insertion sort:");
        Utils.runWithTime(Insertion::sort, arrays[0]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[0]));

        System.out.println("\nInsertion sort with shifting right:");
        Utils.runWithTime(Insertion::sortShift, arrays[1]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[1]));

        System.out.println("\nSelection sort:");
        Utils.runWithTime(Selection::sort, arrays[2]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[2]));

        System.out.println("\nShell sort:");
        Utils.runWithTime(Insertion::sort, arrays[3]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[3]));

        System.out.println("\nMerge sort:");
         Utils.runWithTime(MergeSort::sort, arrays[4]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[4]));

        System.out.println("\nMerge sort in-place:");
        Utils.runWithTime(MergeSort::inPlace, arrays[5]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[5]));
    }

    public static Integer[][] createMultipleIdenticalArrays(int n, int arrSize, int minBound, int maxBound) {
        var result = new Integer[n][];
        var original = Utils.createRandomArray(arrSize, minBound, maxBound);

        for (var i = 0; i < result.length; i++) {
             result[i] = Arrays.copyOfRange(original, 0, original.length);
        }

        return result;
    }
}
