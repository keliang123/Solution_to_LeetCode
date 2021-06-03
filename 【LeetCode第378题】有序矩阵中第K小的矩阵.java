/*
	给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
	请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
	
	示例：
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,
返回 13。

	解答：
		(1) 优先队列，设置一个最大堆，放k的数据，把矩阵所有数放进去，只要比堆中最大值小，就入堆。最后，堆中最大值就是矩阵中第k小元素。
		(2) 二分，利用矩阵排序的性质。对于矩阵中某个数，它的左上角部分都是小于它的，它的右下部分都是大于它的，比较小于它的那部分数的个数就行，比k小就表示第k小
			元素在右下部分，比k大在左上部分。
*/

//方法一
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k , (a , b) -> b - a);
        for (int i = 0 ; i < matrix.length ; i ++) {
            for (int j = 0 ; j < matrix[0].length ; j ++) {
                if (pq.size() < k) pq.add(matrix[i][j]);
                else if (matrix[i][j] <= pq.peek()) {
                    pq.poll();
                    pq.add(matrix[i][j]);
                }
            }
        }
        return pq.peek();
    }
}

/*
	时间复杂度:O(m*n*log(k))
	空间复杂度:O(k)
*/

//方法二
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        if (row == 0) return -1;
        int col = matrix[0].length;
        if (col == 0) return -1;
        int minData = matrix[0][0];
        int maxData = matrix[row - 1][col - 1];
        
        int count = 0;
        while (minData < maxData) {
            int mid = minData + ((maxData - minData)>>1);
            count = countK(matrix , mid);
            if (count < k) minData = mid + 1;
            else maxData = mid;
        }
        return minData;
    }
    
    //获取比data小的个数
    private int countK(int[][] matrix , int data) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = row - 1; int j = 0;
        int res = 0;
        while (i >= 0 && j < col) {
            if (matrix[i][j] <= data) {
                res += i + 1;
                j++;
            } else i--;
        }
        return res;
    }
}

/*
	时间复杂度:O(log(m+n))
	空间复杂度:O(1)
*/
