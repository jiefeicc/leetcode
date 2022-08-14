package leetcode.editor.cn;

import java.util.*;

class MeetingRoomsIi{
    public static void main(String[] args){
        Solution solution = new MeetingRoomsIi().new Solution();
    }
    //会议室 II
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路
            intervals = [[0,30],[5,10],[15,20]]
            可以将问题转换成上下车问题，某个人从 0 上车，从 30 下车，某个人从 5 上车，从 10 下车。
            某个时刻，车上存在的最多的人，就是此题目的答案。
            解题步骤：
                构造一个行为数组(二维)，表示每个时刻是上车还是下车（1， -1）
                对行为数组按照时刻数字进行增序排序，当出现某个时刻既有上车也有下车，要先下车再上车。
                遍历行为数组，求出某个时刻，车上存在的最多的人。
         */
        public int minMeetingRooms(int[][] intervals) {
            int length = intervals.length;
            int[][] actions = new int[length * 2][2];


            int i = 0;
            for (int[] interval : intervals) {
                actions[i++] = new int[]{interval[0], 1};
                actions[i++] = new int[]{interval[1], -1};
            }

            Arrays.sort(actions, (v1, v2) -> {
                int x = v1[0] - v2[0];
                return x == 0 ? v1[1] - v2[1] : x;
            });

            int max = 0;
            int cur = 0;
            for (int[] action : actions) {
                cur = cur + action[1];
                max = Math.max(max, cur);
            }

            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
