/*
	给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

 

示例 1：

输入：s = "bcabc"
输出："abc"
示例 2：

输入：s = "cbacdcbc"
输出："acdb"

	解答：对于某一个一个字符，它比前面的字典序小时，本应该要调到前面，此时前面的字母在这个字符后面出现时符合，若后面没有了，就这个字符就定了。
			这是一种后进先出的顺序，用栈。
*/

class Solution {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        char[] ch = s.toCharArray();
        int[] lastIdx = new int[26];
        boolean[] visit = new boolean[26];
        //存储字符最后出现的位置
        for (int i = 0 ; i < len ; ++i) {
            lastIdx[ch[i] - 'a'] = i;
        }
        //遍历每个字符，将合适的字符放进去
        Stack<Character> stack = new Stack<>();
        for (int i = 0 ; i < len ; ++i) {
            if (visit[ch[i] - 'a']) continue;
            while (!stack.isEmpty() && stack.peek() > ch[i] && lastIdx[stack.peek() - 'a'] > i) {
                visit[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(ch[i]);
            visit[ch[i] - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：o(n)
*/

