package leetcode.editor.cn;

import java.util.*;

class FractionToRecurringDecimal{
    public static void main(String[] args){
        Solution solution = new FractionToRecurringDecimal().new Solution();
        System.out.println(        solution.fractionToDecimal(-50, 8));
    }
    // 分数到小数
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        数学模拟类问题，模拟除法
        解题步骤：
            先转 long，-> a,b
            StringBuffer res;
            考虑正负
                if (a * b < 0) res.append("-")
            a = abs(a), b = abs(b)
            res = a/b
            a = a % b
            需要考虑解决无限循环的问题
            所以需要记录每一个余数和余数所在的位置，不停往后求余
            当出现余数和前面相同时，将这两个余数间的数括号括起来
         */
        public String fractionToDecimal(int numerator, int denominator) {
            long a = numerator;
            long b = denominator;
            StringBuffer res = new StringBuffer();
            if (a % b == 0) {
                return String.valueOf(a / b);
            }
            if (a * b < 0) {
                res.append("-");
            }
            a = Math.abs(a);
            b = Math.abs(b);
            res.append(a / b).append(".");
            a %= b;
            Map<Long, Integer> map = new HashMap<>();
            while (a != 0) {
                map.put(a, res.length());
                a *= 10;
                res.append(a / b);
                a %= b;
                if (map.containsKey(a)) {
                    int index = map.get(a);
                    return res.substring(0, index) + "(" + res.substring(index ) + ")";
                }
            }
            return res.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}