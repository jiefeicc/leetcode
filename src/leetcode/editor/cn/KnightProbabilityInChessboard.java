//在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格
//是 (0,0) ，右下单元格是 (n - 1, n - 1) 。 
//
// 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。 
//
// 
//
// 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。 
//
// 骑士继续移动，直到它走了 k 步或离开了棋盘。 
//
// 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。 
//
// 
//
// 示例 1： 
//
// 
//输入: n = 3, k = 2, row = 0, column = 0
//输出: 0.0625
//解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
//在每一个位置上，也有两种移动可以让骑士留在棋盘上。
//骑士留在棋盘上的总概率是0.0625。
// 
//
// 示例 2： 
//
// 
//输入: n = 1, k = 0, row = 0, column = 0
//输出: 1.00000
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 25 
// 0 <= k <= 100 
// 0 <= row, column <= n 
// 
// Related Topics 动态规划 👍 231 👎 0

package leetcode.editor.cn;

import java.util.*;

//java:骑士在棋盘上的概率
class KnightProbabilityInChessboard{
    public static void main(String[] args){
        // Solution solution = new KnightProbabilityInChessboard().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        1、DFS 记忆化搜索，时间：O(8*k*n^2)，空间：O(k*n^2)
        八个方向，对每个方向dfs看在单元格的可能的概率，八个方向的值相加除以8
        创建一个memo[n][n][k+1]，记录在 (i,j) 还剩 p 步时在棋盘上的概率
         */
        int[][] steps = new int[][]{{-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1}};
        public double knightProbability(int n, int k, int row, int column) {
            double[][][] memo = new double[n][n][k+1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(memo[i][j], -1);
                }
            }
            return dfs(n, k, row, column, memo);
        }
        private double dfs(int n, int k, int row, int column, double[][][] memo) {
            if (!inner(n, row, column)) {
                return 0.0;
            }
            if (k == 0) {
                return 1.0;
            }
            if (memo[row][column][k] != -1) {
                return memo[row][column][k];
            }
            double res = 0;
            for (int[] step : steps) {
                res += dfs(n, k-1, row + step[0], column + step[1], memo);
            }
            memo[row][column][k] = res/8.0;
            return memo[row][column][k];
        }
        public boolean inner(int n, int i, int j) {
            return (0 <= i && i <= n-1) && (0 <= j && j <= n-1);
        }

        /*
        2、线性 dp 解法，时间：O(8*k*n^2)，空间：O(k*n^2)
        状态定义 dp[i][j][p] ： 在 (i, j) 还有 p 步要走时，留在棋盘内的概率
        状态转移方程分析：从还有 p 步要走，从（i,j）走到 (curRow, curColumn) 时
                如果 (row, col) 在棋盘内，dp[i][j][p] = dp[i][j][p] + dp[curRow][curColumn][p-1]/8（除以 8 的原因是从 (i,j)走 有八个等概率方向）
                如果 (row, col) 不在棋盘内，dp[row][col][p-1] = 0，不做计算，不影响结果
         初始状态：dp[i][j][0] = 1，只有 0 步走时，肯定在棋盘内
         */
        public double _knightProbability(int n, int k, int row, int column) {
            double[][][] dp = new double[n][n][k+1];
            for (int i = 0; i <= n-1; i++) {
                for (int j = 0; j <= n-1; j++) {
                    dp[i][j][0] = 1;
                }
            }
            for (int p = 1; p <= k; p++) {
                for (int i = 0; i <= n-1; i++) {
                    for (int j = 0; j <= n-1; j++) {
                        for (int[] step : steps) {
                            int curRow = i + step[0];
                            int curColumn = j + step[1];
                            if (inner(n, curRow, curColumn)) {
                                dp[i][j][p] += dp[curRow][curColumn][p-1]/8;
                            }
                        }
                    }
                }
            }
            return dp[row][column][k];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
