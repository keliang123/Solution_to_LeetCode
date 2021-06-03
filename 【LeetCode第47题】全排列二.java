/*
	给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

	解答：深度遍历。 剪枝是很重要，这里先排序后，只要和前一个数相等就剪枝，然后利用一个boolean数组维护每个数是否访问过，剪枝方法很巧妙。
*/

class Solution {
    
    List<List<Integer>> res;
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);  //排序是剪枝的过程。
        boolean[] vis = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs(nums , path , vis , 0 , nums.length);
        return res;
    }
    
    //dfs
    private void dfs(int[] data , List<Integer> path , boolean[] vis , int idx , int len) {
        if (idx == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0 ; i < data.length ; ++i) {
            if (vis[i]) continue;
            if (i > 0 && data[i] == data[i - 1] && !vis[i - 1]) continue;
            
            path.add(data[i]);
            vis[i] = true;
            dfs(data , path , vis , ++idx , len);
            --idx;
            vis[i] = false;
            path.remove(path.size() - 1);
        }
    } 
}