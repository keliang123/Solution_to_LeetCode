/*
	给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

示例 1:
输入: [3,0,1]
输出: 2
示例 2:

输入: [9,6,4,2,3,5,7,0,1]
输出: 8

	解答：
		(1)哈希表，把所以数据放在哈希表，从0遍历到n，哪个不在哈希表就是结果，但空间复杂度是O(n)
		(2)位运算，将0到n进行位运算，再和数组所有数据进行位运算，最后结果就是缺失数据。
		(3)高斯公式，0到n求和，减去数组所有数据和就是结果。
*/

//方法一
class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        HashSet<Integer> set = new HashSet<>(16);
        for (int num : nums) set.add(num);
        
        for (int i = 0 ; i <= len ; ++i) {
            if (!set.contains(i))
                return i;
        }
        return 0;
    }
}

//方法二
class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        for (int i = 0 ; i < nums.length ; i++) {
            len ^= (i^nums[i]);
        }
        return len;
    }
}

//方法三
class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int arraySum = 0;
        for (int num : nums) arraySum += num;
        return len*(len + 1)/2 - arraySum;
    }
}
