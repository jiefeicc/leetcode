package leetcode

import (
    "testing"
)

func TestSearchA2dMatrix(t *testing.T){
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
   全局二分
   按列依次从左往右，全局递增的，所以使用全局二分查找。
   取中间数时 tmp = mat[mid / col][mid % col]
   mid/col，mid%col 即可得到中间索引的数。
*/
func searchMatrix(matrix [][]int, target int) bool {
    row := len(matrix)
    col := len(matrix[0])
    l := 0
    r := row * col -1
    for l < r {
        mid := (l + r + 1) / 2
        tmp := matrix[mid / col][mid % col]
        if tmp == target {
            return true
        } else if tmp < target {
            l = mid + 1
        } else {
            r = mid - 1
        }
    }
    return matrix[r / col][r % col] == target
}
//leetcode submit region end(Prohibit modification and deletion)
