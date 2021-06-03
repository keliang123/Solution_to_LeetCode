/*
	给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

来源：力扣（LeetCode）

	解答：递归加回溯。 每次的字符串都是第一个的字母和后面字符串的组合。
			所以回溯过程中就进行组合。
*/

class Solution {
    char[][] ch = new char[][]{{'a' ,  'b' , 'c'} , {'d' , 'e' , 'f'} , {'g' , 'h' , 'i'} , {'j' , 'k' , 'l'} , {'m' , 'n' , 'o'} , {'p' , 'q'  , 'r' , 's'} ,
                                   {'t' , 'u' , 'v'} , {'w' , 'x' , 'y' , 'z'}};
    
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() <= 0) return list;
        int idx = digits.charAt(0) - '2';
        List<String> next = letterCombinations(digits.substring(1 , digits.length()));
        for (int i = 0 ; i < ch[idx].length ; i++) {
            if (next == null || next.size() <= 0) {
                list.add(String.valueOf(ch[idx][i]));
                continue;
            }
            for (String e : next) {
                list.add(String.valueOf(ch[idx][i]) + e);
            }
        }
        return list;
    }
}

/*
	时间复杂度： O(3^n)
	空间复杂度： O(n)
*/