package leetcode.editor.cn;

import java.util.*;

class InvertBinaryTree{
    public static void main(String[] args){
        Solution solution = new InvertBinaryTree().new Solution();
    }
    //翻转二叉树
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        /*
        解题思路:
            递归解法
            定义 invertTree(root) 为将 root 翻转的函数
            对于递归思想，直接把 invertTree 拿来用，只需要考虑输入输出即可。
            解题步骤：
                当输入的 root = null，直接返回 null 即可。
                接下来把 root 的左右子树交换一下，但是左右子树的各自子树还没有翻转。
                所以后面分别对 root 的左右子树进行 invertTree 处理。
         */
        public TreeNode _invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            TreeNode temp;
            temp = root.left;
            root.left = root.right;
            root.right = temp;

            _invertTree(root.left);
            _invertTree(root.right);

            return root;
        }

        /*
        迭代解法：
            运用 BFS，遍历二叉树节点。
            每遍历一个节点，将该节点的左右子树交换。
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            LinkedList<TreeNode> queue = new LinkedList<>();
            TreeNode temp;
            queue.addLast(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.removeFirst();

                temp = node.left;
                node.left = node.right;
                node.right = temp;

                if (node.left != null) {
                    queue.addLast(node.left);
                }

                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
