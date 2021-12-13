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
        lowestCommonAncestor(root, p, q) 抽象理解为在root中找p或q的祖先
        如果 left 为空不是 p或q的祖先，那就返回 right
        如果 right 为空不是 p或q的祖先，那就返回 left
        如果 left 不为空且 right 不为空，那就说明 root 肯定是 p q 的公共祖先
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            // p或q是根节点，那根节点就肯定是 p q 的公共祖先
            if (root == p || root == q) {
                return root;
            }
            // 在root.left中找p或q的祖先
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            // 在root.right中找p或q的祖先
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // 如果 left 为空不是 p或q的祖先，那 right 肯定是 p q 的祖先
            if (left == null) {
                return right;
            }
            // 如果 right 为空不是 p或q的祖先，那 left 肯定是 p q 的祖先
            if (right == null) {
                return left;
            }
            // left 不为空且 right 不为空，那就说明 root 肯定是 p q 的公共祖先
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
