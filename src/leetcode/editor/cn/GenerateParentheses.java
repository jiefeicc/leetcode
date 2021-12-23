//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
// Related Topics 字符串 动态规划 回溯 👍 2229 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//java:括号生成
class GenerateParentheses{
    public static void main(String[] args){
        Solution solution = new GenerateParentheses().new Solution();
        List<String> list = solution.generateParenthesis(3);
        System.out.println(list);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        类似回溯
        dfs生成所有组合
        每次进入dfs判断确定不符合的就直接return :（left > n || right > left）
        经过剪枝后，到达叶子节点，那就加入结果集
         */
        List<String> res = new ArrayList<>();
        int n;
        public List<String> _generateParenthesis(int n) {
            if (n == 0) {
                return res;
            }
            this.n = n;
            dfs("", 0, 0);
            return res;
        }
        private void dfs(String curStr, int left, int right) {
            // 剪枝
            if (left > n || right > left) {
                return;
            }
            // 到达叶子节点加入结果集
            if (left + right == n*2) {
                res.add(curStr);
                return;
            }
            dfs(curStr + "(", left+1, right);
            dfs(curStr + ")", left, right+1);
        }

        // 广度优先遍历
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(new Node("", n, n));
            while (!queue.isEmpty()) {
                Node curNode = queue.poll();
                if (curNode.left == 0 && curNode.right == 0) {
                    res.add(curNode.res);
                }
                if (curNode.left > 0) {
                    queue.add(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
                }
                if (curNode.right > 0 && curNode.left < curNode.right) {
                    queue.add(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
                }
            }
            return res;
        }
        class Node {
            // 当前得到的字符串
            private String res;
            // 剩余左括号数量
            private int left;
            // 剩余右括号数量
            private int right;
            public Node(String str, int left, int right) {
                this.res = str;
                this.left = left;
                this.right = right;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
