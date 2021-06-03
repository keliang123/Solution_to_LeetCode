/*
	在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
	计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:
输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

输出: 7 
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

	解答：每次选择都是偷不偷当前结点，偷的话只能在孙辈结点中继续偷；不偷当前结点的话，在子辈结点中偷。
		采取递归+记忆化方法。
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
    public int rob(TreeNode root) {
        if (root == null) return 0;
        HashMap<TreeNode , Integer> map = new HashMap<>();
        return recursive(root , map);
    }
    
    //递归方程
    private int recursive(TreeNode root , HashMap<TreeNode , Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root))
            return map.get(root);
        
        //盗根结点
        int temp = 0;
        if (root.left != null) {
            if (root.left.left != null)
                temp += recursive(root.left.left , map);
            if (root.left.right != null)
                temp += recursive(root.left.right , map);
        }
        if (root.right != null) {
            if (root.right.left != null)
                temp += recursive(root.right.left , map);
            if (root.right.right != null)
                temp += recursive(root.right.right , map);
        }
        temp += root.val;
        
        //不盗根结点
        int tmp = 0;
        if (root.left != null)
            tmp += recursive(root.left , map);
        if (root.right != null)
            tmp += recursive(root.right , map);
        
        int res = Math.max(temp , tmp);
        map.put(root , res);
        
        return res;
     }
    
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/