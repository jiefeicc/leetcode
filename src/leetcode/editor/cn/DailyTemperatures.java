//给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指在第 i 天之后，才会有更高的温度
//。如果气温在这之后都不会升高，请在该位置用 0 来代替。
//
//
//
// 示例 1:
//
//
//输入: temperatures = [73,74,75,71,69,72,76,73]
//输出: [1,1,4,2,1,1,0,0]
//
//
// 示例 2:
//
//
//输入: temperatures = [30,40,50,60]
//输出: [1,1,1,0]
//
//
// 示例 3:
//
//
//输入: temperatures = [30,60,90]
//输出: [1,1,0]
//
//
//
// 提示：
//
//
// 1 <= temperatures.length <= 10⁵
// 30 <= temperatures[i] <= 100
//
// Related Topics 栈 数组 单调栈 👍 1085 👎 0

package leetcode.editor.cn;

import java.util.*;

class DailyTemperatures{
    public static void main(String[] args){
        Solution solution = new DailyTemperatures().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        单调递减栈解法（栈内存储的是数组索引）
        遍历数组索引 i：
            当栈不为空时，且 temperatures[i] > 栈顶
                栈顶出栈
                ans[栈顶索引] = i - 栈顶索引
                重复上面操作，直到不满足条件
            索引入栈
        */
        public int[] dailyTemperatures(int[] temperatures) {
            int length = temperatures.length;
            Stack<Integer> stack = new Stack<>();
            int[] ans = new int[length];
            for (int i = 0; i < length; i++) {
                while (!stack.empty() && temperatures[i] > temperatures[stack.peek()]) {
                    int preIndex = stack.pop();
                    ans[preIndex] = i - preIndex;
                }
                stack.push(i);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
