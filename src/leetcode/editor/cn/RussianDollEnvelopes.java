package leetcode.editor.cn;

import java.util.*;

class RussianDollEnvelopes{
    public static void main(String[] args){
        Solution solution = new RussianDollEnvelopes().new Solution();
    }
    //俄罗斯套娃信封问题
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：排序 + 最长递增子序列
            将 e 按照 e[i][0] 排序，再对 e[i][1] 求最长递增子序列。
            针对本题，排序好的数组中，当 e[i][0] = e[i + 1][0] 时，即 [1, 3] [1, 4] 是无法套娃的
            所以需要特殊处理一下，当 e[i][0] = e[i + 1][0] 时，将数组按照 e[i][1] 降序排列。
            为什么这么特殊处理：
                例如 [1, 3], [1, 4], [1, 5], [2, 4] -> [1, 5], [1, 4], [1, 3], [2, 4]
                前面三个虽然 e[i][1] 递增，但是无法套娃，对 e[i][1] 降序，这样最小的 e[i][1] 就到最右边了，就可以和 [2, 4] 套娃了。
            求解最长递增子序列，LC 300，贪心算法 + 二分算法
         */
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });
            int length = envelopes.length;
            return lengthOfLIS(envelopes);
        }

        public int lengthOfLIS(int[][] nums) {
            int length = nums.length;
            if (length <= 1) {
                return length;
            }

            int[] tail = new int[length];
            tail[0] = nums[0][1];
            // end 表示有序数组 tail 的最后一个已经赋值元素的索引
            int end = 0;

            for (int i = 1; i < length; i++) {
                if (nums[i][1] > tail[end]) {
                    end++;
                    tail[end] = nums[i][1];
                } else {
                    // 使用二分查找法，在有序数组 tail 中找到第 1 个大于等于 nums[i] 的元素
                    int left = 0;
                    int right = end;
                    while (left < right) {
                        int mid = left + (right - left) / 2;
                        if (tail[mid] < nums[i][1]) {
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }
                    // 最开始已经判断过 nums[i] > tail[end]，所以数组中一定存在大于等于 nums[i] 的元素
                    tail[left] = nums[i][1];
                }
            }
            return end + 1;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
