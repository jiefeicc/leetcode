package leetcode

import (
    "testing"
)

func TestDailyTemperatures(t *testing.T){
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
单调递减栈解法（栈内存储的是数组索引）
遍历数组索引 i：
    当栈不为空时，且 temperatures[i] > 栈顶
        栈顶出栈
        ans[栈顶索引] = i - 栈顶索引
        重复上面操作，直到不满足条件
    索引入栈
 */
func dailyTemperatures(temperatures []int) []int {
    length := len(temperatures)
    ans := make([]int, length)
    stack := []int{}
    for i := 0; i < length; i++ {
        temperature := temperatures[i]
        for len(stack) > 0 && temperature > temperatures[stack[len(stack) - 1]] {
            preIndex := stack[len(stack)-1]
            stack = stack[0:len(stack) - 1]
            ans[preIndex] = i - preIndex
        }
        stack = append(stack, i)

    }
    return ans
}
//leetcode submit region end(Prohibit modification and deletion)
