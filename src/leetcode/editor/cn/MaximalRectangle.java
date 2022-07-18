package leetcode.editor.cn;

import java.util.*;

class MaximalRectangle{
    public static void main(String[] args){
        Solution solution = new MaximalRectangle().new Solution();
        solution.maximalRectangle(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        });
    }
    //最大矩形
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            对于每一行，求出 heights[]，可以把问题转换成 84.柱状图中最大的矩形
            遍历对比求出最大值
        */
        public int maximalRectangle(char[][] matrix) {
            int res = 0;
            for (int i = 0; i < matrix.length; i++) {
                int[] heights = getHeights(matrix, i);
                res = Math.max(res, largestRectangleArea(heights));
            }
            return res;
        }

        public int[] getHeights(char[][] matrix, int bottom) {
            int width = matrix[0].length;
            int[] heights = new int[matrix[0].length];
            for (int j = 0; j < width; j++) {
                int h = 0;
                for (int i = bottom; i >= 0; i--) {
                    if (matrix[i][j] == '1') {
                        h++;
                    } else {
                        break;
                    }
                }
                heights[j] = h;
            }
            return heights;
        }

        /*
        解题思路：单调增栈（相等或者更大）
            暴力解法：
                遍历数组，以当前柱子高度为h，分别往左往右，找到能组成高为 h，宽为 w 的矩形。
                遍历对比，得出最大的矩形。
            暴力解法的优化思路，针对指定的柱体 heights[i]，用O(1)时间复杂度的方法找到左右第一个比 heights[i] 小的数，计算面积。
            单调增栈，遍历到的比栈顶元素小的数，针对当前栈顶的柱体，栈顶元素在栈中的下一个元素，就是它左边第一个比它小的数，
            遍历到的比栈顶元素大的数，就是右边第一个比它小的数。
            因此，对应栈顶柱体 heights[i]，它左右第一个比它小的数的位置就确定了，就可以计算面积了
         */
        public int largestRectangleArea(int[] heights) {
            // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
            int[] tmp = new int[heights.length + 2];
            System.arraycopy(heights, 0, tmp, 1, heights.length);

            Deque<Integer> stack = new ArrayDeque<>();
            int area = 0;
            for (int i = 0; i < tmp.length; i++) {
                // 当出现第一个比栈顶元素小的数，栈顶元素左右第一个比栈顶元素小的数的位置就出现了，通过此计算面积。
                // 不断进行计算面积，直到能满足单调增栈的条件，再入栈。
                while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                    // 这里的 w 必须等栈顶元素 pop之后，根据新栈顶元素得出。
                    // 意思是栈顶左边第一个比它小的元素，到栈顶右边第一个比它小的元素之间的宽度。
                    int h = tmp[stack.pop()];
                    int w = i - stack.peek() - 1;
                    area = Math.max(area, w * h);
                }
                stack.push(i);
            }
            return area;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
