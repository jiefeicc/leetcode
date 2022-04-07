package leetcode

import (
	"testing"
)

func TestFlattenBinaryTreeToLinkedList(t *testing.T) {
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
   迭代处理过程：
       1.将右子树替换成左子树
       2.将原来的右子树拼接到新树的最右边节点的右子树上
       3.重复上述操作，直到没有左子树
   递归处理过程：
       注意：不要尝试理解和实现递归函数具体逻辑，假设函数已经具备设定的功能，考虑输入输出即可。
            假设 flatten(root) 是将 root 转换成链表
       代码流程：
           1.递归退出条件(也可以理解输入条件的校验)
           2.flatten(root.left) 将左子树转换成链表（先处理右子树也可以）
           3.flatten(root.right) 将右子树转换成链表（后处理左子树也可以）
           4.拼接两个链表
*/
func flatten(root *TreeNode) {
	if root == nil {
		return
	}
	flatten(root.Left)
	flatten(root.Right)
	temp := root.Right
	root.Right = root.Left
	// 需要将原来的左子树置为空
	root.Left = nil
	for root.Right != nil {
		root = root.Right
	}
	root.Right = temp
}

//leetcode submit region end(Prohibit modification and deletion)
