/*
	判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
	
	输入: 121 输出: true
	
	输入: -121
	输出: false
	解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
	
	输入: 10
	输出: false
	解释: 从右向左读, 为 01 。因此它不是一个回文数。
	
	解答：就是把后面的数颠倒过来，看和前面半边的数是否相等。
*/

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 ||(x != 0 && x%10 == 0)) return false;
        
        int data = 0;
        while (x > data) {
            data = data*10 + x%10;
            x = x/10;
        }
        return x == data || x == data/10; 
    }
}

/*
	时间复杂度:O(w),w是位数，也可以是O(logn)，因为每次都要除以10
	空间复杂度:O(1)
*/