package leetcode

import (
    "testing"
)

func TestLianBiaoZhongDaoShuDiKgeJieDianLcof(t *testing.T){
}

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
/*
快慢节点解法
1.fast 先往后移动 k 位
2.fast 和 slow 一起往后移动，当 fast 到最后的空节点，slow 到倒数第 K 个节点。
*/
func getKthFromEnd(head *ListNode, k int) *ListNode {
    dummy := &ListNode{Val: 0}
    dummy.Next = head
    fast := dummy
    slow := dummy
    for k != 0 {
        fast = fast.Next
        k--
    }
    for fast != nil {
        fast = fast.Next
        slow = slow.Next
    }
    return slow
}
//leetcode submit region end(Prohibit modification and deletion)
