/*
	编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。



一个数独。



答案被标成红色。

Note:

给定的数独序列只包含数字 1-9 和字符 '.' 。
你可以假设给定的数独只有唯一解。
给定数独永远是 9x9 形式的。

	解答： 递归 + 回溯，深度优先遍历，每一个空格都有9种取值，遍历每一种情况。 设置一个行 列 块的boolean数组，判断是否是唯一的。
*/

class Solution {
    //定义行 列 块 每个数是否出现
    private boolean[][] row;
    private boolean[][] col;
    private boolean[][][] box;
    private boolean val;
    private List<int[]> path; //递归过程中的路径，遇到的空格，就可以放数字了
    
    public void solveSudoku(char[][] board) {
        row = new boolean[9][9];
        col = new boolean[9][9];
        box = new boolean[3][3][9];
        val = false;
        path = new ArrayList<>();
        //初始化
        for (int i = 0 ; i < 9 ; ++i) {
            for (int j = 0 ; j < 9 ; ++j) {
                if (board[i][j] == '.') {
                    path.add(new int[]{i , j});
                } else {
                    int data = board[i][j] - '0' - 1;
                    row[i][data] = true;
                    col[j][data] = true;
                    box[i/3][j/3][data] = true;
                }
            }
        }
        dfs(board , 0);
    }
    
    //深度优先遍历
    private void dfs(char[][] board , int idx) {
        if (idx == path.size()) {
            val = true;
            return;
        }
        int[] tmp = path.get(idx);
        int x = tmp[0] ; int y = tmp[1];
        for (int i = 1 ; i <= 9 && !val ; ++i) {
            if (!row[x][i - 1] && !col[y][i - 1] && !box[x/3][y/3][i - 1]) {
                board[x][y] = (char)(i + '0');
                row[x][i - 1] = col[y][i - 1] = box[x/3][y/3][i - 1] = true;
                dfs(board , ++idx);
                row[x][i - 1] = col[y][i - 1] = box[x/3][y/3][i - 1] = false;
                --idx;
            }
        }
    }
}