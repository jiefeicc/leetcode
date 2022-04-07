package leetcode

import (
	"testing"
)

func TestKthSmallestElementInABst(t *testing.T) {
	root1 := &TreeNode{Val: 1}
	root2 := &TreeNode{Val: 2}
	root3 := &TreeNode{Val: 3}
	root4 := &TreeNode{Val: 4}
	root5 := &TreeNode{Val: 5}
	root6 := &TreeNode{Val: 6}
	root2.Left = root1
	root3.Left = root2
	root3.Right = root4
	root5.Left = root3
	root5.Right = root6
	kthSmallest(root5, 3)
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
   中序遍历递归解法
   二叉搜索树的中序遍历是递增的
*/
func kthSmallest(root *TreeNode, k int) int {
	res := -1
	// 闭包
	var inorder func(root *TreeNode)

	inorder = func(root *TreeNode) {
		if root != nil && res == -1 {
			inorder(root.Left)
			k--
			if k == 0 {
				res = root.Val
			}
			inorder(root.Right)
		}
	}
	inorder(root)
	return res
}

//leetcode submit region end(Prohibit modification and deletion)
