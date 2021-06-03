/*
	给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	
	示例 1:
	输入: 123
	输出: 321
	
	示例 2:
	输入: -123
	输出: -321
	
	示例 3:
	输入: 120
	输出: 21

	解答：对于常规整数反转好说，这题关键就是判断越界，对溢出要有提前判断。
*/

class Solution {
    public int reverse(int x) {
        boolean flag = true;
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE)
            return 0;
        if (x < 0) {
            flag = false;
            x = Math.abs(x);
        }
        int res = x%10;
        x = x/10;
        int max = Integer.MAX_VALUE;
        while (x != 0) {
            res = res*10 + x%10;
            x = x/10; 
            if (x != 0 && res > max/10)
                return 0;
            if (flag == true && x != 0 && res == max/10 && x%10 > max%10)
                return 0;
            if (flag == false && x != 0 && res == max/10 && x%10 > max%10 + 1)
                return 0;
        }
        return flag == true ? res : res * (-1);
    }
}

/*
	时间复杂度空间复杂度都是O(1)
*/