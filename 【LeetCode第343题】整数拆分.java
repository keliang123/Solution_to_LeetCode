/*
	给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
示例 1:
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。

	解答：动态规划。 
	dp[i]数组  表示i这个数拆分后最大的值。
	dp[i] = max(j*dp[i - j] , i - j)  j从1到i-1，而且是至少拆成两部分。
*/

class Solution {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        int[] res = new int[n + 1];
        res[1] = 1 ; res[2] = 2;
        for (int i = 3 ; i <= n ; ++i) {
            for (int j = 1 ; j < i ; ++j) {
                res[i] = Math.max(res[i] , j*res[i - j]);
            }
            if (i != n)
                res[i] = Math.max(res[i] , i);
        }
        return res[n];
    }
}

/*
	时间复杂度：O(n^2)
	空间复杂度：O(n)
*/