//给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
//
//
//
// 示例 1：
//
//
//输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,4,7,5,3,6,8,9]
//
//
// 示例 2：
//
//
//输入：mat = [[1,2],[3,4]]
//输出：[1,2,3,4]
//
//
//
//
// 提示：
//
//
// m == mat.length
// n == mat[i].length
// 1 <= m, n <= 10⁴
// 1 <= m * n <= 10⁴
// -10⁵ <= mat[i][j] <= 10⁵
//
// Related Topics 数组 矩阵 模拟 👍 286 👎 0

package leetcode.editor.cn;

import java.util.*;

class DiagonalTraverse{
    public static void main(String[] args){
        Solution solution = new DiagonalTraverse().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        从坐标 (m:0, n:0) 开始，右上->左下->右上->左下->右上->左下->........
        规律：每次朝一个方向时，m + n 是相等的，切方向切换时，m + n 是从 0 开始递增的
        解题思路：
            // i 是 x + y 的和
            for i = 0; i < count; i++
                //通过 i % 2 控制方向
                i % 2 == 0
                    while (m >= 0 && n < col)
                        answer[answerIndex] = matrix[m][n];
                        answerIndex++;
                        m--;
                        n++;
                    超出边界处理
                i % 2 != 0
                    while (m >= 0 && n < col)
                        answer[answerIndex] = matrix[m][n];
                        answerIndex++;
                        m--;
                        n++;
                    超出边界处理
         */
        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix.length == 0) {
                return new int[0];
            }

            int row = matrix.length;
            int col = matrix[0].length;

            int[] answer = new int[row * col];
            int count = row + col - 1;
            int m = 0;
            int n = 0;
            int answerIndex = 0;

            // (m,n) 坐标的移动，i 是 x + y 的和
            for (int i = 0; i < count; i++) {
                // 往右上角移动
                if (i % 2 == 0) {
                    while (m >= 0 && n < col) {
                        answer[answerIndex] = matrix[m][n];
                        answerIndex++;
                        m--;
                        n++;
                    }
                    // 出边界后的回退处理
                    // 第一种 m 出边界了
                    if (n < col) {
                        m++;
                    }
                    // 第二种到右上角的拐角，m，n都出边界了
                    else {
                        m = m + 2;
                        n--;
                    }
                }
                // 往左下角移动
                else {
                    while (m < row && n >= 0) {
                        answer[answerIndex] = matrix[m][n];
                        answerIndex++;
                        m++;
                        n--;
                    }
                    // 出边界后的回退处理
                    // 第一种 n 出边界了
                    if (m < row) {
                        n++;
                    }
                    // // 第二种到左下角的拐角，m，n都出边界了
                    else{
                        m--;
                        n = n + 2;
                    }

                }
            }
            return answer;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
