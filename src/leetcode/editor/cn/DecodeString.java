//给定一个经过编码的字符串，返回它解码后的字符串。
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
//
//
//
// 示例 1：
//
//
//输入：s = "3[a]2[bc]"
//输出："aaabcbc"
//
//
// 示例 2：
//
//
//输入：s = "3[a2[c]]"
//输出："accaccacc"
//
//
// 示例 3：
//
//
//输入：s = "2[abc]3[cd]ef"
//输出："abcabccdcdcdef"
//
//
// 示例 4：
//
//
//输入：s = "abc3[cd]xyz"
//输出："abccdcdcdxyz"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 30
// s 由小写英文字母、数字和方括号 '[]' 组成
// s 保证是一个 有效 的输入。
// s 中所有整数的取值范围为 [1, 300]
//
// Related Topics 栈 递归 字符串 👍 1056 👎 0

package leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//java:字符串解码
class DecodeString{
    public static void main(String[] args){
        Solution solution = new DecodeString().new Solution();
        System.out.println(solution.decodeString("3[a]2[bc]"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        辅助栈解法

        for(char c : s.CharArray())
            碰到左括号:
                kStack.push(k)，resStack.push(res)，k，res归零。
            碰到右括号:
                res = resStack.pop() + res * k(最近的一个左括号入栈的k)
            碰到数字:
                k = c - '0' + k * 10 (连续数字的时候需要处理前面的 * 10)
            碰到字母:
                res = res + c
         */
        public String decodeString(String s) {
            int k = 0;
            StringBuilder res = new StringBuilder();
            Stack<Integer> kStack = new Stack<>();
            Stack<StringBuilder> resStack = new Stack<>();

            for(char c : s.toCharArray()){
                if(c == '['){
                    //碰到括号，记录K和当前res，归零。
                    kStack.push(k);
                    resStack.push(res);
                    k = 0;
                    res = new StringBuilder();
                }else if(c ==']'){
                    //出最近的一个左括号入的k,当前res进行计算不入栈
                    int curk = kStack.pop();
                    StringBuilder temp = new StringBuilder();
                    for(int i = 0; i < curk; i++){
                        temp.append(res);
                    }
                    //与括号外合并
                    res = resStack.pop().append(temp);

                }else if(c >= '0' && c <= '9'){
                    k = c - '0' + k * 10;
                    //如果k是多位数需要x10
                }else{
                    res.append(c);
                    //如果是字母则缓慢添加
                }
            }
            return res.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
