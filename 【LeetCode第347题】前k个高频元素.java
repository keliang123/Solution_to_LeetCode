/*
	给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

 

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
 

提示：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
你可以按任意顺序返回答案。

	解答：先用哈希表找到每个数及其出现的频次。然后用最小堆放前k个出现的最多的数字。
*/

class Solution {
    
    public class Data {
        public int key;
        public int value;
        public Data(int key , int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer , Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num , map.get(num) + 1);
            } else {
                map.put(num , 1);
            }
        }
        PriorityQueue<Data> pq = new PriorityQueue<>(k , (data1 , data2) -> data1.value - data2.value);
        for (Map.Entry<Integer , Integer> entry : map.entrySet()) {
            if (pq.isEmpty() || pq.size() < k) pq.offer(new Data(entry.getKey() , entry.getValue()));
            else if (entry.getValue() > pq.peek().value) {
                pq.poll();
                pq.offer(new Data(entry.getKey() , entry.getValue()));
            }
        }
        int[] res = new int[k];
        for (int i = 0 ; i < k ; i++) {
            res[i] = pq.poll().key;
        }
        return res;
    }
}

/*
	时间复杂度：O(nlogk)
	空间复杂度; O(n)
*/