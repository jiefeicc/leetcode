package leetcode.editor.cn;

import java.util.*;

class StringToIntegerAtoi{
    public static void main(String[] args){
        Solution solution = new StringToIntegerAtoi().new Solution();
    }
    //字符串转换整数 (atoi)
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            1.去除前面的空格
            2.去除前面出现的 + - 号，根据最后一个符号决定正负。
            3.只有 0-9 的字符才有效，出现别的就退出。
                判断溢出时，为了防止溢出，所以不能用 ans * 10 + digit
                要用 ans > (Integer.MAX_VALUE - digit) / 10
         */
        public int myAtoi(String s) {
            int flag = 1;
            int ans = 0;
            int index = 0;
            char[] array = s.toCharArray();
            // 去除前面的空格
            while (index < array.length && array[index] == ' ') {
                index ++;
            }
            // 去除前面所以的 + - 号，根据最后一个符号决定正负。
            if (index < array.length && (array[index] == '-' || array[index] == '+')) {
                flag = array[index] == '-' ? -1 : 1;
                index++;
            }
            // 只有 0-9 的字符才有效，出现别的就退出。
            while (index < array.length && array[index] <= '9' && array[index] >= '0') {
                int digit = array[index] - '0';
                // 判断溢出时，为了防止溢出，所以不能用 ans * 10 + digit
                // ans * 10 + digit > Integer.MAX_VALUE
                if (ans > (Integer.MAX_VALUE - digit) / 10) {
                    return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                ans = ans * 10 + digit;
                index++;
            }
            return ans * flag;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
