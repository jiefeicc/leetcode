//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
// Related Topics 哈希表 字符串 回溯 👍 1846 👎 0

package leetcode.editor.cn;

import java.util.*;

class LetterCombinationsOfAPhoneNumber{
    public static void main(String[] args){
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        组合问题，回溯解法
        构造数字和字母对应的 map
        对 map 的每一个 val 进行拆分组合
         */
        Map<Character, String> map = new HashMap(){
            {
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }
        };
        List<String> res = new ArrayList<>();
        LinkedList<Character> list = new LinkedList<>();
        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return new ArrayList<>();
            }
            backTrack(digits, 0);
            return res;

        }
        public void backTrack(String digits, int index) {
            // 完成结果退出条件
            if (index == digits.length()) {
                res.add(ToString(list));
                return;
            }
            // 遍历做选择
            String s = map.get(digits.charAt(index));
            for (int i = 0; i < s.length(); i++) {
                list.add(s.charAt(i));
                backTrack(digits, index + 1);
                list.removeLast();
            }
        }
        private String ToString(LinkedList<Character> list){
            StringBuilder string = new StringBuilder();
            for (char s : list) {
                string.append(s);
            }
            return string.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
