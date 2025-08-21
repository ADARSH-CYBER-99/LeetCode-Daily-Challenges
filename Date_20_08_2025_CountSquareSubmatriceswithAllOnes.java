// Date : 20-08-2025 
class Solution {
    public int countSquares(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        // We iterate through each cell in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Skip cells with 0
                if (matrix[i][j] == 1) {
                    // For all cells not in the first row or first column
                    if (i > 0 && j > 0) {
                        // Update current cell with the size of the largest square ending here
                        matrix[i][j] = 1 + Math.min(
                                Math.min(matrix[i - 1][j], matrix[i][j - 1]),
                                matrix[i - 1][j - 1]);
                    }
                    // Add the value at current cell to the result
                    count += matrix[i][j];
                }
            }
        }

        return count;
    }
}
