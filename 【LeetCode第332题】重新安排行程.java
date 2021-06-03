/*
	给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。

说明:

如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
所有的机场都用三个大写字母表示（机场代码）。
假定所有机票至少存在一种合理的行程。
示例 1:

输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]

	
	解答：先建立图，然后深度优先遍历。 没到下一个点，删除两点之间的边，因为会碰到死胡同，到了尽头时，说明到头了，应该是结果的最后一个，然后将点插入结果的第一个中，递归后就是正确的顺序。
*/

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String , List<String>> map = new HashMap<>();
        for (List<String> tmp : tickets) {
            if (map.containsKey(tmp.get(0))) {
                map.get(tmp.get(0)).add(tmp.get(1));
            } else {
                map.put(tmp.get(0) , new ArrayList<>());
                map.get(tmp.get(0)).add(tmp.get(1));
            }
        }
        for (Map.Entry<String , List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }
        List<String> res = new LinkedList<>();
        dfs("JFK" , map , res);            
        return res;
    }
    
    //dfs
    private void dfs(String str , Map<String , List<String>> map , List<String> res) {
        List<String> list = map.get(str);
        while (null != list && list.size() > 0) {
            String tmp = list.remove(0);
            dfs(tmp , map , res);
        }
        res.add(0 , str);
    }
}

/*
	时间复杂度：O(nlogn)
	空间复杂度: O(n)
*/