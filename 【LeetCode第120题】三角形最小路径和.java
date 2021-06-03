/*
	给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
	相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

例如，给定三角形：

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：
如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

	解答：
		动态规划，从上到下遍历，到最后一行，顺便选出最小的。
		转移数组可以从二维变为一维。
*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        if (row == 0) return 0;
        int col = triangle.get(row - 1).size();
        if (col == 0) return 0;
        
        int[][] res = new int[col][col];
        res[0][0] = triangle.get(0).get(0);
        if (row == 1) return res[0][0];
        int result = Integer.MAX_VALUE;
        for (int i = 1 ; i < row ; ++i) {
            for (int j = 0 ; j <= i ; ++j) {
                if (j == 0) 
                    res[i][j] = res[i - 1][j] + triangle.get(i).get(j);
                else if (j == i)
                    res[i][j] = res[i - 1][j - 1] + triangle.get(i).get(j);
                else
                    res[i][j] = Math.min(res[i - 1][j] , res[i - 1][j - 1]) +
                        triangle.get(i).get(j);
                if (i == row - 1)
                    result = Math.min(result , res[i][j]);
            }
        }
        return result;
    }
}

/*
	时间复杂度:O(n^2)  实际是 n(n+1)/2
	空间复杂度:O(n^2)   n是三角形行数
*/

//数组降维
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        if (row == 0) return 0;
        int col = triangle.get(row - 1).size();
        if (col == 0) return 0;
        
        int[] res = new int[col];
        res[0] = triangle.get(0).get(0);
        if (row == 1) return res[0];
        int result = Integer.MAX_VALUE;
        for (int i = 1 ; i < row ; ++i) {
            for (int j = i ; j >= 0 ; --j) {
                if (j == i)
                    res[j] = res[j - 1] + triangle.get(i).get(j);
                else if (j == 0)
                    res[j] = res[j] + triangle.get(i).get(j);
                else
                    res[j] = Math.min(res[j] , res[j - 1]) + triangle.get(i).get(j);
                if (i == row - 1)
                    result = Math.min(result , res[j]);
            }
        }
        return result;
    }
}

/*
	时间复杂度:O(n^2)  实际是 n(n+1)/2
	空间复杂度:O(n)   n是三角形行数
*/




