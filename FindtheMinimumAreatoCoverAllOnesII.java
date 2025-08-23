// Date : 23-08-2025
class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private int[][] grid;

    public int minimumSum(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        int ans = m * n; // upper bound

        // Two horizontal cuts → 3 horizontal stripes
        for (int i1 = 0; i1 < m - 1; i1++) {
            for (int i2 = i1 + 1; i2 < m - 1; i2++) {
                ans = Math.min(ans,
                        f(0, 0, i1, n - 1)
                                + f(i1 + 1, 0, i2, n - 1)
                                + f(i2 + 1, 0, m - 1, n - 1));
            }
        }

        // Two vertical cuts → 3 vertical stripes
        for (int j1 = 0; j1 < n - 1; j1++) {
            for (int j2 = j1 + 1; j2 < n - 1; j2++) {
                ans = Math.min(ans,
                        f(0, 0, m - 1, j1)
                                + f(0, j1 + 1, m - 1, j2)
                                + f(0, j2 + 1, m - 1, n - 1));
            }
        }

        // Mixed splits (T-shaped)
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                // horizontal first, vertical in top
                ans = Math.min(ans,
                        f(0, 0, i, j)
                                + f(0, j + 1, i, n - 1)
                                + f(i + 1, 0, m - 1, n - 1));
                // horizontal first, vertical in bottom
                ans = Math.min(ans,
                        f(0, 0, i, n - 1)
                                + f(i + 1, 0, m - 1, j)
                                + f(i + 1, j + 1, m - 1, n - 1));
                // vertical first, horizontal in left
                ans = Math.min(ans,
                        f(0, 0, i, j)
                                + f(i + 1, 0, m - 1, j)
                                + f(0, j + 1, m - 1, n - 1));
                // vertical first, horizontal in right
                ans = Math.min(ans,
                        f(0, 0, m - 1, j)
                                + f(0, j + 1, i, n - 1)
                                + f(i + 1, j + 1, m - 1, n - 1));
            }
        }

        return ans;
    }

    /**
     * Returns area of minimal bounding rectangle covering all 1's
     * in grid[i1..i2][j1..j2]. If none, returns 0.
     */
    private int f(int i1, int j1, int i2, int j2) {
        int minX = INF, minY = INF, maxX = -INF, maxY = -INF;
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (grid[i][j] == 1) {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        if (minX == INF)
            return 0;
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}
