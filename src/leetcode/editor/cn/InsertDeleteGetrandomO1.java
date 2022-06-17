package leetcode.editor.cn;

import java.util.*;

class InsertDeleteGetrandomO1{
    public static void main(String[] args){
        //Solution solution = new InsertDeleteGetrandomO1().new Solution();
    }
    // O(1) 时间插入、删除和获取随机元素
    //leetcode submit region begin(Prohibit modification and deletion)
    class RandomizedSet {
        /*
        解题思路：
            insert 和 remove 可以通过 hash表 来实现
            getRandom设计思路：
                创建一个数组
                insert 时：index++, map.put(val, index), nums[index] = val
                remove 时：temp = map.remove(val), nums[temp] = nums[index], map.put(nums[index], temp), index--
                    通过把 nums[index] 把 map.remove(val)对应的值覆盖，再 index--达到效果。
                    注意：当 temp = index时，map.put就不是覆盖了，而是又重新把 remove 的又 put 回来了。
                getRandom：nums[random.nextInt(index + 1)]
         */
        int[] nums;
        Random random;
        Map<Integer, Integer> map;
        int index = -1;
        public RandomizedSet() {
            random = new Random();
            map = new HashMap<>();
            nums = new int[200010];
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            index++;
            map.put(val, index);
            nums[index] = val;
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int temp = map.remove(val);
            nums[temp] = nums[index];
            // 当 temp = index时，map.put就不是覆盖了，而是又重新把remove的又put回来了。
            if (temp != index) {
                map.put(nums[index], temp);
            }
            index--;
            return true;
        }

        public int getRandom() {
            return nums[random.nextInt(index + 1)];
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)

}