//给定一个含有 n 个正整数的数组和一个正整数 target 。
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
//
//
// 示例 2：
//
//
//输入：target = 4, nums = [1,4,4]
//输出：1
//
//
// 示例 3：
//
//
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= target <= 10⁹
// 1 <= nums.length <= 10⁵
// 1 <= nums[i] <= 10⁵
//
//
//
//
// 进阶：
//
//
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 882 👎 0

package leetcode.editor.cn;
//java:长度最小的子数组
class MinimumSizeSubarraySum{
    public static void main(String[] args){
        Solution solution = new MinimumSizeSubarraySum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        滑动窗口
         */
        public int _minSubArrayLen(int s, int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int left = 0;
            int right = 0;
            int sum = 0;
            int min = Integer.MAX_VALUE;
            while (right < n) {
                sum += nums[right];
                right++;
                while (sum >= s) {
                    min = Math.min(min, right - left);
                    sum -= nums[left];
                    left++;
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
        /*
        二分
        对于长度为 n 的数组，我们先去判断长度为 n/2 的连续数字中最大的和是否大于等于 s。
            如果大于等于 s ，那么我们需要减少长度，继续判断所有长度为 n/4 的连续数字
            如果小于 s，我们需要增加长度，我们继续判断所有长度为 (n/2 + n) / 2，也就是 3n/4 的连续数字。
         */
        public int minSubArrayLen(int s, int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int minLen = 0, maxLen = n;
            int midLen;
            int min = -1;
            while (minLen <= maxLen) {
                //取中间的长度
                midLen = (minLen + maxLen) / 2;
                //判断当前长度的最大和是否大于等于 s
                if (getMaxSum(midLen, nums) >= s) {
                    maxLen = midLen - 1; //减小长度
                    min = midLen; //更新最小值
                } else {
                    minLen = midLen + 1; //增大长度
                }
            }
            return min == -1 ? 0 : min;
        }
        private int getMaxSum(int len, int[] nums) {
            int n = nums.length;
            int sum = 0;
            int maxSum;
            // 达到长度
            for (int i = 0; i < len; i++) {
                sum += nums[i];
            }
            maxSum = sum; // 初始化 maxSum
            for (int i = len; i < n; i++) {
                // 加一个数字减一个数字，保持长度不变
                sum += nums[i];
                sum = sum - nums[i - len];
                // 更新 maxSum
                maxSum = Math.max(maxSum, sum);
            }
            return maxSum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
