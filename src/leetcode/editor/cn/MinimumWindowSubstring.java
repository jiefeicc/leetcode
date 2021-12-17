//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1494 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

//java:最小覆盖子串
class MinimumWindowSubstring{
    public static void main(String[] args){
        Solution solution = new MinimumWindowSubstring().new Solution();
        solution.minWindow("ADOBECODEBANC", "ABC");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static String minWindow(String s, String t) {
            //首先创建的是need数组表示每个字符在t中需要的数量，用ASCII码来保存
            //加入need[76] = 2，表明ASCII码为76的这个字符在目标字符串t中需要两个，如果是负数表明当前字符串在窗口中是多余的，需要过滤掉
            int[] need = new int[128];
            //按照字符串t的内容向need中添加元素
            for (int i = 0; i < t.length(); i++) {
                need[t.charAt(i)]++;
            }
            /*
            l: 滑动窗口左边界
            r: 滑动窗口右边界
            size: 窗口的长度
            count: 当次遍历中还需要几个字符才能够满足包含t中所有字符的条件，最大也就是t的长度
            start: 如果有效更新滑动窗口，记录这个窗口的起始位置，方便后续找子串用
             */
            int l = 0, r = 0, minSize = Integer.MAX_VALUE, count = t.length(), start = 0;
            //循环条件右边界不超过s的长度
            while (r < s.length()) {
                char c = s.charAt(r);
                //表示t中包含当前遍历到的这个c字符，更新目前所需要的count数大小，应该减少一个
                if (need[c] > 0) {
                    count--;
                }
                //无论这个字符是否包含在t中，need[]数组中对应那个字符的计数都减少1，利用正负区分这个字符是多余的还是有用的
                need[c]--;
                //count==0说明当前的窗口已经满足了包含t所需所有字符的条件
                if (count == 0) {
                    //如果左边界这个字符对应的值在need[]数组中小于0，说明他是一个多余元素，不包含在t内
                    while (l < r && need[s.charAt(l)] < 0) {
                        //在need[]数组中维护更新这个值，增加1
                        need[s.charAt(l)]++;
                        //左边界向右移，过滤掉这个元素
                        l++;
                    }
                    //如果当前的这个窗口值比之前维护的窗口值更小，需要进行更新
                    if (r - l + 1 < minSize) {
                        //更新窗口值
                        minSize = r - l + 1;
                        //更新窗口起始位置，方便之后找到这个位置返回结果
                        start = l;
                    }
                    //先将l位置的字符计数重新加1
                    need[s.charAt(l)]++;
                    //重新维护左边界值和当前所需字符的值count
                    l++;
                    count++;
                }
                //右移边界，开始下一次循环
                r++;
            }
            return minSize == Integer.MAX_VALUE ? "" : s.substring(start, start + minSize);
        }

        /*
        滑动窗口解法
        hs 维护s字符串中滑动窗口中各个字符出现的次数
         */
        public String _minWindow(String s, String t) {
            // 维护s字符串中滑动窗口中各个字符出现的次数
            HashMap<Character,Integer> hs = new HashMap<Character,Integer>();
            // 维护t字符串各个字符出现多少次
            HashMap<Character,Integer> ht = new HashMap<Character,Integer>();
            // 遍历t字符串，用ht哈希表记录t字符串各个字符出现的次数。
            for(int i = 0;i < t.length();i ++){
                ht.put(t.charAt(i),ht.getOrDefault(t.charAt(i), 0) + 1);
            }
            String ans = "";
            int minLen = Integer.MAX_VALUE;
            // 有多少个元素符合
            int count = 0;
            for(int i = 0,j = 0;i < s.length();i ++) {
                hs.put(s.charAt(i), hs.getOrDefault(s.charAt(i), 0) + 1);
                if(ht.containsKey(s.charAt(i)) && hs.get(s.charAt(i)) <= ht.get(s.charAt(i))){
                    count ++;
                }
                while(j < i && (!ht.containsKey(s.charAt(j)) || hs.get(s.charAt(j)) > ht.get(s.charAt(j)))) {
                    hs.put(s.charAt(j), hs.get(s.charAt(j)) - 1);
                    j++;
                }
                if(count == t.length() && i - j + 1 < minLen) {
                    minLen = i - j + 1;
                    ans = s.substring(j,i + 1);
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
