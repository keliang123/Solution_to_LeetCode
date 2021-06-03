/*
	给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

注意：

num1 和num2 的长度都小于 5100.
num1 和num2 都只包含数字 0-9.
num1 和num2 都不包含任何前导零。
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。

	解答： 双指针从后面向前遍历，每一位相加，用一个StringBuffer存储.
*/

class Solution {
    public String addStrings(String num1, String num2) {
        int idx1 = num1.length() - 1;
        int idx2 = num2.length() - 1;
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        while (idx1 >= 0 || idx2 >= 0) {
            int tmp1 = idx1 >= 0 ? (num1.charAt(idx1) - '0') : 0;
            int tmp2 = idx2 >= 0 ? (num2.charAt(idx2) - '0') : 0;
            sb.append(String.valueOf((tmp1 + tmp2 + carry)%10));
            carry = (tmp1 + tmp2 + carry)/10;
            --idx1;
            --idx2;
        }
        if (carry == 1)
            sb.append("1");
        return sb.reverse().toString();
    }
}

/*
	时间复杂度：O(max(len1, len2))
	空间复杂度；O(1)
*/