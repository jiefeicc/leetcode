//有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
//
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
//和 "192.168@1.1" 是 无效 IP 地址。
//
//
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你不能重新排序
//或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
//
//
//
// 示例 1：
//
//
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
//
//
// 示例 2：
//
//
//输入：s = "0000"
//输出：["0.0.0.0"]
//
//
// 示例 3：
//
//
//输入：s = "1111"
//输出：["1.1.1.1"]
//
//
// 示例 4：
//
//
//输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
//
//
// 示例 5：
//
//
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 20
// s 仅由数字组成
//
// Related Topics 字符串 回溯 👍 761 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

//java:复原 IP 地址
class RestoreIpAddresses{
    public static void main(String[] args){
        Solution solution = new RestoreIpAddresses().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        /*
        回溯解法
         */
        LinkedList<String> res = new LinkedList<>();
        LinkedList<String> list = new LinkedList<>();
        public List<String> restoreIpAddresses(String s) {
            if (s == null) {
                return res;
            }
            back(s, 0);
            return res;
        }
        private void back(String s, int pos) {
            // 当lis里面存了4个符合条件的字符串段组成IP
            // 注：必须要pos把s遍历完了，每个字节都用到了，才向res里面加入结果
            if (list.size() == 4) {
                if (pos == s.length()) {
                    res.add(String.join(".", list));
                }
                return;
            }
            for (int i = 1; i <= 3; i++) {
                // 当字符串段要取得最后位置超出字符串时，剪枝
                // s.substring(pos, pos + i)，取到s.charAt(pos + i -1)， s.charAt(s.length() -1)是末端字节
                if (pos + i -1 > s.length() -1) {
                    continue;
                }
                // 截取的字符串段，左并右开
                String segment = s.substring(pos, pos + i);
                // segment多个数字时首数字为0 || segment大于255，剪枝
                if (segment.startsWith("0") && segment.length() > 1 || Integer.parseInt(segment) > 255) {
                    continue;
                }
                list.add(segment);
                back(s, pos + i);
                list.removeLast();
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}

