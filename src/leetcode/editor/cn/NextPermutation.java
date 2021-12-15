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
        solution.nextPermutation(new int[]{1,2,3,4,6,5});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        123465
        654321
         */
        public void nextPermutation(int[] nums) {
            int len = nums.length;
            if (len <= 1) {
                return;
            }
            for (int i = len-1; i>0; i--) {
                //从后往前找，找到最右边的升序
                if (nums[i] > nums[i-1]) {
                    // 将最右边第一个比nums[i-1]大的数与nums[i-1]交换
                    // 这个数肯定是右边最小的数，这个数最大就是nums[i]
                    for (int j=len-1; j>=i; j--) {
                        if (nums[j] > nums[i-1]) {
                            swap(nums, i-1, j);
                            // 需要跳出for循环
                            break;
                        }
                    }
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
