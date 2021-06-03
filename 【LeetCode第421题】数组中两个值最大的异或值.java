/*
	给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
你能在O(n)的时间解决这个问题吗？

示例:
输入: [3, 10, 5, 25, 2, 8]
输出: 28

解释: 最大的结果是 5 ^ 25 = 28.

	解答： 最大的异或 最大就是最大数的二进制表示的最大数，比如25 = 11001(2)，最大只可能是11111(2)，不能再大了。
		那么就按照最大二进制数按位遍历。就是这个位可不可能是1。
		
		算法

		首先计算数组中最大数的二进制长度 LL。

		初始化 max_xor = 0。

		从 i = L - 1i=L−1 遍历到 i = 0i=0（代表着从最左侧的比特位 L - 1L−1 遍历到最右侧的比特位 00）：

			将 max_xor 左移，释放出下一比特位的位置。

			初始化 curr_xor = max_xor | 1（即将 max_xor 最右侧的比特置为 1）。

			遍历 nums，计算出长度为 L - iL−i 的所有可能的按位前缀。

			将长度为 L - iL−i 的按位前缀加入哈希集合 prefixes，按位前缀的计算公式如下：num >> i。
			遍历所有可能的按位前缀，检查是否存在 p1，p2 使得 p1^p2 == curr_xor。比较简单的做法是检查每个 p，看 curr_xor^p 是否存在。如果存在，就将 max_xor 改为 curr_xor（即将 max_xor 最右侧的比特位改为 1）。如果不存在，max_xor 最右侧的比特位继续保持为 0。

		返回 max_xor。
*/

class Solution {
    public int findMaximumXOR(int[] nums) {
        int len = nums.length;
        int maxData = nums[0];
        for (int num : nums) maxData = Math.max(maxData , num);
        int stringLen = Integer.toBinaryString(maxData).length();
        
        HashSet<Integer> set = new HashSet<>(16);
        int maxXor = 0 , curXor = 0;
        //遍历最长二进制数字
        for (int i = stringLen - 1 ; i >= 0 ; i--) {
            //让位一个下一个最大值
            maxXor <<= 1;
            //假设当前是最大的，最右一位掷1，就是看数组中前stringLen-i位是否有两个值异或等于curXor
            curXor = maxXor | 1;
            set.clear();
            for (int num : nums) set.add(num>>i);
            //是否存在p1^p2 == curXor ,也就是 p1 = curXor^p2是否存在
            for (int num : nums) {
                if (set.contains((num>>i)^curXor)) {
                    maxXor = curXor;
                    break;
                }  
            }
        }
        return maxXor;
    }
}


/*
	时间复杂度:O(n)
	空间复杂度:O(1)
*/