//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：[1]
//
//
// 示例 4：
//
//
//输入：root = [1,2]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 10⁴] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 814 👎 0

package leetcode.editor.cn;

import java.util.*;

class SerializeAndDeserializeBinaryTree{
    public static void main(String[] args){
        //Solution solution = new SerializeAndDeserializeBinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {
        /*
        类似BFS解法
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder res = new StringBuilder();
            res.append("[");
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                // 这里和常规BFS不同
                // 当前节点不为空，就把当前节点加入结果集，并把左右子节点不用校验，都加入队列。
                if (node != null) {
                    res.append(node.val).append(",");
                    queue.add(node.left);
                    queue.add(node.right);
                }
                // 当前节点为空，那就只把 "null" 加入结果集
                else {
                    res.append("null").append(",");
                }
            }
            res.append("]");
            return res.toString();
        }
        public TreeNode deserialize(String data) {
            if (data.equals("")) {
                return null;
            }
            String[] dataSplit = data.substring(1, data.length() - 1).split(",");
            LinkedList<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(dataSplit[0]));
            int i = 1;
            queue.add(root);
            // 这里跟序列化节点相反
            while (!queue.isEmpty()) {
                // 从队列中拿到节点
                TreeNode node = queue.remove();
                // 如果数组中对应索引处不为 "null" 那就构建 node 的左子节点
                if (!dataSplit[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(dataSplit[i]));
                    queue.offer(node.left);
                }
                i++;
                // 如果数组中对应索引处不为 "null" 那就构建 node 的右子节点
                if (!dataSplit[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(dataSplit[i]));
                    queue.offer(node.right);
                }
                i++;
            }
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}
