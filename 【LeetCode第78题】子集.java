/*
	给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

	解答：递归 回溯
*/

class Solution {
    private List<List<Integer>> res;
    
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        res.add(new ArrayList<>());
        dfs(new ArrayList<>() , 0 , nums);
        return res;
    }
    
    //dfs
    private void dfs(List<Integer> path , int idx , int[] nums) {
        if (idx == nums.length) {
            return;
        }
        
        //加上当前的
        path.add(nums[idx]);
        res.add(new ArrayList<>(path));
        dfs(path , idx + 1 , nums);
        path.remove(path.size() - 1);
        
        //不加当前的
        dfs(path , idx + 1 , nums);
    }
}