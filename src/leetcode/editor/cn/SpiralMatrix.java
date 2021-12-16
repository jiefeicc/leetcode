//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//
//
// 示例 2：
//
//
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 10
// -100 <= matrix[i][j] <= 100
//
// Related Topics 数组 矩阵 模拟 👍 930 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//java:螺旋矩阵
class SpiralMatrix{
    public static void main(String[] args){
        Solution solution = new SpiralMatrix().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        按照右下左上的顺序移动
        每次移动到了边界就重新设定边界
        边界超出就break
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            // 数组为空直接返回
            if (matrix.length == 0) {
                return res;
            }
            // 设定上下左右四个边界
            int up = 0;
            int down = matrix.length-1;
            int left = 0;
            int right = matrix[0].length-1;
            while (true) {
                // 往右移动，当到最右边时停下来
                for (int col=left ; col<=right; col++) {
                    res.add(matrix[up][col]);
                }
                // 重新设定上边界
                up++;
                // 若上边界超过下边界，跳出
                if (up > down) {
                    break;
                }
                // 往下移
                for (int row=up ; row<=down; row++) {
                    res.add(matrix[row][right]);
                }
                right--;
                if (right < left) {
                    break;
                }
                // 往左移
                for (int col=right ; col>=left; col--) {
                    res.add(matrix[down][col]);
                }
                down--;
                if (down < up) {
                    break;
                }
                // 往上移
                for (int row=down ; row>=up; row--) {
                    res.add(matrix[row][left]);
                }
                left++;
                if (left > right) {
                    break;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
