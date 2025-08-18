// Date : 18-08-2025
import java.util.*;

class Solution {
    // Small tolerance for floating-point comparison
    static final double EPSILON = 1e-6;
    
    // The target number we want to reach
    static final double TARGET = 24.0;

    public boolean judgePoint24(int[] cards) {
        // Convert int array to list of Doubles for division and floating-point operations
        List<Double> nums = new ArrayList<>();
        for (int card : cards) {
            nums.add((double) card);
        }
        // Start recursive backtracking
        return solve(nums);
    }

    private boolean solve(List<Double> nums) {
        // Base case: only one number left
        if (nums.size() == 1) {
            // Check if the number is close enough to 24 (account for floating-point errors)
            return Math.abs(nums.get(0) - TARGET) < EPSILON;
        }

        int n = nums.size();

        // Try every pair of numbers in the list
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) continue; // Skip using the same number twice

                // Prepare a new list for the next recursive call, excluding the i-th and j-th numbers
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < n; ++k) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                double a = nums.get(i);
                double b = nums.get(j);

                // Try all 4 operations

                // a + b
                next.add(a + b);
                if (solve(next)) return true;
                next.remove(next.size() - 1); // backtrack

                // a - b
                next.add(a - b);
                if (solve(next)) return true;
                next.remove(next.size() - 1); // backtrack

                // a * b
                next.add(a * b);
                if (solve(next)) return true;
                next.remove(next.size() - 1); // backtrack

                // a / b (only if b is not zero to avoid division by zero)
                if (Math.abs(b) > EPSILON) {
                    next.add(a / b);
                    if (solve(next)) return true;
                    next.remove(next.size() - 1); // backtrack
                }
            }
        }

        // No valid combination found
        return false;
    }
}
