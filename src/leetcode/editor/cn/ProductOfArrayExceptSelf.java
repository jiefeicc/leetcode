package leetcode.editor.cn;

import java.util.*;

class ProductOfArrayExceptSelf{
    public static void main(String[] args){
        Solution solution = new ProductOfArrayExceptSelf().new Solution();
    }
    //除自身以外数组的乘积
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：前缀和思想
            要求 O(n) 时间复杂度，O(1) 的额外空间复杂度（返回的 res[] 不算）
            res[i] = i 左边的乘积 * i 右边的乘积
            第一遍对 nums 正向遍历，得到 “前缀乘积”数组 res[]
            第二遍对 nums 反向遍历，维护临时变量 k 得到 “后缀乘积”，用 k * res[i] 就是结果
         */
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;
            int temp = 1;
            int[] res = new int[length];
            for (int i = 0; i < length; i++) {
                res[i] = temp;
                temp *= nums[i];
            }
            // 后缀乘积
            int behind = 1;
            for (int i = length - 1; i >= 0; i--) {
                res[i] *= behind;
                behind *= nums[i];
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
