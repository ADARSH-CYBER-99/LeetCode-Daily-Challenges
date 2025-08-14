// Date : 14-08-2025
class Solution {

    public String largestGoodInteger(String num) {
        // Initialize a variable to keep track of the largest good integer found.
        String max = "";

        // Loop through the string, checking all substrings of length 3.
        for (int i = 0; i <= num.length() - 3; i++) {
            // Get the three consecutive characters.
            char ch1 = num.charAt(i);
            char ch2 = num.charAt(i + 1);
            char ch3 = num.charAt(i + 2);

            // Check if all three characters are the same.
            if (ch1 == ch2 && ch2 == ch3) {
                // Extract the current good integer substring.
                String current = num.substring(i, i + 3);

                // Update max if current good integer is lexicographically larger.
                if (max.compareTo(current) < 0) {
                    max = current;
                }
            }
        }

        // Return the largest good integer found, or an empty string if none.
        return max;
    }
}
