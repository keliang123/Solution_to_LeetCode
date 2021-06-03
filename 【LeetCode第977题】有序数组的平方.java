/*
	给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

 

示例 1：

输入：[-4,-1,0,3,10]
输出：[0,1,9,16,100]
示例 2：

输入：[-7,-3,2,3,11]
输出：[4,9,9,49,121]

	解答：双指针。因为数组是非降序的，所以利用双指针，平方最大的值只能在两边的数中取得。
*/

class Solution {
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        int L = 0; int R = len - 1;
        int idx = len - 1;
        while (L <= R) {
            int LData = A[L]*A[L];
            int RData = A[R]*A[R];
            res[idx--] = Math.max(LData , RData);
            if (LData < RData) {
                R--;
            } else {
                L++;
            }
        }
        return res;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/