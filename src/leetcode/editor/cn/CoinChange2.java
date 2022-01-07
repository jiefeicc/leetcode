//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
//
// 假设每一种面额的硬币有无限个。
//
// 题目数据保证结果符合 32 位带符号整数。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//
//
// 示例 2：
//
//
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
//
//
// 示例 3：
//
//
//输入：amount = 10, coins = [10]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 300
// 1 <= coins[i] <= 5000
// coins 中的所有值 互不相同
// 0 <= amount <= 5000
//
// Related Topics 数组 动态规划 👍 679 👎 0

package leetcode.editor.cn;
//java:零钱兑换 II
class CoinChange2{
    public static void main(String[] args){
        Solution solution = new CoinChange2().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        状态定义：若只使用 coins 中的前 i 个硬币的面值，若想凑出金额 j，有 dp[i][j] 种凑法。
        初始状态：dp[0][..] = 0， dp[..][0] = 1

        选择：
            不把这第 i 个物品装入背包：dp[i][j] = dp[i-1][j]
            把这第 i 个物品装入了背包：dp[i][j] = dp[i][j-coins[i-1]]

        状态转移方程：
            if (j - coins[i-1] >= 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j-coins[i-1]];
         */
        int _change(int amount, int[] coins) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];
            // base case
            for (int i = 0; i <= n; i++)
                dp[i][0] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= amount; j++)
                    if (j - coins[i-1] >= 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i-1]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
            }
            return dp[n][amount];
        }
        /*
        状态定义：dp[i] 表示金额之和等于 x 的硬币组合数
        初始状态：dp[0] = 1
        状态转移方程：dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + ...........

        注意！爬楼梯泛化解法相当于排列，顺序不同表示不同的解法，本题解法类似于组合，顺序不同也表示相同的解法
        本题将 j(coin) 放在外层先遍历，先花完一种钱，再花下一种
        不允许在后面的硬币层次使用前面的硬币，这样就避免重复了
         */
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int i = 1; i <= amount; i++) {
                    if (i >= coin) {
                        dp[i] += dp[i - coin];
                    }
                }
            }
            return dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
