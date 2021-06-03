/*
	给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。

示例: 
输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。

	解答：
		(1)暴力法，遍历所有子数组，在大于s情况求最小的长度值。
		(2)双指针。两个指针表示子数组的左右两个端点，右端点右移，直到数组和大于等于s，此时就是以左端点为起点的最小长度，然后左端点右移一位，再移动右指针比较。
*/

//方法一
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0 || s <= 0) return 0;
        for (int i = 1 ; i < len ; i ++) 
            nums[i] += nums[i - 1];
        
        int res = Integer.MAX_VALUE;
        for (int i = 0 ; i < len ; i ++) {
            for (int j = i ; j < len ; j ++) {
                int temp = nums[j] - (i - 1 >= 0 ? nums[i - 1] : 0);
                if (temp >= s) 
                    res = Math.min(res , j - i + 1);
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

//方法二
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0 || s <= 0) return 0;
        
        int res = Integer.MAX_VALUE;
        int L = 0 ; int R = 0;
        int sum = nums[R];
        while (R < len) {
            while (sum < s) {
                R++;
                if (R == len) return res == Integer.MAX_VALUE ? 0 : res;
                sum += nums[R];
            }
            res = Math.min(res , R - L + 1);
            sum -= nums[L++];
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

/*
	方法二：
	时间复杂度:O(n)
	空间复杂度:O(1)
*/


