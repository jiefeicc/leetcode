//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4500 👎 0

package leetcode.editor.cn;
//java:最长回文子串
class LongestPalindromicSubstring{
    public static void main(String[] args){
        Solution solution = new LongestPalindromicSubstring().new Solution();
        solution.longestPalindrome("babad");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /*
        动态规划
        dp[i,j]=true/false：以i,j为首尾的字符串是否为回文子串
        dp[i,i] = true
        状态转移方程：
            charArray[i] == charArray[j]
                dp[i][j] = dp[i+1][j-1]
                注：当s[i,s] 去掉头尾两个字符后的区间长度小于2，dp[i][j]肯定是true，即(i+1) - (j-1) + 1 < 2
         */
        public String _longestPalindrome(String s) {
            int len = s.length();
            if (len < 2) {
                return s;
            }
            boolean[][] dp = new boolean[len][len];
            char[] charArray = s.toCharArray();
            int max = 1;
            int begin = 0;
            //单字符，i=j时, charArray[i] == charArray[j]
            for (int j=0; j<len; j++) {
                for (int i=0; i<=j; i++) {
                    if (charArray[i] == charArray[j]) {
                        // s[i,s] 去掉头尾两个字符，s[i+1, j-1]的区间长度小于2，dp[i][j]肯定是true
                        // (i+1) - (j-1) + 1 < 2;
                        if (j-i < 3) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i+1][j-1];
                        }
                    }
                    if (dp[i][j] && max < j-i+1) {
                        max = j-i+1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin, begin+max);
        }
        // 中心扩散法
        public String longestPalindrome(String s) {
            int len = s.length();
            if (len < 2) {
                return s;
            }
            int left;
            int right;
            int begin = 0;
            int max = 1;
            for (int i = 0; i < len; i++) {
                left = i;
                right = i;
                // 中心可能为多个一样的字符
                // 往左寻找中心
                while (left > 0 && s.charAt(left-1) == s.charAt(i)) {
                    left--;
                }
                // 往右寻找中心
                while (right < len-1 && s.charAt(right+1) == s.charAt(i)) {
                    right++;
                }
                // 进行中心扩散
                while (left > 0 && right < len-1 && s.charAt(right+1) == s.charAt(left-1)) {
                    left--;
                    right++;
                }
                if (right-left+1 > max) {
                    max = right-left+1;
                    begin = left;
                }
            }
            return s.substring(begin, begin + max);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}