//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 1572 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:LRU 缓存机制
class LruCache{
    public static void main(String[] args){

    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     通过map来实现get缓存逻辑
     通过双向链表来实现“最近最少使用”逻辑
     通过变量size来维护双向链表的长度
     记录head和tail这两个头尾连接的空节点方便操作

     get(key) {
        node = map.get(key)
        node 存在:
            moveToHead(node)
            return node.val
        不存在:
            return -1
     }
     put(key) {
        key不存在:
            构建node
            addToHead(node)
            size溢出:
                removeTail()
                从map移除
        存在:
            更新map和DLinkedNode的值
            moveToHead(node)
     }

     实现需要的方法
     moveToHead(node)
     addToHead(node)
     removeTail()
     removeNode(node)
     */
    class LRUCache {
        // 构造双向链表
        class DLinkedNode {
            int key;
            int val;
            DLinkedNode pre;
            DLinkedNode next;
            public DLinkedNode(){}
            public DLinkedNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        // 初始化capacity，size
        // 构造cacheMap，还要构造headNode，tailNode这两个头尾连接的空节点方便使用。
        private Map<Integer, DLinkedNode> cacheMap = new HashMap<>();
        private int capacity;
        private int size;
        private DLinkedNode headNode;
        private DLinkedNode tailNode;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            headNode = new DLinkedNode();
            tailNode = new DLinkedNode();
            headNode.next = tailNode;
            tailNode.pre = headNode;
        }

        public int get(int key) {
            DLinkedNode node = cacheMap.get(key);
            if (node != null) {
                int val = node.val;
                moveToHead(node);
                return val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            DLinkedNode node = cacheMap.get(key);
            if (node == null) {
                DLinkedNode linkedNode = new DLinkedNode(key, value);
                cacheMap.put(key, linkedNode);
                addToHead(linkedNode);
                size++;
                if (size > capacity) {
                    // 移除非空尾节点，需要把该非空尾节点返回出来，方便map移除
                    DLinkedNode tailPre = removeTail();
                    removeNode(tailPre);
                    cacheMap.remove(key);
                    size--;
                }
            } else {
                cacheMap.put(key, node);
                node.val = value;
            }
        }

        // 重建前后节点的连接，
        public void removeNode(DLinkedNode node) {
            DLinkedNode preNode = node.pre;
            DLinkedNode nextNode = node.next;
            preNode.next = nextNode;
            nextNode.pre = preNode;
        }
        // 移除空的尾节点tail前一个节点就行
        public DLinkedNode removeTail() {
            DLinkedNode node = tailNode.pre;
            removeNode(node);
            return node;
        }
        // 插入到空的头节点 head 和其下一个节点中间即可
        public void addToHead(DLinkedNode node) {
            DLinkedNode headNext = headNode.next;
            headNode.next = node;
            node.pre = headNode;
            node.next = headNext;
            headNext.pre = node;
        }
        // removeNode(node) -> addToHead
        public void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
