/*
	给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。

（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

示例 1:

输入: N = 10
输出: 9
示例 2:

输入: N = 1234
输出: 1234
示例 3:

输入: N = 332
输出: 299

	解答： 贪心， 找到第一个不是递增的那个点 ，从那个点开始，前面的都减一，知道是递增，后面的都变为9
*/



class Solution {
    public int monotoneIncreasingDigits(int N) {
        String str = Integer.toString(N);
        char[] ch = str.toCharArray();
        int idx = 1;
        while (idx < str.length() && ch[idx] >= ch[idx - 1]) {
            idx++;
        }
        if (idx >= str.length()) return N;
        while (idx > 0 && ch[idx] < ch[idx - 1]) {
            ch[idx - 1]--;
            idx--;
        }
        for (int i = idx + 1 ; i < str.length() ; ++i) {
            ch[i] = '9';
        }
        return Integer.valueOf(new String(ch));
    }
}