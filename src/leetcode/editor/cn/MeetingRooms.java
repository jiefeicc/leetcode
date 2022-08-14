package leetcode.editor.cn;

import java.util.*;

class MeetingRooms{
    public static void main(String[] args){
        Solution solution = new MeetingRooms().new Solution();
    }
    //会议室
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            一个人是否能够参加这里面的全部会议？即 A0, A1, B0, B1, C0, C1 能否单调增
            使用 Arrays.sort() 将二维数组通过 intervals[0][j], 对里面的一维数组排序
            接下来判断 intervals[i][1] > intervals[i + 1][0]，即出现 A1 > B0 的情况就肯定不能参加这里面的全部会议。
         */
        public boolean canAttendMeetings(int[][] intervals) {
            Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
            for (int i = 0; i < intervals.length - 1; i++) {
                if (intervals[i][1] > intervals[i + 1][0]) {
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
