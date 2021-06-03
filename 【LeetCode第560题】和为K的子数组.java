/*
	给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

	解答： sum[i]表示是数组前i个数的和。那么[i , j]这一段子数组的和就是sum[j] - sum[i]。用哈希表遍历一般，如果当前是j。就是看哈希表中有没有sum[j]-k.
	
*/

//暴力法
class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] memo = new int[len + 1];
        int res = 0;
      
        for (int i = 0 ; i < len; i ++) {
            memo[i] = nums[i];
            if (nums[i] == k) res ++;
            for (int j = i + 1 ; j < len ; j ++) {
                memo[j] = memo[j - 1] + nums[j];
                if (memo[j] == k) res ++;
            }
        }
        return res;
    }
}


//哈希表
class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len <= 0) return 0;
        HashMap<Integer , Integer> map = new HashMap<>();
        map.put(0 , 1);
        int sum = 0 , res = 0;
        for (int i = 0 ; i < len ; ++i) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum , map.getOrDefault(sum , 0) + 1);
        }
        return res;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/