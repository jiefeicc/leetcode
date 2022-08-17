package leetcode.editor.cn;

import java.util.*;

class FindMinimumInRotatedSortedArrayIi{
    public static void main(String[] args){
        Solution solution = new FindMinimumInRotatedSortedArrayIi().new Solution();
    }
    //寻找旋转排序数组中的最小值 II
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        数组中不存在相同元素的解题思路：
            二分法
                二分法主要是不断收缩左右空间，直到满足条件为止。
                如何判断能不能使用二分法，主要看区间是否满足二分性，即一边肯定满足条件，一边肯定不满足条件，这样就能明确排除掉一部分区间。
            对于本题，具体分析下：
                23456701, nums[mid] > nums[left] || nums[mid] > nums[right] 就能判断出结果在右边区间
                    left = mid + 1
                67012345, nums[mid] < nums[left] || nums[mid] < nums[right] 就能判断出结果在左边区间
                    right = mid -1
                012     , nums[mid] > nums[left] || nums[mid] < nums[right] 就能判断出结果在左边区间
                    right = mid
            综上，对于本题的判断和取区间如下，区间收缩直到 left = right，那么此时索引就在最小值的时候。
            if(nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        存在相同元素的解题思路：
            在不存在相同元素的解题思路的基础上，有一些额外的思考。
            23444456701, nums[mid] > nums[right] 就能判断出结果在右边区间
                left = mid + 1
            67012333345 或者 0112， nums[mid] < nums[right] 就能判断出结果在左边区间
                right = mid
            这两种情况没有变化，但是存在 1,0,1,1,1 或 1,1,1,0,1，也就是nums[mid] = nums[right]时，数组失去了二段性，我们无法判断取哪个区间。
                此时我们可以使 right-1 来缩小区间。思考：right-1 会数组越界或者跳过最小值么？
                1.while (left < right) 保证了 right-1 也不会数组越界。
                2.nums[right] 是最小值的话，那么 nums[mid] 也是最小值，right-1 并没有使最小值丢失。
         */
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = left + (right - left)/2;
                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else if (nums[mid] < nums[right]) {
                    right = mid;
                } else {
                    right--;
                }
            }
            return nums[left];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
