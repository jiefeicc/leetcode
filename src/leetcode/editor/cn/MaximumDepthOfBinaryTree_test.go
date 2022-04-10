package leetcode

import (
    "testing"
)

func TestMaximumDepthOfBinaryTree(t *testing.T){
}

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
/*
   递归解法
   假定函数已经具备已有功能：maxDepth(root) 求出 root 的最大深度
   代码流程：
       1.递归退出条件(也可以理解输入条件的校验)
       2.求出左子树最大深度 maxDepth(root.left)
       3.求出左子树最大深度 maxDepth(root.left)
       4.返回 max(leftDepth, rightDepth) + 1
*/
func maxDepth(root *TreeNode) int {
    if root == nil {
        return 0
    }
    leftDepth := maxDepth(root.Left)
    rightDepth := maxDepth(root.Right)
    return max_maxDepth(leftDepth, rightDepth) + 1
}
func max_maxDepth(a int, b int) int {
    if a > b {
        return a
    }
    return b
}
//leetcode submit region end(Prohibit modification and deletion)
