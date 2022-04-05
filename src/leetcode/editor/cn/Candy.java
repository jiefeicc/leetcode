//n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
//
// 你需要按照以下要求，给这些孩子分发糖果：
//
//
// 每个孩子至少分配到 1 个糖果。
// 相邻两个孩子评分更高的孩子会获得更多的糖果。
//
//
// 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
//
//
//
// 示例 1：
//
//
//输入：ratings = [1,0,2]
//输出：5
//解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
//
//
// 示例 2：
//
//
//输入：ratings = [1,2,2]
//输出：4
//解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
//     第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
//
//
//
// 提示：
//
//
// n == ratings.length
// 1 <= n <= 2 * 10⁴
// 0 <= ratings[i] <= 2 * 10⁴
//
// Related Topics 贪心 数组 👍 810 👎 0

package leetcode.editor.cn;

import java.util.*;

class Candy{
    public static void main(String[] args){
        Solution solution = new Candy().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        贪心思想：
            求最小糖果数，即要满足左规则的同时满足右规则。匹配规则时，每次加一，取两次匹配规则时的最大值。
        左规则：当 ratings[i] > ratings[i-1], ratings[i] 比 ratings[i-1] 多一个。
        右规则：当 ratings[i] > ratings[i+1], ratings[i] 比 ratings[i+1] 多一个。
        先每个学生发一个糖果，再从左到右、从右到左，分别遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。
        取以上 2 轮遍历 left 和 right 对应学生糖果数的 最大值 ，这样则同时满足左规则和右规则 ，即得到每个同学的最少糖果数量。
         */
        public int candy(int[] ratings) {
            int ans = 0;
            int length = ratings.length;
            int[] left = new int[length];
            // Arrays.fill(left, 1);
            for (int i = 0; i < length; i++) {
                if (i > 0 && ratings[i] > ratings[i-1]) {
                    left[i] = left[i-1] + 1;
                } else {
                    left[i] = 1;
                }
            }
            // 省略 right 数组，只需要用单个变量记录当前位置的右规则
            int right = 0;
            for (int i = length - 1; i >= 0; i--) {
                if (i < length - 1 && ratings[i] > ratings[i+1]) {
                    right++;
                } else {
                    right = 1;
                }
                ans += Math.max(left[i], right);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
