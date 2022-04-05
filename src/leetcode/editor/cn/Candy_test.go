package leetcode

import (
    "testing"
)

func TestCandy(t *testing.T){
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
贪心思想：
    求最小糖果数，即要满足左规则的同时满足右规则。匹配规则时，每次加一，取两次匹配规则时的最大值。
左规则：当 ratings[i] > ratings[i-1], ratings[i] 比 ratings[i-1] 多一个。
右规则：当 ratings[i-1] > ratings[i], ratings[i-1] 比 ratings[i] 多一个。
先每个学生发一个糖果，再从左到右、从右到左，分别遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。
取以上 2 轮遍历 left 和 right 对应学生糖果数的 最大值 ，这样则同时满足左规则和右规则 ，即得到每个同学的最少糖果数量。
 */
func candy(ratings []int) int {
    ans := 0
    length := len(ratings)
    left := make([]int, length)
    for i := 0; i < length; i++ {
        if i > 0 && ratings[i] > ratings[i-1] {
            left[i] = left[i-1] + 1
        } else {
            left[i] = 1
        }
    }
    // 省略 right 切片，只需要用单个变量记录当前位置的右规则
    right := 0
    for i := length -1; i >= 0; i-- {
        if i < length - 1 &&  ratings[i] > ratings[i+1]{
            right++
        } else {
            right = 1
        }
        ans += max(left[i], right)
    }
    return ans
}
func max(a int, b int) int {
    if a > b {
        return a
    }
    return b
}
//leetcode submit region end(Prohibit modification and deletion)
