package leetcode

import (
    "testing"
)

func TestContainerWithMostWater(t *testing.T){
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
   双指针解法
   面积 = min(height[i], height[j]) * (j - i)
   讨论两种情况：
       向内移动短板，min(height[i], height[j]) 可能会变大，面积可能会增大
       向内移动长板，min(height[i], height[j]) 肯定不会变大，面积肯定不会增大
   得出解法：左右指针从左右两端开始，依次向内移动更小端，更新结果值。
*/
func maxArea(height []int) int {
    i := 0
    j := len(height) - 1
    res := 0
    for i < j {
        if height[i] < height[j] {
            res = max_maxArea(res, (j - i) * height[i])
            i++
        } else {
            res = max_maxArea(res, (j - i) * height[j])
            j--
        }
    }
    return res
}

func max_maxArea(a int, b int) int {
    if a > b {
        return a
    }
    return b
}
//leetcode submit region end(Prohibit modification and deletion)
