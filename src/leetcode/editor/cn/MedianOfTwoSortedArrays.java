//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。
//
//
//
// 示例 1：
//
//
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
//
// 示例 2：
//
//
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
//
// 示例 3：
//
//
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
//
//
// 示例 4：
//
//
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
//
//
// 示例 5：
//
//
//输入：nums1 = [2], nums2 = []
//输出：2.00000
//
//
//
//
// 提示：
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -10⁶ <= nums1[i], nums2[i] <= 10⁶
//
// Related Topics 数组 二分查找 分治 👍 4861 👎 0

package leetcode.editor.cn;
//java:寻找两个正序数组的中位数
class MedianOfTwoSortedArrays{
    public static void main(String[] args){
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        题目是求中位数，其实就是求第 k 小数的一种特殊情况，而求第 k 小数有一种算法。
        解法二中，我们一次遍历就相当于去掉不可能是中位数的一个值，也就是一个一个排除。
        由于数列是有序的，其实我们完全可以一半儿一半儿的排除。假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数。

        A[1] ，A[2] ，A[3]，A[k/2] ... ，B[1]，B[2]，B[3]，B[k/2] ...
        如果 A[k/2]<B[k/2] ，那么A[1]，A[2]，A[3]，A[k/2]都不可能是第 k 小的数字。
        A 数组中比 A[k/2] 小的数有 k/2-1 个，B 数组中，B[k/2] 比 A[k/2] 小
        假设 B[k/2] 前边的数字都比 A[k/2] 小，也只有 k/2-1 个
        所以比 A[k/2] 小的数字最多有 k/1-1+k/2-1=k-2个，所以 A[k/2] 最多是第 k-1 小的数。
        而比 A[k/2] 小的数更不可能是第 k 小的数了，所以可以把它们排除。

        由于我们已经排除掉了 3 个数字，就是这 3 个数字一定在最前边
        所以在两个新数组中，我们只需要找第 7 - 3 = 4 小的数字就可以了，也就是 k = 4。
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            // 因为数组是从索引0开始的，因此我们在这里必须+1，即索引(k+1)的数，才是第k个数。
            int left = (n + m + 1) / 2;
            int right = (n + m + 2) / 2;
            // 将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
            return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
        }
        private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            //因为索引和算数不同6-0=6，但是是有7个数的，因为end初始就是数组长度-1构成的。
            //最后len代表当前数组(也可能是经过递归排除后的数组)，符合当前条件的元素的个数
            int len1 = end1 - start1 + 1;
            int len2 = end2 - start2 + 1;
            //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
            //就是如果len1长度小于len2，把getKth()中参数互换位置，即原来的len2就变成了len1，即len1，永远比len2小
            if (len1 > len2) {
                return getKth(nums2, start2, end2, nums1, start1, end1, k);
            }
            //如果一个数组中没有了元素，那么即从剩余数组nums2的其实start2开始加k再-1.
            //因为k代表个数，而不是索引，那么从nums2后再找k个数，那个就是start2 + k-1索引处就行了。因为还包含nums2[start2]也是一个数。因为它在上次迭代时并没有被排除
            if (len1 == 0) {
                return nums2[start2 + k - 1];
            }
            //如果k=1，表明最接近中位数了，即两个数组中start索引处，谁的值小，中位数就是谁(start索引之前表示经过迭代已经被排出的不合格的元素，即数组没被抛弃的逻辑上的范围是nums[start]--->nums[end])。
            if (k == 1) {
                return Math.min(nums1[start1], nums2[start2]);
            }
            //为了防止数组长度小于 k/2,每次比较都会从当前数组所生长度和k/2作比较，取其中的小的(如果取大的，数组就会越界)
            //然后数组如果len1小于k / 2，表示数组经过下一次遍历就会到末尾，然后后面就会在那个剩余的数组中寻找中位数
            int i = start1 + Math.min(len1, k / 2) - 1;
            int j = start2 + Math.min(len2, k / 2) - 1;
            //如果nums1[i] > nums2[j]，表示nums2数组中包含j索引，之前的元素，逻辑上全部淘汰，即下次从J+1开始。
            //而k则变为k - (j - start2 + 1)，即减去逻辑上排出的元素的个数(要加1，因为索引相减，相对于实际排除的时要少一个的)
            if (nums1[i] > nums2[j]) {
                return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
            }
            else {
                return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }
        /*
        不合并，直接找中位数
        aStart 和 bStart 分别表示当前指向 A 数组和 B 数组的位置
        aStart 还没有到最后并且此时 A 位置的数字小于 B 位置的数组，那么就可以后移了
         */
        public double _findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int len = m + n;
            int left = -1, right = -1;
            int aStart = 0, bStart = 0;
            for (int i = 0; i <= len / 2; i++) {
                left = right;
                // 如果 B 数组此刻已经没有数字了
                // 继续取数字 B[ bStart ]，则会越界，所以判断下 bStart 是否大于数组长度了
                // 这样 || 后边的就不会执行了，也就不会导致错误了
                if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                    right = nums1[aStart++];
                } else {
                    right = nums2[bStart++];
                }
            }
            if ((len & 1) == 0)
                return (left + right) / 2.0;
            else
                return right;
        }
        // 合并两个数组(归并排序)，根据奇数偶数确定中位数
        public double __findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int[] nums = new int[m + n];
            if (m == 0) {
                return n%2 == 0 ? (nums2[n/2-1]+nums2[n/2])/2.0 : nums2[n/2];
            }
            if (n == 0) {
                return m%2 == 0 ? (nums1[m/2-1]+nums1[m/2])/2.0 : nums1[m/2];
            }
            int count = 0;
            int i = 0, j = 0;
            while (count != (m + n)) {
                if (i == m) {
                    while (j != n) {
                        nums[count++] = nums2[j++];
                    }
                    break;
                }
                if (j == n) {
                    while (i != m) {
                        nums[count++] = nums1[i++];
                    }
                    break;
                }
                if (nums1[i] < nums2[j]) {
                    nums[count++] = nums1[i++];
                } else {
                    nums[count++] = nums2[j++];
                }
            }
            return count%2==0 ? (nums[count/2-1]+nums[count/2])/2.0 : nums[count/2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
