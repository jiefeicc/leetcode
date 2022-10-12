package leetcode.editor.cn;

import java.util.*;

class EightQueensLcci{
    public static void main(String[] args){ 
        Solution solution = new EightQueensLcci().new Solution();
    }
    // 八皇后
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：回溯，剪枝，全排列
        回溯模板：
            res = List<List<Integer>>
            list = LinkedList<Integer>
            backtrack(选择列表):
                if 满足结束条件:
                    res.add(list)
                    return
                for 选择 in 选择列表:
                    剪枝
                    做选择
                    backtrack(选择列表)
                    撤销选择
         */
        List<List<String>> res = new ArrayList<>();
        char[][] charArray;

        public List<List<String>> solveNQueens(int n) {
            if (n <= 0) {
                return res;
            }
            charArray = new char[n][n];
            for (char[] chars : charArray) {
                Arrays.fill(chars, '.');
            }
            return backTrack(0);
        }

        private List<List<String>> backTrack(int row) {
            if (row == charArray.length) {
                res.add(charToList(charArray));
            }
            for (int col = 0; col < charArray[0].length; col++) {
                if (valid(row, col)) {
                    charArray[row][col] = 'Q';
                    backTrack(row + 1);
                    charArray[row][col] = '.';
                }
            }
            return res;
        }

        private boolean valid(int row, int col) {
            /*
            因为第 row 层以下，还没有 dfs 到，所以对角线只有检查左上角和右上角就行。
            用的回溯，会有回退操作，一层只会存在一个 'Q'，所以只用检查同一列即可。
             */

            // 检查同一列
            for (char[] chars : charArray) {
                if (chars[col] == 'Q') {
                    return false;
                }
            }

            // 检查右上角，j 需要从 col + 1 开始，因为不能检查自己所在列。
            int colSize = charArray[0].length;
            for (int i = row - 1, j = col + 1; i >= 0 && j < colSize; i--, j++) {
                if (charArray[i][j] == 'Q') {
                    return false;
                }
            }

            // 检查左上角，j 需要从 col - 1 开始，因为不能检查自己所在列。
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (charArray[i][j] == 'Q') {
                    return false;
                }
            }

            return true;
        }

        private List<String> charToList(char[][] charArray) {
            List<String> list = new ArrayList<>();
            for (char[] chars : charArray) {
                list.add(String.valueOf(chars));
            }
            return list;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}