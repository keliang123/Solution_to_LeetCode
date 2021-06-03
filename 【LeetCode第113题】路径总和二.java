/*
	给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

	解答：
		递归，深度优先遍历
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
    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(Arrays.asList(root.val));
            return res;
        }
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        dfs(path , root , root.val , sum);
        return res;
    }
    
    //dfs
    private void dfs(List<Integer> path , TreeNode cur , int data , int sum) {
        if (data == sum && cur.left == null && cur.right == null) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (cur.left != null) {
            path.add(cur.left.val);
            data += cur.left.val;
            dfs(path , cur.left , data , sum);
            path.remove(path.size() - 1);
            data -= cur.left.val;
        }
        if (cur.right != null) {
            path.add(cur.right.val);
            data += cur.right.val;
            dfs(path , cur.right , data , sum);
            path.remove(path.size() - 1);
            data -= cur.right.val;
        }
    }
}

/*
	时间复杂度：O(n^2)
	空间复杂度：O(n)
*/



