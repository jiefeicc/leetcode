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
        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        单调栈解法
        用非递增栈存储数组索引，入栈递减或者相等
         */
        public int _trap(int[] height) {
            int len = height.length;
            if (len == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            int res = 0;
            for (int i=0; i<len; i++) {
                // 当栈不为空 且 即将入栈的数开始递增，那就不入栈，开始做处理计算雨水面积
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    // 取出栈顶，height[curIdx] 作为基底，后续计算面积从此往上面计算
                    int baseIndex = stack.pop();
                    // 如果有多个连续相同的基底，那就出栈，往左找到第一个比基底高的数的索引
                    while (!stack.isEmpty() && height[baseIndex] == height[stack.peek()]) {
                        stack.pop();
                    }
                    // 基底左边是空的，那雨水接不住的，例如基底左边是 y 轴
                    if (!stack.isEmpty()) {
                        // 从栈中到左边第一个比基底高的数的索引
                        int firstTopIndex = stack.peek();
                        // 这部分雨水的面积 = (min(左边第一个比基底高的数，最开始没入栈的索引对应的数) - 基底) * 没入栈的索引到栈中左边第一个比基底高的数的索引之间夹着的距离
                        res += (Math.min(height[firstTopIndex], height[i]) - height[baseIndex]) * (i - firstTopIndex -1);
                    }
                }
                // 当栈是空或者遍历取出数组的数不大于栈顶，那就入栈
                stack.add(i);
            }
            return res;
        }

        /*
        解题思路：动态规划
            遍历思路分析：
                按照列来遍历，对于当前列 height[i]，找到该列左边最高的列和右边最高的列，找到后判断该列能存多少水。
                只有左右最高的列都 大于当前列，才能存住水，water = Math.min(lMax, rMax) - height[i]
            动态规划优化：
                找左右最高的列，可以提前用 dp 来求解，用两个数组保存好。
                lMax[i] : i 列左边最高的列，不包含当前列。
                rMax[i] : i 列右边最高的列，不包含当前列。
         */
        public int trap(int[] height) {
            int waterSum = 0;
            int length = height.length;
            int[] lMax = new int[length];
            int[] rMax = new int[length];
            for (int i = 1; i < length; i++) {
                lMax[i] = Math.max(lMax[i - 1], height[i - 1]);
            }

            for (int i = length - 2; i >= 0; i--) {
                rMax[i] = Math.max(rMax[i + 1], height[i + 1]);
            }

            for (int i = 1; i < length; i++) {
                int tempMin = Math.min(lMax[i], rMax[i]);
                if (tempMin > height[i]) {
                    waterSum += tempMin - height[i];
                }
            }
            return waterSum;
        }


        /*
        解题思路：动态规划 -> dp 空间优化
            在 dp 解法的基础上
            对于 lMax[i]，发现在遍历过程中每个只用一次，所以这个数组空间可以优化掉
            rMax[i] 也可以优化掉
         */
        public int __trap(int[] height) {
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}