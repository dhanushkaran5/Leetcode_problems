class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n=arr.length,i=0,j=0,sum=0,bestLen=Integer.MAX_VALUE,result=Integer.MAX_VALUE;
        int[]minTillIndex=new int[n];
        Arrays.fill(minTillIndex,Integer.MAX_VALUE);
        while(j<n){
            sum+=arr[j];
            while(i<j&&sum>target){
                sum-=arr[i++];
            }
            if(target==sum){
                int len=j-i+1;
                if(i>0&&minTillIndex[i-1]!=Integer.MAX_VALUE)result= Math.min(result,len+minTillIndex[i-1]);
                bestLen=Math.min(len,bestLen);
            }
            minTillIndex[j]=bestLen;
            j++;
        }
        return result==Integer.MAX_VALUE?-1:result;
    }
}