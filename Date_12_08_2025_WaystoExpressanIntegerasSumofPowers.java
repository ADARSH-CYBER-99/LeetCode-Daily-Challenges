// Date : 12-08-2025
class Solution {
    // Modulo constant to avoid large number overflow
    static final int MOD = 1_000_000_007;

    public int numberOfWays(int n, int x) {
        // Step 1: Precompute all x-th powers of positive integers <= n
        // Example: if x = 2, store 1^2, 2^2, 3^2, ...
        int[] powers = new int[n + 1];
        int maxBase = 0; // largest base whose power <= n

        for (int i = 1; i <= n; i++) {
            long powVal = 1;
            // Manual power calculation (faster than Math.pow for small integers)
            for (int p = 0; p < x; p++) {
                powVal *= i;
            }
            if (powVal > n) break; // No need to store powers greater than n
            powers[i] = (int) powVal;
            maxBase = i;
        }

        // Step 2: Initialize DP array
        // dp[sum] = number of ways to form 'sum' using unique integers' x-th powers
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: 1 way to make sum 0 (choose nothing)

        // Step 3: Fill DP table
        // We loop over each base number (ensuring uniqueness by forward order)
        for (int base = 1; base <= maxBase; base++) {
            int val = powers[base];
            // Loop backwards so each number is only used once (0/1 knapsack style)
            for (int sum = n; sum >= val; sum--) {
                dp[sum] = (dp[sum] + dp[sum - val]) % MOD;
            }
        }

        // Step 4: Return the number of ways to form 'n'
        return dp[n];
    }
}



