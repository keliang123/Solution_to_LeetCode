/*
	你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。

示例 1:

输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
输出: [20,24]
解释: 
列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
注意:

给定的列表可能包含重复元素，所以在这里升序表示 >= 。
1 <= k <= 3500
-105 <= 元素的值 <= 105
对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。

	解答：
		将每个列表的一个数放入一个TreeSet，这样就是看treeset的最大值和最小值的差值的最小值。
		自定义一个对象，表示第几个列表第几个数，数值是多少，需要自定义treeset排序。
		每次TreeSet删除最小的，放入当前列表最大的。
	
*/

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        TreeSet<Element> set = new TreeSet<>( (e1 , e2) -> {
            if (e1.val == e2.val) return e1.list - e2.list;
            else 
                return e1.val - e2.val;
            });
        for (int i = 0 ; i < nums.size() ; ++i) {
            set.add(new Element(i , 0 , nums.get(i).get(0)));
        }
        
        int[] res = new int[2];
        int len = Integer.MAX_VALUE;
        while (set.size() == nums.size()) {
            int min = set.first().val;
            int max = set.last().val;
            if (max - min < len) {
                res[0] = min; res[1] = max;
                len = max - min;
            } 
            
            //删除最小的，再添加这一列中下一个
            Element tmp = set.pollFirst();
            if (tmp.index + 1 == nums.get(tmp.list).size()) 
                return res;
            set.add(new Element(tmp.list , tmp.index + 1 , nums.get(tmp.list).get(tmp.index + 1)));
        }
        return res;
    }
    
    //自定义类,表示第list个集合中第index个值，值为val
    public class Element {
        int list;
        int index;
        int val;
        public Element(int list , int index , int val) {
            this.list = list;
            this.index = index;
            this.val = val;
        }
    }
}

/*
	时间复杂度：O(k*nlogk) 最坏情况每个数都会遍历到。
	空间复杂度：O(k)
*/