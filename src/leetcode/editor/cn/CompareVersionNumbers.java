package leetcode.editor.cn;

import java.util.*;

class CompareVersionNumbers{
    public static void main(String[] args){
        Solution solution = new CompareVersionNumbers().new Solution();
        solution.compareVersion("1.0.1", "1");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        双指针解法：
            两个指针，一个指向 version1，一个指向 version2
            以 '.' 为一次界限，每次取出两个 '.' 中间的数，计算出值，比较大小
         */
        public int compareVersion(String version1, String version2) {
            int l1 = version1.length();
            int l2 = version2.length();
            for (int i = 0, j = 0; i < l1 || j < l2; i++, j++) {
                int num1 = 0;
                while (i < l1 && version1.charAt(i) != '.') {
                    num1 += 10 * num1 + (version1.charAt(i) - '0');
                    i++;
                }
                int num2= 0;
                while (j < l2 && version2.charAt(j) != '.') {
                    num2 += 10 * num2 + (version2.charAt(j) - '0');
                    j++;
                }
                if (num1 > num2) {
                    return 1;
                } else if (num1 < num2) {
                    return -1;
                }
            }
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}