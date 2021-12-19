//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
//
//
//
// Related Topics 位运算 数组 回溯 👍 706 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//java:子集 II
class SubsetsIi{
    public static void main(String[] args){
        Solution solution = new SubsetsIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    // 在子集的基础上添加剪枝
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            // 添加used， 记录数字是否被使用过
            boolean[] used = new boolean[nums.length];
            backtrack(nums, used, 0);
            return res;
        }
        public void backtrack(int[] nums, boolean[] used, int start){
            res.add(new ArrayList<>(list));
            for (int i = start; i < nums.length; i++){
                if (!cut(nums, used, i)) {
                    list.add(nums[i]);
                    used[i] = true;
                    backtrack(nums, used, i+1);
                    used[i] = false;
                    list.removeLast();
                }
            }
        }
        // 剪枝
        private boolean cut(int[] nums, boolean[] used, int i) {
            /*
            1,2,2,5,7,8
            1,2(第1个2),8 和 1,2(第2个2),8 是不可以的，但是 1,2,2是可以的
            所以使用 nums[i - 1] && !used[i - 1]作为条件来剪枝
            i>0 保证nums[i - 1]合法
             */
            return i > 0 && nums[i] == nums[i - 1] && !used[i - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
