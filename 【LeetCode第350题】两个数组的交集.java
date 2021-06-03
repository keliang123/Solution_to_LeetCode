/*
	给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
进阶:

	解答：
		1、用一个哈希表存储一个数组的数及其出现的次数，然后遍历第二个数组，如果在哈希表中就是一个结果，且哈希表中这个数个数减1.
		2、两个数组排序后，双指针分别遍历两个数组。
*/

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 || len2 == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int index1 = 0 ; int index2 = 0;
        List<Integer> list = new ArrayList<>(Math.min(len1 , len2));
        while (index1 < len1 && index2 < len2) {
            if (nums1[index1] == nums2[index2]) {
                list.add(nums1[index1]);
                ++index1 ; ++index2;
            } else if (nums1[index1] > nums2[index2]) 
                ++index2;
            else ++index1;
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (int e : list) {
            res[index ++] = e;
        }
        return res;
    }
}

/*
	时间复杂度：O(mlogm + nlogn)  ,排序耗时
	空间复杂度:O(max(m , n))
*/