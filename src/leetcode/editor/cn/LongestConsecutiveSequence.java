//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
//
//
//
// 示例 1：
//
//
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
//
// 示例 2：
//
//
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
//
// Related Topics 并查集 数组 哈希表 👍 1031 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

//java:最长连续序列
class LongestConsecutiveSequence{
    public static void main(String[] args){
        Solution solution = new LongestConsecutiveSequence().new Solution();
        solution.longestConsecutive(new int[]{1,2,3,4,6,7});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        部分动态规划思想 + 哈希表
        比如 num是5，此时1234 678都在哈希表中
        此时哈希表中，1的位置和4存的值都是4，6和8存的值都是3
        所以5进来之后，发现左边有4个连续的，右边有3个连续的，加上自己一个，那么组成一个大连续的
        4+1+3 = 8
        所以要更新当前最长连续串的端点，也就是1的位置（5-4），8的位置（5+3），更新长度为8
        只需要端点存值就行，因为端点中的值在遍历的时候如果在哈希表中就会略过
         */
        public int longestConsecutive(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int res = 0;
            for (Integer n : nums) {
                if (map.containsKey(n)) {
                    continue;
                }
                // 获取当前数的左边连续长度,没有的话就更新为0
                int left = map.getOrDefault(n - 1, 0);
                // 同理获取右边的数
                int right = map.getOrDefault(n + 1, 0);
                // 当前数左右连续长度
                int len = left + 1 + right;
                // 当前数存入map，仅代表当前数字出现过
                map.put(n, -1);
                //更新两端值，当两端没出现时，更新的就是自己的值
                map.put(n - left, len);
                map.put(n + right, len);

                res = Math.max(res, len);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
