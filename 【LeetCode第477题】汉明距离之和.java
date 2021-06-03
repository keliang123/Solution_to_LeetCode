/*
	两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。

计算一个数组中，任意两个数之间汉明距离的总和。

示例:

输入: 4, 14, 2

输出: 6

解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
所以答案为：
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

	解答： 按位比较，所有元素每一位1的个数 和 0个数，每一位的汉明距离就是这两位的积。
*/

class Solution {
    public int totalHammingDistance(int[] nums) {
        int len = nums.length;
        int res = 0;
        if (len == 0) return res;
        for (int i = 1 ; i <= 32 ; ++i) {
            int count = 0;
            for (int e : nums) {
                count += ((e>>i)&1);
            }
            res += (count*(len - count));
        }
        return res;
    }
}

/*
	时间复杂度：O(n*L)
	空间复杂度：O(1)
*/