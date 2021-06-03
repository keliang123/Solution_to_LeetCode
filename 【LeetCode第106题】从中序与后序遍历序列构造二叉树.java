/*
	根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7

	解答：递归  分段构造
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(0 , inorder.length - 1 , inorder , 0 , postorder.length - 1 , postorder);
    }
    
    //Recu
    private TreeNode dfs(int idxIn1 , int idxIn2 , int[] inorder , int idxPo1 , int idxPo2 , int[] postorder) {
        if (idxIn1 == idxIn2 && idxPo1 == idxPo2) {
            return new TreeNode(inorder[idxIn1]);
        }
        if (idxIn1 > idxIn2 || idxPo1 > idxPo2) {
            return null;
        } 
        int idxIn = idxIn1; 
        for (; idxIn <= idxIn2 ; ++idxIn) {
            if (inorder[idxIn] == postorder[idxPo2]) 
                break;
        }
        TreeNode root = new TreeNode(postorder[idxPo2]);
        //极端情况
        if (idxIn == idxIn1) {
            root.left = null;
            root.right = dfs(idxIn + 1 , idxIn2 , inorder , idxPo1 , idxPo2 - 1 , postorder);
            return root;
        }
        if (idxIn == idxIn2) {
            root.left = dfs(idxIn1 , idxIn - 1 , inorder , idxPo1 , idxPo2 - 1 , postorder);
            root.right = null;
            return root;
        }
        int len = idxIn - idxIn1;
        
        root.left = dfs(idxIn1 , idxIn - 1 , inorder , idxPo1 , idxPo1 + len - 1 , postorder);
        root.right = dfs(idxIn + 1 , idxIn2 , inorder , idxPo1 + len , idxPo2 - 1 , postorder);
        return root;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/