// Date : 13-08-2025
class Solution {
    public boolean isPowerOfThree(int n) {
        // 3^19 = 1162261467 is the largest power of 3 that fits in a 32-bit signed
        // integer
        // If n is a power of 3, it must divide 1162261467 with no remainder
        return n > 0 && 1162261467 % n == 0;
    }
}
