//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//
//
// 示例 2：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 10⁵
// 0 <= prices[i] <= 10⁴
//
// Related Topics 数组 动态规划 👍 1985 👎 0

package leetcode.editor.cn;
//java:买卖股票的最佳时机
class BestTimeToBuyAndSellStock{
    public static void main(String[] args){
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        base case：
        dp[-1][...][0] = dp[...][0][0] = 0            // i = -1 意味着还没有开始，这时候的利润是0。 k=0 意味着根本不允许交易，这时候利润是0。
        dp[-1][...][1] = dp[...][0][1] = -infinity    // 还没开始的时候，是不可能持有股票的。不允许交易的情况下，是不可能持有股票的。因为我们的算法要求⼀个最⼤值，所以初始值设为⼀个最⼩值，⽅便取最⼤值。
        状态转移⽅程：
        dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])     // ( 今天选择 rest, 今天选择 sell )
        dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])   // ( 今天选择 rest, 今天选择 buy  )

        dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
        dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) = max(dp[i-1][1][1], -prices[i])
        // 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
        // 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
        // 可以进⾏进⼀步化简去掉所有 k：
        dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        dp[i][1] = max(dp[i-1][1], -prices[i])

        if (i - 1 == -1) {
            // base case
            // 根据状态转移⽅程可得：
            // dp[i][0]
            // = max(dp[-1][0], dp[-1][1] + prices[i])
            // = max(0, -infinity + prices[i]) = 0
            dp[i][0] = 0;
            // 根据状态转移⽅程可得：
            // dp[i][1]
            // = max(dp[-1][1], dp[-1][0] - prices[i])
            // = max(-infinity, 0 - prices[i])
            // = -prices[i]
            dp[i][1] = -prices[i];
            continue;
         }
         */
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                if (i - 1 == -1) {
                    // base case
                    // 根据状态转移⽅程可得：
                    // dp[i][0]
                    // = max(dp[-1][0], dp[-1][1] + prices[i])
                    // = max(0, -infinity + prices[i]) = 0
                    dp[i][0] = 0;
                    // 根据状态转移⽅程可得：
                    // dp[i][1]
                    // = max(dp[-1][1], dp[-1][0] - prices[i])
                    // = max(-infinity, 0 - prices[i])
                    // = -prices[i]
                    dp[i][1] = -prices[i];
                    continue;
                }
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
            }
            return dp[n - 1][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
