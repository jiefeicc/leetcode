//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
//输出：4
//解释：最长递增路径为 [1, 2, 6, 9]。
//
// 示例 2：
//
//
//输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
//输出：4
//解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
//
//
// 示例 3：
//
//
//输入：matrix = [[1]]
//输出：1
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 200
// 0 <= matrix[i][j] <= 2³¹ - 1
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 动态规划 👍 575 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;

//java:矩阵中的最长递增路径
class LongestIncreasingPathInAMatrix{
    public static void main(String[] args){
        Solution solution = new LongestIncreasingPathInAMatrix().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        dfs + 记忆化搜索
        从一个格子上下左右四个方向寻找（满足未超过边界和下一个方向的值大于当前格子值）
        dfs每一个格子
        记忆化搜索：用一个visited[][]，记住每一个格子的最大递增长度
         */
        public int longestIncreasingPath(int[][] matrix) {
            if(matrix.length == 0){
                return 0;
            }
            //visited有两个作用：1.判断是否访问过，2.存储当前格子的最长递增长度
            int[][] visited = new int[matrix.length][matrix[0].length];
            int max = 0;
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix[0].length; j++){
                    //if(visited[i][j] == 0){
                        int path = dfs(i, j, matrix, visited);
                        max = Math.max(max, path);
                    //}
                }
            }
            return max;
        }
        public int dfs(int i, int j, int[][] matrix, int[][] visited){
            if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length){
                return 0;
            }
            if(visited[i][j] > 0){
                return visited[i][j];
            }
            int max = 0;
            // 上下左右
            if(i - 1 >= 0 && matrix[i-1][j] < matrix[i][j]){
                max = dfs(i-1, j, matrix, visited);
            }
            if(i + 1 < matrix.length && matrix[i+1][j] < matrix[i][j]){
                max = Math.max(max, dfs(i+1, j, matrix, visited));
            }
            if(j - 1 >= 0 && matrix[i][j-1] < matrix[i][j]){
                max = Math.max(max, dfs(i, j-1, matrix, visited));
            }
            if(j + 1 < matrix[0].length && matrix[i][j+1] < matrix[i][j]){
                max = Math.max(max, dfs(i, j+1, matrix, visited));
            }
            visited[i][j] = max+1;
            return max+1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
