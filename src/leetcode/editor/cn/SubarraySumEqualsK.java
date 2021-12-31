//给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1], k = 2
//输出：2
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3], k = 3
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2 * 10⁴
// -1000 <= nums[i] <= 1000
// -10⁷ <= k <= 10⁷
//
// Related Topics 数组 哈希表 前缀和 👍 1238 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:和为 K 的子数组
class SubarraySumEqualsK{
    public static void main(String[] args){
        Solution solution = new SubarraySumEqualsK().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /*
        前缀和解法
        1 2 3 4 5
        1 的前缀和是1,3的前缀和是6
        6-1=5,1到3（不包括1）的路径就是 子数组和为5
        注意：前缀和数组偏移量为 +1
         */
        public int subarraySum(int[] nums, int k) {
            int len = nums.length;
            int count = 0;
            if (len == 0) {
                return count;
            }
            // 构建前缀和数组
            int[] preSum = new int[len+1];
            for (int i=0; i<len; i++) {
                preSum[i+1] = preSum[i] + nums[i];
            }
            // 寻找路径
            for (int left = 0; left < len; left++) {
                for (int right = left; right < len; right++) {
                    if (preSum[right+1] - preSum[left] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
        // 哈希表优化前缀和
        public int _subarraySum(int[] nums, int k) {
            // key：前缀和，value：key 对应的前缀和的个数
            Map<Integer, Integer> preSumFreq = new HashMap<>();
            // 对于下标为 0 的元素，前缀和为 0，个数为 1
            preSumFreq.put(0, 1);
            int preSum = 0;
            int count = 0;
            for (int num : nums) {
                preSum += num;
                // 先获得前缀和为 preSum - k 的个数，加到计数变量里
                if (preSumFreq.containsKey(preSum - k)) {
                    count += preSumFreq.get(preSum - k);
                }
                // 然后维护 preSumFreq 的定义
                preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
            }
            return count;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
