/*
	@keliang123
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

解答：
1、暴力法，直接用两个for循环遍历两遍数组，只要两个数相加为target值，则返回两个数的下标即可。
2、使用HashMap，遍历一遍数组过程中，看map中是否有target - nums[i] 这个数，有则返回下标，没有就把数添加到map中。

*/

//代码：
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
		
        for(int i = 0 ; i < len ; i ++) {
            int data = target - nums[i];
            if (map.containsKey(data))
                return new int[]{map.get(data),i};
            if(!map.containsKey(nums[i]))
                map.put(nums[i] , i);
        }
        return new int[]{-1,-1};
    }
}	

/*
使用HashMap
时间复杂度：O(n)
空间复杂度：O(n)
*/