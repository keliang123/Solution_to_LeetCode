/*
	给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

	解答：利用递归深度遍历
*/

class Solution {
    
    public List<List<Integer>> combine(int n, int k) {
        return dfs(1 , n , k);
    }
    
    //dfs
    private List<List<Integer>> dfs(int idx , int n , int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n - idx + 1 == k) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = idx ; i <= n ; ++i) {
                tmp.add(i);
            }
            res.add(tmp);
            return res;
        }
            
        if (k == 1) {
            for (int i = idx ; i <= n ; ++i) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                res.add(tmp);
            }
            return res;
        }
        //不包括此时的数
        List<List<Integer>> tmp1 = dfs(idx + 1 , n , k);
        for (List<Integer> e : tmp1) {
            res.add(e);
        }
    
        //包括此时的数
        List<List<Integer>> tmp2 = dfs(idx + 1 , n , k - 1);
        for (List<Integer> e : tmp2) {
            e.add(0 , idx);
            res.add(e);
        }
        return res;
    }
    
    
}