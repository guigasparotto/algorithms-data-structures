package algorithms;

import java.util.Arrays;

/**
 *     In the context of algorithms like Quick-Find, Quick-Union, and their variants, the array used to
 *     represent component numbers or groupings is often referred to as the "id array" or "parent array."
 * <p>
 *     - Id Array: In Quick-Find, each entry in the array represents the component id to which the
 *     element belongs. All elements with the same id are considered connected.
 *     - Parent Array: In Quick-Union and its variants, the array represents a forest of trees, where
 *     each entry points to the parent of the element in its tree. The root of each tree represents
 *     the component id.
 * <p>
 *     In general, this kind of array is used to implement a data structure called a "Disjoint Set"
 *     or "Union-Find," which keeps track of a partition of a set into disjoint, non-overlapping subsets
 *     (components). The terms "id array" or "parent array" are specific to the way these algorithms
 *     represent the disjoint sets, but there isn't a universal term that applies to all such
 *     representations in every algorithm.
 * <p>
 *     It's performance can get to O(n^2) in the worst case.
 */

public class QuickFind {
    private final int[] ids;
    private int count;

    public QuickFind(int n) {
        ids = new int[n];
        for (var i = 0; i < n; i++) {
            ids[i] = i;
        }

        count = n;
    }

    public int getCount() {
        return count;
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return ids[p] == ids[q];
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) return;

        for (var i = 0; i < ids.length; i++) {
            if (ids[i] == pid) {
                ids[i] = qid;
            }
        }
        count--;
    }

    public int find(int p) {
        validate(p);
        return ids[p];
    }

    @Override
    public String toString() {
        return Arrays.toString(ids);
    }

    private void validate(int p) {
        if (p < 0 || p >= ids.length) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (ids.length - 1));
        }
    }

    public static void main(String[] args) {
        var qf = new QuickFind(100);

        long before = System.nanoTime();
        qf.union(2, 30);
        qf.union(15, 50);
        System.out.println(qf);
        System.out.println("Time: " + (System.nanoTime() - before));
    }
}
