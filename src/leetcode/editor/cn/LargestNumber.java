package leetcode.editor.cn;

import java.util.*;

class LargestNumber {
    public static void main(String[] args) {
        Solution solution = new LargestNumber().new Solution();
    }

    // 最大数
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：贪心算法
            从局部最优解得到全局最优解
            对 nums 进行排序，比较 a，b 大小的规则是 "a" + "b" compareTo "b" + "a"，按照“从大到小“的顺序排。
            注意：前导 0 需要排除
        证明：TODO

         */
        public String largestNumber(int[] nums) {
            int length = nums.length;
            String[] numStr = new String[length];
            for (int i = 0; i < length; i++) {
                numStr[i] = String.valueOf(nums[i]);
            }

            Arrays.sort(numStr, (a, b) -> {
                String ab = a + b;
                String ba = b + a;
                return ba.compareTo(ab);
            });

            StringBuilder builderRes = new StringBuilder();

            for (String s : numStr) {
                builderRes.append(s);
            }

            while (length >= 1) {
                if (builderRes.charAt(0) == '0') {
                    builderRes = new StringBuilder(builderRes.substring(1, builderRes.length()));
                }
                length--;
            }

            return "".equals(builderRes.toString()) ? "0" : builderRes.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}