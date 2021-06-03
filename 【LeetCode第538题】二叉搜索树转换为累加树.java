/*
	给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

 

例如：

输入: 原始二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13

	
	解答：反向中序遍历，二叉搜索树的中序遍历，是递增的。反向中序后，就是递减的过程，而这个过程中记录前面的累加值。
*/

//暴力法
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
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return root;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            list.add(tmp.val);
            if (tmp.right != null) stack.push(tmp.right);
            if (tmp.left != null) stack.push(tmp.left);
        }
        int[] data = new int[list.size()];
        for (int i = 0 ; i < list.size() ; i++) {
            data[i] = list.get(i);
        }
        Arrays.sort(data);
        int sum = 0;
        HashMap<Integer , Integer> map = new HashMap<>();
        for (int i = data.length - 1 ; i >= 0 ; --i) {
            if (i == data.length - 1) {
                map.put(data[i] , data[i]);
                sum = data[i];
                continue;
            }
            if (i < data.length - 1 && data[i] < data[i + 1])
                sum += data[i];
            if (!map.containsKey(data[i])) {
                map.put(data[i] , sum);
            }
        }
        
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            tmp.val = map.get(tmp.val);
            if (tmp.right != null) stack.push(tmp.right);
            if (tmp.left != null) stack.push(tmp.left);
        }
        return root;
    }
}




//反向中序遍历
class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return root;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}















