// Date : 11-08-2025
import java.util.*;

class Solution {
    // Define modulo constant (as per the problem constraints)
    private static final int MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        // Step 1: Extract powers of 2 from the binary representation of n
        // Each set bit in `n` corresponds to a power of 2 used in the sum
        List<Integer> powers = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                powers.add(1 << i); // Add power of 2
            }
        }

        // Step 2: Precompute prefix products of the powers array using modular
        // multiplication
        int m = powers.size();
        long[] prefix = new long[m];
        prefix[0] = powers.get(0);
        for (int i = 1; i < m; i++) {
            prefix[i] = (prefix[i - 1] * powers.get(i)) % MOD;
        }

        // Step 3: Answer each query using prefix products and modular inverse
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            if (left == 0) {
                result[i] = (int) (prefix[right]); // Direct prefix product
            } else {
                // Use modular inverse to compute product of subarray [left, right]
                result[i] = (int) ((prefix[right] * modInverse(prefix[left - 1], MOD)) % MOD);
            }
        }

        return result;
    }

    /**
     * Computes modular inverse using Fermat's Little Theorem:
     * a^(mod - 2) ≡ a^(-1) (mod mod), valid when mod is prime
     */
    private long modInverse(long a, int mod) {
        return modPow(a, mod - 2, mod);
    }

    /**
     * Computes (base^exp) % mod using fast modular exponentiation
     */
    private long modPow(long base, int exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}

