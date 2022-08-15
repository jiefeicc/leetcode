//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。”
//
//
//
// 示例 1：
//
//
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
//
//
// 示例 2：
//
//
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
//
//
// 示例 3：
//
//
//输入：root = [1,2], p = 1, q = 2
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [2, 10⁵] 内。
// -10⁹ <= Node.val <= 10⁹
// 所有 Node.val 互不相同 。
// p != q
// p 和 q 均存在于给定的二叉树中。
//
// Related Topics 树 深度优先搜索 二叉树 👍 1428 👎 0

package leetcode.editor.cn;
//java:二叉树的最近公共祖先
class LowestCommonAncestorOfABinaryTree{
    public static void main(String[] args){
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        /*
        后序遍历
        明确 lowestCommonAncestor(root, p, q) 函数作用，这个函数有三个功能：
            1.如果 p 和 q 都存在，则返回它们的最近公共祖先；
            2.如果只存在一个，则返回存在的一个；
            3.如果 p 和 q 都不存在，则返回 null
        假定该函数已经具备上述1、2、3的功能，直接对子问题使用该函数，考虑输入输出即可。
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            // p或q是根节点，那根节点就肯定是 p q 的公共祖先
            if (root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // 如果 left 为空，那么p、q肯定都在root.right，返回的 right 就是最近公共祖先。
            if (left == null) {
                return right;
            }
            // 如果 right 为空，那么p、q肯定都在root.left，返回的 left 就是最近公共祖先。
            if (right == null) {
                return left;
            }
            // left 不为空且 right 不为空，那就说明 p、q 在 root.left 和 root.right 一边一个。
            // 所以 root 就是最近公共祖先。
            return root;
        }

        public TreeNode _lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            // 如果p,q为根节点，则公共祖先为根节点
            if (root.val == p.val || root.val == q.val) {
                return root;
            }
            // 如果p,q在左子树，则公共祖先在左子树查找
            if (find(root.left, p) && find(root.left, q)) {
                return _lowestCommonAncestor(root.left, p, q);
            }
            // 如果p,q在右子树，则公共祖先在右子树查找
            if (find(root.right, p) && find(root.right, q)) {
                return _lowestCommonAncestor(root.right, p, q);
            }
            // 如果p,q分属两侧，则公共祖先为根节点
            return root;
        }
        private boolean find(TreeNode root, TreeNode c) {
            if (root == null) {
                return false;
            }
            if (root == c) {
                return true;
            }
            return find(root.left, c) || find(root.right, c);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
