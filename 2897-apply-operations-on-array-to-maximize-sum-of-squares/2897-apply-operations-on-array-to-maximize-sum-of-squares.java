class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bits = new int[31];

        for (int x : nums)
            for (int b = 0; b < 31; b++)
                if ((x & (1 << b)) != 0)
                    bits[b]++;

        long ans = 0;

        while (k-- > 0) {
            long x = 0;

            for (int b = 0; b < 31; b++)
                if (bits[b] > 0) {
                    x |= 1L << b;
                    bits[b]--;
                }

            ans = (ans + x * x) % MOD;
        }

        return (int) ans;
    }
}