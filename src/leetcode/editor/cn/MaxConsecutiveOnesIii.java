package leetcode.editor.cn;

import java.util.*;

class MaxConsecutiveOnesIii{
    public static void main(String[] args){
        Solution solution = new MaxConsecutiveOnesIii().new Solution();
        System.out.println(solution.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
    }
    // 最大连续1的个数 III
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            滑动窗口解法
            注意：本题需要经过题意转换 -> 字符串中 除了1，最多包含k个0的最长子串。
            题解类似 340. 至多包含 K 个不同字符的最长子串。
            滑动窗口遍历 nums，r++，控制窗口不要出现 k 个以上的 0，维护 res = max(res, r - l - 1);
            如何控制控制窗口不出现 k 个以上的 0：
                维护一个 zeroNum，窗口遍历更新  zeroNum = 该数出现的次数
                当 zeroNum > k，说明出现 k 个以上的0了，那么窗口左边界右移，更新zeroNum，zeroNum <=  k。
         */
        public int longestOnes(int[] nums, int k) {
            int res = 0;
            int zeroNum = 0;
            for (int l = 0, r = 0; r < nums.length; r++) {
                if (nums[r] == 0) {
                    zeroNum++;
                }
                while (zeroNum > k) {
                    int leftInteger = nums[l];
                    l++;
                    if (leftInteger == 0) {
                        zeroNum--;
                    }
                }
                res = Math.max(res, r - l + 1);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}