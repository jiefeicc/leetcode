//根据一棵树的中序遍历与后序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 636 👎 0

package leetcode.editor.cn;
//java:从中序与后序遍历序列构造二叉树
class ConstructBinaryTreeFromInorderAndPostorderTraversal{
    public static void main(String[] args){
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
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
        递归法实现
        与（从前序与中序遍历序列构造二叉树）相比
        后序遍历和前序遍历相反，根节点对应的值为 postorder 的最后⼀个元素。
         */
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            int inLen = inorder.length;
            int postLen = postorder.length;;
            return build(inorder, 0, inLen-1, postorder, 0, postLen-1);
        }
        TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
            if (inStart > inEnd) {
                return null;
            }
            // root 节点对应的值就是后序遍历数组的最后⼀个元素
            int rootVal = postorder[postEnd];
            // rootVal 在中序遍历数组中的索引
            int index = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == rootVal) {
                    index = i;
                    break;
                }
            }
            // 左⼦树的节点个数
            int leftSize = index - inStart;
            TreeNode root = new TreeNode(rootVal);
            // 递归构造左右⼦树
            root.left = build(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
            root.right = build(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
            return root;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
