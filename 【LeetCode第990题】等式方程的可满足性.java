/*
	给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
	并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
	只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

示例 1：

输入：["a==b","b!=a"]
输出：false
解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
示例 2：

输出：["b==a","a==b"]
输入：true
解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
示例 3：

输入：["a==b","b==c","a==c"]
输出：true
示例 4：

输入：["a==b","b!=c","c==a"]
输出：false

	解答： 
		相等符号说明在一个集合之内，不相等不在一个集合之内，用并查集的方法。
*/

class Solution {
    public class UnionFind {
        private int[] parent;
        private int[] sz;
        
        public UnionFind(int capa) {
            this.parent = new int[capa];
            this.sz = new int[capa];
            for (int i = 0 ; i < capa ; i ++) {
                parent[i] = i;
                sz[i] = 1;
            }
        }
        
        private int findParent(int index) {
            while (index != parent[index])
                index = parent[index];
            return parent[index];
        }
        
        private boolean isConnected(int index1 , int index2) {
            return findParent(index1) == findParent(index2);
        }
        
        private void union(int index1 , int index2) {
            if (findParent(index1) == findParent(index2))
                return;
            int parent1 = findParent(index1);
            int parent2 = findParent(index2);
            
            if (sz[parent1] > sz[parent2]) {
                parent[parent2] = parent1;
                sz[parent1] += sz[parent2];
            } else {
                parent[parent1] = parent2;
                sz[parent2] += sz[parent1];
            }
        }
    }
    public boolean equationsPossible(String[] equations) {
        int len = equations.length;
        UnionFind union = new UnionFind(26);
        for (String e : equations) {
            if (e.charAt(1) == '=' && e.charAt(0) != e.charAt(3)) {
                union.union(e.charAt(0) - 'a' , e.charAt(3) - 'a');
            }
        }
        for (String e : equations) {
            if (e.charAt(1) == '!' && e.charAt(0) == e.charAt(3)) return false;
            if (e.charAt(1) == '!' && e.charAt(0) != e.charAt(3)) {
                if (union.isConnected(e.charAt(0) - 'a' , e.charAt(3) - 'a'))
                    return false;
            }
        }
        return true;
    }
}

/*
	时间复杂度：O(n + c) n是equations数组个数，c是并查集定义数组长度
	空间复杂度:O(c) c是并查集定义数组长度
*/

