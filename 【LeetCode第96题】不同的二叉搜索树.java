/*
	给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


	解答：
		遍历每个i,以它为根结点的情况，那么当以i为根结点，此时左边是以1... (i-1)构造的树，右边是(i+1)....n构造的树，两种结果相乘就可以。
		然后对所有根结点情况累加。
*/

class Solution {
    public int numTrees(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int[] res = new int[n + 1];
        res[0] = 1; res[1] = 1;
        for (int i = 2 ; i <= n ; i ++) {
            for (int j = 1 ; j <= i ; j++) {
                res[i] += res[j - 1]*res[i - j];
            }
        }
        return res[n];
    }
}

/*
	时间复杂度:O(n^2)
	空间复杂度:O(n)
*/