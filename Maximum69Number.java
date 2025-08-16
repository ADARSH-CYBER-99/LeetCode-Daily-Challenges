// Date : 16-08-2025
class Solution {
    public int maximum69Number(int num) {
        // Convert the integer to a char array for easy manipulation
        char[] digits = String.valueOf(num).toCharArray();

        // Traverse the digits from left to right
        for (int i = 0; i < digits.length; i++) {
            // Change the first occurrence of '6' to '9' to maximize the number
            if (digits[i] == '6') {
                digits[i] = '9';
                break; // Only one change allowed, so break after the first change
            }
        }

        // Convert the modified char array back to integer
        return Integer.parseInt(new String(digits));
    }
}
