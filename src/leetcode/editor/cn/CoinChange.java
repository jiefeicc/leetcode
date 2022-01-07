//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
// Related Topics 广度优先搜索 数组 动态规划 👍 1624 👎 0
package leetcode.editor.cn;

import java.util.Arrays;

//java:零钱兑换
class CoinChange{
    public static void main(String[] args){
        Solution solution = new CoinChange().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        状态：dp[n]=x,凑出n金额，最少需要x枚金币
        初始状态：dp[0]=0

        选择：要不要第i枚金币

        递推关系: dp[11] = min (dp[10] + 1, dp[9] + 1, dp[6] + 1, , , , , , ,)

        return: dp[amount]
        */
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount+1];
            Arrays.fill(dp, amount+1);
            // dp[amount] 最大不可能超过 amount，所以 amount + 1 就是一个无意义的数。
            // 所以将 dp 数组的所有元素都初始化为 amount + 1
            dp[0]=0;
            for (int i=1;i<=amount;i++){
                for (int coin : coins) {
                    if (coin <= i) {
                        // dp[11] = min (dp[10] + 1, dp[9] + 1, dp[6] + 1，............)
                        // 所以遍历 coins ，dp[i] = Math.min(dp[i], dp[i - coin] + 1)
                        // min 中的dp[i] 记录的遍历过程中的最小值
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
            // 哪怕有一个元素是大于 amount + 1 的最终都会被最小化为 amount + 1，
            // 所以这里使用 dp[amount] > amount 还是 dp[amount] == amount + 1 没有区别。
            if (dp[amount] == amount + 1) {
                return -1;
            } else {
                return dp[amount];
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}