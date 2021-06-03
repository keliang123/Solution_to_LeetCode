/*
	
	给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
	示例 1:

	输入: "abcabcbb"
	输出: 3 
	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

	解答：1、暴力法，用两次for循环，就是遍历所有[i , j]这个区间，里面的字符用map存储，只要新来的j + 1字符不在map里面就加入，结果加1.否则这个i不行，i+1再次循环。
		  2、滑动窗口，也是在[i , j]区间上寻找，把里面字符放在set集合里面，新来的A[j]不在集合里面res加1，否则，删除左侧的A[i]，直到A[j]在集合里面。
		  3、进一步优化，用映射代替集合，使用HashMap存储字符和对应的索引，这样当[i , j)之间存在j* , 使得A[j*] = A[j] ,此时j和j*的差就是一个res.在更新新的j。比较所有的res即为最长长度。
		  
*/

//实现代码（第二种方法）
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len <= 0)
            return len;
        HashSet<Character> set = new HashSet<>(len);

        int res = 0;
        int i = 0 , j = 0;
        while (j < len) {
            char temp = s.charAt(j);
            if (set.isEmpty() || !set.contains(temp)) {
                set.add(s.charAt(j ++));
                res = Math.max(res , set.size());
            }
            else
                set.remove(s.charAt(i++));   
        }
        return res;
    }
}

/*
	时间复杂度是O(n) , 准确是2n。
	空间复杂度是O(n)，定义了一个字符串长度的集合。
*/