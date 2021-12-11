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
        int[] nums = new int[]{3,7,1,5,6,0};
        solution.findKthLargest(nums, 2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        快速选择(切分)算法：
        findK() {
            寻找第K大的数就是寻找”排序“数组中第num.len-K个数
            target = len-K
            left = 0
            right = len -1;
            while(true) {
                int index = partition(num[],left,right)
                if (index > target) {
                    right = index -1
                } else if (index < target) {
                    left = index+1
                } else {
                    return nums[target]
                }
            }
        }
        partition(num[],left,right) {
            使num[left]大于左边的数，小于右边的数
            返回num[left]的下标
        }
         */
        public int findKthLargest(int[] nums, int k) {
            int length = nums.length;
            int target = length-k;
            int left = 0;
            int right = length -1;
            while(true) {
                int index = partition(nums, left, right);
                if (index > target) {
                    right = index -1;
                } else if (index < target) {
                    left = index+1;
                } else {
                    return nums[target];
                }
            }
        }
        public int partition(int[] nums, int left, int right) {
            //temp就是基准位
            int pivot = nums[left];
            int i = left;
            int j = right;
            while (i<j) {
                //先看右边，依次往左递减
                while (pivot<=nums[j]&&i<j) {
                    j--;
                }
                //再看左边，依次往右递增
                while (pivot>=nums[i]&&i<j) {
                    i++;
                }
                //此时满足条件（nums[j] < pivot < nums[i]），交换
                if (i<j) {
                    swap(nums, i, j);
                }
            }
            //最后将基准为与i和j相等位置的数字交换
            swap(nums, left, i);
            return  i;
        }
        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
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
        public int findKthLargest1(int[] nums, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int num : nums) {
                minHeap.add(num);
                if (minHeap.size()>k){
                    minHeap.poll();
                }
            }
            return minHeap.peek();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
