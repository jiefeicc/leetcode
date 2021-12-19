//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。
//
// 注意：解集不能包含重复的组合。
//
//
//
// 示例 1:
//
//
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]
//
// 示例 2:
//
//
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//]
//
//
//
// 提示:
//
//
// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30
//
// Related Topics 数组 回溯 👍 768 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//java:组合总和 II
class CombinationSumIi{
    public static void main(String[] args){
        Solution solution = new CombinationSumIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    // 在组合总和的基础上添加剪枝
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            // 需要先给数组排序， 让target从小到大的减
            Arrays.sort(candidates);
            // 添加used， 记录数字是否被使用过
            boolean[] used = new boolean[candidates.length];
            backtrack(candidates, target, used, 0);
            return res;
        }
        private void backtrack(int[] candidates, int target, boolean[] use, int start) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                if (target < candidates[i]) {
                    break;
                }
                if (!cut(candidates, i, use)) {
                    list.add(candidates[i]);
                    use[i] = true;
                    // 注：同一个位置的数字只能使用一次，所以传给下一层 i+1
                    backtrack(candidates, target - candidates[i], use, i+1);
                    use[i] = false;
                    list.removeLast();
                }
            }
        }
        // 剪枝
        private boolean cut(int[] candidates, int i, boolean[] use) {
            /*
            1,2,2,5,7,8
            1,2(第1个2),8 和 1,2(第2个2),8 是不可以的，但是 1,2,2是可以的
            所以使用 nums[i - 1] && !used[i - 1]作为条件来剪枝
            i>0 保证nums[i - 1]合法
             */
            return i > 0 && candidates[i] == candidates[i - 1] && !use[i - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
