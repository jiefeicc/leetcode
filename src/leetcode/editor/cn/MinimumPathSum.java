package leetcode.editor.cn;

import java.util.*;

class MinimumPathSum{
    public static void main(String[] args){
        Solution solution = new MinimumPathSum().new Solution();
        solution.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        状态：dp[i][j] 从左上角到 grid[i][j] 的最小路径和
        状态转移：
            dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        base case：
            dp[0][0] = grid[0][0]
            dp[0][j] = dp[0][j-1] + grid[0][j]
            dp[i][0] = dp[i-1][0] + grid[i][0]
         */
        public int minPathSum(int[][] grid) {
//            Map<String, String> map = new HashMap<>();

            int row = grid.length;
            int col = grid[0].length;
            int[][] dp = new int[row][col];
            // base case
            dp[0][0] = grid[0][0];
            for (int i = 1; i < row; i++) {
                dp[i][0] = dp[i-1][0] + grid[i][0];
            }
            for (int j = 1; j < col; j++) {
                dp[0][j] = dp[0][j-1] + grid[0][j];
            }
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
//                    如果要找到其中一条路径，那就根据 min(dp[i-1][j], dp[i][j-1])，记录从哪个小的坐标过来的。
//                    map 记录了 每个坐标与上一个坐标的映射
//                    从 map.get([row-1][col-1]) 往前反推就可以得到一条可行路径
//                     StringBuilder tmp = new StringBuilder();
//                     String ij = String.valueOf(i) + String.valueOf(j);
//                    if (dp[i-1][j] < dp[i][j-1]) {
//                         tmp.append(i-1).append(j);
//                         map.put(ij, tmp.toString());
//                         System.out.println(tmp + "->" + ij);
//                    } else {
//                         tmp.append(i).append(j-1);
//                         map.put(ij, tmp.toString());
//                         System.out.println(tmp + "->" + ij);
//                    }
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }

            return dp[row-1][col-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}