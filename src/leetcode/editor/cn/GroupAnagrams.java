package leetcode.editor.cn;

import java.util.*;

class GroupAnagrams{
    public static void main(String[] args){
        Solution solution = new GroupAnagrams().new Solution();
    }
    //字母异位词分组
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：字符串转数组，排序，通过 map<String, List<String>> 判段是否存在异位词并保存记录
        */
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] strArray = str.toCharArray();
                Arrays.sort(strArray);
                String key = new String(strArray);
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }
            List<List<String>> res = new ArrayList<>();
            for (List<String> value : map.values()) {
                res.add(value);
            }
            return res;
            // return new ArrayList<>(map.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
