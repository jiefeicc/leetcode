//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
// Related Topics 数组 回溯 👍 887 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//java:全排列 II
class PermutationsIi{
    public static void main(String[] args){
        Solution solution = new PermutationsIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    // 在全排列的基础上添加剪枝
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            boolean[] used = new boolean[len];
            //  排序是剪枝的前提
            Arrays.sort(nums);
            backtrack(nums, used);
            return res;
        }
        private void backtrack(int[] nums, boolean[] used) {
            if (list.size() == nums.length) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                // 添加剪枝条件，起到去重效果
                if (!used[i] && !cut(nums, used, i)) {
                    list.add(nums[i]);
                    used[i] = true;
                    backtrack(nums, used);
                    used[i] = false;
                    list.removeLast();
                }
            }
        }
        // 剪枝
        private boolean cut(int[] nums, boolean[] used, int i) {
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在过程中刚刚被撤销选择
            return i > 0 && nums[i] == nums[i - 1] && !used[i - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
