/*
	给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

示例1:

输入: pattern = "abba", str = "dog cat cat dog"
输出: true
示例 2:

输入:pattern = "abba", str = "dog cat cat fish"
输出: false

	解答： 哈希表 把pattern不同字母对应的单词放进哈希表，而且这些单词要不一样
			然后遍历单词表，看每个单词对应的字母是不是和哈希表对应。
*/

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] str = s.split(" ");
        if (pattern.length() != str.length) return false;
        Map<Character , String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0 ; i < pattern.length() ; ++i) {
            char ch = pattern.charAt(i);
            if (!map.containsKey(ch)) {
                if (set.contains(str[i])) return false;
                else {
                    map.put(ch , str[i]);
                    set.add(str[i]);
                }
            }
            
        } 
        for (int i = 0 ; i < str.length ; ++i) {
            if (!str[i].equals(map.get(pattern.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}

/*
	时间复杂度： O(m + n)
	空间复杂度： O(m + n)
*/