/*
	给定一个未排序的整数数组，找出最长连续序列的长度。
	要求算法的时间复杂度为 O(n)。
	
	输入: [100, 4, 200, 1, 3, 2]
	输出: 4
	解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
	
	解答： 要求时间复杂度是O(n)，也就是遍历一遍就可以。
		这题我没有想到用hash表，哈希表添加删除查看时间复杂度都是O(1)，怎么就想不到呢。
		把数组所有数放进哈希表，然后遍历每个数x，就看有没有x+1 x+2....然后比较最大长度。
		然后为了不用每个都遍历，比如x+2就不用遍历，所以每次都在x-1不存在时遍历。这样保证是从第一个开始的
		这题技巧很强，我没想到.
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0 ; i < len ; i ++) 
            set.add(nums[i]);
        
        int res = 0 ;
        for (int e : set) {
            if (!set.contains(e - 1)) {
                int index = e; int temp = 1;
                while (set.contains(index + 1)) {
                    index ++;
                    temp ++;
                }
                res = Math.max(res , temp);
            }
        }
        return res;
        
    }
}

/*
	时间复杂度是O(n)
	空间复杂度是O(n)
*/