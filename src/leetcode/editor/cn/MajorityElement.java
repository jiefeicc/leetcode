package leetcode.editor.cn;

import java.util.*;

class MajorityElement{
    public static void main(String[] args){
        Solution solution = new MajorityElement().new Solution();
    }
    //多数元素
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：摩尔投票法
            候选人(candidate)初始化为nums[0]，票数count初始化为1。
            当遇到与 candidate 相同的数，则票数count = count + 1，否则票数count = count - 1。
            当票数count为0时，更换候选人，并将票数count重置为1。
            遍历完数组后，candidate 即为最终答案。
        摩尔投票法证明：
            遇到相同的则票数 + 1，遇到不同的则票数 - 1。
            “多数元素”的个数> n/2，其余元素的个数总和 <= n/2。
            所以“多数元素”的个数 - 其余元素的个数总和 >= 1。
            相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后至少还剩余1个“多数元素”。
        */
        public int majorityElement(int[] nums) {
            int cand = nums[0];
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == cand) {
                    count++;
                } else {
                    count--;
                    if (count == 0) {
                        count = 1;
                        cand = nums[i];
                    }
                }
            }
            return cand;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
