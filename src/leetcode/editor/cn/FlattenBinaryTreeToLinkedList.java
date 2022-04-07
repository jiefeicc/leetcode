package leetcode.editor.cn;

import java.util.*;

class FlattenBinaryTreeToLinkedList{
    public static void main(String[] args){
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
    }
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
        迭代处理过程：
            1.将右子树替换成左子树
            2.将原来的右子树拼接到新树的最右边节点的右子树上
            3.重复上述操作，直到没有左子树
        递归处理过程：
            注意：不要尝试理解和实现递归函数具体逻辑，假设函数已经具备设定的功能，考虑输入输出即可。
                 假设 flatten(root) 是将 root 转换成链表
            代码流程：
                1.递归退出条件(也可以理解输入条件的校验)
                2.flatten(root.left) 将左子树转换成链表（先处理右子树也可以）
                3.flatten(root.right) 将右子树转换成链表（后处理左子树也可以）
                4.拼接两个链表
         */
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            flatten(root.left);
            flatten(root.right);
            TreeNode temp = root.right;
            root.right = root.left;
            // 需要将原来的左子树置为空
            root.left = null;
            while (root.right != null) {
                root = root.right;
            }
            root.right = temp;
        }
        // 迭代解法
        public void _flatten(TreeNode root) {
            while (root != null) {
                //左子树为 null，直接考虑下一个节点
                if (root.left == null) {
                    root = root.right;
                } else {
                    // 找左子树最右边的节点
                    TreeNode pre = root.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    //将原来的右子树接到左子树的最右边节点
                    pre.right = root.right;
                    // 将左子树插入到右子树的地方
                    root.right = root.left;
                    root.left = null;
                    // 考虑下一个节点
                    root = root.right;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}