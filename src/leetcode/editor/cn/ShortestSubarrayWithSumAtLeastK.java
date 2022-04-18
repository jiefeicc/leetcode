//给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回
//-1 。
//
// 子数组 是数组中 连续 的一部分。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1], k = 1
//输出：1
//
//
// 示例 2：
//
//
//输入：nums = [1,2], k = 4
//输出：-1
//
//
// 示例 3：
//
//
//输入：nums = [2,-1,2], k = 3
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁵ <= nums[i] <= 10⁵
// 1 <= k <= 10⁹
//
// Related Topics 队列 数组 二分查找 前缀和 滑动窗口 单调队列 堆（优先队列） 👍 381 👎 0

package leetcode.editor.cn;

import java.util.*;

class ShortestSubarrayWithSumAtLeastK{
    public static void main(String[] args){
        Solution solution = new ShortestSubarrayWithSumAtLeastK().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        思考：找出 nums 中和至少为 k 的 最短非空子数组
            连续子数组 + 求和 -> 前缀和
            最短非空子数组    -> 滑动窗口
        解题步骤:
            1.构建前缀和数组 preSum
            2.对 preSum “滑动窗口“处理找到和至少为 k 的最短非空子数组。(O(N^2) 的时间复杂的会导致超时！)
        思考：对于指定的 j,如果 preSum[i] <= preSum[i - x],(i-x) -> j 满足和至少为 k，那么 i -> j 一定满足
             同时，i 到 j 距离更小，所以 i - x 就可以排除了。所以针对该题，可以使用单调队列实现（减少计算次数）
        单调队列解题步骤：
            i 遍历 preSum
                从队尾删除比 preSum[i] 大或相等的元素(preSum 数组索引)
                当队首的 preSum[queue.getFirst()] 与 preSum[i] 的差不小于k时
                    更新min，删除队首(队首的索引元素类似于 j)
                入队尾
         */
        public int shortestSubarray(int[] nums, int k) {
            /*
            1.构建前缀和数组 preSum
            前缀和数组 preSum 需要和 nums 有 +1 的偏移量
            nums[1, 2, 3] preSum[1, 3, 6], 没有偏移量的话，一次遍历没法求出 和为 6 的子数组
             */
            int length = nums.length;
            long [] preSum = new long [length + 1];

            for (int i = 0; i < length; i++) {
                if (nums[i] >= k) {
                    return 1;
                }
                preSum[i + 1] = preSum[i] + nums[i];
            }
            /*
            2.对 preSum “滑动窗口”处理找到最短非空子数组
            由于数组存在负数，所以前缀和数组不具有单调性，滑动窗口无法判断一次就退出，需要暴力算出所有结果
            但是 O(N^2) 的时间复杂的会导致超时

             */
            int min = Integer.MAX_VALUE;
            LinkedList<Integer> queue = new LinkedList<>();
            for(int i = 0; i < preSum.length; i++){
                while(!queue.isEmpty() && preSum[i] <= preSum[queue.getLast()]) {
                    queue.removeLast();
                }
                while(!queue.isEmpty() && preSum[i] - preSum[queue.getFirst()] >= k) {
                    min = Math.min(min, i - queue.removeFirst());
                }
                queue.addLast(i);
            }
            return min != Integer.MAX_VALUE ? min : -1;
            // 这个O(N^2) 的时间复杂的会导致超时
//            int min = Integer.MAX_VALUE;
//            for (int i = 0; i < length; i++) {
//                for (int j = i + 1; j <= length; j++) {
//                    if (preSum[j] - preSum[i] >= k) {
//                        min = Math.min(min, j - i);
//                    }
//                }
//            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
