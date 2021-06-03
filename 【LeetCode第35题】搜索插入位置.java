/*
	给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
	你可以假设数组中无重复元素。

示例 1:
输入: [1,3,5,6], 5
输出: 2
示例 2:
输入: [1,3,5,6], 2
输出: 1

	解答：
		二分搜索即可，最后判断目标在不在数组中。
*/



class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return 0;
        int mid = 0 , low = 0, high = len - 1;
        while (low <= high) {
            mid = (high + low)/2;
            if (nums[mid] > target)
                high = mid - 1;
            else if (nums[mid] < target)
                low = mid + 1;
            else 
                return mid;
        }
        if (nums[mid] < target)
            return mid + 1;
        return mid;
    }
}

/*
	时间复杂度：O(logn)
	空间复杂度：O(1)
*/