package leetcode

import (
	"strings"
	"testing"
)

func TestReverseWordsInAString(t *testing.T) {
}

//leetcode submit region begin(Prohibit modification and deletion)
/*
   使用 trim() 删除首尾空格
   解题流程：
       1.搜索单词左边首个空格
       2.添加单词
       3.跳过单词间空格
       4.重置j = i，重复1,2,3操作
       5.记得返回值去除尾部空格
*/
func reverseWords(s string) string {
	s = strings.TrimSpace(s)
	var res string
	i := len(s) - 1
	j := i
	for i >= 0 {
		for i >= 0 && s[i] != ' ' {
			i--
		}
		res += s[i+1:j+1] + " "
		for i >= 0 && s[i] == ' ' {
			i--
		}
		j = i
	}
	return res[0 : len(res)-1]
}

//leetcode submit region end(Prohibit modification and deletion)
