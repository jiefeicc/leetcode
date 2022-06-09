package leetcode.editor.cn;

import java.util.LinkedList;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode arrayToBTree(Integer[] nums) {
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        list.addLast(root);
        int k = 1;

        while(k < nums.length){
            int size = list.size();
            for(int i = 0; i < size; i++){
                TreeNode tmp = list.removeFirst();
                if(nums[k] != null){
                    TreeNode node = new TreeNode(nums[k]);
                    list.addLast(node);
                    tmp.left = node;
                }else{
                    tmp.left = null;
                }
                k++;
                if(nums[k] != null){
                    TreeNode node = new TreeNode(nums[k]);
                    list.addLast(node);
                    tmp.right = node;
                }else{
                    tmp.right = null;
                }
                k++;
            }
        }
        return root;
    }
}
