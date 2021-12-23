//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 👍 1571 👎 0

package leetcode.editor.cn;
//java:最长有效括号
class LongestValidParentheses{
    public static void main(String[] args){
        Solution solution = new LongestValidParentheses().new Solution();
        solution.longestValidParentheses("((())");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        dp[i] 是以 s[i] 为字符结尾的最长有效子字符串的长度。
        s[i] == '(':
            这时，s[i] 无法和其之前的元素组成有效的括号对，dp[i] = 0
        s[i] == ')':
            s[i - 1] == '('
                即 s[i] 和 s[i - 1] 组成一对有效括号，那么有：dp[i] = dp[i - 2] + 2
                注意，如果是前两个，即 i<2，那么 dp[i] = 2
            s[i - 1] == ')'
                这种情况下，判断前面是否有和s[i]组成有效括号对的字符，即形如((....))。
                即跨过 dp[i - 1] 判断前一个字符：i - dp[i - 1] - 1。
                注意，需要dp[i - 1]是有效字符串才用判断前面的，即dp[i - 1]>0
                s[i - dp[i - 1] - 1] == '(':
                    有效括号长度新增长度2：dp[i] = dp[i - 1] + 2
                        注意，i - dp[i - 1] - 1 和 i 组成了有效括号对，这将是一段独立的有效括号序列 ((....))
                        如果之前的子序列是 (...)(...) 这种序列，那么当前位置的最长有效括号长度还需要加上这一段。所以：
                        dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2
        */
        public int longestValidParentheses(String s) {
            int n = s.length();
            int[] dp = new int[n];//dp是以i处括号结尾的有效括号长度
            int max_len = 0;
            //i从1开始，一个是单括号无效，另一个是防i - 1索引越界
            for(int i = 1; i < n; i++) {
                //遇见右括号才开始判断
                if(s.charAt(i) == ')') {
                    //上一个是左括号
                    if(s.charAt(i - 1) == '(') {
                        if(i < 2) { //开头处
                            dp[i] = 2;
                        } else { //非开头处
                            dp[i] = dp[i - 2] + 2;
                        }
                    }
                    //上一个是右括号
                    else {
                        //pre_left为i处右括号对应左括号下标，推导：(i-1)-dp[i-1]+1 - 1
                        int pre_left = i - dp[i - 1] - 1;
                        //dp[i - 1]是有效字符串 && s[]i - dp[i - 1] - 1 存在
                        if(dp[i - 1] > 0 && pre_left >= 0 && s.charAt(pre_left) == '(') {//左括号存在且为左括号（滑稽）
                            dp[i] = dp[i - 1] + 2;
                            //左括号前还可能存在有效括号
                            if(pre_left - 1 > 0) {
                                dp[i] = dp[i] + dp[pre_left - 1];
                            }
                        }
                    }
                }
                max_len = Math.max(max_len, dp[i]);
            }
            return max_len;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
