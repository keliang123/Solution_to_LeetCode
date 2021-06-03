/*
	给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
例如：
给定 BST [1,null,2,2],

   1
    \
     2
    /
   2
返回[2].

	解答： 暴力法：用哈希表记录每个数出现的频次。
			Morris中序遍历，遍历过程中记录众数。
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
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        if (root.left == null && root.right == null) return new int[]{root.val};
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        HashMap<Integer , Integer> map = new HashMap<>();
        int max = -1;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (!map.containsKey(root.val)) {
                map.put(root.val , 1);
                max = Math.max(max , 1);
            } else {
                map.put(root.val , map.get(root.val) + 1);
                max = Math.max(max , map.get(root.val));
            }
            if (!list.contains(root.val))
                list.add(root.val);
            root = root.right;
        }
        int count = 0;
        for (int i = 0 ; i < list.size() ; ++i) {
            if (map.get(list.get(i)) == max) 
                ++count;
        }
        int[] res = new int[count];
        count = 0;
        for (int i = 0 ; i < list.size() ; ++i) {
            if (map.get(list.get(i)) == max) {
                res[count ++] = list.get(i);
            }
        }
        return res;
    }
}


//Morris中序遍历
class Solution {
    
    int curData , count = 0 , maxCount = 1;
    List<Integer> ans = new ArrayList<>();
    
    public int[] findMode(TreeNode root) {
        //Morris中序遍历
        TreeNode cur = root; TreeNode pre;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            //继续下一层或者已经访问了一个数
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0 ; i < ans.size() ; ++i) {
            res[i] = ans.get(i);
        }
        return res;
    }
    
    //更新众数
    private void update(int data) {
        if (data == curData) {
            count++;
        } else {
            curData = data;
            count = 1;
        }
        if (count == maxCount) {
            ans.add(data);
        }
        if (count > maxCount) {
            ans.clear();
            ans.add(data);
            maxCount = count;
        }
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(1)
*/












