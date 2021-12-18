//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
// Related Topics 数组 回溯 👍 1690 👎 0

package leetcode.editor.cn;

import java.util.*;

//java:全排列
class Permutations{
    public static void main(String[] args){
        Solution solution = new Permutations().new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public  class Solution {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            boolean[] used = new boolean[len];
            backtrack(nums, used);
            return res;
        }
        private void backtrack(int[] nums, boolean[] used) {
            if (list.size() == nums.length) {
                res.add(new ArrayList<>(list));
                return;
            }
            // 每次从 i=0开始，如果used就下一个
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    list.add(nums[i]);
                    used[i] = true;
                    backtrack(nums, used);
                    used[i] = false;
                    list.removeLast();
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
