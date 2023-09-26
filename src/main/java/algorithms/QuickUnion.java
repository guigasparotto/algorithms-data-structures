package algorithms;

/**
 * The representation of linked components in a QuickUnion array are different from the QuickFind
 * While 2, 5, 7 and 10 are linked in the QuickFind array with [0 1 10 3 4 10 6 10 8 9 10]
 * <p>
 * they are linked in a QuickUnion array as: [0 1 5 3 4 7 6 10 8 9 10]
 * <p>
 * In the QuickFind algorithm, all entries in the array representing connected components are
 * changed to be the same, indicating that they belong to the same connected component.
 * In contrast, in the QuickUnion algorithm, the array represents a forest of trees, where each
 * entry in the array points to the parent of that element in its tree. The root of each tree
 * represents the component to which all elements in that tree belong.
 * <p>
 * Definitions:
 * - the size of a tree is its number of nodes
 * - the depth of a node in a tree is the number of links on the path from it to the root
 * - the height of a tree is the maximum depth among its nodes
 * </p>
 */

public class QuickUnion {
    private int[] ids;
    private int count;

    public QuickUnion(int n) {
        count = n;
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
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

    // If the components have the same root, nothing to do, otherwise,
    // the root of p will be changed to the same value as root of q,
    // connecting the trees
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        ids[rootP] = rootQ;
        count--;
    }
}
