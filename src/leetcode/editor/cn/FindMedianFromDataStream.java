package leetcode.editor.cn;

import java.util.*;

class FindMedianFromDataStream{
    public static void main(String[] args){
        MedianFinder medianFinder = new FindMedianFromDataStream().new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        /*
        双堆解法
        思路：
            一个大顶堆 largeHeap，一个小顶堆 smallHeap，满足largeHeap的最大值小于smallHeap的最小值。
            这样中位数是largeHeap的顶或smallHeap的顶或两个顶的平均数
            addNum() 保证largeHeap的最大值小于smallHeap的最小值：
                每次来都插到一个堆里面，然后出堆顶插到另一个堆里。
                还要保证两个堆的 size 大小最大差距不超过 1
                if(l.size > s.size) {
                    l插入再出堆顶
                    刚出的堆顶插入s
                } else {
                    s插入再出堆顶
                    刚出的堆顶插入l
                }
         */
        PriorityQueue<Integer> smallHeap;
        PriorityQueue<Integer> largeHeap;

        public MedianFinder() {
            smallHeap = new PriorityQueue<>();
            largeHeap = new PriorityQueue<>((a,b) -> b - a);
        }

        public void addNum(int num) {
            if (largeHeap.size() > smallHeap.size()) {
                largeHeap.add(num);
                smallHeap.add(largeHeap.poll());
            } else {
                smallHeap.add(num);
                largeHeap.add(smallHeap.poll());
            }
        }

        public double findMedian() {
            if (largeHeap.size() > smallHeap.size()) {
                return largeHeap.peek();
            } else if (largeHeap.size() < smallHeap.size()) {
                return smallHeap.peek();
            } else {
                return ((double) largeHeap.peek() + (double)smallHeap.peek())/2;
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}