/*
	给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
示例 1:

输入: n = 3, k = 3
输出: "213"
示例 2:

输入: n = 4, k = 9
输出: "2314"

	解答：深度优先遍历，每次根据当前的k算出应该是哪个数。
*/

class Solution {
    public String getPermutation(int n, int k) {
        StringBuffer sb = new StringBuffer();
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i <= n ; ++i)
            list.add(i);
        dfs(list , n , k , sb);
        return sb.toString();
    }
    
    //dfs
    private static void dfs(List<Integer> list , int n , int k , StringBuffer sb) {
        if (n == 1) {
            sb.append(list.get(1));
            return;
        }
        int fact = factorial(n - 1);
        int data = k%fact == 0 ? k/fact : k/fact + 1;
        sb.append(list.get(data));
        list.remove(data);
        k -= data > 1 ? (data - 1)*fact : 0;
        dfs(list , --n , k , sb);
    }
    
    //算n的阶乘
    private static int factorial(int n) {
        if (n == 1) return 1;
        return n*factorial(n - 1);
    }
}