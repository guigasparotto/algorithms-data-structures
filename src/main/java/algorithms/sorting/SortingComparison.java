package algorithms.sorting;

import utils.Utils;

public class SortingComparison {

    public static void main(String[] args) {
        var arrayNumber = 8;
        var size = 10_000_000;
        var minBound = 0;
        var maxBound = 5_000_000;

        var arrays = Utils.createMultipleIdenticalArrays(arrayNumber, size, minBound, maxBound);

//        System.out.println("Insertion sort:");
//        Utils.runWithTime(Insertion::sort, arrays[0]);
//        System.out.println("Sorted: " + Utils.isSorted(arrays[0]));
//
//        System.out.println("\nInsertion sort with shifting right:");
//        Utils.runWithTime(Insertion::sortShift, arrays[1]);
//        System.out.println("Sorted: " + Utils.isSorted(arrays[1]));
//
//        System.out.println("\nSelection sort:");
//        Utils.runWithTime(Selection::sort, arrays[2]);
//        System.out.println("Sorted: " + Utils.isSorted(arrays[2]));
//
//        System.out.println("\nShell sort:");
//        Utils.runWithTime(Insertion::sort, arrays[3]);
//        System.out.println("Sorted: " + Utils.isSorted(arrays[3]));

        System.out.println("\nMerge sort:");
         Utils.runWithTime(MergeSort::sort, arrays[4]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[4]));

        System.out.println("\nMerge sort in-place:");
        Utils.runWithTime(MergeSort::inPlace, arrays[5]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[5]));

        System.out.println("\nQuick sort:");
        Utils.runWithTime(QuickSort::sort, arrays[6]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[6]));

        System.out.println("\nQuick sort (parallel):");
        Utils.runWithTime(QuickSort::parallelSort, arrays[7]);
        System.out.println("Sorted: " + Utils.isSorted(arrays[7]));
    }

}
