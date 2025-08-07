// Date : 07-08-2025
class Solution {

    /*
     * Checks whether a given coordinate lies in the upper triangular matrix.
     * Used to restrict DP traversal only to valid paths.
     */
    private boolean isPossible(int x, int y, int n) {
        return y > x && x >= 0 && x < n && y >= 0 && y < n;
    }

    /*
     * Calculates the maximum score by simulating one of the paths from the triangle
     * part of the grid (either original or transposed).
     */
    private int maxscore(int[][] fruits) {
        int n = fruits.length;
        int[][] dp = new int[n][n];

        // Initialize dp with original fruits
        for (int i = 0; i < n; i++) {
            System.arraycopy(fruits[i], 0, dp[i], 0, n);
        }

        // Handle small grids separately
        if (n == 2) return dp[0][n - 1];
        if (n == 3) return dp[0][n - 1] + dp[1][n - 1];

        // Dynamic programming over upper triangular matrix
        for (int i = n - 2; i > 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int x = i - 1;
                for (int y = j - 1; y <= j + 1; y++) {
                    if (isPossible(x, y, n)) {
                        // Update max fruit count at (x, y)
                        dp[x][y] = Math.max(dp[x][y], fruits[x][y] + dp[i][j]);
                    }
                }
            }
        }

        // Return score starting from top to near bottom-right
        return fruits[0][n - 1] + Math.max(dp[1][n - 2], dp[1][n - 1]);
    }

    /*
     * Main method to calculate the maximum fruits that can be collected.
     * Runs maxscore on original and transposed grid to simulate both symmetric paths.
     */
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int ans = 0;

        // Add main diagonal fruits (each child gets one unique diagonal cell)
        for (int i = 0; i < n; i++) {
            ans += fruits[i][i];
        }

        // Collect max score from one direction
        ans += maxscore(fruits);

        // Transpose the matrix to simulate the other direction
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = fruits[i][j];
                fruits[i][j] = fruits[j][i];
                fruits[j][i] = temp;
            }
        }

        // Collect max score from transposed direction
        ans += maxscore(fruits);

        return ans;
    }
}

