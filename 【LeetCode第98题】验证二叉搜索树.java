/*
	给定一个二叉树，判断其是否是一个有效的二叉搜索树。
	假设一个二叉搜索树具有如下特征：

	节点的左子树只包含小于当前节点的数。
	节点的右子树只包含大于当前节点的数。
	所有左子树和右子树自身必须也是二叉搜索树。
	示例 1:

	输入:
		2
	   / \
	  1   3
	输出: true
	
	解答：中序遍历二叉树，判断结点值是否按照升序排列。注意设置初始比较值时也要比Integer.MIN_VALUE小。

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
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long compare = Long.MIN_VALUE;
    
        while( !stack.isEmpty() || root != null ){
            while( root != null ){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if( root.val <= compare ) 
                return false;
            compare = root.val;
            root = root.right;
        }
        return true;
    }
}

/*
	时间复杂度是O(n)，空间复杂度是O(n)
*/
