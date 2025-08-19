// Date : 19-08-2025
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0; // Total number of zero-filled subarrays
        long current = 0; // Current streak of consecutive zeros

        for (int num : nums) {
            if (num == 0) {
                current++; // Extend the current zero sequence
                count += current; // Add all subarrays ending at this position
            } else {
                current = 0; // Reset current streak if non-zero
            }
        }

        return count;
    }
}
