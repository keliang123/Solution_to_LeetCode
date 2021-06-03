/*
	给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
	示例：
二叉树：[3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
	
	解答：思路还是用队列实现层序遍历，但是需要记录每层的个数，这样好将每层数据放入数组中。
	
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root); int preCount = 1 , index = 0 , nowCount = 0;
        List<Integer> temp = new ArrayList<>();
        while (!que.isEmpty()) {
            TreeNode node = que.poll();
            int data = node.val;
            temp.add(data); index++;
            if (node.left != null)  { que.add(node.left);  nowCount ++; }
            if (node.right != null) { que.add(node.right); nowCount ++; }
            if (index == preCount) {
                List<Integer> list = new ArrayList<>();
                for (int e : temp)   list.add(e);            
                res.add(list);  temp.clear();
                index = 0; preCount = nowCount; nowCount = 0;
            }
        }
        return res;
    }
}

/*
	每个结点都遍历了一遍，时间复杂度为O(logn)，空间复杂度是O(logn)
*/

