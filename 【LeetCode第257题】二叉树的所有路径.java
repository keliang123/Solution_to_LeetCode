/*
	给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。

示例:

输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

	解答：递归。前序遍历，需要定义一个路径表示从根节点到目前节点走过的点。
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        String path = "";
        preOrder(res , root , path);
        return res;
    }
    
    //前序遍历
    private void preOrder(List<String> res, TreeNode root , String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (path.equals("")) {
                path = String.valueOf(root.val);
            } else {
                path += ("->" + String.valueOf(root.val));
            }
            res.add(new String(path));
            path = path.substring(0 , path.length() - 1);
            return;
        }
        if (path.equals("")) {
            path = String.valueOf(root.val);
        } else {
            path += ("->" + String.valueOf(root.val));
        }
        //前序遍历
        preOrder(res , root.left , path);
        preOrder(res , root.right , path);
        path = path.substring(0 , path.length() - 1);
        
    }
}

/*
	时间复杂度:O(n^2)
	空间复杂度:O(n^2)
*/


