/*
	给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

	解答：递归，深度遍历。
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0 ; i < candidates.length ; i++) {
            if (candidates[i] == target) {
                res.add(new ArrayList<>(Arrays.asList(candidates[i])));
                
            } else if (candidates[i] > target) {
                continue;
            }
            else {
                List<List<Integer>> tmp = combinationSum(candidates , target - candidates[i]);
                if (tmp.size() <= 0) continue;
                for (List<Integer> e : tmp) {
                    e.add(0 , candidates[i]);
                    Collections.sort(e);
                    if (!res.contains(e))
                        res.add(new ArrayList<>(e));
                }
            }
        }
        return res;
    }
}

