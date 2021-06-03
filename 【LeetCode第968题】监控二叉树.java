/*
	给定一个二叉树，我们在树的节点上安装摄像头。

节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

计算监控树的所有节点所需的最小摄像头数量。

 

示例 1：



输入：[0,0,null,0,0]
输出：1
解释：如图所示，一台摄像头足以监控所有节点。

	解答： 设置每个节点的状态 0：节点未覆盖  1：节点已经覆盖  2：节点上安装了相机
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int res = 0;
    public int minCameraCover(TreeNode root) {
        if (dfs(root) == 0) {
            res++;
        }
        return res;
    }
    
    //dfs 0:节点未覆盖  1：节点已经覆盖 2：节点上安装了相机
    private int dfs(TreeNode root) {
        if (root == null) return 1;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 2;
        } else if (left == 1 && right == 1) {
            return 0;
        } else {
            return 1;
        }
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/