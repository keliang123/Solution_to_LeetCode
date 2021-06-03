/*
	给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

示例 1：

输入：nums = [2,2,3,2]
输出：3
示例 2：

输入：nums = [0,1,0,1,0,1,99]
输出：99
	
	解答： 按位运算
			非答案的数字出现三次，那么每个位相加后，都是3的倍数。
			所以对每个位(32位)进行相加，对3取余后肯定是3的余数。
*/

class Solution {
    public int singleNumber(int[] nums) {
        
        int res = 0;
        for (int i = 0 ; i < 32 ; i ++) {
            int sum = 0;
            for (int num : nums) {
                sum += (num>>i) & 1;
            }
            res ^= (sum%3 <<i);
        }
        return res;
    }
}