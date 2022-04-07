//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
//
// 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
//
//
//
// 示例 1：
//
//
//输入：num1 = "11", num2 = "123"
//输出："134"
//
//
// 示例 2：
//
//
//输入：num1 = "456", num2 = "77"
//输出："533"
//
//
// 示例 3：
//
//
//输入：num1 = "0", num2 = "0"
//输出："0"
//
//
//
//
//
//
// 提示：
//
//
// 1 <= num1.length, num2.length <= 10⁴
// num1 和num2 都只包含数字 0-9
// num1 和num2 都不包含任何前导零
//
// Related Topics 数学 字符串 模拟 👍 487 👎 0

package leetcode.editor.cn;
//java:字符串相加
class AddStrings{
    public static void main(String[] args){
        Solution solution = new AddStrings().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        两个num数组，从最右侧开始，一个一个的取出数（n1,n2）来加
        一个加到头了，就取0
        维护一个 carry = tmp / 10，表示进位
        int tmp = n1 + n2 + carry;
        StringBuilder res
        res.append(tmp % 10);
        注：最后的进位1需要考虑
        res.reverse()
         */
        public String addStrings(String num1, String num2) {
            StringBuilder res = new StringBuilder();
            int length1 = num1.length() - 1;
            int length2 = num2.length() - 1;
            int carry = 0;
            while(length1 >= 0 || length2 >= 0){
                int n1;
                if (length1 >= 0) {
                    n1 = num1.charAt(length1) - '0';
                } else {
                    n1 = 0;
                }
                int n2;
                if (length2 >= 0) {
                    n2 = num2.charAt(length2) - '0';
                } else {
                    n2 = 0;
                }
                int tmp = n1 + n2 + carry;
                carry = tmp / 10;
                res.append(tmp % 10);
                length1--;
                length2--;
            }
            // 注意：不要忘了最后一个进位
            if(carry == 1) {
                res.append(1);
            }
            return res.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
