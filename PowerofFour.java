// Date : 15-08-2025
class Solution {
    public boolean isPowerOfFour(int n) {
        // Condition 1: n must be positive
        // Condition 2: n must be a power of 2 -> only one bit is set in binary
        // Condition 3: That set bit must be at an even position (0-indexed)
        // Example: 1 (0001), 4 (0100), 16 (0001 0000) — all set bits at even positions

        // (n & (n - 1)) == 0 checks if only one bit is set (i.e., power of 2)
        // (n & 0x55555555) != 0 ensures that the set bit is at an even position
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
}
