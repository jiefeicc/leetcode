//给定一个二叉树，检查它是否是镜像对称的。
//
//
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
//
//
//
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
//
//
//
//
// 进阶：
//
// 你可以运用递归和迭代两种方法解决这个问题吗？
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1676 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;

//java:对称二叉树
class SymmetricTree{
    public static void main(String[] args){
        Solution solution = new SymmetricTree().new Solution();
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
        递归解法
        根节点左子树 ‘相当’ 根节点右子树
            根节点左子树的左子树值 = 根节点右子树的右子树值
            根节点左子树的右子树值 = 根节点右子树的左子树值
        递归出口：
            left == null && right == null true
            left == null || right == null false
            left.val != right.val false
         */
        public boolean _isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return dfs(root.left, root.right);
        }
        private boolean dfs(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            return dfs(left.left,right.right) && dfs(left.right, right.left);
        }
        // 迭代解法
        public boolean isSymmetric(TreeNode root) {
            if(root==null) {
                return true;
            }
            //用队列保存节点
            LinkedList<TreeNode> list = new LinkedList<>();
            //将根节点的左右孩子放到队列中
            list.add(root.left);
            list.add(root.right);
            while(list.size()>0) {
                //从队列中取出两个节点，再比较这两个节点
                TreeNode left = list.removeFirst();
                TreeNode right = list.removeFirst();
                //如果两个节点都为空就继续循环，两者有一个为空就返回false
                if(left==null && right==null) {
                    continue;
                }
                if(left==null || right==null) {
                    return false;
                }
                if(left.val!=right.val) {
                    return false;
                }
                //将左节点的左孩子， 右节点的右孩子放入队列
                list.add(left.left);
                list.add(right.right);
                //将左节点的右孩子，右节点的左孩子放入队列
                list.add(left.right);
                list.add(right.left);
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
