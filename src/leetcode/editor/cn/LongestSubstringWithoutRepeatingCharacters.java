//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 10⁴
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 字符串 滑动窗口 👍 5970 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:无重复字符的最长子串
class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        solution.lengthOfLongestSubstring("abbba");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        abcabcbb
        滑动窗口

        低效率：使用s.contain来判断，用s的left,right截取来维持滑动窗口
        left,right维持一个窗口，max维护最大值
        right每次往前移动，然后拿出最新的那个字符c
        当最新的字符c包含在窗口里面，那就移动left直到窗口不包含c
        !s(left,right).contain(c) {
            max = Math.max(s(left,right+1).len,max)
        } else {
            while(!s(left,right).contain(c)) {
                left++;
            }
        }

        高效率：使用map.contain来判断，使用left,right截取来维持滑动窗口，用map记录s.charAt(index),index
        right每次往前移动，然后拿出最新的那个字符c
        当最新的字符c包含在窗口里面，那就从map里面取出c的位置，left = index(c)+1，注意：c的位置可能在left的左边，所以要判断一下大小(abba)
        再放入最新的c，然后计算max
         */
        public int lengthOfLongestSubstring(String s) {
            int max = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int left = 0,right = 0; right < s.length(); right++) {
                char c = s.charAt(right);
                if (map.containsKey(c)) {
                    // abba  c的位置可能在left的左边，所以要判断一下大小
                    left = Math.max(map.get(c)+1, left);
                }
                // put之后会把原来的值覆盖了，map里面永远只有一个c
                map.put(c, right);
                max = Math.max(max, right-left+1);
            }
            return max;
        }

        public int lengthOfLongestSubstring1(String s) {
            int max = 0;
            for (int left = 0,right = 0; right < s.length(); right++) {
                String c = s.substring(right, right + 1);
                String String = s.substring(left, right);
                if (!String.contains(c)) {
                    max = Math.max(String.length() + 1, max);
                } else {
                    while (String.contains(c)) {
                        left++;
                        String = s.substring(left, right);
                    }
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
