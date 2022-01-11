//给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
//
// 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
//
// 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎
//，则可以在之后的操作中 重复使用 这枚鸡蛋。
//
// 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
//
//
// 示例 1：
//
//
//输入：k = 1, n = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
//否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
//如果它没碎，那么肯定能得出 f = 2 。
//因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
//
//
// 示例 2：
//
//
//输入：k = 2, n = 6
//输出：3
//
//
// 示例 3：
//
//
//输入：k = 3, n = 14
//输出：4
//
//
//
//
// 提示：
//
//
// 1 <= k <= 100
// 1 <= n <= 10⁴
//
// Related Topics 数学 二分查找 动态规划 👍 740 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:鸡蛋掉落
class SuperEggDrop{
    public static void main(String[] args){
        Solution solution = new SuperEggDrop().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        状态定义：dp[i][j] 使用 i 个鸡蛋,一共有 j 层楼梯（注意：这里 j 不表示高度，表示区间楼层数量）的情况下的最少实验的次数。
        初始状态：
            区间楼层数为0，不可能测出鸡蛋的个数 dp[0][j] = 0;
            区间楼层数为1，0个鸡蛋，测不出，大于等于1个鸡蛋都只要扔一次
            鸡蛋个数为0，测不出
            鸡蛋个数为1,区间楼层数为几要测几次
         状态转移方程：
            在x层蛋碎了，得到dp[i][j]的结果的实验在x层下面做（先不管哪一层，就知道在下面，剩余层数k-1）：dp[i][j] = dp[x-1][j-1]
            在x层蛋没碎，得到dp[i][j]的结果的实验在x层下面做：dp[i][j] = dp[i][j-x]
            求最坏情况下扔鸡蛋的最小次数，所以鸡蛋在第 i 层楼碎没碎，取决于哪种情况的结果更大，在该层又扔一次所以 +1
                res = min(res, max(dp(K - 1, i - 1), dp(K, N - i)) + 1)
         二分查找优化：
            根据 dp(K,N)数组的定义（有K个鸡蛋面对N层楼，最少需要扔几次），K固定时，函数随着N的增加单调递增
            注意 dp(K-1, i-1) 和 dp(K, N-i) 这两个函数
            i是从 1 到 N 单增的，固定 K 和 N，把这两个函数看做关于 i 的函数，“碎了”随着 i 的增加单调递增的，“不碎”随着 i 的增加单调递减的
            这时求 min(res, max(dp(K - 1, i - 1), dp(K, N - i)) + 1)，就是求两条函数直线的交点
            使用二分查找找“山谷”的
         */
        // 构造备忘录
        Map<Integer, Integer> memo = new HashMap<>();
        public int superEggDrop(int K, int N) {
            if (N == 0) {
                return 0;
            } else if (K == 1) {
                return N;
            }
            // 构造 key，保证key唯一，K <= 100，所以N*1000
            Integer key = N * 1000 + K;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }
            // 用二分搜索代替线性搜索
            int low = 1, high = N;
            int res = Integer.MAX_VALUE;
            while (low  <= high) {
                int mid = (low + high) / 2;
                int broken = superEggDrop(K - 1, mid - 1);
                int notBroken = superEggDrop(K, N - mid);
                // 当碎了比不碎大，往小了找
                if (broken > notBroken) {
                    high = mid - 1;
                    res = Math.min(res, broken + 1);
                } else {
                    low = mid + 1;
                    res = Math.min(res, notBroken + 1);
                }
            }
            memo.put(key, res);
            return res;
        }
        // dp解法
        public int _superEggDrop(int K, int N) {
            int[][] dp = new int[K + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                dp[1][i] = i; // only one egg
                dp[0][i] = 0; // no egg
            }
            for (int i = 1; i <= K; i++) {
                dp[i][0] = 0; // zero floor
            }
            for (int k = 2; k <= K; k++) { // start from two egg
                for (int n = 1; n <= N; n++) {
                    int tMinDrop = Integer.MAX_VALUE;
                    for (int x = 1; x <= n; x++) {
                        tMinDrop = Math.min(tMinDrop, 1 + Math.max(dp[k - 1][x - 1], dp[k][n - x]));
                    }
                    dp[k][n] = tMinDrop;
                }
            }
            return dp[K][N];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
