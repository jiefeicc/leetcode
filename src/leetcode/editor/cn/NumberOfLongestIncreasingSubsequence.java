package leetcode.editor.cn;

import java.util.*;

class NumberOfLongestIncreasingSubsequence{
    public static void main(String[] args){
        Solution solution = new NumberOfLongestIncreasingSubsequence().new Solution();
    }
    //最长递增子序列的个数
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：300.最长递增子序列
            单串题型： i 位置必须取 & 依赖比 i 小的 O(n) 个子问题
            状态定义：f[i] 的值代表 nums 以 nums[i] 结尾的最长子序列长度。
            转移方程：设 j∈[0,i)，考虑每轮计算新 f[i] 时，遍历 [0,i) 列表区间，做以下判断：
                if(nums[i]>nums[j]):
                    nums[i] 可以接在nums[j] 之后,此情况下最长上升子序列长度为 f[j] + 1。
                    接下来更新 f[i]，要考虑 f[i] 在 f[j] 之前已经被赋予了更大的值的情况。
                    f[i] = Math.max(f[i], f[j]+1);
                else:
                    nums[i] 无法接在 nums[j] 之后，不满足状态定义，跳过。
            初始状态：f[i] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。
            返回值：f 列表最大值
        解题思路：对于 673.最长递增子序列的个数
            增加一个状态定义：g[i] 代表 nums 以 nums[i] 结尾的最长递增子序列的个数。
            状态转移：
                if(nums[i]>nums[j]):
                    f[i] < f[j] + 1：
                        说明 f[i] 会被 f[j] + 1 更新，以 nums[i] 结尾的最长子序列长度已经更新，此时同步更新 g[i] = g[j]。
                    f[i] = f[j] + 1：
                        说明以 nums[i] 结尾的最长子序列长度 = 说明以 nums[j] 结尾的最长子序列长度 + 1
                        说明当前以 nums[i] 结尾的最长递增子序列的个数 = g[i] + g[j]（即之前以 nums[i] 结尾的最长递增子序列的个数 + 以 nums[j] 结尾的最长递增子序列的个数）
            初始状态：g[i] 所有元素置 1，含义是每个元素都都能独自一个成为子序列。
            返回值：在状态转移处理里面维护一个 max：最长递增子序列的长度，遍历 f，当f[i] = max，res += f[i]。
        */
        public int findNumberOfLIS(int[] nums) {
            int length = nums.length;
            int[] f = new int[length];
            int[] g = new int[length];
            Arrays.fill(f, 1);
            Arrays.fill(g, 1);
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        if (f[j] + 1 > f[i]) {
                            f[i] = f[j] + 1;
                            g[i] = g[j];
                        } else if (f[i] == f[j] + 1) {
                            g[i] += g[j];
                        }
                    }
                }
                max = Math.max(max, f[i]);
            }
            int res = 0;
            for (int i = 0; i < length; i++) {
                if (f[i] == max) {
                    res += g[i];
                }
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}
