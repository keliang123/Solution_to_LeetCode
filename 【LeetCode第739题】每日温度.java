/*
	根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
	例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

	提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

	解答：运用栈，每次来了个数 和栈顶元素比较，大的话，就是比这个栈顶数据大的而且序号最小，然后出栈，继续和下面数据比，否则将这个数入栈.
	
*/

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        if (len == 0) return new int[]{};
        int[] res = new int[len];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0 , T[0]});
        
        for (int i = 1 ; i < len ; i ++) {
            if (stack.isEmpty())  stack.push(new int[]{i , T[i]});
            int[] temp = stack.peek(); 
            if (T[i] <= temp[1]) stack.push(new int[]{i , T[i]});
            else {
                while (T[i] > temp[1]) {
                    res[temp[0]] = i - temp[0];
                    stack.pop();
                    if (!stack.isEmpty()) temp = stack.peek();
                    else break;
                }
                stack.push(new int[]{i , T[i]});
            }
        }
        return res;
    }
}

/*
	时间复杂度:O(n)
	空间复杂度:O(n)
*/