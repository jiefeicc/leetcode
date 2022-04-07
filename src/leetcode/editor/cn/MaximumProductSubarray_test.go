package leetcode

import (
	"testing"
)

func TestMaximumProductSubarray(t *testing.T) {
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
   dp解法
   注意：由于存在负数，所以要维护两个 dp，一个 maxDp，一个minDp
   状态定义：dp[i] 是以 nums[i] 为结尾的乘积最大的非空连续子数组
           dp[i] 是以 nums[i] 为结尾的乘积最大的非空连续子数组
   状态转移：
           if nums[i] >= 0
               maxDp[i] = max(nums[i], maxDp[i-1]*nums[i])
               minDp[i] = min(nums[i], minDp[i-1]*nums[i])
           else
               maxDp[i] = max(nums[i], minDp[i-1]*nums[i])
               minDp[i] = min(nums[i], maxDp[i-1]*nums[i])
   base case：maxDp[0] = nums[0],minDp[0] = nums[0];
*/
func maxProduct(nums []int) int {
	length := len(nums)
	if length == 0 {
		return 0
	}
	maxDp := make([]int, length)
	minDp := make([]int, length)
	maxDp[0] = nums[0]
	minDp[0] = nums[0]
	for i := 1; i < length; i++ {
		if nums[i] >= 0 {
			maxDp[i] = maxProductMax(nums[i], maxDp[i-1]*nums[i])
			minDp[i] = maxProductMin(nums[i], minDp[i-1]*nums[i])
		} else {
			maxDp[i] = maxProductMax(nums[i], minDp[i-1]*nums[i])
			minDp[i] = maxProductMin(nums[i], maxDp[i-1]*nums[i])
		}
	}
	res := maxDp[0]
	for _, i := range maxDp {
		res = maxProductMax(i, res)
	}
	return res
}
func maxProductMax(a int, b int) int {
	if a > b {
		return a
	}
	return b
}
func maxProductMin(a int, b int) int {
	if a > b {
		return b
	}
	return a
}

//leetcode submit region end(Prohibit modification and deletion)
