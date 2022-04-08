package leetcode.editor.cn;

import java.util.*;

class SearchA2dMatrix{
    public static void main(String[] args){
        Solution solution = new SearchA2dMatrix().new Solution();
        solution.searchMatrix(new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}}, 34);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        全局二分
        按列依次从左往右，全局递增的，所以使用全局二分查找。
        取中间数时 tmp = mat[mid / col][mid % col]
        mid/col，mid%col 即可得到中间索引的数。
         */
        public boolean searchMatrix(int[][] mat, int t) {
            int row = mat.length;
            int col = mat[0].length;
            int l = 0;
            int r = row * col - 1;
            while (l < r) {
                int mid = (l + r + 1) / 2;
                int tmp = mat[mid / col][mid % col];
                if (tmp == t) {
                    return true;
                } else if (tmp < t) {
                    l = mid + 1;
                }
                else {
                    r = mid - 1;
                }
            }
            return mat[r / col][r % col] == t;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}