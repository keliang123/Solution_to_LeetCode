/*
	给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

	输入: [1,2,3,4]
	输出: [24,12,8,6]
	
	提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
	说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
	进阶：
	你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

	解答：新建两个数组，分别从左边和右边遍历顺便乘积，那么就是一边顺序的乘积，而某个点除了自身以外的乘积就是这两个数组那个点处的乘积。
		但是题目要求是除了输出数组以外，空间复杂度是O(1)，那么就是在右边和左边遍历的数组就是输出数组，在原先基础上改。
		
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        //从左向右遍历
        res[0] = nums[0];
        for (int i = 1 ; i < len ; i ++) {
            if (i == 1) res[i] = nums[0];
            else res[i] = res[i - 1]*nums[i - 1];
        }
		//计算输出数组
        int temp = 1;
        for (int i = len - 2 ; i >= 0 ; i --) {
            temp *= nums[i + 1];
            res[i] = res[i]*temp;
            if (i == 1) { res[i - 1] = temp*nums[i]; break;}
        }
        return res;
    }
}

/*
	时间复杂度：O(n)
	空间复杂度：O(1)
*/