import java.util.*;

class Solution {
    public String getPermutation(int n, int k) {
        // Store numbers from 1 to n
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // Precompute factorials
        int[] factorial = new int[n + 1];
        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // Convert k to zero-based indexing
        k--;

        StringBuilder result = new StringBuilder();

        // Build the permutation position by position
        for (int remaining = n; remaining > 0; remaining--) {
            int blockSize = factorial[remaining - 1];

            // Find which block contains k
            int index = k / blockSize;

            // Select the number from the block
            result.append(numbers.get(index));
            numbers.remove(index);

            // Move k inside the selected block
            k %= blockSize;
        }

        return result.toString();
    }
}