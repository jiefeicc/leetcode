package leetcode.editor.cn;

import java.util.*;

class RemoveAllAdjacentDuplicatesInString{
    public static void main(String[] args){
        Solution solution = new RemoveAllAdjacentDuplicatesInString().new Solution();
    }
    //删除字符串中的所有相邻重复项
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：
            辅助栈解法
            遍历字符串
                当前字符和栈顶相同那就出栈（栈不为空）
                不相同那就把当前字符入栈
            最后把栈按照栈底到栈顶 toString 即可
         */
        public String removeDuplicates(String s) {
            LinkedList<Character> stack = new LinkedList<>();
            for (char c : s.toCharArray()) {
                if (!stack.isEmpty() && stack.peekFirst().equals(c)) {
                    stack.removeFirst();
                } else {
                    stack.addFirst(c);
                }
            }
            StringBuilder res = new StringBuilder();
            while (!stack.isEmpty()) {
                res.append(stack.removeLast());
            }
            return res.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
