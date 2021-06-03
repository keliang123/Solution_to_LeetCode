/*
	给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

	示例 1:
给定的树 s:

     3
    / \
   4   5
  / \
 1   2
给定的树 t：

   4 
  / \
 1   2
返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值

示例 2:
给定的树 s：

     3
    / \
   4   5
  / \
 1   2
    /
   0
给定的树 t：

   4
  / \
 1   2
返回 false。

	解答：
		对数进行层序遍历，也就是广度优先遍历，对每个结点判断是否和给定树t是否匹配。
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t != null)
            return false;
        if (t == null)
            return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp.val == t.val && isMatch(temp , t))
                return true;
            if (temp.right != null) q.add(temp.right);
            if (temp.left != null) q.add(temp.left);
        }
        return false;
    }
    //判断两个树结构是否相等
    private boolean isMatch(TreeNode s , TreeNode t) {
        if (s == null && t == null)
            return true;
        if ((s == null && t != null) || (s != null) && t == null)
            return false;
        if (s.val != t.val)
            return false;
        
        return isMatch(s.left , t.left) && isMatch(s.right , t.right);
    }
}
/*
	时间复杂度：最坏情况是每个结点都要判断匹配，s总共有|s|个结点。O(|s|*|t|)
	空间复杂度：s深度是ds，t的深度是dt。空间最大使用代价是O(max(ds,dt))
*/
















