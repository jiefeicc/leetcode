//现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 
//prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。 
//
// 
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。 
// 
//
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：[0,1]
//解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
// 
//
// 示例 2： 
//
// 
//输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//输出：[0,2,1,3]
//解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。 
//
// 示例 3： 
//
// 
//输入：numCourses = 1, prerequisites = []
//输出：[0]
// 
//
// 
//提示：
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// 所有[ai, bi] 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 544 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//java:课程表 II
class CourseScheduleIi{
    public static void main(String[] args){
        Solution solution = new CourseScheduleIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        拓扑排序解法
            选择图中一个入度为0的点，记录下来
            在图中删除该点和所有以它为起点的边
            重复1和2，直到图为空或没有入度为0的点。
         */
        public int[] findOrder(int numCourses, int[][] prerequisites){
            int[] inDegree = new int[numCourses];
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            LinkedList<Integer> queue = new LinkedList<>();
            for (int[] prerequisite : prerequisites) {
                // 创建入度表
                inDegree[prerequisite[0]]++;
                // 创建哈希表，维护一个节点和它指向的节点之间的关系（节点，该节点指向的节点集合）
                if (map.containsKey(prerequisite[1])) {
                    map.get(prerequisite[1]).add(prerequisite[0]);
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
            // 更新该节点指向的节点的入度
            // 查哈希表，再将入度为零节点的入队
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
            //使用list的流来转为int[]数组，也可以通过遍历一遍完成转换。
            return res.size() == numCourses ? res.stream().mapToInt(Integer::valueOf).toArray() : new int[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
