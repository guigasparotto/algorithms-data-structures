package algorithms;

import java.util.Arrays;

public class WeightedQuickUnion {
    private final int[] ids;
    private final int[] sizes;
    private int count;

    public WeightedQuickUnion(int n) {
        count = n;
        ids = new int[n];
        sizes = new int[n];
        for (var i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    public int getCount() {
        return count;
    }

    // Returns the root of the tree to which the component p belongs
    public int find(int p) {
        while (p != ids[p]) {
            p = ids[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // Make smaller root point to the larger one
        if (sizes[rootP] < sizes[rootQ]) {
            ids[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            ids[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
        count--;
    }

    @Override
    public String toString() {
        return "Array: " + Arrays.toString(ids) + "\n" +
                "Sizes: " + Arrays.toString(sizes);
    }

    public static void main(String[] args) {
        var arr = new WeightedQuickUnion(11);
        arr.union(2, 7);
        arr.union(7, 8);
        arr.union(3, 10);
        arr.union(3, 2);
        System.out.println(arr);
    }
}
