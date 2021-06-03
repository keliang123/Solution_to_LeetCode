/*
	给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
示例 1:
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
示例 2:
输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"

	解答: 括号匹配问题都用到栈，而且有个技巧是栈放的不是左括号(，而是左括号的位数i。
			因为要找最长的有效括号，需要确定一个起点，所以维护一个一个起点start,遇到栈为空且是)就更新start，
			围绕栈是否为空，是左括号还是右括号进行讨论.
*/

class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int index = -1 ; int res = 0;
        for (int i = 0 ; i < len ; i ++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                if (c == ')') index = i;
                if (c == '(') stack.push(i);
            } else {
                //右括号匹配后判断栈是否为空
                if (c == ')') {
                    stack.pop();
                    if (stack.isEmpty()) res = Math.max(res , i - index);
                    else res = Math.max(res , i - stack.peek());
                }
                if (c == '(') stack.push(i);
            }
        }
        return res;
    }
}

/*
	时间复杂度: O(n)
	空间复杂度: O(n)
*/
