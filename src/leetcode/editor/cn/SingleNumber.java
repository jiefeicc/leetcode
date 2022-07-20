package leetcode.editor.cn;

import java.util.*;

class SingleNumber{
    public static void main(String[] args){
        Solution solution = new SingleNumber().new Solution();
    }
    // 只出现一次的数字
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            a ^ b ^ a ^ c ^ d ^ c ^ d = b
            按位异或，最终剩下就出现一次的数
         */
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int num : nums) {
                res ^= num;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}