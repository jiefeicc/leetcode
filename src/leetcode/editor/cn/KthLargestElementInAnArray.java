//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1240 👎 0

package leetcode.editor.cn;

import java.util.PriorityQueue;

//java:数组中的第K个最大元素
class KthLargestElementInAnArray{
    public static void main(String[] args){
        Solution solution = new KthLargestElementInAnArray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * TopK系列问题，使用快速选择或者堆来解决
         */
        /**
         * 快速选择算法
         * findK{
         *     target = len-k ,第 k 大元素的下标是 len - k
         *     while (true) {
         *         index = parti (left,right)
         *         if:
         *             index = target, return
         *             index < target, left = index+1
         *             index > target, right = index-1
         *     }
         * }
         * partition{
         *      使num[left]左边的数小于num[left]，右边的大于num[left]
         *      然回num[left]所在的位置
         * }
         */
        /**
         * 小顶堆实现（当前节点小于等于左右孩子节点）
         * for (int num : nums) {
         *     heap.add(num);
         *     if (heap.size() > k) {
         *         heap.poll();
         *      }
         * }
         * return heap.peek();
         */
        //小顶堆实现
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int num : nums) {
                minHeap.add(num);
                if (minHeap.size()>k){
                    minHeap.poll();
                }
            }
            return minHeap.peek();
        }
        //快速选择算法实现
        public int findKthLargest1(int[] nums, int k) {
            int len = nums.length;
            int left = 0;
            int right = len - 1;
            int target = len - k;
            while (true) {
                int index = partition(nums, left, right);
                if (index == target) {
                    return nums[index];
                } else if (index < target) {
                    left = index + 1;
                } else {
                    right = index - 1;
                }
            }
        }
        public int partition(int[] nums, int left, int right) {
            int pivot = nums[left];
            int j = left;
            for (int i = left + 1; i <= right; i++) {
                if (nums[i] < pivot) {
                    j++;
                    swap(nums, j, i);
                }
            }
            swap(nums, j, left);
            return j;
        }
        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
