//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
//
//
//
// 示例 1:
//
//
//输入: num1 = "2", num2 = "3"
//输出: "6"
//
// 示例 2:
//
//
//输入: num1 = "123", num2 = "456"
//输出: "56088"
//
//
//
// 提示：
//
//
// 1 <= num1.length, num2.length <= 200
// num1 和 num2 只能由数字组成。
// num1 和 num2 都不包含任何前导零，除了数字0本身。
//
// Related Topics 数学 字符串 模拟 👍 895 👎 0

package leetcode.editor.cn;

import java.util.*;

class MultiplyStrings{
    public static void main(String[] args){
        Solution solution = new MultiplyStrings().new Solution();
        solution.multiply("1234", "567");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        模拟过程：
            遍历 num2 每一位与 num1 进行相乘，将每一步的结果进行累加。
            注意：
                num2 除了第一位，其他位与 num1 运算的结果需要补0
                计算字符串数字累加其实就是 415. 字符串相加
         */
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            String res = "0";

            int length1 = num1.length();
            int length2 = num2.length();

            // num2 逐位与 num1 相乘
            for (int i = length2 - 1; i >= 0; i--) {
                // 保存 num2[i] 与 num1 相乘的结果
                StringBuilder num2i_multiply_num1 = new StringBuilder();

                // 根据 num2[i] 的位置，在 num2i_multiply_num1 前面补 number_0 个 0
                int number_0 = (length2 - 1) - i;
                while (number_0 != 0) {
                    num2i_multiply_num1.append(0);
                    number_0--;
                }

                // num1 逐位 与 num2 的第 i 位数字 n2 相乘
                int n2 = num2.charAt(i) - '0';
                int carry = 0;
                for (int j = length1 - 1; j >= 0; j--) {
                    int n1 = num1.charAt(j) - '0';
                    int tmp = (n1 * n2) + carry;
                    num2i_multiply_num1.append(tmp % 10);
                    carry = tmp / 10;
                }
                // 注意：不要忘了最后一个进位
                if (carry != 0) {
                    num2i_multiply_num1.append(carry);
                }
                // 将当前结果与新计算的结果求和作为新的结果
                res = addStrings(res, num2i_multiply_num1.reverse().toString());
            }
            return res;
        }

        // 对两个字符串数字进行相加，返回字符串形式的和
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
