import java.util.*;
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            arr[i][0] = nums[i];
            arr[i][1] = sum;
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = arr[i][0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != sorted[i]) {
                int j = map.get(sorted[i]);
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                map.put(nums[j], j);
                map.put(nums[i], i);
                swaps++;
            }
        }
        return swaps;
    }
}