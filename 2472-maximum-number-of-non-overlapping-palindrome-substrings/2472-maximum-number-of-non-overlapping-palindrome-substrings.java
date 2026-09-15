class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0, lastEnd = -1;

        for (int center = 0; center < 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 >= k && left > lastEnd) {
                    count++;
                    lastEnd = right;
                    break;
                }
                left--;
                right++;
            }
        }

        return count;
    }
}