package leetcode.editor.cn;

public class BackToOrigin {
    /*
    圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步.
    问走n步回到0点共有多少种走法
    输入: 2
    输出: 2
    解释：有2种方案。分别是0->1->0和0->9->0

    状态定义: dp[i] [j] , 表示 走 i 步 回到 j 点的走法种树
    初始状态：dp[0][0]=1
    递推方程: 走n步到0的方案数 = 走n-1步到1的方案数 + 走n-1步到9的方案数
            dp[i][j] = dp[i-1][(j-1+length)%length] + dp[i-1][(j+1)%length]
     */
    public int backToOrigin(int n) {
        int len = 10;
        int[][] dp = new int[n + 1][len];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 注意这里j 从 0 开始
            for (int j = 0; j < len; j++) {
                // dp[i] [j]  ,  表示 走 i 步 回到 j 点有多少种走法
                dp[i][j] = dp[i - 1][(j - 1 + len) % len] + dp[i - 1][(j + 1) % len];
            }
        }
        return dp[n][0];
    }
}
