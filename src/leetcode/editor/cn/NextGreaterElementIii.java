package leetcode.editor.cn;

import java.util.*;

class NextGreaterElementIii{
    public static void main(String[] args){
        Solution solution = new NextGreaterElementIii().new Solution();
        solution.nextGreaterElement(21);
    }
    //下一个更大元素 III
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        下一个排列问题的变种
        思路：
            把 n 转换成数组，运用解决下一个排列问题的方法解决。
            题目要求超过32位整数时返回-1，可以通过在 try catch 中对 string 转换成 int 时抛异常来解决。
        解决”下一个排列“
            思路：
                1.下一个数比当前数大，所以将后面一个大数与前面一个小数交换
                2.变换成的下一个数的增幅尽可能小
                    1.在尽可能靠右的低位进行交换，从后往前遍历。
                    2.将后面尽可能小的大数与前面的小数进行交换，12345876是把6和5交换，不是把7或8和5交换。
                    3.后面的大数交换到前面后，将大数后面的数升序排序，这样后面就是最小的排列，交换到前面的大数让总体变大了，所以总体就是字典序中下一个更大的排列。
         */
        public int nextGreaterElement(int n) {
            String numStr = String.valueOf(n);
            int length = numStr.length();
            int[] nums = new int[length];
            for (int i = 0; i < length; i++) {
                nums[i] = numStr.charAt(i) - '0';
            }
            for (int i = length-1; i>0; i--) {
                //从后往前找，找到最右边的升序
                if (nums[i] > nums[i-1]) {
                    // nums[i-1] 到 nums[i]是最右边的升序，nums[i]后面都是降序。
                    // 从右往左找到最右边最小的比 nums[i-1] 大的数，交换。
                    for (int j=length-1; j>=i; j--) {
                        if (nums[j] > nums[i-1]) {
                            swap(nums, i-1, j);
                            // 需要跳出for循环，不然后面还会发生交换影响结果。
                            break;
                        }
                    }
                    // 交换过后，还需要把交换到前面的数后面的数升序排序，这样才能保证后面最小。
                    Arrays.sort(nums, i, length);
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                sb.append(num);
            }
            try {
                int res = Integer.parseInt(sb.toString());
                return res > n ? res : -1;
            } catch (Exception e) {
                return -1;
            }
        }
        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
