/*
	给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。

示例:

输入: [4, 6, 7, 7]
输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
说明:

给定数组的长度不会超过15。
数组中的整数范围是 [-100,100]。
给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。

	解答：深度优先遍历，只要遇到当前数字不小于前面最大的数字时，说明是可以当成子序列一个的，这时加不加这个数就有两种情况。
*/

class Solution {
    List<Integer> tmp = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>(); 
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length <= 0) return res;
        dfs(0 , Integer.MIN_VALUE , nums);
        return res;
    }
    
    //深度优先遍历
    private void dfs(int cur , int last , int[] nums) {
        if (cur == nums.length) {
            if (tmp.size() >= 2) {
                res.add(new ArrayList<>(tmp));
            }
            return;
        }
        //选择nums[cur]加不加，分别判断。
        if (nums[cur] >= last) {
            tmp.add(nums[cur]);
            dfs(cur + 1 , nums[cur] , nums);
            tmp.remove(tmp.size() - 1);
        }
        if (nums[cur] != last) {
            dfs(cur + 1 , last , nums);
        }
    }
}

/*
	时间复杂度：O(2^n*n)
	空间复杂度: O(n) 递归栈的深度。
*/