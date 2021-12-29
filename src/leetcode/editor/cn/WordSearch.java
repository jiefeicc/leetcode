//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"ABCCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"SEE"
//输出：true
//
//
// 示例 3：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"ABCB"
//输出：false
//
//
//
//
// 提示：
//
//
// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
// Related Topics 数组 回溯 矩阵 👍 1133 👎 0

package leetcode.editor.cn;
//java:单词搜索
class WordSearch{
    public static void main(String[] args){
        Solution solution = new WordSearch().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        回溯解法，类似岛屿问题，四个方向走
         */
        public boolean exist(char[][] board, String word) {
            // visited 数组防止重复访问
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];
            // 每个格子都可能是起点
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dfs(board, i, j, 0, word, visited))
                        return true;
                }
            }
            return false;
        }
        public boolean dfs(char[][] a, int row, int col, int index, String word, boolean[][] visited) {
            // 不在网格内
            if (!inArea(a, row, col)) {
                return false;
            }
            // 重复访问
            if (visited[row][col]) {
                return false;
            }
            // 当前字符不等
            if (a[row][col] != word.charAt(index)) {
                return false;
            }
            // 如果word的每个字符都查找完了，直接返回true
            if (index == word.length() - 1) {
                return true;
            }
            // 当前字符匹配 做访问标记
            visited[row][col] = true;
            // 当前点四个方向匹配下一个字符
            boolean flag = dfs(a, row - 1, col, index + 1, word, visited) ||
                           dfs(a, row + 1, col, index + 1, word, visited) ||
                           dfs(a, row, col - 1, index + 1, word, visited) ||
                           dfs(a, row, col + 1, index + 1, word, visited);
            // 回溯修改当前不能访问的点 但是接下来的方向可以访问它
            visited[row][col] = false;
            return flag;
        }
        public boolean inArea(char[][] a, int i, int j) {
            return 0 <= i && i < a.length && 0 <= j && j < a[0].length;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
