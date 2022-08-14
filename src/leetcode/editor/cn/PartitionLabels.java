package leetcode.editor.cn;

import java.util.*;

class PartitionLabels{
    public static void main(String[] args){
        Solution solution = new PartitionLabels().new Solution();
        System.out.println(solution.partitionLabels("babcbacadefegdehijhklij"));
    }
    //划分字母区间
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            贪心思想
            条件一：同一字母只能出现在一个片段中 & 条件二：划分为尽可能多的片段
            使用 int[] last = new int[26] 记录字符串每个字符最后出现的位置
            遍历字符串，维护一个 end 来表示当前遍历区间中，相同字符最后出现的位置。
            当遍历索引 i = end 时，说明该区间满足条件一，res.add(区间一的长度)。
            对后面的字符串做同样操作，直到索引结束。
            证明：
                当遍历索引 i = end 时，说明该区间满足条件一。
                由于从头遍历到 i 时第一次满足条件一，所以这个就是前面的满足条件一的最小串。
                同理，运用该方法，每个子串都是最小串，所以自然满足条件二。
         */
        public List<Integer> partitionLabels(String s) {
            int[] lastIndex = new int[26];
            for (int i = 0; i < s.length(); i++) {
                lastIndex[s.charAt(i) - 'a'] = i;
            }
            int start = 0;
            int end = 0;
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                end = Math.max(end, lastIndex[s.charAt(i) - 'a']);
                if (i == end) {
                    res.add(end - start + 1);
                    start = i + 1;
                }
            }
            return res;
        }

    };
    //leetcode submit region end(Prohibit modification and deletion)

}
