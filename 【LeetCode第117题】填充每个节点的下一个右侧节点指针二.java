/*
	给定一个二叉树

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

 

进阶：

你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 

	解答：层序遍历，没访问一个节点可以知道其右侧节点，但是最右侧节点时next指向null。
*/


// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


class Solution {
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> que = new LinkedList<>();
        //寻找每个节点的下一个右侧节点
        que.add(root); int count = 1;
        while (!que.isEmpty()) {
            int num = 0;
            for (int i = 0 ; i < count ; ++i) {
                Node cur = que.poll();
                if (cur.left != null) {
                    ++num;
                    que.add(cur.left);
                }
                if (cur.right != null) {
                    ++num;
                    que.add(cur.right);
                }
                Node next = que.peek();
                if (i != count - 1) {
                    cur.next = next;
                } else {
                    cur.next = null;
                }
            }
            count = num;
        }
        return root;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/

