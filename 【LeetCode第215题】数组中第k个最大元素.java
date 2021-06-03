/*
	在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
	
示例 1:
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5

示例 2:
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4

	解答;使用优先队列，优先队列默认是最小堆，那么只要一个大小是k的优先队列

*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k > len) return -1;
        //新建一个数量为k的最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int num : nums) {
            if (pq.size() < k) { pq.add(num); continue; }
            if (pq.peek() < num) {
                pq.poll();
                pq.add(num);
            }
        }
        return pq.poll();
    }
}


/*
	时间复杂度:O(nlogk)
	空间复杂度:O(k)
*.