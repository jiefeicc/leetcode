package leetcode.editor.cn;

import java.util.*;

class FindMinimumInRotatedSortedArray{
    public static void main(String[] args){
        Solution solution = new FindMinimumInRotatedSortedArray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
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
         */
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length -1;
            while (left < right) {
                int mid = left + (right - left)/2;
                if(nums[mid] > nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return nums[left];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}