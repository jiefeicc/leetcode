//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1：
//
//
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
//
//
// 示例 2：
//
//
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] 的值为 '0' 或 '1'
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1456 👎 0

package leetcode.editor.cn;
//java:岛屿数量
class NumberOfIslands{
    public static void main(String[] args){
        Solution solution = new NumberOfIslands().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         遍历“图“的每一个节点
         遇到一个是岛屿就res++
         然后从该节点开始dfs，将属于该岛屿的节点都标记为已经遍历 grid[r][c] = '2'
         最终res++就是岛屿数量
         */
        public int numIslands(char[][] grid) {
            int res = 0;
            for (int i=0; i<grid.length; i++) {
                for (int j=0; j< grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        res++;
                    }
                }
            }
            return res;
        }
        public void dfs(char[][] grid, int r, int c) {
            // 行row，列column
            // 判断 base case
            if(!inArea(grid, r, c)) {
                return;
            }
            // 做处理
            // 不是岛屿直接返回
            if (grid[r][c] != '1') {
                return;
            }
            // 将岛屿标记为已经遍历
            grid[r][c] = '2';

            // 访问上下左右四个节点
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }
        public boolean inArea(char[][] grid, int r, int c) {
            return 0<=r && r<grid.length && 0<=c && c<grid[0].length;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
