// Date : 25-08-2025
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0)
            return new int[0];

        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0, dir = 1; // dir = 1: up, -1: down
        int index = 0;

        while (index < m * n) {
            result[index++] = mat[row][col];

            if (dir == 1) { // Moving up
                if (col == n - 1) {
                    row++; // hit right wall
                    dir = -1;
                } else if (row == 0) {
                    col++; // hit top wall
                    dir = -1;
                } else {
                    row--;
                    col++;
                }
            } else { // Moving down
                if (row == m - 1) {
                    col++; // hit bottom wall
                    dir = 1;
                } else if (col == 0) {
                    row++; // hit left wall
                    dir = 1;
                } else {
                    row++;
                    col--;
                }
            }
        }

        return result;
    }
}
