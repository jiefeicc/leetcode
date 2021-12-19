//返回与给定的前序和后序遍历匹配的任何二叉树。
//
// pre 和 post 遍历中的值是不同的正整数。
//
//
//
// 示例：
//
// 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//输出：[1,2,3,4,5,6,7]
//
//
//
//
// 提示：
//
//
// 1 <= pre.length == post.length <= 30
// pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
// 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 203 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:根据前序和后序遍历构造二叉树
class ConstructBinaryTreeFromPreorderAndPostorderTraversal{
    public static void main(String[] args){
        Solution solution = new ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
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
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            if(pre==null || pre.length==0) {
                return null;
            }
            return dfs(pre,post);
        }
        /*
        用前序遍历的第一个元素创建出根节点
        用前序遍历的第二个元素x，去后序遍历中找对应的下标y，将y+1就能得到左子树的节点个数了
        将前序数组，后序数组拆分左右两部分
        递归的处理前序数组左边、后序数组右边
        递归的处理前序数组右边、后序数组右边
        返回根节点
         */
        private TreeNode dfs(int[] pre,int[] post) {
            if(pre==null || pre.length==0) {
                return null;
            }
            //数组长度为1时，直接返回即可
            if(pre.length==1) {
                return new TreeNode(pre[0]);
            }
            //根据前序数组的第一个元素，创建根节点
            TreeNode root = new TreeNode(pre[0]);
            int n = pre.length;
            for(int i=0;i<post.length;++i) {
                if(pre[1]==post[i]) {
                    //根据前序数组第二个元素，确定后序数组左子树范围
                    int left_count = i+1;
                    //拆分前序和后序数组，分成四份
                    int[] pre_left = Arrays.copyOfRange(pre,1,left_count+1);
                    int[] pre_right = Arrays.copyOfRange(pre,left_count+1,n);
                    int[] post_left = Arrays.copyOfRange(post,0,left_count);
                    int[] post_right = Arrays.copyOfRange(post,left_count,n-1);
                    //递归执行前序数组左边、后序数组左边
                    root.left = dfs(pre_left,post_left);
                    //递归执行前序数组右边、后序数组右边
                    root.right = dfs(pre_right,post_right);
                    break;
                }
            }
            //返回根节点
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
