// Date : 17-08-2025
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        // Edge case: if k == 0, Alice never draws, so she ends with 0 points
        // or if n is large enough that she can't go over it even with maxPts
        if (k == 0 || n >= k + maxPts) return 1.0;

        // dp[i] = probability of getting i points
        double[] dp = new double[n + 1];
        dp[0] = 1.0; // Start with 0 points, with 100% probability

        double Wsum = 1.0;  // Sum of probabilities in the window [i-maxPts, i-1]
        double result = 0.0; // Final probability that Alice ends with <= n points

        // Loop through each possible point total from 1 to n
        for (int i = 1; i <= n; i++) {
            // Probability of reaching i points is the average of the last maxPts states
            dp[i] = Wsum / maxPts;

            // If i is still less than k, Alice can continue drawing
            if (i < k) {
                Wsum += dp[i]; // Add this to the sliding window
            } else {
                // Otherwise, Alice stops at i points, count it in result
                result += dp[i];
            }

            // Maintain the window size to maxPts
            if (i - maxPts >= 0) {
                Wsum -= dp[i - maxPts]; // Slide the window
            }
        }

        return result;
    }
}

