//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
//
//
//
// 示例 1：
//
//
//输入：x = 4
//输出：2
//
//
// 示例 2：
//
//
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
//
//
//
//
// 提示：
//
//
// 0 <= x <= 2³¹ - 1
//
// Related Topics 数学 二分查找 👍 843 👎 0

package leetcode.editor.cn;

import java.text.DecimalFormat;

//java:Sqrt(x)
class Sqrtx{
    public static void main(String[] args){
        Solution solution = new Sqrtx().new Solution();
        solution._mySqrt(8);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 二分法
        public int mySqrt(int x) {
            if (x==0 || x==1) {
                return x;
            }
            long left = 0;
            long right = x;
            while (left <= right) {
                long mid = (right + left ) / 2;
                long tmp = mid*mid;
                if (tmp > x) {
                    right = mid - 1;
                } else if (tmp < x){
                    left = mid+1;
                } else {
                    return (int)mid;
                }
            }

            // mid = (right + left )/2，退出while条件是 left > right,
            // 所以最后返回 left - 1
            return (int)left - 1;
        }

        // 牛顿法，可以自己控制保留几位小数
        int s;
        public int _mySqrt(int x) {
            s=x;
            if(x==0) {
                return 0;
            }
            double res = sqrts(x);
            // 控制保留几位有效数字
            DecimalFormat df = new DecimalFormat("###.000"); //保留三位有效数字（四舍五入）
            return (int)res;
        }
        public double sqrts(double x){
            double res = (x + s / x) / 2;
            if (res == x) {
                return x;
            } else {
                return sqrts(res);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

