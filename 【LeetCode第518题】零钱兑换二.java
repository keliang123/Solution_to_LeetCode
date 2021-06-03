/*
	给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

示例 1:
输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

示例 2:
输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成总金额3。

	解答： 明显的动态规划问题，关键是思考动态转移方程。
	1、 memo[j][i]表示只使用了前j个硬币时候，凑钱金额是i的时候的组合数。
		转移方程： memo[j][i] = memo[j - 1][i] + memo[j][i - coins[j]];
		也就是 有没有使用第j个硬币的情况，分为两种情况。
	2、 memo[i] = memo[i] + memo[i - coin];
		即 memo[i]是只使用了前k个硬币的下的凑钱金额是i的组合数。
		那么也就等于 之前没有使用过第k个硬币时候值memo[i]  加上  使用了第k个硬币时候值（memo[i - coins]）
	
*/

//方法一
class Solution {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (amount < 0) return 0;
        int[][] memo = new int[len + 1][amount + 1];
        for (int i = 0 ; i <= len ; i ++) 
            memo[i][0] = 1;
        
        for (int i = 1 ; i <= amount ; i ++) {
            for (int j = 1 ; j <= len ; j ++) {
                if (i >= coins[j - 1]) {
                    memo[j][i] = memo[j][i - coins[j - 1]] + memo[j - 1][i];
                } else memo[j][i] = memo[j - 1][i];
            }
        }
        return memo[len][amount];
    }
}
/*
	时间复杂度：O(n*m)
	空间复杂度:O(n*m)
*/

//方法二
class Solution {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (amount < 0) return 0;
        int[] memo = new int[amount + 1];
        memo[0] = 1;
        for (int coin : coins) {
            for (int i = 1 ; i <= amount ; ++i) {
                if (i >= coin)
                    memo[i] += memo[i - coin];
            }
        }
        return memo[amount];
    }
}

/*
	时间复杂度：O(n*m)
	空间复杂度:O(n)
*/
