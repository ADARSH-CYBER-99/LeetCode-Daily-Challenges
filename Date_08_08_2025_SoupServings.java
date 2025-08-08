// Date : 08-08-2025
import java.util.HashMap;
import java.util.Map;

class Solution {

    // Memoization map to store already computed states
    private Map<String, Double> memo = new HashMap<>();

    public double soupServings(int n) {
        // Optimization: for large n, the probability is effectively 1.0
        if (n > 4800) return 1.0;

        // Scale down n by 25 since all operations are multiples of 25
        return helper((int)Math.ceil(n / 25.0), (int)Math.ceil(n / 25.0));
    }

    private double helper(int a, int b) {
        // Base case: both soups empty at the same time
        if (a <= 0 && b <= 0) return 0.5;

        // Base case: only soup A is empty
        if (a <= 0) return 1.0;

        // Base case: only soup B is empty
        if (b <= 0) return 0.0;

        // Create a unique key for memoization
        String key = a + "," + b;

        // Return the result if it's already computed
        if (memo.containsKey(key)) return memo.get(key);

        // Recursive computation of probabilities for each operation
        double prob = 0.25 * (
            helper(a - 4, b) +      // Operation 1: A - 100, B - 0
            helper(a - 3, b - 1) +  // Operation 2: A - 75,  B - 25
            helper(a - 2, b - 2) +  // Operation 3: A - 50,  B - 50
            helper(a - 1, b - 3)    // Operation 4: A - 25,  B - 75
        );

        // Store the computed result in the map
        memo.put(key, prob);

        return prob;
    }
}
