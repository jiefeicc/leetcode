package leetcode.editor.cn;

import java.util.*;

class DecodeWays{
    public static void main(String[] args){
        Solution solution = new DecodeWays().new Solution();
        solution.numDecodings("1");
    }
    // 解码方法
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        思路：
            DFS递归+剪枝 ? DP
            DFS递归+剪枝+memo：
                无需输出具体编码，所以无需dfs回溯操作，直接简化成条件判断递归。
                结果超时，因为每个位置两种解法，100个位置就是2^100种解法，O(2^n)时间复杂度太高。
                考虑加入记忆表，记录 start 位置和对应的从start开始后面的解法，通过测试。
            DP:
                最优子结构，子问题的重叠性，无后效性。
                无后效性：某个阶段上过程的状态已知，则从此阶段以后过程的发展变化仅与此阶段的状态有关，而与过程在此阶段以前的阶段所经历过的状态无关。
                对于 s 的位置 i，我们只关心 i 和 i-1 的位置是否是一种选择，前面我们不关注。
                dp步骤：
                    状态定义：dp(i)：以 s(i) 为结尾的字符串总共有多少种解码方法
                    选择：dp(i) = dp(i-1) + dp(i-2)，当 s(i)，解码方法就是 +=dp(i-1)，当 s(i-1, i) 可以解码时，解码方法就是 +=dp(i-2)
                    base case：f(0) = 1
         */
        public int numDecodings(String s) {
            int length = s.length();
            int[] dp = new int[length];
            char[] charArray = s.toCharArray();
            // 第一位是 0，那么无法解码。
            if (charArray[0] == '0') {
                return 0;
            }
            // base case
            dp[0] = 1;
            if (length >= 2) {
                if (charArray[1] != '0') {
                    dp[1] = 1;
                }
                // 如果 i=1 在第二位吗，则需要特判，看 s(0,1) 两位数能不能解码。
                int num = 10 * (charArray[0] - '0') + (charArray[1] - '0');
                if (num >= 10 && num <= 26) {
                    dp[1]++;
                }
            }
            for (int i = 2; i < length; i++) {
                if (charArray[i] != '0') {
                    dp[i] = dp[i - 1];
                }
                int num = 10 * (charArray[i - 1] - '0') + (charArray[i] - '0');
                if (num >= 10 && num <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
            return dp[length - 1];
        }

        public int _numDecodings(String s) {
            // 定义 memo，实现记忆化搜索
            Map<Integer, Integer> memo = new HashMap<>();
            return getAns(s, 0, memo);
        }
        public int getAns(String s, int start, Map<Integer, Integer> memo) {
            // 当到最后一位了，那就没得选就一种解法了。
            if (start == s.length()) {
                return 1;
            }
            // 某个开头是 0 的字符串，从 0 开始 无解。
            if (s.charAt(start) == '0') {
                return 0;
            }
            if (memo.containsKey(start)) {
                return memo.get(start);
            }
            // 取一个数的结果
            int ans1 = getAns(s, start + 1, memo);
            // 取两个数的结果
            int ans2 = 0;
            if (start < s.length() - 1) {
                StringBuilder tmp = new StringBuilder();
                tmp.append(s.charAt(start)).append(s.charAt(start + 1));
                if (Integer.parseInt(tmp.toString()) <= 26) {
                    ans2 = getAns(s, start + 2, memo);
                }
            }
            memo.put(start, ans1 + ans2);
            return ans1 + ans2;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}