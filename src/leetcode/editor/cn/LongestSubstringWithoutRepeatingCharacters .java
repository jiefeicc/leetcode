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
class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args){
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        solution.lengthOfLongestSubstring("abba");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 滑动窗口
         * 两个指针，left,right构成滑动窗口
         * 用一个map(字节, 字节所在位置)来映射字节和他的位置
         * max维护最大长度
         * right向右移动，往map里面添字节，判断是否包含
         * 包含那就left移动到不包含的位置，不包含就...
         * 添加字节，更新max
         */
        public int lengthOfLongestSubstring(String s) {
            int max = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int left = 0, right = 0; right < s.length(); right++) {
                char c = s.charAt(right);
                if (map.containsKey(c)) {
                    // left = map.get(c)+1;
                    // 由于map没有移除元素，所以c虽然是之前出现过的，但是重复的不在滑动窗口中，在left左边，
                    // 重新获取left位置之后可能会使left向左移了
                    // 例如“abba”,到最后一个a时，把left的位置放在第一个b的位置了
                    // 所以和当前left比大小，小了说明重的那个元素不在滑动窗口里面，就不需要移动滑动窗口
                    left = Math.max(map.get(c)+1, left);
                }
                max = Math.max(max, right - left + 1);
                map.put(c, right);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
