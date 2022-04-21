package leetcode.editor.cn;

import java.util.*;

class JumpGame{
    public static void main(String[] args){
        Solution solution = new JumpGame().new Solution();
        solution.canJump(new int[]{3,2,1,0,4});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        思路：
            能到某个位置一定能到达他前面的位置！
        贪心解法：尽量到达更远的位置
        解题思路：
            遍历数组，更新最远能到达的位置 max（首先需要 max >= i，确定能到达当前位置）
            当 max >= nums.len,肯定能到达最后一个下标。
         */
        public boolean canJump(int[] nums) {
            int max = 0;
            int length = nums.length;
            for (int i = 0; i < length - 1; i++) {
                if (max >= i) {
                    max = Math.max(max, i + nums[i]);
                }
            }
            return max >= length - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}