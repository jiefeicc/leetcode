package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class haveCircularDependency {
    /*
    现有n个编译项，编号为0 ~ n-1。给定一个二维数组，表示编译项之间有依赖关系。如[0, 1]表示1依赖于0。
    若存在循环依赖则返回空；不存在依赖则返回可行的编译顺序。
    若给定一个依赖关系是[[0,2],[1,2],[2,3],[2,4]]，可以看出，它们之间不存在循环依赖。
     0    3
      \ /
       2
      / \
     1   4
     haveCircularDependency
     */

    /*
    拓扑排序算法过程：
        选择图中一个入度为0的点，记录下来
        在图中删除该点和所有以它为起点的边
        重复1和2，直到图为空或没有入度为0的点。
     */
    public int[] _findOrder(int numCourses, int[][] prerequisites){
        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int[] prerequisite : prerequisites) {
            // 创建入度表
            inDegree[prerequisite[1]]++;
            // 创建哈希表（节点，该节点指向的节点集合）
            if (map.containsKey(prerequisite[0])) {
                map.get(prerequisite[0]).add(prerequisite[1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(prerequisite[1]);
                map.put(prerequisite[0], list);
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
        // 查哈希表，将入度为零节点的入队
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
