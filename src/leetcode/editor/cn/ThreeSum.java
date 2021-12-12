//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：[]
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 3000
// -10⁵ <= nums[i] <= 10⁵
//
// Related Topics 数组 双指针 排序 👍 4076 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java:三数之和
class ThreeSum{
    public static void main(String[] args){
        Solution solution = new ThreeSum().new Solution();
        int[] nums = new int[]{0,0,0,0};
        solution.threeSum(nums);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        三指针解法
        i=0,j=i+1,k=nums.len
         for (int i=0; i<length-2; i++){
            int temp = nums[i] + nums[j] + nums[k];
            temp > 0 k--
            temp < 0 j++
            temp = 0 res.add
                // 去重操作
                while(j<k && nums[j]=nums[j+1])
                    j++
                while(j<k && nums[k]=nums[k-1])
                    k--
         }
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length<3) {
                return res;
            }
            Arrays.sort(nums);
            int length = nums.length;
            for (int i=0; i<length-2; i++){
                if (nums[i] > 0) {
                    break;
                }
                if(i > 0 && nums[i] == nums[i-1]) continue;
                int j = i + 1;
                int k = length-1;
                while (j<k) {
                    int temp = nums[i] + nums[j] + nums[k];
                    if (temp > 0) {
                        k--;
                    } else if (temp < 0) {
                        j++;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        res.add(list);
                        // 去重
                        while (j<k && nums[j]==nums[j+1]) {
                            j++;
                        }
                        while (j<k && nums[k]==nums[k-1]) {
                            k--;
                        }
                        k--;
                        j++;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
