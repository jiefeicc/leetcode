package leetcode

import (
    "testing"
)

func TestSearchA2dMatrixIi(t *testing.T){
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
根据矩阵特性：一个数比他上面的大，比他右边的小
从左下角开始遍历
 */
func _searchMatrix(matrix [][]int, target int) bool {
    row := len(matrix)
    col := len(matrix[0])
    i := row - 1
    j := 0
    for i >= 0 && j <= col -1 {
        if  matrix[i][j] > target{
            i--
        } else if matrix[i][j] < target{
            j++
        } else {
            return true
        }

    }
    return false
}
//leetcode submit region end(Prohibit modification and deletion)
