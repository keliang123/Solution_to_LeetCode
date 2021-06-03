/*
	给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

	解答： 哈希表，用每个单词排序后的字符串作为键。
	字符串数组转string   用 new String(ch)。
	
*/

//哈希表
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) return res;
        if (strs.length == 1) {
            res.add(Arrays.asList(strs[0]));
            return res;
        }
        Map<String , List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String key = new String(ch);
            List<String> list = map.getOrDefault(key , new ArrayList<>());
            list.add(str);
            map.put(key , list);
        }
        return new ArrayList<>(map.values());
    }
}

//暴力法
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) return res;
        if (strs.length == 1) {
            res.add(Arrays.asList(strs[0]));
            return res;
        }
        
        boolean[] visit = new boolean[strs.length];
        for (int i = 0 ; i < strs.length ; ++i) {
            if (visit[i]) continue;
            visit[i] = true;
            List<String> tmp = new ArrayList<>();
            tmp.add(strs[i]);
            for (int j = i + 1 ; j < strs.length ; ++j) {
                if (visit[j]) continue;
                if (strs[i].length() == 0 && strs[j].length() != 0) continue;
                if (strs[i].length() != 0 && strs[j].length() == 0) continue;
                if (helper(strs[i] , strs[j])) {
                    tmp.add(strs[j]);
                    visit[j] = true;
                }
            }
            res.add(tmp);
        }
        return res;
    }
    
    //判断两个字符串是否是字母异味词组
    public boolean helper(String s1 , String s2) {
        if (s1.length() == 0 && s2.length() == 0) return true;
        if (s1.length() != s2.length()) return false;
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        for (int i = 0 ; i < s1.length() ; ++i) {
            if (ch1[i] != ch2[i]) return false;
        }
        return true;
    }
}


