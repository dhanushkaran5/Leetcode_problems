class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        lazy = new long[4 * n];
        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long total = 0, answer = 0;
        for (int r = 0; r < n; r++) {
            int a = last[nums[r]] + 1;
            long before = query(1, 0, n - 1, a, r);
            long delta = (2 * before % MOD + (r - a + 1)) % MOD;
            total = (total + delta) % MOD;
            update(1, 0, n - 1, a, r, 1);
            answer = (answer + total) % MOD;
            last[nums[r]] = r;
        }
        return (int) answer;
    }

    void applyAdd(int node, int lo, int hi, long v) {
        sum[node] = (sum[node] + v * (hi - lo + 1)) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    void pushDown(int node, int lo, int hi) {
        if (lazy[node] != 0) {
            int mid = (lo + hi) >>> 1;
            applyAdd(2 * node, lo, mid, lazy[node]);
            applyAdd(2 * node + 1, mid + 1, hi, lazy[node]);
            lazy[node] = 0;
        }
    }

    void update(int node, int lo, int hi, int ql, int qr, long v) {
        if (qr < lo || hi < ql) return;
        if (ql <= lo && hi <= qr) {
            applyAdd(node, lo, hi, v);
            return;
        }
        pushDown(node, lo, hi);
        int mid = (lo + hi) >>> 1;
        update(2 * node, lo, mid, ql, qr, v);
        update(2 * node + 1, mid + 1, hi, ql, qr, v);
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
    }

    long query(int node, int lo, int hi, int ql, int qr) {
        if (qr < lo || hi < ql) return 0;
        if (ql <= lo && hi <= qr) return sum[node];
        pushDown(node, lo, hi);
        int mid = (lo + hi) >>> 1;
        return (query(2 * node, lo, mid, ql, qr)
                + query(2 * node + 1, mid + 1, hi, ql, qr)) % MOD;
    }
}