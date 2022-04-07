package leetcode.editor.cn;

import java.util.*;

class MaximumProductSubarray{
    public static void main(String[] args){
        Solution solution = new MaximumProductSubarray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        dp解法
        注意：由于存在负数，所以要维护两个 dp，一个 maxDp，一个minDp
        状态定义：dp[i] 是以 nums[i] 为结尾的乘积最大的非空连续子数组
                dp[i] 是以 nums[i] 为结尾的乘积最大的非空连续子数组
        状态转移：
                if nums[i] >= 0
                    maxDp[i] = max(nums[i], maxDp[i-1]*nums[i])
                    minDp[i] = min(nums[i], minDp[i-1]*nums[i])
                else
                    maxDp[i] = max(nums[i], minDp[i-1]*nums[i])
                    minDp[i] = min(nums[i], maxDp[i-1]*nums[i])
        base case：maxDp[0] = nums[0],minDp[0] = nums[0];
         */
        public int maxProduct(int[] nums) {
            int length = nums.length;
            if (length == 0) {
                return 0;
            }
            int[] maxDp = new int[length];
            int[] minDp = new int[length];
            maxDp[0] = nums[0];
            minDp[0] = nums[0];
            for (int i = 1; i < length; i++) {
                if (nums[i] >= 0) {
                    maxDp[i] = Math.max(nums[i], maxDp[i-1] * nums[i]);
                    minDp[i] = Math.min(nums[i], minDp[i-1] * nums[i]);
                } else {
                    maxDp[i] = Math.max(nums[i], minDp[i-1] * nums[i]);
                    minDp[i] = Math.min(nums[i], maxDp[i-1] * nums[i]);
                }
            }
            int max = maxDp[0];
            for (int i : maxDp) {
                max = Math.max(max, i);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}