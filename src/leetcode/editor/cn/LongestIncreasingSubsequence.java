//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。
//
//
// 示例 1：
//
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
//
// 示例 3：
//
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2500
// -10⁴ <= nums[i] <= 10⁴
//
//
//
//
// 进阶：
//
//
// 你可以设计时间复杂度为 O(n²) 的解决方案吗？
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
// Related Topics 数组 二分查找 动态规划 👍 2081 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java:最长递增子序列
class LongestIncreasingSubsequence{
    public static void main(String[] args){
        Solution solution = new LongestIncreasingSubsequence().new Solution();
        solution.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        单串题型： i 位置必须取 & 依赖比 i 小的 O(n) 个子问题
        状态定义：dp[i] 的值代表 nums 以 nums[i] 结尾的最长子序列长度。
        转移方程：设 j∈[0,i)，考虑每轮计算新 dp[i] 时，遍历 [0,i) 列表区间，做以下判断：
            if(nums[i]>nums[j]):
                nums[i] 可以接在nums[j] 之后,此情况下最长上升子序列长度为 dp[j] + 1。
                接下来更新 dp[i]，要考虑 dp[i] 在 dp[j] 之前已经被赋予了更大的值的情况。
                dp[i] = Math.max(dp[i], dp[j]+1);
            else:
                nums[i] 无法接在 nums[j] 之后，不满足状态定义，跳过。
        初始状态：dp[i] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。
        返回值：dp 列表最大值
        */
        public int _lengthOfLIS(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return len;
            }
            int[] dp = new int[len];
            Arrays.fill(dp,1);
            int res = 0;
            int pos = 0;
            for (int i=0; i<len; i++) {
                for (int j=0; j<i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
                if (dp[i] >= res) {
                    res = dp[i];
                    pos = i;
                }
            }

            int posdp = res;
            List<Integer> list = new ArrayList<>();
            list.add(nums[pos]);
            for (int i=pos-1; i>=1; i--) {
                if (dp[i] == posdp-1) {
                    list.add(nums[i]);
                    posdp = dp[i];
                }
            }
            System.out.println(list);

            return res;
        }

        /*
        解题思路：贪心算法 + 二分算法
        原动态规划解法状态定义：dp[i] 的值代表 nums 以 nums[i] 结尾的最长子序列长度。
        新的状态定义：tail[i]，长度为 i + 1 的 所有上升子序列的结尾的最小值。
            举例：对于nums = [10,9,2,5,3,7,101,18]，tail = {2, 3, 7, 18}。
            数组tail是一个严格的递增数组，证明：反证法
                对于i < j,如果有 tail[i] >= tail[j]
                对于tail[i] 而言，对应一个上升子序列 [a0, a1, ..., ai], tail[i] = ai
                对于tail[j] 而言，对应一个上升子序列 [b0, b1, ..bi..., bj], tail[j] = bj
                tail[i] >= tail[j], 即ai >= bj。在上升子序列[b0, b1, ..bi..., bj]中，bi 肯定小于 bj
                所以 ai >= bj > bi, 那么 [b0, b1, ..bi] 就是长度为 i + 1 的结尾更小的上升子序列，即 tail[i] = bi。
                上述 tail[i] >= tail[j] 与推导的 tail[i] = bi相矛盾，所以数组tail是一个严格的递增数组。
        所以，遍历数组，维护tail的定义，最终tail的长度就是最长上升子序列的长度。
        解题步骤：即如何在遍历数组的过程中维护tail
            遍历数组 nums
                num > 有序数组 tail 的最后一个元素：
                    把 num 放在有序数组 tail 的后面，对于整个串来说，最长子序列的长度增加了。
                小于等于：
                    在 tail 中查找第 1 个 >= num 的那个数
                        如果 tail 中存在等于 num 的数，那就继续下一次循环，因为 num 已经是某个长度的上升子序列的最小值了
                        如果 tail 中存在大于 num 的数，找到第一个，更新tail，把该数更新为 num，这样我们就找到了一个结尾更小的相同长度的上升子序列。
                    使用二分算法优化查找效率
         */
        public int lengthOfLIS(int[] nums) {
            int length = nums.length;
            if (length <= 1) {
                return length;
            }

            int[] tail = new int[length];
            tail[0] = nums[0];
            // end 表示有序数组 tail 的最后一个已经赋值元素的索引
            int end = 0;

            for (int i = 1; i < length; i++) {
                if (nums[i] > tail[end]) {
                    end++;
                    tail[end] = nums[i];
                } else {
                    // 使用二分查找法，在有序数组 tail 中找到第 1 个大于等于 nums[i] 的元素
                    int left = 0;
                    int right = end;
                    while (left < right) {
                        int mid = left + (right - left) / 2;
                        if (tail[mid] < nums[i]) {
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }
                    // 最开始已经判断过 nums[i] > tail[end]，所以数组中一定存在大于等于 nums[i] 的元素
                    tail[left] = nums[i];
                }
            }
            return end + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
