/*
	给定一个非空二叉树，返回其最大路径和。
本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
示例 1:

输入: [1,2,3]

       1
      / \
     2   3

输出: 6
示例 2:

输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42

	解答： 就是一次前序遍历，比较每个结点的最大路径。
		前序遍历的时候，函数返回的当前结点的单边路径最大值。
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
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        preOrder(root);
        return res;
    }
    //每个结点root的单边路径最大值
    private int preOrder(TreeNode root) {
        if (root == null) return 0;
        
        int leftData = Math.max(0 , inOrder(root.left));
        int rightData = Math.max(0 , inOrder(root.right));
        res = Math.max(res , leftData + rightData + root.val);
        
        return root.val + Math.max(leftData , rightData);
    }
}

/*
	时间复杂度: O(n)
	空间复杂度：O(n)
*/