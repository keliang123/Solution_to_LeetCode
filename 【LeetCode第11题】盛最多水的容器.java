/*
	给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) .
	在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
	找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	
	示例：

	输入：[1,8,6,2,5,4,8,3,7]
	输出：49

	解答：（1），暴力法，用两个for循环遍历所有的可能， 取最大值。
		  （2），双指针，一个从索引0开始移动，另一个从索引len - 1 开始移动，因为而且每次都是从height[]值小的移动，因为木桶效应
				只有移动小的，下一次才有可能取大的。

*/

class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
    
        int i = 0 , j = len - 1;
        int res = 0 ;
        while (i < j) {
            res = Math.max(res , Math.min(height[i] , height[j])*(j - i));
            if (height[i] < height[j]) {
                i ++;
            } else {
                j --;
            }
        }
        return res;
    }
}

/*
	时间复杂度：O(n)，只遍历了一遍数组。
	空间复杂度：O(1),用到常数级别的内存空间
*/