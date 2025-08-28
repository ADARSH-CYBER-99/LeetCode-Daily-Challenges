
// Date : 28-08-2025
import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        // Bottom-left triangle including main diagonal: sort in non-increasing order
        for (int i = n - 1; i >= 0; i--) {
            sortAndSetDiagonal(grid, i, 0, false); // false → descending
        }

        // Top-right triangle: sort in non-decreasing order
        for (int j = 1; j < n; j++) {
            sortAndSetDiagonal(grid, 0, j, true); // true → ascending
        }

        return grid;
    }

    private void sortAndSetDiagonal(int[][] grid, int i, int j, boolean ascending) {
        int n = grid.length;
        List<Integer> diagonal = new ArrayList<>();

        int x = i, y = j;

        // Extract diagonal
        while (x < n && y < n) {
            diagonal.add(grid[x][y]);
            x++;
            y++;
        }

        // Sort diagonal
        if (ascending) {
            Collections.sort(diagonal);
        } else {
            diagonal.sort(Collections.reverseOrder());
        }

        // Put sorted values back into matrix
        x = i;
        y = j;
        int idx = 0;
        while (x < n && y < n) {
            grid[x][y] = diagonal.get(idx++);
            x++;
            y++;
        }
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] grid1 = { { 1, 7, 3 }, { 9, 8, 2 }, { 4, 5, 6 } };
        System.out.println("Output: " + Arrays.deepToString(sol.sortMatrix(grid1)));

        int[][] grid2 = { { 0, 1 }, { 1, 2 } };
        System.out.println("Output: " + Arrays.deepToString(sol.sortMatrix(grid2)));

        int[][] grid3 = { { 1 } };
        System.out.println("Output: " + Arrays.deepToString(sol.sortMatrix(grid3)));
    }
}
