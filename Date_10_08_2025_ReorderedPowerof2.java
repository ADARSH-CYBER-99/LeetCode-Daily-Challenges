// Date : 10-08-2025
import java.util.*;

class Solution {

    public boolean reorderedPowerOf2(int n) {
        // Get the sorted digits of the input number
        String target = sortDigits(n);

        // Precompute powers of 2 up to 2^30 and compare their sorted digits
        for (int i = 0; i <= 30; i++) {
            int power = 1 << i; // Compute 2^i using bitwise shift
            if (target.equals(sortDigits(power))) {
                return true; // Match found
            }
        }
        return false; // No match found
    }

    private String sortDigits(int num) {
        char[] chars = String.valueOf(num).toCharArray(); // Convert number to char array
        Arrays.sort(chars); // Sort the digits
        return new String(chars); // Return sorted digits as a string
    }
}
