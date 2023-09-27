package algorithms.sorting;

import algorithms.sorting.Insertion;
import utils.Utils;

import java.util.function.Consumer;

public class SortingComparison {

    public static void main(String[] args) {
        var size = 100_000;
        var minBound = 0;
        var maxBound = 5_000_000;

        System.out.println("Insertion sort:");
        var arr = Utils.createRandomArray(size, minBound, maxBound);
        Consumer<Integer[]> insertion = Insertion::sort;
        Utils.runWithTime(insertion, arr);
        System.out.println("Sorted: " + Utils.isSorted(arr));

        System.out.println("\nInsertion sort with shifting right:");
        var arr2 = Utils.createRandomArray(size, minBound, maxBound);
        Consumer<Integer[]> shiftInsertion = Insertion::sortShift;
        Utils.runWithTime(shiftInsertion, arr2);
        System.out.println("Sorted: " + Utils.isSorted(arr2));

        System.out.println("\nSelection sort:");
        var arr3 = Utils.createRandomArray(size, minBound, maxBound);
        Consumer<Integer[]> selection = Selection::sort;
        Utils.runWithTime(selection, arr3);
        System.out.println("Sorted: " + Utils.isSorted(arr3));

        System.out.println("\nShell sort:");
        var arr4 = Utils.createRandomArray(size, minBound, maxBound);
        Consumer<Integer[]> shell = Insertion::sort;
        Utils.runWithTime(shell, arr4);
        System.out.println("Sorted: " + Utils.isSorted(arr4));

        System.out.println("\nMerge sort:");
        var arr5 = Utils.createRandomArray(size, minBound, maxBound);
        Consumer<Integer[]> mergeSort = MergeSort::sort;
        Utils.runWithTime(mergeSort, arr5);
        System.out.println("Sorted: " + Utils.isSorted(arr5));

        System.out.println("\nMerge sort in-place:");
        var arr6 = Utils.createRandomArray(size, minBound, maxBound);
        Consumer<Integer[]> inPlace = MergeSort::inPlace;
        Utils.runWithTime(inPlace, arr6);
        System.out.println("Sorted: " + Utils.isSorted(arr6));
    }
}
