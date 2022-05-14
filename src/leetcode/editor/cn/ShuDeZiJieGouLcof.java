package leetcode.editor.cn;

import java.util.*;

class ShuDeZiJieGouLcof{
    public static void main(String[] args){
        Solution solution = new ShuDeZiJieGouLcof().new Solution();
    }
    //树的子结构
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
        双重递归
        这一题是判断是否是子结构，和判断是否是树的子树有本质区别。
        isSub(A,B) 判断A是否包含B
        isSubStructure 递归判断
         */
        public boolean _isSubStructure(TreeNode A, TreeNode B) {
            if (A == null || B == null)
                return false;
            //先从根节点判断B是不是A的子结构，如果不是再分别从左右两个子树判断，
            //只要有一个为true，就说明B是A的子结构
            if (isSub(A, B)) {
                return true;
            } else {
                return isSubStructure(A.left, B) || isSubStructure(A.right, B);
            }
        }
        boolean isSub(TreeNode A, TreeNode B) {
            //这里如果B为空，说明B已经访问完了，确定是A的子结构
            if (B == null) {
                return true;
            }
            //如果B不为空A为空，或者这两个节点值不同，说明B树不是
            //A的子结构，直接返回false
            if (A == null || A.val != B.val) {
                return false;
            }
            //当前节点比较完之后还要继续判断左右子节点
            return isSub(A.left, B.left) && isSub(A.right, B.right);
        }

        /*
        迭代解法
        isSubStructure(A,B):
            先遍历树A，如果遍历到和B节点值相同的节点，进入helper方法判断接下来的节点是否都相同
            节点都相同返回True；不相同返回False，并且继续遍历树A找下一个相同的节点
            如果遍历完了A还没有返回过True，说明B不是A的子结构，返回False
        helper(A,B): 用于判断从A的子树是否有和B相同的部分
            正常BFS步骤
            因为入队的条件是只要树B节点存在就入队，如果A已经没有了相应节点返回False
            如果A和B对应节点值不相同也返回False
            如果遍历完了B也没有返回过False，说明B是A的子结构，返回True
         */
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if(B == null) {
                return false;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(A);
            while(!queue.isEmpty()){
                TreeNode node = queue.remove();
                if(node.val == B.val){
                    if(helper(node, B)){
                        return true;
                    }
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            return false;
        }
        private boolean helper(TreeNode A, TreeNode B){
            Queue<TreeNode> queueA = new LinkedList<>();
            Queue<TreeNode> queueB = new LinkedList<>();
            queueA.offer(A);
            queueB.offer(B);

            while(!queueB.isEmpty()){
                A = queueA.poll();
                B = queueB.poll();
                if(A == null){
                    return false;
                }
                if (A.val != B.val){
                    return false;
                }
                if(B.left != null){
                    queueA.offer(A.left);
                    queueB.offer(B.left);
                }
                if(B.right != null){
                    queueA.offer(A.right);
                    queueB.offer(B.right);
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
