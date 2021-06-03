/*
	给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

X X X X
X O O X
X X O X
X O X X
运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
解释:

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

	解答：广度优先遍历，先把边界上的点O和相邻的O都找出来。标记出来。
			最后再遍历，修改未标记的O
*/

class Solution {
    public void solve(char[][] board) {
        int row = board.length;
        if (row <= 2) return;
        int col = board[0].length;
        if (col <= 0 ) return;
        //标记在“边界”(包括相邻)上的O
        boolean[][] flag = new boolean[row][col];
        //广度遍历中的一访问的点。
        boolean[][] visited = new boolean[row][col];
        //偏移数组
        int[][] offset = new int[][]{{-1 , 0} , {0 , 1} , {1 , 0} , {0 , -1}};
        
        //四条边界上遍历
        for (int i = 0 ; i < col ; ++i) {
            Queue<int[]> que = new LinkedList<>();
            if (board[0][i] == 'O' && !visited[0][i]) {
                que.offer(new int[]{0 , i});
                flag[0][i] = true;
                visited[0][i] = true;
                while (!que.isEmpty()) {
                    int[] tmp = que.poll();
                    //四个方向
                    for (int m = 0 ; m < 4 ; m++) {
                        int newX = tmp[0] + offset[m][0];
                        int newY = tmp[1] + offset[m][1];
                        if (inBoard(newX , newY , row , col) && 
                            board[newX][newY] == 'O' && !visited[newX][newY]) {
                            flag[newX][newY] = true;
                            visited[newX][newY] = true;
                            que.offer(new int[]{newX , newY});
                        }
                    }
                }
            }
        }
        for (int i = 0 ; i < col ; ++i) {
            Queue<int[]> que = new LinkedList<>();
            if (board[row - 1][i] == 'O' && !visited[row - 1][i]) {
                que.offer(new int[]{row - 1 , i});
                flag[row - 1][i] = true;
                visited[row - 1][i] = true;
                while (!que.isEmpty()) {
                    int[] tmp = que.poll();
                    //四个方向
                    for (int m = 0 ; m < 4 ; m++) {
                        int newX = tmp[0] + offset[m][0];
                        int newY = tmp[1] + offset[m][1];
                        if (inBoard(newX , newY , row , col) && 
                            board[newX][newY] == 'O' && !visited[newX][newY]) {
                            flag[newX][newY] = true;
                            visited[newX][newY] = true;
                            que.offer(new int[]{newX , newY});
                        }
                    }
                }
            }
        }
        //第一列
        for (int i = 0 ; i < row ; ++i) {
            Queue<int[]> que = new LinkedList<>();
            if (board[i][0] == 'O' && !visited[i][0]) {
                que.offer(new int[]{i , 0});
                flag[i][0] = true;
                visited[i][0] = true;
                while (!que.isEmpty()) {
                    int[] tmp = que.poll();
                    //四个方向
                    for (int m = 0 ; m < 4 ; m++) {
                        int newX = tmp[0] + offset[m][0];
                        int newY = tmp[1] + offset[m][1];
                        if (inBoard(newX , newY , row , col) && 
                            board[newX][newY] == 'O' && !visited[newX][newY]) {
                            flag[newX][newY] = true;
                            visited[newX][newY] = true;
                            que.offer(new int[]{newX , newY});
                        }
                    }
                }
            }
        }
        //最后一列
        for (int i = 0 ; i < row ; ++i) {
            Queue<int[]> que = new LinkedList<>();
            if (board[i][col - 1] == 'O' && !visited[i][col - 1]) {
                que.offer(new int[]{i , col - 1});
                flag[i][col - 1] = true;
                visited[i][col - 1] = true;
                while (!que.isEmpty()) {
                    int[] tmp = que.poll();
                    //四个方向
                    for (int m = 0 ; m < 4 ; m++) {
                        int newX = tmp[0] + offset[m][0];
                        int newY = tmp[1] + offset[m][1];
                        if (inBoard(newX , newY , row , col) && 
                            board[newX][newY] == 'O' && !visited[newX][newY]) {
                            flag[newX][newY] = true;
                            visited[newX][newY] = true;
                            que.offer(new int[]{newX , newY});
                        }
                    }
                }
            }
        }
        for (int i = 1 ; i < row - 1 ; ++i) {
            for (int j = 1 ; j < col - 1 ; ++j) {
                if (board[i][j] == 'O' && !flag[i][j])
                    board[i][j] = 'X';
            }
        }
    }
    
    //点在界内
    private boolean inBoard(int i , int j , int row , int col) {
        return i >= 0 && i < row && j >= 0 && j < col;
    }
}

/*
	时间复杂度：O(n*m)
	空间复杂度：O(n*m)
*/