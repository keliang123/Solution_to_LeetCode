/*
	给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
示例 1:
输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出: true
示例 2:

输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出: false

	解答：
		动态规划，dp[i][j]，s1前i个和s2的前j个能否匹配s3前i + j个。
*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1 + len2 != len3) return false;
        
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        
        for (int i = 0 ; i <= len1 ; ++i) {
            for (int j = 0 ; j <= len2 ; ++j) {
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
            }
        }
        return dp[len1][len2];
    }
}

/*
	时间复杂度: O(m*n)
	空间复杂度：O(m*n)
*/