//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
//
//
//
// 示例 1:
//
//
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
//
//
//
//
// 提示:
//
//
// 1 <= preorder.length <= 3000
// inorder.length == preorder.length
// -3000 <= preorder[i], inorder[i] <= 3000
// preorder 和 inorder 均无重复元素
// inorder 均出现在 preorder
// preorder 保证为二叉树的前序遍历序列
// inorder 保证为二叉树的中序遍历序列
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1339 👎 0

package leetcode.editor.cn;

import java.util.Stack;

//java:从前序与中序遍历序列构造二叉树
class ConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args){
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
        前序遍历数组的第一个数就是根节点，可以在中序遍历数组中找到把其分割开（左边是左子树中序遍历数组，右边是右子树中序遍历数组）
        然后前序遍历可以分成两个部分（根据中序遍历分割点），左边是左子树前序遍历数组，右边是右子树前序遍历数组
        然后分治+递归就可以得出答案
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int preLen = preorder.length;;
            int inLen = inorder.length;
            return build(preorder, 0, preLen-1, inorder, 0, inLen-1);
        }
        TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd) {
                return null;
            }
            // root 节点对应的值就是前序遍历数组的第⼀个元素
            int rootVal = preorder[preStart];
            // rootVal 在中序遍历数组中的索引
            int index = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == rootVal) {
                    index = i;
                    break;
                }
            }
            int leftSize = index - inStart;
            // 先构造出当前根节点
            TreeNode root = new TreeNode(rootVal);
            // 递归构造左右⼦树
            root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
            root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
            return root;
        }
        /*
        迭代法
         */
        public TreeNode _buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            Stack<TreeNode> roots = new Stack<TreeNode>();
            int pre = 0;
            int in = 0;
            //先序遍历第一个值作为根节点
            TreeNode curRoot = new TreeNode(preorder[pre]);
            TreeNode root = curRoot;
            roots.push(curRoot);
            pre++;
            //遍历前序遍历的数组
            while (pre < preorder.length) {
                //出现了当前节点的值和中序遍历数组的值相等，寻找是谁的右子树
                if (curRoot.val == inorder[in]) {
                    //每次进行出栈，实现倒着遍历
                    while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                        curRoot = roots.peek();
                        roots.pop();
                        in++;
                    }
                    //设为当前的右孩子
                    curRoot.right = new TreeNode(preorder[pre]);
                    //更新 curRoot
                    curRoot = curRoot.right;
                    roots.push(curRoot);
                    pre++;
                } else {
                    //否则的话就一直作为左子树
                    curRoot.left = new TreeNode(preorder[pre]);
                    curRoot = curRoot.left;
                    roots.push(curRoot);
                    pre++;
                }
            }
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
