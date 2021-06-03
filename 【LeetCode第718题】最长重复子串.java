/*
	给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
示例 1:
输入:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出: 3
解释: 
长度最长的公共子数组是 [3, 2, 1]。

	解答:很明显的动态规划题目，二维数组可以压缩为一维数组.
*/

class Solution {
    public int findLength(int[] A, int[] B) {
        int lenA = A.length;
        int lenB = B.length;
        int res = 0;
        //int[][] dp = new int[lenA + 1][lenB + 1];
        int[] dp = new int[lenB + 1];
        for (int a = 1 ; a <= lenA ; a++) {
            for (int b = lenB ; b >= 1 ; b--) {
                if (A[a - 1] == B[b - 1]) {
                    dp[b] = 1 + dp[b - 1];
                }else dp[b] = 0;
                res = Math.max(dp[b] , res);
            }
        }
        return res;
    }
}

/*
	时间复杂度:O(m*n)
	空间复杂度:O(n)
*/