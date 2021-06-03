/*
	给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

 

示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：

输入：s = "a", t = "a"
输出："a"


*/

class Solution {
    Map<Character , Integer> tMap = new HashMap<>();
    Map<Character , Integer> cur = new HashMap<>();
    
    public String minWindow(String s, String t) {
        for (int i = 0 ; i < t.length() ; ++i) {
            char tmp = t.charAt(i);
            tMap.put(tmp , tMap.getOrDefault(tmp , 0) + 1);
        }
        
        int sLen = s.length();
        int L = 0 , R = -1;
        int resL = -1 , resR = -1;
        int minLen = Integer.MAX_VALUE;
        while (R < sLen) {
            ++R;
            if (R < sLen) {
                cur.put(s.charAt(R) , cur.getOrDefault(s.charAt(R) , 0) + 1);
            }
            while (match() && L <= R) {
                if (R - L + 1 < minLen) {
                    resL = L;
                    resR = R;
                    minLen = R - L + 1;
                }
                if (L < sLen && cur.containsKey(s.charAt(L))) {
                    cur.put(s.charAt(L) , cur.getOrDefault(s.charAt(L) , 0) - 1);
                }
                ++L;
            }
            
        }
        
        return resL == -1 ? "" : s.substring(resL , resR + 1);
    }
    
    //判断此时的cur是否包括了t所有的字符
    public boolean match() {
        for (Map.Entry<Character , Integer> entry : tMap.entrySet()) {
            char tmp = entry.getKey();
            int count = entry.getValue();
            if (cur.getOrDefault(tmp , 0) < count) {
                return false;
            }
        }
        return true;
    }
}