//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 2931 👎 0

package leetcode.editor.cn;

import java.util.Stack;

//java:接雨水
class TrappingRainWater{
    public static void main(String[] args){
        Solution solution = new TrappingRainWater().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        单调栈解法
        https://leetcode-cn.com/problems/trapping-rain-water/solution/dan-diao-zhan-jie-jue-jie-yu-shui-wen-ti-by-sweeti/
        将数组索引操作进入单调栈
         */
        public int trap(int[] height) {
            int len = height.length;
            if (len == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            int res = 0;
            for (int i=0; i<len; i++) {
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    int curIdx = stack.pop();
                    while (!stack.isEmpty() && height[curIdx] == height[stack.peek()]) {
                        stack.pop();
                    }
                    if (!stack.isEmpty()) {
                        int stackTop = stack.peek();
                        res += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop -1);
                    }
                }
                stack.add(i);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}