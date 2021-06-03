/*
	给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
	如果没有任何一种硬币组合能组成总金额，返回 -1。

示例 1:

输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1

	求最值，很明显动态规划问题。
*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        if (len == 0 || amount < 0) return -1;
        int[] dp = new int[amount + 1];
        //初始化
        for (int i = 0 ; i < amount + 1 ; i ++)
            dp[i] = amount + 1;
        dp[0] = 0;
        //自底向上
        for (int i = 1 ; i <= amount ; i ++) {
            for (int j = 0 ; j < len ; j ++) {
                if (i >= coins[j]) 
                    dp[i] = Math.min(dp[i - coins[j]] + 1 , dp[i]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

/*
	时间复杂度:O(n*m),n是兑换零钱的大小，m是零钱数组的长度。
	空间复杂度：O(n)，存储定义的dp数组。
*/