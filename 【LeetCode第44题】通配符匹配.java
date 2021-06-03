/*
	给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
	'?' 可以匹配任何单个字符。'*' 可以匹配任意字符串（包括空字符串）。
	两个字符串完全匹配才算匹配成功。

说明:
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。


示例：
输入:    s = "aa"    p = "a"    输出: false
输入:    s = "aa"    p = "*"    输出: true
输入:    s = "adceb"    p = "*a*b"    输出: true
输入:    s = "acdcb"    p = "a*c?b"    输出: false

	解答：
		两个字符串在一起的题目，其他都不要想，直接考虑动态规划，而且是字符串匹配题型。首先是构造动态规划转移方程。
		dp[ i ][ j ]表示s的前i位和p的前j位是否匹配。那么转移方程首先考虑的是s的第i位和p的第j位的取值情况，
		当然这里的s都是字母，关键就是p的第j位，p的第j位可以是字母可以是?，可以是*。转移方程如下所示：(s[ i ]表示s的第i位的字母)
	
		if ( s[ i ] == p[ j ])  dp[ i ][ j ] = dp[ i - 1 ][ j - 1 ]
		if ( s[ i ] ！= p[ j ])
		p[ j ]是字母 ：dp[ i ][ j ] = false;
		p[ j ]是？：dp[ i ][ j ] = dp[ i - 1 ][ j - 1 ]
		p[ j ]是*  ：dp[ i ][ j ] = dp[ i ][ j - 1 ] || dp[ m ][ j - 1 ]  m从[ 1 , i  - 1]遍历

*/

class Solution {
    public boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        //初始化
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        for (int j = 1 ; j <= lenP && p.charAt(j - 1) == '*' ; j++)
            dp[0][j] = true;
        dp[0][0] = true;
        
        for (int i = 1 ; i <= lenS ; i ++) {
            for (int j = 1 ; j <= lenP ; j ++) {
                char charS = s.charAt(i - 1);
                char charP = p.charAt(j - 1);
                //如果charP是字母
                if (charP - 'a' >= 0 && charP - 'a' <= 26) {
                    if (charS == charP) dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
               
                //charP是？时
                if (charP == '?')  dp[i][j] = dp[i - 1][j - 1];
                //charP是*时
                if (charP == '*') {
                    //p的第j - 1位是否存在
                    if (j - 2 < 0) dp[i][j] = true;
                    else {
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                        for (int m = 1 ; m < i ; m ++) {
                            dp[i][j] = dp[i][j] || dp[m][j - 1];
                            if (dp[i][j]) break;
                        }
                    }
                }
            }
        }
        return dp[lenS][lenP];
    }
}

/*
	时间复杂度：O(m*n)
	空间复杂度:	O(m*n)
*/
