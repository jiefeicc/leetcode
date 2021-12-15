//给你一个大小为 m x n 的二进制矩阵 grid 。
//
// 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都
//被 0（代表水）包围着。
//
// 岛屿的面积是岛上值为 1 的单元格的数目。
//
// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
//
//
//
// 示例 1：
//
//
//输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,
//0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,
//0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出：6
//解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
//
//
// 示例 2：
//
//
//输入：grid = [[0,0,0,0,0,0,0,0]]
//输出：0
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 50
// grid[i][j] 为 0 或 1
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 631 👎 0

package leetcode.editor.cn;
//java:岛屿的最大面积
class MaxAreaOfIsland{
    public static void main(String[] args){
        Solution solution = new MaxAreaOfIsland().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            for (int i=0; i<grid.length; i++) {
                for (int j=0; j< grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        int area = dfs(grid, i, j);
                        max = Math.max(area, max);
                    }
                }
            }
            return max;
        }
        public int dfs(int[][] grid, int r, int c) {
            // 行row，列column
            // 判断 base case
            if(!inArea(grid, r, c)) {
                return 0;
            }
            // 不是岛屿返回0
            if (grid[r][c] != 1) {
                return 0;
            }
            // 将岛屿标记为已经遍历
            grid[r][c] = 2;

            // 访问上下左右四个节点
            // 进入方法并且判断过没有超出边界，然后还判断了是岛屿，那面积就是 1 + dfs(上) + dfs(下) + dfs(左) + dfs(右);
            return 1 + dfs(grid, r - 1, c)
                      +dfs(grid, r + 1, c)
                      +dfs(grid, r, c - 1)
                      +dfs(grid, r, c + 1);
        }
        public boolean inArea(int[][] grid, int r, int c) {
            return 0<=r && r<grid.length && 0<=c && c<grid[0].length;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
