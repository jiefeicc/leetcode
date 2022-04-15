package leetcode.editor.cn;

import java.util.*;

class BasicCalculator{
    public static void main(String[] args){
        Solution solution = new BasicCalculator().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        双栈解法：通用解法，解决 模拟计算器相关问题，需要提前构造好运算符优先级
        解题流程
            明确各个运算符对应的优先级，构造 map
            正则去除空格和把 (- 转换成 (0-
            开头补 0，防止开头就是负号
            遍历 s.toCharArray()
                遇到 ( ，入 opts 栈
                遇到数字，将 i 后面的数字都读取组合再入 nums 栈
                遇到运算符，把栈内可以算的都算了(当 栈内运算符优先级 >= 当前运算符优先级 时)
                遇到 ')'，做运算( 当 opt 栈顶有数据且不为 ( 时)
            s.toCharArray() 遍历完了，opts 中元素还没全部出栈，还需要再做计算
         */
        Map<Character, Integer> map = new HashMap() {
            {
                put('+', 1);
                put('-', 1);
                put('*', 2);
                put('/', 2);
                put('%', 2);
                put('^', 3);
            }
        };
        private void calculate(LinkedList<Integer> nums, LinkedList<Character> opts) {
            // opts 不为空时，nums 里面肯定至少有两个数据
            int a = nums.pop();
            int b = nums.pop();
            int ans = 0;
            char opt = opts.pop();
            if (opt == '+') ans = b + a;
            else if (opt == '-') ans = b - a;
            else if (opt == '*') ans = b * a;
            else if (opt == '/')  ans = b / a;
            else if (opt == '^') ans = (int)Math.pow(b, a);
            else if (opt == '%') ans = b % a;
            nums.push(ans);
        }
        public int _calculate(String s) {
            // 去除空格，并且把 5 * (-2 + 4) -> 5 * (0 - 2 + 4)
            s = s.replaceAll(" ", "").replaceAll("\\(-", "(0-");
            char[] chs = s.toCharArray();
            int length = chs.length;
            // 存放数字的栈
            LinkedList<Integer> nums = new LinkedList<>();
            // 开头补 0，防止开头就是负号
            nums.push(0);
            // 存放符号的栈
            LinkedList<Character> opts = new LinkedList<>();
            for (int i = 0; i < length; i++) {
                char ch = chs[i];
                // 处理 '('
                if (ch == '(') {
                    opts.push(ch);
                }
                // 处理 ')'
                else if (ch == ')') {
                    while (!opts.isEmpty()) {
                        // 当遇到 ')' 且 opt 栈顶有数据且不为 '('，做计算
                        if (opts.peek() != '(') {
                            calculate(nums, opts);
                        } else {
                            // 当遇到 ')' 且 opt 栈顶有数据且是 '('，出栈
                            opts.pop();
                            break;
                        }
                    }
                }
                // 处理数字
                else if (Character.isDigit(ch)) {
                    int num = 0;
                    // 将 i 后面的数字都读取组合再入栈
                    while (i < length && Character.isDigit(chs[i])) {
                        num = num * 10 + (chs[i++] - '0');
                    }
                    nums.push(num);
                    // i已经到符号位了，需要回退一格
                    i--;
                }
                // 处理计算符号
                else {
                    // 新计算符号要入栈时，先把栈内可以算的都算了
                    // 当 栈内运算符优先级 >= 当前运算符优先级 时，才运算
                    while (!opts.isEmpty() && opts.peek() != '(' && map.get(opts.peek()) >= map.get(ch)) {
                        calculate(nums, opts);
                    }
                    opts.push(ch);
                }
            }
            // s 串遍历完了，opts 中元素还没全部出栈，还需要再做计算
            while (!opts.isEmpty()) {
                calculate(nums, opts);
            }
            return nums.pop();
        }
        public int calculate(String s) {
            // 去除空格，并且把 5 * (-2 + 4) -> 5 * (0 - 2 + 4)
            s = s.replaceAll(" ", "").replaceAll("\\(-", "(0-");
            char[] chs = s.toCharArray();
            int length = chs.length;
            // 存放数字的栈
            LinkedList<Integer> nums = new LinkedList<>();
            // 开头补 0，防止开头就是负号
            nums.push(0);
            // 存放符号的栈
            LinkedList<Character> opts = new LinkedList<>();
            for (int i = 0; i < length; i++) {
                char ch = chs[i];
                // 处理 '('
                if (ch == '(') {
                    opts.push(ch);
                }
                // 处理 ')'
                else if (ch == ')') {
                    while (!opts.isEmpty()) {
                        // 当遇到 ')' 且 opt 栈顶有数据且不为 '('，做计算
                        if (opts.peek() != '(') {
                            calculate(nums, opts);
                        } else {
                            // 当遇到 ')' 且 opt 栈顶有数据且是 '('，出栈
                            opts.pop();
                            break;
                        }
                    }
                }
                // 处理数字
                else if (Character.isDigit(ch)) {
                    int num = 0;
                    // 将 i 后面的数字都读取组合再入栈
                    while (i < length && Character.isDigit(chs[i])) {
                        num = num * 10 + (chs[i++] - '0');
                    }
                    nums.push(num);
                    // i已经到符号位了，需要回退一格
                    i--;
                }
                // 处理计算符号
                else {
                    // 新计算符号要入栈时，先把栈内可以算的都算了
                    // 当 栈内运算符优先级 >= 当前运算符优先级 时，才运算
                    while (!opts.isEmpty() && opts.peek() != '(') {
                        calculate(nums, opts);
                    }
                    opts.push(ch);
                }
            }
            // s 串遍历完了，opts 中元素还没全部出栈，还需要再做计算
            while (!opts.isEmpty()) {
                calculate(nums, opts);
            }
            return nums.pop();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}