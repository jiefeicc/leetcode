package leetcode.editor.cn.designPatterns.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer1 {
    static class Goods {
    }

    static class Producer extends Thread {
        private String threadName;
        private Queue<Goods> queue;
        private int maxSize;

        public Producer(String threadName, Queue<Goods> queue, int maxSize) {
            this.threadName = threadName;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                //模拟生产过程中的耗时操作
                Goods goods = new Goods();
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("队列已满，【" + threadName + "】进入等待状态");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.add(goods);
                    System.out.println("【" + threadName + "】生产了一个商品：【" + goods.toString() + "】，目前商品数量：" + queue.size());
                    queue.notifyAll();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private String threadName;
        private Queue<Goods> queue;

        public Consumer(String threadName, Queue<Goods> queue) {
            this.threadName = threadName;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                Goods goods;
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("队列已空，【" + threadName + "】进入等待状态");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    goods = queue.remove();
                    System.out.println("【" + threadName + "】消费了一个商品：【" + goods.toString() + "】，目前商品数量：" + queue.size());
                    queue.notifyAll();
                }
                //模拟消费过程中的耗时操作
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int maxSize = 5;
        Queue<Goods> queue = new LinkedList<>();

        Thread producer1 = new Producer("生产者1", queue, maxSize);
        Thread producer2 = new Producer("生产者2", queue, maxSize);
        Thread producer3 = new Producer("生产者3", queue, maxSize);

        Thread consumer1 = new Consumer("消费者1", queue);
        Thread consumer2 = new Consumer("消费者2", queue);


        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
    }
}
