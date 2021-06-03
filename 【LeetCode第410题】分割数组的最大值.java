/*
	给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
	注意:
	数组长度 n 满足以下条件:

	1 ≤ n ≤ 1000
	1 ≤ m ≤ min(50, n)
	示例:

	输入:
	nums = [7,2,5,10,8]
	m = 2

	输出:
	18

	解释:
	一共有四种方法将nums分割为2个子数组。
	其中最好的方式是将其分为[7,2,5] 和 [10,8]，
	因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。

	解答：动态规划:
		dp[i][j] 前i个数分割为j组，转移方程,  前i个数分为两组，最后一段和前j-1段，所以两组的分组就有i种分法，k就要遍历所有的i。sub[i - k]表示从k到i的和
			dp[i][j] = min( max(dp[k][j - 1] , sub[i - k]))  k要从0遍历到i - 1;
			
*/

class Solution {
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        int[][] dp = new int[len + 1][m + 1];
        for (int i = 0 ; i <= len ; ++i) {
            Arrays.fill(dp[i] , Integer.MAX_VALUE);
        }
        //数组前i个数之和
        int[] sub = new int[len + 1];
        for (int i = 0 ; i < len ; ++i)
            sub[i + 1] = sub[i] + nums[i];
        
        //动态规划
        dp[0][0] = 0;
        for (int i = 1 ; i <= len ; ++i) {
            for (int j = 1 ; j <= Math.min(i , m) ; ++j) {
                for (int k = 0 ; k < i ; k ++) {
                    int temp = Math.max(dp[k][j - 1] , sub[i] - sub[k]);
                    dp[i][j] = Math.min(dp[i][j] , temp);
                }
            }
        }
        return dp[len][m];
    }
}

/*
	时间复杂度: O(n*m*n)
	空间复杂度：O(n*m)
*/


