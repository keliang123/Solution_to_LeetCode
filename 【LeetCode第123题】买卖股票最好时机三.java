/*
	给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
	设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
	注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

	解答：
		动态规划，三维数组，dp[i][j][k] ,i是天数，j是状态，0持有股票，1持有现金； k是卖了几次，k = 0 , 1 , 2
*/

//暴力法，遍历所有情况
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int res = 0;
        
        for (int i = 0 ; i < len; i ++) {
            res = Math.max(res , maxPrfitPartArray(prices , 0 , i - 1) +                                
				maxPrfitPartArray(prices , i , len - 1));
        }
        return res;
    }
    
    private int maxPrfitPartArray(int[] prices , int L , int R) {
        if (L >= R) return 0;
        int res = 0;
        int minPrfit = Integer.MAX_VALUE;
        for (int i = L ; i <= R ; i++) {
            if (prices[i] < minPrfit) {
                minPrfit = prices[i];
            } else if (prices[i] - minPrfit > res) {
                res = prices[i] - minPrfit;
            }
        }
        return res;
    }
}


//动态规划
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        int[][][] dp = new int[len][2][3];
        dp[0][0][0] = -prices[0]; dp[0][1][0] = 0;
        dp[0][0][1] = -prices[0]; dp[0][1][0] = 0;
        dp[0][0][2] = -prices[0]; dp[0][1][2] = 0;
        
        for (int i = 1 ; i < len ; ++i) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0] , dp[i - 1][1][0] - prices[i]);
            dp[i][1][0] = dp[i - 1][1][0];
            dp[i][0][1] = Math.max(dp[i - 1][0][1] , dp[i - 1][1][1] - prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1] , dp[i - 1][0][0] + prices[i]);
            dp[i][1][2] = Math.max(dp[i - 1][1][2] , dp[i - 1][0][1] + prices[i]);
            dp[i][0][2] = 0;
        }
        int temp = Math.max(dp[len - 1][1][0] , dp[len - 1][1][1]);
        return Math.max(dp[len - 1][1][2] , temp);
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/
