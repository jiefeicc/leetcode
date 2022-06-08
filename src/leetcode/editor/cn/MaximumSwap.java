package leetcode.editor.cn;

class MaximumSwap{
    public static void main(String[] args){
        Solution solution = new MaximumSwap().new Solution();
        System.out.println(solution.maximumSwap(2736));
    }
    // 最大交换
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            int[] maxIndexArr 存储当前到最后面位置，最大数的索引。
            倒着遍历 char[] numChars，得出 maxIndexArr
            再正着遍历 numChars，哪一个数 不等于 numChars[maxIndexArr[i]]，就交换这两个数。
         */
        public int maximumSwap(int num) {
            char[] numChars = String.valueOf(num).toCharArray();
            int length = numChars.length;
            // 保存当前到最后面位置，最大数的索引。
            int[] maxIndexArr = new int[length];
            int maxIndex = length - 1;
            for (int i = length - 1; i >= 0; i--) {
                // 条件满足时，更新最大数索引。
                if (numChars[i] > numChars[maxIndex]) {
                    maxIndex = i;
                }
                maxIndexArr[i] = maxIndex;
            }
            for (int i = 0; i < length; i++) {
                if (numChars[i] != numChars[maxIndexArr[i]]) {
                    swap(numChars, i, maxIndexArr[i]);
                    break;
                }
            }
            return Integer.parseInt(new String(numChars));
        }
        private void swap(char[] numChars, int i, int j) {
            char temp = numChars[i];
            numChars[i] = numChars[j];
            numChars[j] = temp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}