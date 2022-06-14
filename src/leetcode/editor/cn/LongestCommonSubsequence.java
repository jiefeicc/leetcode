//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
// Related Topics 字符串 动态规划 👍 805 👎 0

package leetcode.editor.cn;
//java:最长公共子序列
class LongestCommonSubsequence{
    public static void main(String[] args){ 
        Solution solution = new LongestCommonSubsequence().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        状态定义：dp[i][j]：text1[0 ~ (i - 1)] 和 text2[0 ~ (j - 1)]的最长 LCS
        base case : i or j==0, dp[i][j]=0;
        注意：dp数组 对应 text数组，两者下标错了 1 位， dp[0][0] 对应 text1[-1], text2[-1]，意思是 text1[-1], text2[-1]不存在，所以肯定没有 LCS。
        状态转移方程：
            text1[i] = text2[j]  : dp[i][j] = 1 + dp[i - 1][j - 1]
            text1[i] != text2[j] : dp[i][j] = max(dp[i - 1][j],dp[i][j - 1])
         */
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text2 == null) {
                return 0;
            }
            int n = text1.length();
            int m = text2.length();
            int[][] dp = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= m; j++){
                    //dp数组 对应 text数组，两者下标需要错 1 位。dp 从 1 开始，所以 text 从 i - 1, j - 1 开始。
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)){
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    }
                    else {
                        dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
                    }
                }
            }
            return dp[n][m];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
