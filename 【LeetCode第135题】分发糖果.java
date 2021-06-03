/*
	老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
相邻的孩子中，评分高的孩子必须获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？

示例 1:

输入: [1,0,2]
输出: 5
解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。

	解答： 从左到右 从右到左遍历，就是比大小，大的比小的大1。
	
*/

class Solution { 
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 0) return 0;
        if (len == 1) return 1;
        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1 ; i < len ; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        
        int[] right = new int[len];
        right[len - 1] = 1;
        for (int i = len - 2 ; i >= 0 ; --i) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        
        int res = 0;
        for (int i = 0 ; i < len ; ++i) {
            res += Math.max(left[i] , right[i]);
        }
        return res;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(n)
*/
