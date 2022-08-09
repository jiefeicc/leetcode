package leetcode.editor.cn;

import java.util.*;

class LongestSubstringWithAtMostKDistinctCharacters{
    public static void main(String[] args){
        Solution solution = new LongestSubstringWithAtMostKDistinctCharacters().new Solution();
    }
    //至多包含 K 个不同字符的最长子串
    /*
    给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。

    输入: s = "eceba", k = 2
    输出: 3
    解释: 则 T 为 "ece"，所以长度为 3。

    输入: s = "aa", k = 1
    输出: 2
    解释: 则 T 为 "aa"，所以长度为 2。
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            滑动窗口解法
            注意：至多包含k个不同字符 && 最长子串，需要注意的是光包含k个不同字符还不行，还需要是最长子串。
            滑动窗口遍历 s，每次 r++, 控制窗口内不要出现k个不同字符，维护 res = max(res, r - l + 1);
            如何控制窗口内不超过k个不同字符：
                维护一个 map<Character, Integer>，每次遍历都往 map中存储（遍历到的字节，该字节出现的频次）。
                当 map.size > k 时，说明窗口内出现k个不同字符，所以左边界右移，更新map，直到 map.size <= k。
         */
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            Map<Character, Integer> map = new HashMap<>();
            int res = 0;
            for (int l = 0, r = 0; r < s.length(); r++) {
                Character c = s.charAt(r);
                map.put(c, map.getOrDefault(c, 0) + 1);
                if (map.size() > k) {
                    char leftChar = s.charAt(l);
                    int leftCharSize = map.get(leftChar);
                    l++;
                    leftCharSize--;
                    if (leftCharSize == 0) {
                        map.remove(leftChar);
                    } else {
                        map.put(leftChar, leftCharSize);
                    }
                }
                res = Math.max(res, r - l + 1);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
