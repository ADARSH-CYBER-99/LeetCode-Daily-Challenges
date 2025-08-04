import java.util.*;

class Solution {
    public int totalFruit(int[] fruits) {
        // A HashMap to keep track of the count of each fruit type in our basket
        Map<Integer, Integer> basket = new HashMap<>();

        // 'left' marks the start of our sliding window
        int left = 0;

        // Variable to store the maximum number of fruits collected
        int maxFruits = 0;

        // Expand the window with 'right' pointer
        for (int right = 0; right < fruits.length; right++) {
            int fruit = fruits[right];

            // Add the current fruit into the basket (increase its count)
            basket.put(fruit, basket.getOrDefault(fruit, 0) + 1);

            // If we have more than 2 different fruits in the basket,
            // shrink the window from the left until we only have 2 types
            while (basket.size() > 2) {
                int leftFruit = fruits[left];

                // Reduce count of the fruit at 'left' since we are moving the window
                basket.put(leftFruit, basket.get(leftFruit) - 1);

                // If count becomes zero, remove that fruit type completely
                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }

                // Move the left pointer ahead
                left++;
            }

            // Calculate the current window size and update the maximum
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        // Return the maximum number of fruits we can collect
        return maxFruits;
    }
}
