/*
	给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。

重复出现的子串要计算它们出现的次数。

示例 1 :

输入: "00110011"
输出: 6
解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。

请注意，一些重复出现的子串要计算它们出现的次数。

另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
示例 2 :

输入: "10101"
输出: 4
解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。

	解答：
		暴力法，找到01或者10这种对称点的位置，然后两边扩展。
		建立字符数组,就是记录相同字符的连续个数，放在数组里。那么数组中相邻的两个字符都是不同的。子串结果加上最小数即可。
*/

//暴力法
class Solution {
    public int countBinarySubstrings(String s) {
        int len = s.length();
        if (s == null || len < 2) return 0;
        
        int res = 0;
        boolean flag = false;
        for (int i = 0 ; i < len - 1 ; ++i) {
            //判断是否是对称点
            if (s.charAt(i) == s.charAt(i + 1)) continue;
            flag = true;
            ++res;
            //扩展长度4到最大len
            for (int L = 1 ; L <= len/2 ; ++L) {
                int start = i - L; int end = i + 1 + L;
                if (start < 0 || end >= len || !flag || 
                    s.charAt(start) != s.charAt(start + 1) || 
                        s.charAt(end) != s.charAt(end - 1)) break;;
                
                flag = true;
                ++res;
            }
        }
        return res;
    }
}

/*
	时间复杂度：O(n^2)
	空间复杂度：O(1)
*/


//字符数组解法：
class Solution {
    public int countBinarySubstrings(String s) {
        int len = s.length();
        if (s == null || len < 2) return 0;
        
        int idx = 0, res = 0;
        int last = 0;
        while (idx < len) {
            int tmp = 1;
            while (idx + 1 < len && s.charAt(idx) == s.charAt(idx + 1)) {
                ++tmp;
                ++idx;
            }
            if (last == 0) {
                last = tmp;
                ++idx;
                continue;
            }
            res += Math.min(tmp , last);
            last = tmp;
            ++idx;
        }
        return res;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(1)
*/