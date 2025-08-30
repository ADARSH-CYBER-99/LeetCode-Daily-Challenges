// Date : 30-08-2025
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Use HashSets to track seen numbers in rows, columns, and 3x3 sub-boxes
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Traverse the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current = board[i][j];

                if (current == '.')
                    continue;

                // Check row
                if (rows[i].contains(current)) {
                    return false;
                }
                rows[i].add(current);

                // Check column
                if (cols[j].contains(current)) {
                    return false;
                }
                cols[j].add(current);

                // Check 3x3 sub-box
                int boxIndex = (i / 3) * 3 + (j / 3);
                if (boxes[boxIndex].contains(current)) {
                    return false;
                }
                boxes[boxIndex].add(current);
            }
        }

        return true; // No conflicts found
    }
}
