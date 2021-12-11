//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回锯齿形层序遍历如下：
//
//
//[
//  [3],
//  [20,9],
//  [15,7]
//]
//
// Related Topics 树 广度优先搜索 二叉树 👍 557 👎 0

package leetcode.editor.cn;

import java.util.*;

//java:二叉树的锯齿形层序遍历
class BinaryTreeZigzagLevelOrderTraversal{
    public static void main(String[] args){
        Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();
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
    广度优先搜索，通过flag控制在每层遍历时的方向
    queue.add(root)
    while(!queue.isEmpyt){
        int size = queue.size();
        for循环size次，每个size次为一层，根据flag确定插入方向
        for (int i=0; i<size; i++) {}
        res.add(linkedList),flag=!flag
    }
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = true;
        queue.add(root);
        while (!queue.isEmpty()){
            // 通过size来一层一层的加入linkedList
            int size = queue.size();
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.remove();
                if (flag) {
                    linkedList.addLast(node.val);
                } else {
                    linkedList.addFirst(node.val);
                }

                if (node.left != null ) {
                    queue.add(node.left);
                }
                if (node.right != null ) {
                    queue.add(node.right);
                }
            }
            res.add(linkedList);
            flag = !flag;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
