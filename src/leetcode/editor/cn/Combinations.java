//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
// 你可以按 任何顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
//
// 示例 2：
//
//
//输入：n = 1, k = 1
//输出：[[1]]
//
//
//
// 提示：
//
//
// 1 <= n <= 20
// 1 <= k <= n
//
// Related Topics 数组 回溯 👍 796 👎 0

package leetcode.editor.cn;

import java.util.*;

//java:组合
class Combinations{
    public static void main(String[] args){
        Solution solution = new Combinations().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> combine(int n, int k) {
            backtrack(1, n, k);
            return res;
        }
        private void backtrack(int start, int n, int k) {
            if (k == list.size()) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i=start; i<=n; i++) {
                list.add(i);
                backtrack(i+1, n, k);
                list.removeLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
