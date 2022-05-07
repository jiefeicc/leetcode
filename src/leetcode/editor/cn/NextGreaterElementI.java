package leetcode.editor.cn;

import java.util.*;

class NextGreaterElementI{
    public static void main(String[] args){
        Solution solution = new NextGreaterElementI().new Solution();
    }
    //下一个更大元素 I
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        思路：
            找到最近一个比其大的元素 --> 单调栈解法
        注：可正序可倒序遍历数组，倒序遍历是为了把右边的值先入栈.
           这样遍历数组，num[i]下一个更大元素就是栈顶元素，就能“正序”解决问题。
        倒序遍历解题步骤：
            单调递减栈 + 哈希表
            逆序遍历 nums2
                出栈，直到栈顶元素大于 nums2[i]
                此时栈顶元素（不为空）是 nums2[i] 下一个更大元素，构建哈希表映射
                nums2[i] 入栈
            遍历 nums1，从哈希表获得数据
        正序遍历解题步骤：
            非单调递增栈 + 哈希表
            正序遍历 nums2
                出栈(栈不为空)，直到栈顶元素不小于 nums
                    此时出栈的元素的下一个更大元素就是 num，构建哈希表映射
                不满足上述条件（栈为空 或 无法保持非单调递增栈）
                    num 入栈
            遍历nums1，从哈希表获得数据（如果存在）
         */
        // 正序遍历
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            LinkedList<Integer> stack = new LinkedList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums2) {
                while (!stack.isEmpty() && stack.peekFirst() < num) {
                    map.put(stack.pollFirst(), num);
                }
                stack.addFirst(num);
            }
            int[] ans = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                ans[i] = map.getOrDefault(nums1[i], -1);
            }
            return ans;
        }
        // 倒序遍历
        public int[] _nextGreaterElement(int[] nums1, int[] nums2) {
            LinkedList<Integer> stack = new LinkedList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = nums2.length - 1; i >= 0; i--) {
                int tmp = nums2[i];
                while (!stack.isEmpty() && stack.peekFirst() <= tmp) {
                    stack.pollFirst();
                }
                map.put(tmp, stack.isEmpty() ? -1 : stack.peekFirst());
                stack.addFirst(nums2[i]);
            }
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
