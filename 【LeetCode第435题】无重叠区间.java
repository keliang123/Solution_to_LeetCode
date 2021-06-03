/*
	给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

注意:

可以认为区间的终点总是大于它的起点。
区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
示例 1:

输入: [ [1,2], [2,3], [3,4], [1,3] ]

输出: 1

解释: 移除 [1,3] 后，剩下的区间没有重叠。

	解答： 右端点排序
			假设在某一种最优的选择方法中，[lk,rk][lk,rk] 是首个（即最左侧的）区间，那么它的左侧没有其它区间，右侧有若干个不重叠的区间。
			设想一下，如果此时存在一个区间 [l_j, r_j][l j,rj]使得 r_j < r_kr j<rk，
			即区间 jj 的右端点在区间 kk 的左侧，那么我们将区间 kk 替换为区间 jj，
			其与剩余右侧被选择的区间仍然是不重叠的。而当我们将区间 kk 替换为区间 jj 后，
			就得到了另一种最优的选择方法。
			
			贪心，右端点始终最小，那么尽可能多的选择了区间。维护一个右边界。
*/

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int row = intervals.length;
        if (row == 0) return 0;
        Arrays.sort(intervals , new Comparator<int[]>() {
            public int compare(int[] a1 , int[] a2) {
                return a1[1] - a2[1];
            }
        });
        
        int right = intervals[0][1];
        int res = 1;
        for (int i = 1 ; i < intervals.length ; ++i) {
            if (intervals[i][0] >= right) {
                res++;
                right = intervals[i][1];
            }
        } 
        return intervals.length - res;
    }
}

/*
	时间复杂度：nlog(n)
	空间复杂度：1
*/

