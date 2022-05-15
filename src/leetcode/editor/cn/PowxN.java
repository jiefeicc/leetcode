package leetcode.editor.cn;

import java.util.*;

class PowxN{
    public static void main(String[] args){
        Solution solution = new PowxN().new Solution();
    }
    //Pow(x, n)
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        分治法：
            当n是偶数的时候，对n进行分治，拆解为x*x的n/2的次方。
            当n为奇数的时候拆分成x * myPow(x,n-1)
            注：当n是负数或者是0的特殊情况。
         */
        public double myPow(double x, int n) {
            if (x == 1 || n == 0) {
                return 1;
            }
            long a = n;
            return a >= 0 ? pow(x, a) : 1 / pow(x, -a);
        }
        public double  pow(double  x, long y) {
            if (y == 0) {
                return 1;
            }
            double ret = pow(x, y / 2);
            if (y % 2 == 0) {
                return ret * ret;
            } else {
                return ret * ret * x;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
