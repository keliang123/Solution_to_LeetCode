/*
	给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
	你可以对一个单词进行如下三种操作：
		插入一个字符
		删除一个字符
		替换一个字符
 
示例 1：
	输入：word1 = "horse", word2 = "ros"
	输出：3
	解释：
	horse -> rorse (将 'h' 替换为 'r')
	rorse -> rose (删除 'r')
	rose -> ros (删除 'e')

解答：类似于两个字符串的最长公共子串，使用动态规划，对于两个字符串A和B，都想法构造转移表达式，也就是降到长度小一点时候比较。
		插入 删除 替换分别为一步操作
		当两个最后一个字符A[i]和B[j]相等时： 
				res[i][j] = min( 1 + res[i][j - 1] , 1 + res[i - 1][j] , res[i - 1][j - 1]);
		当两个最后一个字符A[i]和B[j]不相等时：
				res[i][j] = min( 1 + res[i][j - 1] , 1 + res[i - 1][j] , 1 + res[i - 1][j - 1]);
		设置记忆数组时，数组大小是： len1 + 1 * len2 + 1 (包括“”)
*/

//代码:
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0)
            return len2;
        if (len2 == 0)
            return len1;
        int[][] memo = new int[len1 + 1][len2 + 1];
        
        if (word1.charAt(0) == word2.charAt(0))
            memo[0][0] = 0;
        else 
            memo[0][0] = 1;

        for (int i = 0 ; i <= len1 ; i ++)
            memo[i][0] = i;
        for (int j = 1 ; j <= len2 ; j ++)
            memo[0][j] = j;

        for (int i = 1 ; i <= len1 ; i ++) {
            for (int j = 1 ; j <= len2 ; j ++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    int temp = Math.min(memo[i - 1][j - 1] , 1 + memo[i - 1][j]);
                    memo[i][j] = Math.min(temp , 1 + memo[i][j - 1]);
                } else {
                    int temp = Math.min(1 + memo[i - 1][j - 1] , 1 + memo[i - 1][j]);
                    memo[i][j] = Math.min(temp , 1 + memo[i][j - 1]);
                }
            }
        }
        return memo[len1][len2];
    }
}


/*
	时间复杂度：O(m*n) 与两个字符串长度有关
	空间复杂度：O(m*n) 定义记忆数组大小与字符串长度有关
*/
