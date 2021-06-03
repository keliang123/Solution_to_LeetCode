/*
	@keliang123
	*LeetCode第8题： 字符串转化整数
	
首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：
	本题中的空白字符只包括空格字符 ' ' 。
	假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
	如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
	示例：
		输入: "4193 with words"
		输出: 4193
		解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

结题思路：
	从头开始遍历字符串，首先遍历到第一次出现非空字符处，判断这个点处符号，如果是字母直接返回0.
	如果是正负号，用flag记录下符号。
	然后开始取数字部分了，这里有个问题就是判断正负值溢出的情况，所以分情况讨论。
	这里有个小技巧，用res和Integer.MAX_VALUE/10比较，这样不会出现溢出情况同时判断了是否溢出。

*/

//实现代码：
class Solution {
    public int myAtoi(String str) {
        
        int len = str.length();
        if(len == 0)
            return 0;
        
        int index = 0;
        while (str.charAt(index) == ' ') {
            index++;
            if (index == len)
                return 0;
        }
       
        //判断正负
        int flag = 1;
        if (str.charAt(index) == '+') {
            flag = 1;
            index ++;
            if(index == len || str.charAt(index) < '0' || str.charAt(index) > '9')
                return 0;
        }
        if(str.charAt(index) >= '0' && str.charAt(index) <= '9')
            flag = 1;
        if(str.charAt(index) == '-') {
            flag = -1;
            index ++;
        }
        //求数字部分
        int res = 0;
        for ( ; index < len ; index ++){
            if (str.charAt(index) < '0' || str.charAt(index) > '9')
                break;
            if (str.charAt(index) >= '0' && str.charAt(index) <= '9'){
                res = str.charAt(index) - '0' + res*10;
                //溢出判断
                if (flag == 1 && index < len - 1 && str.charAt(index + 1) >= '0' && 
                        str.charAt(index + 1) <= '9' && res > Integer.MAX_VALUE/10 )
                    return Integer.MAX_VALUE;
                if (flag == 1 && index < len-1 && str.charAt(index + 1) >= '0' && 
                        str.charAt(index + 1) <= '9' &&  res == Integer.MAX_VALUE/10 && 
                            str.charAt(index + 1) - '0' > Integer.MAX_VALUE%10 )
                    return Integer.MAX_VALUE;
                if (flag == -1 && index < len - 1 && str.charAt(index + 1) >= '0' && 
                        str.charAt(index + 1) <= '9'&& - res < Integer.MIN_VALUE/10 )
                    return Integer.MIN_VALUE;
                if (flag == -1 && index < len - 1 && str.charAt(index + 1) >= '0' && 
                        str.charAt(index + 1) <= '9' && -res == Integer.MIN_VALUE/10 && 
                            - (str.charAt(index + 1) - '0') < Integer.MIN_VALUE%10 )
                    return Integer.MIN_VALUE;
            }
        }
        return res*flag;
      
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("    764287hjsda766"));
    }


}

/*
时间复杂度：遍历了一遍字符串 O(n)
空间复杂度：O(1)
*/





