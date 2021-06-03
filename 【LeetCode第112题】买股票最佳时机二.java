/*
	给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
	设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
	注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

	解答：
		1、将数组用曲线图表示就很明显，就是加上所有的上坡值，就是结果
		2、动态规划，dp[i][j],表示第i天是最大收益，这里j表示状态，0是持有现金，也就是还没买股票。1是持有股票买了股票。
*/

//方法一
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        
        int res = 0;
        for (int i = 1 ; i < len ; i++) {
            res += (prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0);
        }
        return res;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(1)
*/


//方法二
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        
        //0是持有现金，1是持有股票
        int[][] dp = new int[len][2];
        dp[0][0] = 0; dp[0][1] = -prices[0];
        for (int i = 1 ; i < len ; i ++) {
            dp[i][0] = Math.max(dp[i - 1][0] , dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1] , dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/
