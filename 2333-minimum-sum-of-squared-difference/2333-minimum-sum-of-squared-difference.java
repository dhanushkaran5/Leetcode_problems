class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long k = (long) k1 + k2;
        long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (total <= k) {
            return 0;
        }

        int left = 0, right = maxDiff;
        while (left < right) {
            int mid = left + (right - left) / 2;
            long operations = 0;
            
            for (int d : diff) {
                if (d > mid) {
                    operations += d - mid;
                }
            }
            
            if (operations <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int threshold = left;
        long remaining = k;
        
        for (int d : diff) {
            if (d > threshold) {
                remaining -= d - threshold;
            }
        }

        long result = 0;
        for (int d : diff) {
            d = Math.min(d, threshold);
            
            if (d == threshold && remaining > 0) {
                d--;
                remaining--;
            }
            
            result += (long) d * d;
        }

        return result;
    }
}