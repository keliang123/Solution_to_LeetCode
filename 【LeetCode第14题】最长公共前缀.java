/*
	编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
	
	输入: ["flower","flow","flight"]输出: "fl"
	
	输入: ["dog","racecar","car"]输出: ""解释: 输入不存在公共前缀。
	
	解答:暴力，每个数组元素从第一个开始遍历，就是看第一位是否都相等，如果想当再看第二位是否相等。
	只要有一个不相等，就返回前面相等的部分。
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (strs == null || len == 0) return "";
        if (len == 1) return strs[0];
        
        for (int i = 0 ; ; i ++) {
            if (i + 1 > strs[0].length()) return strs[0].substring(0 , i);
            char temp = strs[0].charAt(i);
            String res = strs[0].substring(0 , i);
            for (String e : strs) {
                if (e.length() < i + 1 || e.charAt(i) != temp) return res;
                else continue;
            }
        }
    }
}

/*
	时间复杂度:O(mn),n是数组长度，m是相等部分的长度.
	空间复杂度:O(1)
*/