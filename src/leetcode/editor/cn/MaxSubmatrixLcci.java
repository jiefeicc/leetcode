package leetcode.editor.cn;

import java.util.*;

class MaxSubmatrixLcci{
    public static void main(String[] args){
        Solution solution = new MaxSubmatrixLcci().new Solution();
    }
    //最大子矩阵
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：二维数组前缀和 + 最大子序和（dp）
            针对该题，我们需要将求解最大子序和的二维数组降维。对降维后的一维数组求解最大子序和。
        解题步骤：
            生成一个二维数组前缀和 preSum[i][j]，代表从左上角 (0,0) 到右下角 (i - 1, j - 1)的二维数组所有数的和。
            for top = 0; top < row; top++
                for bottom = top; bottom < row; bottom++
                    在这两个 for 循环中，确定了要处理数组的上下边界，接着对于确定上下边界的数组，开始从头遍历。
                    left = 0
                    for right = 0; right < col; right++
                        在这里面的逻辑，就是求出一维数组的最大子序和，这里就体现了二维数组的降维。
                        对这里的一维数组的详解：
                            从左上角 (top, left) 到右下角 (bottom, right) 的二维数组，对每一列单独求和得出一个一维数组。
                        接下来的逻辑就是求出一维数组的最大子序和，当出现更大的子序和就更新 res
         */
        public int[] getMaxMatrix(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            // 二维前缀和
            int[][] preSum = new int[row + 1][col + 1];
            // 前缀和是需要错一位的
            for(int i = 1; i < row + 1; i++) {
                for(int j = 1; j < col + 1; j ++) {
                    preSum[i][j] = matrix[i - 1][j - 1] + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
                }
            }
            // 开始最大子序和
            int preMaxSum = Integer.MIN_VALUE;
            // 要返回的结果
            int[] res = new int[4];
            // 先固定上下两条边
            for(int top = 0; top < row; top++) {
                for(int bottom = top; bottom < row; bottom++) {
                    int localSum;
                    int left = 0;
                    // 然后从左往右一遍扫描找最大子序和
                    for(int right = 0; right < col; right++) {
                        // 利用 preSum 快速求出 localSum
                        localSum = (preSum[bottom + 1][right + 1] - preSum[top][right + 1]) - (preSum[bottom + 1][left] - preSum[top][left]);
                        // 如果比 global 大，更新
                        if(preMaxSum < localSum) {
                            preMaxSum = localSum;
                            res[0] = top;
                            res[1] = left;
                            res[2] = bottom;
                            res[3] = right;
                        }
                        // 如果不满0，前面都舍弃，从新开始计算，left更新到right+1，right下一轮递增之后left == right
                        if(localSum < 0) {
                            left = right + 1;
                        }
                    }
                }
            }
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
