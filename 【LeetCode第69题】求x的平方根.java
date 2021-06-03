/*
	实现 int sqrt(int x) 函数。
	计算并返回 x 的平方根，其中 x 是非负整数。
	由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

	输入: 8
	输出: 2
	说明: 8 的平方根是 2.82842..., 
    由于返回类型是整数，小数部分将被舍去。
	
	解答：
		可以从0开始到n一个一个试。
		最好是二分查找。
		牛顿迭代是数学方法，也可以参考。
	
*/

//方法一 时间复杂度O(n)
class Solution {
    public int mySqrt(int x) {
        for (int i = 0 ; i <= x ; i ++) {
            if (i*i <= x && (long)(i + 1)*(i + 1) > x )
                return i;
        }
        return -1;
    }
}

//方法二 二分查找 时间复杂度O(logn)
class Solution {
    public int mySqrt(int x) {
        int low = 0 , high = x;
        if (x == 0 || x == 1)
            return x;
        while (low <= high) {
            int mid = (low + high)/2;
            if ((long)mid*mid == x) {
                return mid;
            } else if ((long)mid*mid < x){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low - 1;
    }
}