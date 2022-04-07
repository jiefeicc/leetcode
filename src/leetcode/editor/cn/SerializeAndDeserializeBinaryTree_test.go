package leetcode

import (
	"strconv"
	"strings"
	"testing"
)

func TestSerializeAndDeserializeBinaryTree(t *testing.T) {
	root1 := &TreeNode{Val: 1}
	root2 := &TreeNode{Val: 2}
	root3 := &TreeNode{Val: 3}
	root4 := &TreeNode{Val: 4}
	root5 := &TreeNode{Val: 5}
	root3.Left = root4
	root3.Right = root5
	root1.Left = root2
	root1.Right = root3
	//s := serialize(root1)
	//node := deserialize(s)
	//s2 := serialize(node)
	//println(s)
	//println(s2)
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

type Codec struct{}

func Constructor() (_ Codec) {
	return
}

/*
类似BFS解法
*/
func (this *Codec) serialize(root *TreeNode) string {
	queue := []*TreeNode{root}
	res := []string{}
	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]
		// 这里和常规BFS不同
		// 当前节点不为空，就把当前节点加入结果集，并把左右子节点不用校验，都加入队列。
		if node != nil {
			res = append(res, strconv.Itoa(node.Val))
			queue = append(queue, node.Left)
			queue = append(queue, node.Right)
		} else {
			// 当前节点为空，那就只把 "null" 加入结果集
			res = append(res, "null")
		}
	}
	strings.Join(res, " ")
	return strings.Join(res, ",")
}

func (this *Codec) deserialize(data string) *TreeNode {
	if data == "null" {
		return nil
	}
	dataSplit := strings.Split(data, ",")
	Val, _ := strconv.Atoi(dataSplit[0])
	root := &TreeNode{Val: Val}
	queue := []*TreeNode{root}
	i := 1
	// 这里跟序列化节点相反
	for len(queue) > 0 {
		// 从队列中拿到节点
		node := queue[0]
		queue = queue[1:]
		// 如果数组中对应索引处不为 "null" 那就构建 node 的左子节点
		if dataSplit[i] != "null" {
			v, _ := strconv.Atoi(dataSplit[i])
			leftNode := &TreeNode{Val: v}
			node.Left = leftNode
			queue = append(queue, leftNode)
		}
		i++
		// 如果数组中对应索引处不为 "null" 那就构建 node 的右子节点
		if dataSplit[i] != "null" {
			v, _ := strconv.Atoi(dataSplit[i])
			rightNode := &TreeNode{Val: v}
			node.Right = rightNode
			queue = append(queue, rightNode)
		}
		i++
	}
	return root
}

/**
 * Your Codec object will be instantiated and called as such:
 * ser := Constructor();
 * deser := Constructor();
 * data := ser.serialize(root);
 * ans := deser.deserialize(data);
 */
//leetcode submit region end(Prohibit modification and deletion)
