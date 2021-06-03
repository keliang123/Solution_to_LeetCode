/*
	将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
	本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:
给定有序数组: [-10,-3,0,5,9],
一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
      0
     / \
   -3   9
   /   /
 -10  5


	解答:
		数组是升序的也就是二叉树的中序遍历，每次递归以数组中间数构造根结点.
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return arrayToBSTRecursive(nums , 0 , nums.length - 1);
    }
    
    //递归构造
    private TreeNode arrayToBSTRecursive(int[] nums , int L , int R) {
        if (L > R) return null;
        int mid = L + ((R - L)>>1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = arrayToBSTRecursive(nums , L , mid - 1);
        root.right = arrayToBSTRecursive(nums , mid + 1 , R);
        return root;
    }
}

/*
	时间复杂度:O(n)
	空间复杂度:O(log(n)) 递归栈的深度
*/

