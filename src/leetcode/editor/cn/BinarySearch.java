package leetcode.editor.cn;

import java.util.*;

class BinarySearch{
    public static void main(String[] args){
        Solution solution = new BinarySearch().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 不断排除一半的区间，最后剩下的那个肯定是结果（如果数组里面包含的话）。
        public int search(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l)/2;
                if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return nums[l] == target ? l : -1;
        }

        // 每次判断中间的那个数，知道判断完所有数组的元素。
        public int _search(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else if (nums[mid] < target) {
                    l = mid + 1;
                }
            }
            return -1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}