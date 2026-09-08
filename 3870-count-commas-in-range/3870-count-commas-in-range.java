class Solution {
    public int countCommas(int n) {
        long answer = 0;
        long threshold = 1000;

        while (threshold <= n) {
            answer += n - threshold + 1;

            threshold *= 1000;
        }

        return (int) answer;
    }
}