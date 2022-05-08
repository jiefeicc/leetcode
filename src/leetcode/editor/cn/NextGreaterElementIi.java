package leetcode.editor.cn;

import java.util.*;

class NextGreaterElementIi{
    public static void main(String[] args){
        Solution solution = new NextGreaterElementIi().new Solution();
    }
    //下一个更大元素 II
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        思路:
            下一个更大的元素类问题 -> 单调栈解决
            循环数组只要遍历两遍数组就能解决问题
        注:栈里存的是数组下标
         */
        public int[] nextGreaterElements(int[] nums) {
            int length = nums.length;
            int[] res = new int[length];
            /*
            顺序遍历，所以栈里存的都是还没得出答案的数的索引
            之后的遍历中，如果没有比他大的数，他的索引就一直不会出栈
            所以res[index]就会等于0，所以要提前置为 -1
             */
            Arrays.fill(res, -1);
            LinkedList<Integer> stack = new LinkedList<>();
            for (int i = 0; i < 2 * length; i++) {
                int tmp = nums[i % length];
                while (!stack.isEmpty() && tmp > nums[stack.peekFirst()]) {
                    int index = stack.pollFirst();
                    res[index] = tmp;
                }
                stack.addFirst(i % length);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
