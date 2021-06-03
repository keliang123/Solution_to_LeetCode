/*
	给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
	示例 1:

	输入: [1,2,0]
	输出: 3
	示例 2:

	输入: [3,4,-1,1]
	输出: 2
	
	解答：
	(1) 使用哈希表存储都有数，从1开始遍历是否在哈希表中，但此时空间复杂度是O(n)，不符合题意。
	(2) 借用哈希表的方法，可以将给定的数组改造成哈希表，关键就是对出现的数字标记。首先，需要明确只要数组中出现小于等于0或者大于len的数，
			那么数组中确实的正数肯定在[1 ,len]中。 所以可以将小于等于0或者大于len的数掷为1(首先得排除1的存在),那么数组中数所有数为正数。
			此时，遍历数组，对于数a，它出现了，就将索引a-1处的数变为负数，表示a这个数字已经出现过。
			最后遍历一遍数组，找出第一个正数，它的索引值+1就是结果
*/

//方法一
class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        if (len ==0) return 1;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num))
                set.add(num);
        }
        for (int i = 1 ; ; i ++) {
            if (!set.contains(i)) return i;
        }
    }
}


//方法二
class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        if (len ==0) return 1;
        //判断是否有1存在
        for (int i = 0 ; i < len ; i++) {
            if (nums[i] == 1) break;
            if (i == len - 1 && nums[i] != 1) return 1;
        }
        //将所有小于等于0 或者大于len的数掷1
        for (int i = 0 ; i < len ; i ++) {
            if (nums[i] <= 0 || nums[i] > len)
                nums[i] = 1;
        }
        //将出现过的数标记(变为负数)
        for (int i = 0 ; i < len ; i++) {
            int temp = Math.abs(nums[i]);
            nums[temp - 1] = -Math.abs(nums[temp - 1]);
        }
        //找出第一个正数
        for (int i = 0 ; i < len ; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return len + 1;
    }
}

/*
	时间复杂度:O(n)
	空间复杂度:O(1)
*/



