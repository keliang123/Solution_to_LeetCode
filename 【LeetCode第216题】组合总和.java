/*
	找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]

	解答：递归，深度遍历
*/

class Solution {
    //List<List<Integer>> res;
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k , n , 1 , new ArrayList<>() , res);
        return res;
    }
    
    //dfs
    private void dfs(int k , int n , int start , List<Integer> path , List<List<Integer>> res) {
        if (path.size() > k || n < 0) return;
        if (path.size() == k && n == 0) {
            Collections.sort(path);
            if (!res.contains(path))
                res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start ; i <= 9 ; ++i) {
            if (path.contains(i)) continue;
            path.add(i);
            dfs(k , n - i , i + 1 , path , res); 
            path.remove(path.size() - 1);
        } 
    }
}