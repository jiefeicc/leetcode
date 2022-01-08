//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
//
//
// 示例 :
//给定二叉树
//
//           1
//         / \
//        2   3
//       / \
//      4   5
//
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
//
//
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。
// Related Topics 树 深度优先搜索 二叉树 👍 873 👎 0

package leetcode.editor.cn;
//java:二叉树的直径
class DiameterOfBinaryTree{
    public static void main(String[] args){
        Solution solution = new DiameterOfBinaryTree().new Solution();
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
        直径 ！= 左子树深度 + 右子树深度
        直径 = 最大的某个节点的左子树深度 + 右子树深度
        思路：后序遍历树的节点，维护一个全局变量max
             求得每个节点的直径，更新max
         */
        int max;
        public int diameterOfBinaryTree(TreeNode root) {
            traverse(root);
            return max;
        }
        // 返回树的深度
        int traverse(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = traverse(root.left); // 左子树的深度
            int right = traverse(root.right); // 右子树的深度
            // 直接访问全局变量
            max = Math.max(max, left + right);
            return 1 + Math.max(left, right);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
