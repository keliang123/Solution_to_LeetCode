/*
	给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false

	解答： 深度优先遍历，递归。
*/

class Solution {
    boolean[][] isVisit ; 
    public boolean exist(char[][] board, String word) {
        int width = board.length;
        int len = board[0].length;
        if(width == 0) return false;
        if(word.length() == 0) return false;

        isVisit = new boolean[width][len];
        for(int i = 0 ; i < width ; i++)
            for(int j = 0 ; j < len ; j++)
                if(searchWord(board, word, 0, i, j))
                    return true;
        return false;
    }

    //递归函数 在startx 和 starty 坐标开始，此时这个字母是否和 单词index个字母是否相等
    private boolean searchWord(char[][] board, String word, int index, int startx, int starty){
        if(index == word.length() -1)
                return board[startx][starty] == word.charAt(index);
        int[][] searchSort = {{0,-1},{0,1},{-1,0},{1,0}};
        if(!isIn( board , startx , starty)) return false;
        if(board[startx][starty] == word.charAt(index)){
            isVisit[startx][starty] = true ;
            for(int i = 0 ; i < 4 ; i++){
                int newx = startx + searchSort[i][0];
                int newy = starty + searchSort[i][1];
                if(isIn( board , newx , newy ) && !isVisit[newx][newy] && 
                        searchWord(board , word , index + 1 , newx , newy))
                        return true;
            }
            //回溯
            isVisit[startx][starty] = false ;
        }
        return false;
    }

    //判断startx 和starty是否越界
    private boolean isIn(char[][] board , int startx , int starty){
        return  startx>=0 && startx < board.length && starty >= 0 && starty < board[0].length;
    }
}