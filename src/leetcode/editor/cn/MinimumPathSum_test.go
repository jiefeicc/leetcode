package leetcode

import (
	"testing"
)

func TestMinimumPathSum(t *testing.T) {
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
   状态：dp[i][j] 从左上角到 grid[i][j] 的最小路径和
   状态转移：
       dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
   base case：
       dp[0][0] = grid[0][0]
       dp[0][j] = dp[0][j-1] + grid[0][j]
       dp[i][0] = dp[i-1][0] + grid[i][0]
*/
func minPathSum(grid [][]int) int {
	row := len(grid)
	col := len(grid[0])
	var dp [][]int
	for i := 0; i < row; i++ {
		dpTmp := make([]int, col)
		dp = append(dp, dpTmp)
	}
	dp[0][0] = grid[0][0]
	for i := 1; i < row; i++ {
		dp[i][0] = dp[i-1][0] + grid[i][0]
	}
	for j := 1; j < col; j++ {
		dp[0][j] = dp[0][j-1] + grid[0][j]
	}
	for i := 1; i < row; i++ {
		for j := 1; j < col; j++ {
			dp[i][j] = min_minPathSum(dp[i-1][j], dp[i][j-1]) + grid[i][j]
		}
	}
	return dp[row-1][col-1]
}
func min_minPathSum(x, y int) int {
	if x < y {
		return x
	}
	return y
}

//leetcode submit region end(Prohibit modification and deletion)
