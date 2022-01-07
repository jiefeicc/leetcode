//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 2083 👎 0

package leetcode.editor.cn;
//java:爬楼梯
class ClimbingStairs{
    public static void main(String[] args){
        Solution solution = new ClimbingStairs().new Solution();
        for (int i=0; i<=5; i++) {
            int n = solution._climbStairs(i);
            System.out.println(i + ":" + n);
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        从第0级开始爬，不动，1种方法
        从第1级开始爬，上一阶，1种方法
         */
        public int climbStairs(int n) {
            int[] dp = new int[n+2];
            dp[0] = 1;
            dp[1] = 1;
            for (int i=2; i<=n; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            return dp[n];
        }
        /*
        泛化，可以走任意步时的解法
        dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + ...........
         */
        public int _climbStairs(int n) {
            int[] dp = new int[n+2];
            dp[0] = 1;
            for (int i=1; i<=n; i++) {
                for (int j=0; j<=i; j++) {
                    dp[i] = dp[i] + dp[i-j];
                }
            }
            return dp[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
