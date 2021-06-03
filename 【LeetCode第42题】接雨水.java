/*
	给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
	示例:
		输入: [0,1,0,2,1,0,1,3,2,1,2,1]
		输出: 6
		
	题解：使用双指针从两边出发向山顶爬山，遇到凹坑就填水，也就是计算高度差。
*/

//代码：
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3)
            return 0;

        int maxData =height[0] , maxIndex = 0;
        for (int i = 0 ; i < len ; i ++) {
            maxIndex = (height[i] > maxData ? i : maxIndex);
            maxData = (height[i] > maxData ? height[i] : maxData);
        }

        int i = 0 , j = len - 1;
        int res = 0;
        while (i < maxIndex || j > maxIndex) {
            int tempData1 = height[i];
            while (i < maxIndex && height[i] <= tempData1) {
                res += (tempData1 - height[i++]);
            }

            int tempData2 = height[j];
            while (j > maxIndex && height[j] <= tempData2) {
                res += (tempData2 - height[j--]);
            }
        }
        return res;
    }
}