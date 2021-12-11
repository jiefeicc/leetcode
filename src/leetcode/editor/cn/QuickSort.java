package leetcode.editor.cn;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[]{2,1,3,5,9,34,12,4,5};
        quickSort.quickSort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
    public void quickSort(int[] nums, int left, int right) {
        // 递归终止条件
        if (left >= right) {
            return;
        }
        // 定义基准位的数
        int pivot = nums[left];
        int i = left, j = right;
        while (i<j) {
            // 从右边开始找到比基准数小的
            while (i<j && nums[j]>=pivot) {
                j--;
            }
            // 从左边开始找到比基准数大的
            while (i<j && nums[i]<=pivot) {
                i++;
            }
            swap(nums, i, j);
        }
        // 最终i，j移动到一个位置，这个位置左边的数<=pivot,右边的数>=pivot
        // 最后将基准为与i和j相等位置的数字交换
        swap(nums, left, i);

        // 分治思想，对左边和右边分别进行快排
        quickSort(nums, left, i-1);
        quickSort(nums, i+1, right);
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
