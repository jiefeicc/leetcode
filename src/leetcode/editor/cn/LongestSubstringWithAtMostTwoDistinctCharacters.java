package leetcode.editor.cn;

import java.util.*;

class LongestSubstringWithAtMostTwoDistinctCharacters{
    public static void main(String[] args){
        Solution solution = new LongestSubstringWithAtMostTwoDistinctCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("eceba"));
    }
    //至多包含两个不同字符的最长子串
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            滑动窗口解法
            注意：至多包含两个不同字符 && 最长子串，需要注意的是光包含两个不同字符还不行，还需要是最长子串。
            滑动窗口遍历 s，每次 r++, 控制窗口内不要出现三个不同字符，维护 res = max(res, r - l + 1);
            如何控制窗口内不出现三个不同字符：
                维护一个 map<Character, Integer>，每次遍历都往 map中存储（遍历到的字节，该字节出现的频次）。
                当 map.size > 2 时，说明窗口内出现三个不同字符，所以左边界右移，更新map，直到 map.size <= 2。
         */
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int res = 0;
            for (int l = 0, r = 0; r < s.length(); r++) {
                Character c = s.charAt(r);
                map.put(c, map.getOrDefault(c, 0) + 1);
                if (map.size() > 2) {
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
