/*
	一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
	机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
	现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

	输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

	解答：动态规划,dp[i][j]表示第i行第j列处的路径数，状态转移方程
		obstacleGrid[i - 1][j - 1] == 1 dp[i][j] = 0;
		否则 dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
		
*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        if (row == 0) return 0;
        int col = obstacleGrid[0].length;
        if (col == 0) return 0;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[row - 1][col - 1] == 1) return 0;
        
        int[][] dp = new int[row + 1][col + 1];
        dp[1][1] = 1;
        for (int i = 1 ; i <= row ; i ++) {
            for (int j = 1 ; j <= col ; j++) {
                if (obstacleGrid[i - 1][j - 1] == 1 || (i == 1 && j == 1)) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[row][col];
    }
}

/*
	时间复杂度:O(m*n)
	空间复杂度:O(m*n)
*/


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //边界判断
        int row = obstacleGrid.length;
        if (row == 0) return 0;
        int col = obstacleGrid[0].length;
        if (col == 0) return 0;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[row - 1][col - 1] == 1) return 0;
        
        int[] dp = new int[col + 1];
        dp[1] = 1;
        for (int i = 1 ; i <= row ; i ++) {
            for (int j = 1 ; j <= col ; j++) {
                if (i == 1 && j == 1) continue;
                if (obstacleGrid[i - 1][j - 1] == 1) { dp[j] = 0; continue; }
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[col];
    }
}


/*
	时间复杂度:O(m*n)
	空间复杂度:O(n)  n是二维数组的列数
*/