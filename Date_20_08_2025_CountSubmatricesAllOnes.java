// Date : 21-08-2025
class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] heights = new int[m][n];

        // Build heights histogram (like for maximal rectangle problem)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    heights[i][j] = 0;
                } else {
                    heights[i][j] = (i == 0) ? 1 : heights[i - 1][j] + 1;
                }
            }
        }

        int total = 0;

        // For each row, count the number of rectangles ending at that row
        for (int i = 0; i < m; i++) {
            total += countRow(heights[i]);
        }

        return total;
    }

    // Helper function to count submatrices in a histogram row
    private int countRow(int[] heights) {
        int n = heights.length;
        int count = 0;
        int[] stack = new int[n];
        int top = -1;
        int[] sum = new int[n];

        for (int j = 0; j < n; j++) {
            while (top >= 0 && heights[stack[top]] >= heights[j]) {
                top--;
            }

            if (top == -1) {
                sum[j] = heights[j] * (j + 1);
            } else {
                sum[j] = sum[stack[top]] + heights[j] * (j - stack[top]);
            }

            stack[++top] = j;
            count += sum[j];
        }

        return count;
    }
}
