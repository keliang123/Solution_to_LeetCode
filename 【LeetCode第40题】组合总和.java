/*
	给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]

	解答： 递归+回溯  
*/
class Solution {
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
       List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        //先排序
        Arrays.sort(candidates);
        //用栈存储队列
        Deque path = new ArrayDeque<>();
        dfs(candidates , 0 , target , path , res);
        return res;
    }
    
    private void dfs(int[] candi , int begin , int target , Deque path , List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (int idx = begin ; idx < candi.length ; idx++) {
            if (candi[idx] > target) {
                break;
            }
            if (idx > begin && candi[idx] == candi[idx - 1])
                continue;
            path.addLast(candi[idx]);
            dfs(candi , idx + 1 , target - candi[idx] , path , res);
            path.removeLast();
        }
    }
}