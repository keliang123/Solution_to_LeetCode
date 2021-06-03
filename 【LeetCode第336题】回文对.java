/*
	给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

示例 1:

输入: ["abcd","dcba","lls","s","sssll"]
输出: [[0,1],[1,0],[3,2],[2,4]] 
解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
示例 2:

输入: ["bat","tab","cat"]
输出: [[0,1],[1,0]] 
解释: 可拼接成的回文串为 ["battab","tabbat"]

	解答：
		1、暴力法，遍历所有判断两个字符串是不是回文对。做一些剪枝。
		2、所有字符串和对应位置放入哈希表，遍历一遍字符数组，翻转后
*/

//暴力法
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        int len = words.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0 ; i < len ; ++i) {
            for (int j  = 0 ; j < len ; ++j) {
                if (i == j) continue;
                //剪枝
                if (words[i].length() > 0 && words[j].length() > 0 &&
                    words[i].charAt(0) != words[j].charAt(words[j].length() - 1))
                    continue;
                if (words[i].length() > 1 && words[j].length() > 1 &&
                    words[i].charAt(1) != words[j].charAt(words[j].length() - 2))
                    continue;
                if (words[i].length() > 2 && words[j].length() > 2 &&
                    words[i].charAt(2) != words[j].charAt(words[j].length() - 3))
                    continue;
                //如果能够合并
                if (isMatch(words[i] , words[j])) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i); tmp.add(j);
                    res.add(tmp);
                }
            }
        }
        return res;
    }
    
    //两个String是否能合并成回文对。
    private boolean isMatch(String s1 , String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1); sb.append(s2);
        return sb.toString().equals(sb.reverse().toString());
    }
}