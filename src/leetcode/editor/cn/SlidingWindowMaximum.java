package leetcode.editor.cn;

import java.util.*;

class SlidingWindowMaximum{
    public static void main(String[] args){
        Solution solution = new SlidingWindowMaximum().new Solution();
        solution.maxSlidingWindow(new int[]{1,3,-1,-3,2,3,6,7}, 3);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        双向单调队列解法：
            维持一个单调队列，存储窗口内可能出现的最大值的索引。
            nums[i] < nums[i+1]，前者就可以出队列了，因为前者就不可能是nums[i+1]在的情况下的窗口的最大值
        解题步骤：
            for right,index; right < len; right++
                (先确定队列不为空)当队尾元素（索引）小于即将加入的索引对应的数组值，出队，一直重复这步操作
                元素入队
                求出左边界left（左边界可能还没进入数组，还没形成窗口）
                当队首元素索引小于 left，说明队首已经出窗口了，出队首
                当 right >= k - 1，说明右边界已经到达第 k 个位置了，窗口形成了，该求最大值了
                    res[index] = nums[queue.peekFirst]
                    index++
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            int length = nums.length;
            int[] res = new int[length - k + 1];
            LinkedList<Integer> queue = new LinkedList<>();
            for (int right = 0, index = 0; right < length; right++) {
                while (!queue.isEmpty() && nums[queue.peekLast()] < nums[right]) {
                    queue.removeLast();
                }
                queue.add(right);
                // 求出左边界left（左边界可能还没进入数组，还没形成窗口）
                int left = right - (k - 1);
                // 当队首元素索引小于 left，说明队首已经出窗口了，出队首
                if (left > queue.peekFirst()) {
                    queue.removeFirst();
                }
                // 说明已经形成窗口，需要求最大值了
                if (right >= k - 1) {
                    res[index] = nums[queue.peekFirst()];
                    index++;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}