/*
	给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:

每个数组中的元素不会超过 100
数组的大小不会超过 200
示例 1:

输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].

	解答： 动态规划，看是否有部分和等于数组和的一半。
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0 ; i < nums.length ; ++i) {
            sum += nums[i];
        }
        if (sum%2 != 0) return false;
        
        //动态规划 dp[i][j]表示[0 , i]区间内是否有部分和为j
        int target = sum/2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        if (nums[0] == target) dp[0][target] = true;
        for (int i = 1 ; i < nums.length ; ++i) {
            for (int j = 0 ; j <= target ; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) dp[i][j] = true;
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            } 
        }
        return dp[nums.length - 1][target];
    }
}


/*
	时间复杂度：O(n*c)
	空间复杂度：O(n*c)
*/