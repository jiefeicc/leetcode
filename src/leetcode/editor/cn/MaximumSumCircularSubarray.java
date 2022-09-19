package leetcode.editor.cn;

import java.util.*;

class MaximumSumCircularSubarray{
    public static void main(String[] args){
        Solution solution = new MaximumSumCircularSubarray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        明确题意：求环形数组最大子数组的和 res，每个数字最多只能被使用一次。
        解题思路：
            最大子数组出现情况：
                没有包含环的连接处：res = 非环形数组最大子数组和 maxSum
                包含环的连接处：
                    此时最大子数组横跨数组开头和结尾，也就意味着 数组最小子数组的和没有包含环的连接处。
                    那么此时 res = 数组总和 sum - 非环数组最小子数组的和 minSum
            综上：res = max(非环形数组最大子数组和 maxSum，数组总和 sum - 非环数组最小子数组的和 minSum)
            注意：存在特殊情况，当数组全部为负数时，sum - minSum = 0 > maxSum, 得出 res = sum - minSum，但此时 res 应该等于 maxSum。
         */
        public int maxSubarraySumCircular(int[] nums) {
            int maxSum = nums[0];
            int minSum = nums[0];
            int preMin = 0;
            int preMax = 0;
            int sum = 0;
            for (int num : nums) {
                preMax = Math.max(preMax + num, num);
                preMin = Math.min(preMin + num, num);
                sum += num;

                maxSum = Math.max(maxSum, preMax);
                minSum = Math.min(minSum, preMin);
            }
            return Math.max(maxSum, sum - minSum == 0 ? maxSum : sum - minSum);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}