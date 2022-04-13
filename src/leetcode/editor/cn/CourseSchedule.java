package leetcode.editor.cn;

import java.util.*;

class CourseSchedule{
    public static void main(String[] args){
        Solution solution = new CourseSchedule().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        拓扑排序解法
            选择图中一个入度为0的点，记录下来
            在图中删除该点和所有以它为起点的边
            重复1和2，直到图为空或没有入度为0的点。
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] inDegree = new int[numCourses];
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            LinkedList<Integer> queue = new LinkedList<>();
            for (int[] prerequisite : prerequisites) {
                // 创建入度表
                inDegree[prerequisite[0]]++;
                // 创建哈希表，维护一个节点和它指向的节点之间的关系（节点，该节点指向的节点集合）
                if (map.containsKey(prerequisite[1])) {
                    map.get(prerequisite[1]).add(prerequisite[0]);
                    /*
                    下面这种写法是上面写法的详细，传到 map 中的 list 是个“引用”
                    所以直接把 list “引用” get 出来，然后做改变，map 中的 list 也就变了
                    List<Integer> tmpList = map.get(prerequisite[1]);
                    tmpList.add(prerequisite[0]);
                    map.put(prerequisite[1], tmpList);
                     */
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(prerequisite[0]);
                    map.put(prerequisite[1], list);
                }
            }
            List<Integer> res = new ArrayList<>();
            //将所有入度为0的节点入队
            for (int i = 0; i < numCourses; i++) {
                if(inDegree[i] == 0){
                    queue.add(i);
                }
            }
            // 入度为0的节点出队放结果集
            // 查哈希表，更新某个节点（被该节点指向的）的入度
            // 若该节点入度为0了，再入队。
            while (!queue.isEmpty()){
                Integer cur = queue.remove();
                res.add(cur);
                if(map.containsKey(cur) && map.get(cur).size() != 0){
                    for (Integer num : map.get(cur)) {
                        inDegree[num]--;
                        if(inDegree[num] == 0) {
                            queue.add(num);
                        }
                    }
                }
            }
            return res.size() == numCourses;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}