/*
	在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
	火车票有三种不同的销售方式：

	一张为期一天的通行证售价为 costs[0] 美元；
	一张为期七天的通行证售价为 costs[1] 美元；
	一张为期三十天的通行证售价为 costs[2] 美元。
	通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。

	返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
	
	输入：days = [1,4,6,7,8,20], costs = [2,7,15]
	输出：11
	解释： 
	例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
	在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
	在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
	在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
	你总共花了 $11，并完成了你计划的每一天旅行。
	
	解答：
		从后向前进行动态规划。memo[i]表示第i天到days中最后一天花费的最小的值。那么第i天有三种选择
			i天在days里面  memo[i] = min(costs[0] + memo[i + 1] , costs[1] + memo[i + 7] , costs[2] + memo[i + 30])
		设置一个长度366的数组memo。

*/

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length;
        if (len == 0)
            return 0;
        int[] memo = new int[366];
        int index = len - 1;
        for (int i = 365 ; i >= 0 ; i --) {
            if (i == days[index]) {
                memo[i] = Math.min(costs[0] + (i + 1 <= 365 ? memo[i + 1] : 0) , 
                                    costs[1] + (i + 7 <= 365 ? memo[i + 7] : 0));
                memo[i] = Math.min(memo[i] , costs[2] + (i + 30 <= 365 ? memo[i + 30] : 0));
                index--;
            } else {
                memo[i] = (i + 1 <= 365 ? memo[i + 1] : 0);
            }
            if (index == -1)
                return memo[days[0]];
        }
        return memo[days[0]];
    }
}

/*
	时间复杂度：O(w)w是365天天数。空间复杂度：O(w)
*/
