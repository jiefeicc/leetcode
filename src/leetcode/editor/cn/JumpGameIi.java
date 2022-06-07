package leetcode.editor.cn;

import java.util.*;

class JumpGameIi{
    public static void main(String[] args){
        Solution solution = new JumpGameIi().new Solution();
        System.out.println(solution.jump(new int[]{2,3,1,1,4}));
    }
    //跳跃游戏 II
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        贪心思想：
            从 nums[0] 出发，跳跃的选择有 nums[0] 个，遍历后面 nums[0] 个选择，哪个能跳更远，就从 nums[0] 跳到哪个位置。
         */
        public int jump(int[] nums) {
            int step = 0;
            // 起跳点下一次跳跃范围的起点
            int start = 0;
            // 起跳点下一次跳跃范围的终点
            int end = 0;
            while (end < nums.length - 1) {
                // 跳跃范围内，选择某个起跳点，能跳到的最远位置。
                int maxPos = 0;
                for (int i = start; i <= end; i++) {
                    // 能跳到最远的距离
                    maxPos = Math.max(maxPos, i + nums[i]);
                }
                // 下一次起跳点范围开始的格子
                start = end + 1;
                // 下一次起跳点范围结束的格子
                end = maxPos;
                step++;
            }
            return step;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
