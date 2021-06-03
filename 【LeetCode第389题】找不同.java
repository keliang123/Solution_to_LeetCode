/*
	给定两个字符串 s 和 t，它们只包含小写字母。

字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。

请找出在 t 中被添加的字母。

 

示例 1：

输入：s = "abcd", t = "abcde"
输出："e"
解释：'e' 是那个被添加的字母。
示例 2：

输入：s = "", t = "y"
输出："y"
示例 3：

输入：s = "a", t = "aa"
输出："a"
示例 4：

输入：s = "ae", t = "aea"
输出："a"

	解答：  用哈希表计数
			两个字符串的ASCII和相减
			找出奇数不同，用异或。
*/

//哈希表
class Solution {
    public char findTheDifference(String s, String t) {
        if (s.length() == 0) return t.charAt(0);
        Map<Character , Integer> map1 = new HashMap<>();
        Map<Character , Integer> map2 = new HashMap<>();
        for (int i = 0 ; i < s.length() ; i++) {
            char tmp = s.charAt(i);
            if (map1.containsKey(tmp)) {
                map1.put(tmp , map1.get(tmp) + 1);
            } else {
                map1.put(tmp , 1);
            }
        }
        for (int i = 0 ; i < t.length() ; ++i) {
            char tmp = t.charAt(i);
            if (map2.containsKey(tmp)) {
                map2.put(tmp , map2.get(tmp) + 1);
            } else {
                map2.put(tmp , 1);
            }
        }      
                
        for (int i = 0 ; i < t.length() ; i++) {
            char tmp = t.charAt(i);
            if (!map1.containsKey(tmp) || (map1.containsKey(tmp) && map1.get(tmp) == map2.get(tmp) - 1))
                return tmp;
        }
        return '0';
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/


//ASCII码
class Solution {
    public char findTheDifference(String s, String t) {
        int ScharSum = 0 , TcharSum = 0;
        for (int i = 0 ; i < s.length() ; ++i) {
            ScharSum += s.charAt(i);
        }
        for (int i = 0 ; i < t.length() ; ++i) {
            TcharSum += t.charAt(i);
        }
        return (char)(TcharSum - ScharSum);
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(1)
*/

//异或
class Solution {
    public char findTheDifference(String s, String t) {
        char res = 0;
        for (int i = 0 ; i < s.length() ; ++i) {
            res ^= s.charAt(i);
        }
        for (int i = 0 ; i < t.length() ; ++i) {
            res ^= t.charAt(i);
        }
        return res;
    }
}
/*
	时间复杂度：O(n)
	空间复杂度：O(1)
*/


