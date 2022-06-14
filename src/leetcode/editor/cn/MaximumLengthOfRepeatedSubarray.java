package leetcode.editor.cn;

import java.util.*;

class MaximumLengthOfRepeatedSubarray{
    public static void main(String[] args){
        Solution solution = new MaximumLengthOfRepeatedSubarray().new Solution();
    }
    // 最长重复子数组
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        状态定义：dp[i][j] 是以 nums1[i - 1], nums2[j - 1] 为结尾的最长重复子数组的长度。
        base case：当 i = 0 or j = 0时，nums1[i - 1], nums2[j - 1]不存在。
                   所以最长重复子数组不存在，即 dp[i][j] = 0;
        状态转移方程：dp[i][j] = nums1[i - 1] == nums2[j - 1] ? 1 + dp[i - 1][j - 1] : 0;
         */
        public int findLength(int[] nums1, int[] nums2) {
            int length1 = nums1.length;
            int length2 = nums2.length;
            if (length1 == 0 || length2 == 0) {
                return 0;
            }
            int max = 0;
            // 根据 base case，dp 和 num 索引要错开一位。
            int[][] dp = new int[length1 + 1][length2 + 1];
            for (int i = 1; i <= length1; i++) {
                for (int j = 1; j <= length2; j++) {
                    dp[i][j] = nums1[i - 1] == nums2[j - 1] ? 1 + dp[i - 1][j - 1] : 0;
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}