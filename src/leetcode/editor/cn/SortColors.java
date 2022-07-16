package leetcode.editor.cn;

import java.util.*;

class SortColors{
    public static void main(String[] args){
        Solution solution = new SortColors().new Solution();
        // 2,0,2,1,1,0
        solution.sortColors(new int[]{0,1,2,2,2,0});
    }
    //颜色分类
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /*
        解题思路：
            1.遍历数组把 0 交换到左边
            2.从 0 后面的数字遍历，把 1 交换到 0 后面的数字中
         */
        public void sortColors(int[] nums) {
            int n = nums.length;
            int ptr = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    swap(nums, i, ptr);
                    ptr++;
                }
            }
            for (int i = ptr; i < n; i++) {
                if (nums[i] == 1) {
                    swap(nums, i, ptr);
                    ptr++;
                }
            }
        }

        /*
        解题思路：
            刷油漆算法
            遍历一遍数组
                从头到尾把数组刷成 2
                从头开始把数组刷（0 和 1总数） 个数的1
                从头开始把数组刷 0 个数的 0
         */
        public void __sortColors(int[] nums) {
            int n0 = 0;
            int n1 = 0;
            for(int i = 0; i < nums.length; i++){
                int num = nums[i];
                nums[i] = 2;
                if(num == 0 || num == 1){
                    nums[n1] = 1;
                    n1++;
                }
                if(num == 0){
                    nums[n0] = 0;
                    n0++;
                }
            }
        }

        public void _sortColors(int[] nums) {
            if (nums.length <= 2) {
                return;
            }
            int index = nums.length - 1;
            for (int i = 0; i < nums.length && i < index; i++) {
                if (nums[i] == 2 ) {
                    index = judge(nums, index, 2);
                    if (index <= i) {
                        break;
                    }
                    swap(nums, i, index);
                    index-- ;
                }
            }
            for (int i = 0; i < nums.length && i < index && index >= 0; i++) {
                if (nums[i] == 1) {
                    index = judge(nums, index, 1);
                    if (index <= i) {
                        break;
                    }
                    swap(nums, i, index);
                    index-- ;
                }
            }
            System.out.println(Arrays.toString(nums));
        }
        private int judge(int[] nums, int index, int n) {
            if (index < 0) {
                return 0;
            }
            if (nums[index] < n) {
                return index;
            }
            return judge(nums, index - 1, n);
        }
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
