package leetcode.editor.cn;

import java.util.*;

class MoveZeroes{
    public static void main(String[] args){
        Solution solution = new MoveZeroes().new Solution();
    }
    // 移动零
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路
            双指针解法
            慢指针一直指向 0，快指针到非 0 就停下来，快慢指针指向的数字交换。
            如何保证慢指针一直指向 0？
                快慢指针一起从 index = 0 出发
                    遇到非 0 的值，快慢指针指向的数字交换，慢指针++，快指针++
                    遇到为 0 的值，慢指针不动，快指针++
                这种情况下，就能保证慢指针一直指向 0，发生交换时符合要求
         */
        public void moveZeroes(int[] nums) {
            for (int slow = 0, fast = 0; fast < nums.length; fast++) {
                if (nums[fast] != 0) {
                    swap(nums, slow, fast);
                    slow++;
                }
            }
        }

        private void swap(int[] nums, int slow, int fast) {
            int temp = nums[slow];
            nums[slow] = nums[fast];
            nums[fast] = temp;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}