//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//
// 必须 原地 修改，只允许使用额外常数空间。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[1,3,2]
//
//
// 示例 2：
//
//
//输入：nums = [3,2,1]
//输出：[1,2,3]
//
//
// 示例 3：
//
//
//输入：nums = [1,1,5]
//输出：[1,5,1]
//
//
// 示例 4：
//
//
//输入：nums = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
//
// Related Topics 数组 双指针 👍 1446 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:下一个排列
class NextPermutation{
    public static void main(String[] args){
        Solution solution = new NextPermutation().new Solution();
        solution.nextPermutation(new int[]{1,2,3,4,5,8,7,6});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        思路：
            1.下一个数比当前数大，所以将后面一个大数与前面一个小数交换
            2.变换成的下一个数的增幅尽可能小
                1.在尽可能靠右的低位进行交换，从后往前遍历。
                2.将后面尽可能小的大数与前面的小数进行交换，12345876是把6和5交换，不是把7或8和5交换。
                3.后面的大数交换到前面后，将大数后面的数升序排序，这样后面就是最小的排列，交换到前面的大数让总体变大了，所以总体就是字典序中下一个更大的排列。
         */
        public void nextPermutation(int[] nums) {
            int len = nums.length;
            if (len <= 1) {
                return;
            }
            for (int i = len-1; i>0; i--) {
                //从后往前找，找到最右边的升序
                if (nums[i] > nums[i-1]) {
                    // nums[i-1] 到 nums[i]是最右边的升序，nums[i]后面都是降序。
                    // 从右往左找到最右边最小的比 nums[i-1] 大的数，交换。
                    for (int j=len-1; j>=i; j--) {
                        if (nums[j] > nums[i-1]) {
                            swap(nums, i-1, j);
                            // 需要跳出for循环，不然后面还会发生交换影响结果。
                            break;
                        }
                    }
                    // 交换过后，还需要把交换到前面的数后面的数升序排序，这样才能保证后面最小。
                    Arrays.sort(nums, i, len);
                    return;
                }
            }
            Arrays.sort(nums);
        }
        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
