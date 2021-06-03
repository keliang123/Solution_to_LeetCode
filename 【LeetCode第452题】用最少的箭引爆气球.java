/*
	在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。

一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。

 
示例 1：

输入：points = [[10,16],[2,8],[1,6],[7,12]]
输出：2
解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球

	解答： 箭是尽可能的往右边射，这样保证能射到更多的气球，而到了一个气球的右端点时才会停下来。即这是射出的一支箭。 体现了贪心的思想。
			二维数组需要排序， 以每个子数组的右端点排序。需要注意是自定义排序时，不能一味的返回 两个数字作减法。
*/


	class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        //按照数组右端点排序
        Arrays.sort(points , new Comparator<int[]>() {
            @Override
            public int compare(int[] o1 , int[] o2) {
               return o1[1] < o2[1] ? -1 : 1;
           }
        });
        
        int res = 1;
        int pos = points[0][1];
        for (int[] point : points) {
            if (point[0] > pos) {
                pos = point[1];
                res++;
            }
        }
        return res;
    }
}

/*
	时间复杂度：O(nlogn)  排序算法
	空间复杂度：O(logn)   排序使用的栈空间
*/