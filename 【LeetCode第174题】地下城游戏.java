/*
	一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。

骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。

有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。

为了尽快到达公主，骑士决定每次只向右或向下移动一步。

 

编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。

例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)
 

	解答： 动态规划，
		这题是既要知道每条路径的最小值，还要知道这些路径中的最大值，也就是路径和和路径最小值都要知道。
		这里的动态规划需要到这来，也就是从右下到左上。
		dp[i][j]是坐标[i][j]到终点的最下初始值，所有动态规划方程就是：
		dp[i][j] = max (1 , min(dp[i+1][j] , dp[i][j+1]) - dungeon[i][j])
		当前的初始值加上当前伤害要到达这个坐标下面或者右面，所以等于那两个坐标最小值就好。
*/

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        if (row == 0) return 1;
        int col = dungeon[0].length;
        if (col == 0) return 1;
        
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = Math.max(1 , 1- dungeon[row - 1][col - 1]);
        for (int i = row - 1 ; i >= 0 ; i--) {
            for (int j = col - 1 ; j >= 0 ; j --) {
                if (i == row - 1 && j == col - 1) continue;
                int temp = Math.min((i + 1 == row ? Integer.MAX_VALUE : dp[i + 1][j]) , 
            (j + 1 == col ? Integer.MAX_VALUE : dp[i][j + 1])) - dungeon[i][j];
                dp[i][j] = Math.max(temp , 1);
            }
        }
        return dp[0][0];
    }
}

/*
	时间复杂度:O(m*n)
	空间复杂度:O(m*n)
*/