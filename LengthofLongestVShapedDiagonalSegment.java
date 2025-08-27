// Date : 27-08-2025
class Solution {
    public int lenOfVDiagonal(int[][] grid) {
        int[][] directions = {
                { 1, 1 }, // ↘
                { -1, -1 }, // ↖
                { 1, -1 }, // ↙
                { -1, 1 } // ↗
        };
        int[] turnMap = { 2, 3, 1, 0 }; // clockwise mapping

        int n = grid.length;
        int m = grid[0].length;
        int maxLen = 0;

        // Try starting from every cell with value 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 1)
                    continue;

                for (int d = 0; d < 4; d++) {
                    // Collect the full sequence before turn
                    List<int[]> path = new ArrayList<>();
                    int x = i, y = j;
                    path.add(new int[] { x, y });
                    int valIdx = 0;
                    int[] pattern = { 2, 0 };

                    while (true) {
                        x += directions[d][0];
                        y += directions[d][1];
                        if (!inBounds(x, y, n, m) || grid[x][y] != pattern[valIdx])
                            break;
                        path.add(new int[] { x, y });
                        valIdx ^= 1;
                    }

                    // Try making a turn at any point along this path
                    maxLen = Math.max(maxLen, path.size()); // even without a turn
                    int newDir = turnMap[d];
                    for (int k = 1; k < path.size(); k++) {
                        int[] pivot = path.get(k);
                        int nx = pivot[0];
                        int ny = pivot[1];
                        valIdx = (k % 2 == 0) ? 0 : 1;

                        int len = k + 1;
                        int tx = nx;
                        int ty = ny;

                        while (true) {
                            tx += directions[newDir][0];
                            ty += directions[newDir][1];
                            if (!inBounds(tx, ty, n, m) || grid[tx][ty] != pattern[valIdx])
                                break;
                            len++;
                            valIdx ^= 1;
                        }
                        maxLen = Math.max(maxLen, len);
                    }
                }
            }
        }

        return maxLen;
    }

    private boolean inBounds(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
