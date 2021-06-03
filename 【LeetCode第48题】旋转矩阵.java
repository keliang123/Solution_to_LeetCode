/*
	给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

	解答： 先沿主对角线对折  再沿横轴中心线对称。
*/

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //先对折
        for (int i = 0 ; i < n - 1 ; ++i) {
            for (int j = 0 ; j < n - 1 - i ; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = tmp;
            }
        }
        
        //再对称
        for (int i = 0 ; i < n/2 ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] =  tmp;
            }
        }       
    }
}

/*
	时间复杂度：O(n^2)
	空间复杂度：O(1)
*/