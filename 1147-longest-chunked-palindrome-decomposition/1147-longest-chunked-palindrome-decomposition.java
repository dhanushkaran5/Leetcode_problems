class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        int k = 0, totalLength = 0;
        int str1Start = 0, str1End = 0;
        int str2Start = n-1, str2End = n;
        while (str1End < str2Start) {
            if (text.substring(str1Start, str1End + 1).equals(text.substring(str2Start, str2End))) {
                totalLength += (str2End - str2Start) * 2; 
                k++;
                str1Start = str1End + 1; 
                str2End = str2Start; 
            }
            str1End++;
            str2Start--;
        }
        if (totalLength < n) return (k * 2) + 1;
        return k * 2;
    }
}
        