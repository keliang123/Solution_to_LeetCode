/*
	给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
	注意：答案中不可以包含重复的三元组。

	示例：
	给定数组 nums = [-1, 0, 1, 2, -1, -4]，
	满足要求的三元组集合为：
	[
		[-1, 0, 1],
		[-1, -1, 2]
	]
	
	解答：还是排序后使用双指针，就是要剪枝和去重比较麻烦。
	
	
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 3) return res;
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[len - 1] < 0) return res;
        for (int i = 0 ; i < len - 2 ; i ++) {
            //去重
            if(i > 0 && nums[i] == nums[i - 1])   continue;
            if (nums[i] > 0 ) return res;
            int L = i + 1 ; int R = len - 1;
            while (L < R) {
                if (nums[L] + nums[i] > 0) break;
                if (nums[L] + nums[R] + nums[i] > 0)    R--;    
                else if (nums[L] + nums[R] + nums[i] < 0)   L ++;
                else  {
                    res.add(Arrays.asList(nums[i] , nums[L] , nums[R]));
                    //去重
					R --;
                    while (L < R && nums[R] == nums[R + 1])  R --;
                    L ++;
                    while (L < R && nums[L] == nums[L - 1])  L ++;
                } 
            }
        }
        return res;
    }
}

/*
	时间复杂度:O(n^2)
	空间复杂度:O(logn),排序需要用到空间
*/
