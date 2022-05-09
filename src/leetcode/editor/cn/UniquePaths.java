package leetcode.editor.cn;

import java.util.*;

class UniquePaths{
    public static void main(String[] args){
        Solution solution = new UniquePaths().new Solution();
        solution.uniquePaths(3,7);
    }
    // 不同路径
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        dp解法：
            终点只能由左边和上边两种方式过来
            状态定义：dp[i][j] = x 到 (i,j) 有 x 种方法
            base case：dp[0][j] = dp[i][0] = 1;
            状态转移：
                dp[i][j] =  dp[i - 1][j] + dp[i][j - 1]
            */
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                dp[i][0] = 1;
            }
            for (int j = 0; j < n; j++) {
                dp[0][j] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] =  dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}