//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。 
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
// 
//
// 示例 2： 
//
// 
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中的所有字符串 互不相同 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1446 👎 0

package leetcode.editor.cn;

import java.util.*;

//java:单词拆分
class WordBreak{
    public static void main(String[] args){
        Solution solution = new WordBreak().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /*
        dp解法：类似完全背包，从一个集合中没有限制的取东西，组合成目标对象

        状态定义：dp[i] = true/false ， 以 s[i] 结尾的子字符串是否符合题意。
        初始状态：dp[i] = wordSet.contains(s.substring(0, i + 1)) ? true:false
        状态转移：
                for (int r=0; r<len; r++) {
                    for (int l = r-1; l>=0; l--)
                        if (wordSet.contains(s.substring(l+1, r+1)) && dp[l])
                            dp[r] = true;
                            break;
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            int len = s.length();

            // 状态定义：以 s[i] 结尾的子字符串是否符合题意
            boolean[] dp = new boolean[len];
            // base case
            for (int i = 0; i < len; i++) {
                //（substring 右端点不包含，所以是 right + 1）
                if (wordSet.contains(s.substring(0, i + 1))) {
                    dp[i] = true;
                }
            }
            for (int r = 0; r < len; r++) {
                for (int l = r - 1; l >= 0; l--) {
                    if (wordSet.contains(s.substring(l + 1, r + 1)) && dp[l]) {
                        dp[r] = true;
                        // 一旦得到 dp[right] = True ，break
                        // 若是继续循环，可能会导致 dp[r] = false
                        break;
                    }
                }
            }
            return dp[len - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}