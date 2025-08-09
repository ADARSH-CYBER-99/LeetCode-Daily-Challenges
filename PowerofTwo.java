class Solution {
    
    public boolean isPowerOfTwo(int n) {
        // Check if n is greater than 0
        // AND (n & (n - 1)) == 0 ensures only one bit is set in the number
        // For example: 8 -> 1000, 8 - 1 = 7 -> 0111, 1000 & 0111 = 0000
        return n > 0 && (n & (n - 1)) == 0;
    }

}