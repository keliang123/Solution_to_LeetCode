/*
	你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

	示例 1:

输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。

	解答： 图的问题。
		也就是看有向图中有没有环。
		用一个数组表示每门课程的度。
		用一个列表表示修了这门课程后，能够修其他的所以课程。
		用一个队列来修课程，出队将一个度是0的课程修了，然后，看它能继续修的课程中，度减1后有没有度是0的，
		代表这门课也可以修了。入队。
		最后判断修的课程和总课程是不是相等即可。
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //一个数组表示每门课程的入度
        int[] degree = new int[numCourses];
        //一个列表表示每门课修了后，可以修哪些课程。
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0 ; i < numCourses ; ++i) 
            list.add(new ArrayList<>());
        //度的初始化,列表初试化。
        for (int[] pre : prerequisites) {
            degree[pre[0]]++;
            list.get(pre[1]).add(pre[0]);
        }
        
        //用一个队列来学习度为0的课程
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0 ; i < numCourses ; ++i) {
            if (degree[i] == 0)
                que.offer(i);
        }
        int count = numCourses;
        //学习一门课程后，将度为0的课程入队。
        while (!que.isEmpty()) {
            int tmp = que.poll();
            --count;
            for (int cur : list.get(tmp)) {
                if (--degree[cur] == 0)
                    que.offer(cur);
            }
        }
        return count == 0;
    }
}

/*
	时间复杂度：O(N+M) ,图的遍历需要访问所有结点和边。
	空间复杂度：O(N+M)，需要存储所有结点和边。
*/

