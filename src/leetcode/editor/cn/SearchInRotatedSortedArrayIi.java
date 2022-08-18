package leetcode.editor.cn;

import java.util.*;

class SearchInRotatedSortedArrayIi{
    public static void main(String[] args){
        Solution solution = new SearchInRotatedSortedArrayIi().new Solution();
    }
    //搜索旋转排序数组 II
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        数组中不存在相同元素的解题思路：
             旋转数组二分
             二分同时需要考虑mid在左右哪一段
             分四种情况
             例：5 6 7 8 9 0 1 2 3 4
             mid在左段（nums[left] <= nums[mid]）:
                target在mid左边（nums[left] <= target < nums[right]）
                target在mid右边
             mid在右段:
                target在mid左边
                target在mid右边（nums[mid] < target <= nums[right]）
        数组中存在相同元素的解题思路：
             当出现 1,0,1,1,1 或者 1,1,1,0,1 ，nums[left] = nums[mid]时，
             我们无法判断取哪个区间，数组失去了二段性。
             所以在原有思路的基础上，在 判断 nums[mid] == target 之后，在判断 nums[mid] >= nums[left] 之前
             判断 nums[left] == nums[mid]？符合的情况就要去掉这个重复的干扰项，即 left++; 再continue 进行下一次 nums[mid] >= nums[left] 判断
         */
        public boolean search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left)/2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[left] == nums[mid]) {
                    left++;
                    continue;
                }
                if (nums[mid] > nums[left]) {
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return false;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
