/*
	给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
	
	示例:

	输入: [1,2,3,null,5,null,4]
	输出: [1, 3, 4]
	解释:

	  1            <---
	/   \
   2     3         <---
    \     \
     5     4       <---
	
	解答：
	层级遍历二叉树，每层从右开始往左遍历，每次第一个取第一个数即为右视图第一个数字。
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int num = q.size();
            for (int i = 0 ; i < num ; i ++) {
                TreeNode node = q.remove();
                if (i == 0)
                    list.add(node.val);
                if (node.right != null)
                    q.add(node.right);
                if (node.left != null)
                    q.add(node.left);
            }
        }
        return list;
    }
}

/*
	时间复杂度是O(n)，空间复杂度是O(n)。
*/
