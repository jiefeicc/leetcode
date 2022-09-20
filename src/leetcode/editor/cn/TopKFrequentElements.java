package leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TopKFrequentElements{
    public static void main(String[] args){
        Solution solution = new TopKFrequentElements().new Solution();
        solution.topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        解题思路：哈希表 + 堆
            遍历数组，构造哈希表(数组中元素， 数组中元素出现的次数)。
            创建一个小根堆，遍历哈希表，往堆里面添加元素，维护堆使堆最终存储出现频率前 k 高的元素。
         */
        public int[] topKFrequent(int[] nums, int k) {
            // 统计每个数字出现的次数
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            // 创建小根堆，存储的是数组元素，排序方式是数组元素出现的次数
            Queue<Integer> heap = new PriorityQueue<>((v1, v2) -> map.get(v1) - map.get(v2));
            // 遍历map，当堆大小超过k时，如果下一个要放进来的元素的频次大于堆顶的，堆顶去掉，新加元素。
            // 这样遍历完map后，堆里元素的顺序就是元素出现频次从大到小的顺序，并且只有前 k 给
            for (Integer key : map.keySet()) {
                if (heap.size() < k) {
                    heap.offer(key);
                } else if (map.get(heap.peek()) < map.get(key)) {
                    heap.poll();
                    heap.offer(key);
                }
            }
            // 构造返回结果
            int[] res = new int[k];
            int i = 0;
            for (int num: heap) {
                res[i++] = num;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}