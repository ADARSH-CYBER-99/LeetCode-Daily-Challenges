// Date : 04-08-2025
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        // Get the total number of fruits (and baskets, since both arrays have the same
        // length)

        boolean[] used = new boolean[n];
        // Create a boolean array to track which baskets are already used

        int unplacedCount = 0;
        // Counter to keep track of fruits that cannot be placed

        // Loop through each fruit
        for (int i = 0; i < n; i++) {
            boolean placed = false;
            // Flag to check if the current fruit is placed

            // Try to find a suitable basket for the current fruit
            for (int j = 0; j < n; j++) {
                // Check if this basket is unused AND large enough to hold the fruit
                if (!used[j] && baskets[j] >= fruits[i]) {
                    used[j] = true;
                    // Mark the basket as used so it can't be used again
                    placed = true;
                    // Mark the fruit as placed
                    break;
                    // Exit loop since the fruit is placed
                }
            }

            // If no suitable basket was found, increment unplaced fruit count
            if (!placed) {
                unplacedCount++;
            }
        }

        return unplacedCount;
        // Return the total number of fruits that could not be placed in baskets
    }
}

