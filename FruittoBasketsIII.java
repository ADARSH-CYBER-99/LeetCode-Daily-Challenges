
// Date : 06-08-2025
import java.util.*;

class Solution {
    private int[] segtree; // Segment tree to store maximum basket capacity in a range

    // Build the segment tree recursively
    private void build(int[] baskets, int i, int l, int r) {
        if (l == r) {
            // Leaf node represents a single basket
            segtree[i] = baskets[l];
            return;
        }
        int mid = (l + r) / 2;
        build(baskets, 2 * i + 1, l, mid); // Build left child
        build(baskets, 2 * i + 2, mid + 1, r); // Build right child
        segtree[i] = Math.max(segtree[2 * i + 1], segtree[2 * i + 2]); // Store max of children
    }

    // Query the segment tree to find the leftmost basket with capacity >= val
    private boolean query(int i, int l, int r, int val) {
        if (segtree[i] < val) {
            // No basket in this segment can hold the fruit
            return false;
        }
        if (l == r) {
            // Leaf node found; mark basket as used
            segtree[i] = -1;
            return true;
        }
        int mid = (l + r) / 2;
        boolean placed;

        // Prefer left child to ensure leftmost placement
        if (segtree[2 * i + 1] >= val) {
            placed = query(2 * i + 1, l, mid, val);
        } else {
            placed = query(2 * i + 2, mid + 1, r, val);
        }

        // Update current node after placement
        segtree[i] = Math.max(segtree[2 * i + 1], segtree[2 * i + 2]);
        return placed;
    }

    // Main method to return the number of unplaced fruits
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        segtree = new int[4 * n]; // Safe size for segment tree
        Arrays.fill(segtree, -1); // Initialize tree values

        build(baskets, 0, 0, n - 1); // Build the segment tree with basket capacities

        int unplaced = 0;

        // Try placing each fruit in the leftmost suitable basket
        for (int fruit : fruits) {
            if (!query(0, 0, n - 1, fruit)) {
                unplaced++; // Couldn't place this fruit
            }
        }

        return unplaced;
    }
}