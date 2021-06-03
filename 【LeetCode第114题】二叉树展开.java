/*
	给定一个二叉树，原地将它展开为一个单链表。

例如，给定二叉树

    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

	解答：前序遍历中，同时利用递归展开链表。
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode rootRight = root.right;
        TreeNode tmp;
        if (root.left != null) {
            flatten(root.left);
            root.right = root.left;
            root.left = null;
            tmp = root.right;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
        } else 
            tmp = root;
        
        flatten(rootRight);
        tmp.right = rootRight;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(logn)
*/