package leetcode.editor.cn;

import java.util.*;

class BinaryTreeLevelOrderTraversal{
    public static void main(String[] args){
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
    }
    //二叉树的层序遍历
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if(root == null) {
                return res;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                List<Integer> temp = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.removeFirst();
                    if(node.left != null) {
                        queue.addLast(node.left);
                    }
                    if(node.right != null) {
                        queue.addLast(node.right);
                    }
                    temp.add(node.val);
                }
                res.add(temp);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
