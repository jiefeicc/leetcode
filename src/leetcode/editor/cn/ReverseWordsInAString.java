package leetcode.editor.cn;

import java.util.*;

class ReverseWordsInAString{
    public static void main(String[] args){
        Solution solution = new ReverseWordsInAString().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        使用 trim() 删除首尾空格
        解题流程：
            1.搜索单词左边首个空格
            2.添加单词
            3.跳过单词间空格
            4.重置j = i，重复1,2,3操作
            5.记得返回值去除尾部空格
         */
        public String reverseWords(String s) {
            // 删除首尾空格
            s = s.trim();
            int j = s.length() - 1, i = j;
            StringBuilder res = new StringBuilder();
            while(i >= 0) {
                // 搜索单词左边首个空格
                while(i >= 0 && s.charAt(i) != ' ') {
                    i--;
                }
                // 添加单词
                res.append(s, i + 1, j + 1).append(" ");
                // 跳过单词间空格
                while(i >= 0 && s.charAt(i) == ' ') {
                    i--;
                }
                // j 指向下个单词的尾字符
                j = i;
            }
            // 记得再去除尾部空格
            return res.substring(0, res.length() - 1);
        }
        /*
        不使用 trim() 删除首尾空格
        解题流程：
            1.去除首尾以及中间多余空格
            2.反转整个字符串
            3.反转各个单词
         */
        public String _reverseWords(String s) {
            // 1.去除首尾以及中间多余空格
            StringBuilder sb = removeSpace(s);
            // 2.反转整个字符串
            reverseString(sb, 0, sb.length() - 1);
            // 3.反转各个单词
            reverseEachWord(sb);
            return sb.toString();
        }
        // 去除首尾以及中间多余空格
        private StringBuilder removeSpace(String s) {
            int start = 0;
            int end = s.length() - 1;
            while (s.charAt(start) == ' ') {
                start++;
            }
            while (s.charAt(end) == ' ') {
                end--;
            }
            StringBuilder sb = new StringBuilder();
            while (start <= end) {
                char c = s.charAt(start);
                if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }
                start++;
            }
            return sb;
        }
        // 反转字符串指定区间[start, end]的字符
        public void reverseString(StringBuilder sb, int start, int end) {
            while (start < end) {
                char temp = sb.charAt(start);
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, temp);
                start++;
                end--;
            }
        }
        // 反转各个单词
        private void reverseEachWord(StringBuilder sb) {
            int start = 0;
            int end = 1;
            int n = sb.length();
            while (start < n) {
                while (end < n && sb.charAt(end) != ' ') {
                    end++;
                }
                reverseString(sb, start, end - 1);
                start = end + 1;
                end = start + 1;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}