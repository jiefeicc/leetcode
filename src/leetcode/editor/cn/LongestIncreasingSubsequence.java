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
        public int lengthOfLIS(int[] nums) {
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

        // 纸牌算法，二分
        public int _lengthOfLIS(int[] nums) {
            int[] top = new int[nums.length];
            // 牌堆数初始化为 0
            int piles = 0;
            for (int i = 0; i < nums.length; i++) {
                // 要处理的扑克牌
                int poker = nums[i];

                /***** 搜索左侧边界的二分查找 *****/
                int left = 0, right = piles;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (top[mid] > poker) {
                        right = mid;
                    } else if (top[mid] < poker) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                /*********************************/

                // 没找到合适的牌堆，新建一堆
                if (left == piles) piles++;
                // 把这张牌放到牌堆顶
                top[left] = poker;
            }
            // 牌堆数就是 LIS 长度
            return piles;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
