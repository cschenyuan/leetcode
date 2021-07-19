package chenyuan.newcoder.nc93;

import java.util.*;

/**
 * @author chenyuan
 */
public class Solution {

    static class Node {
        int key;
        int val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    '}';
        }
    }

    static class LRUCache {

        private final Map<Integer, Node> map = new HashMap<>();
        private final LinkedList<Node> queue = new LinkedList<>();
        final private int size;

        public LRUCache(int size) {
            this.size = size;
        }

        public void set(int key, int val) {
            if (map.containsKey(key)) {
                Node n = map.get(key);
                n.val = val;
                moveToHead(n);
            } else {
                // 检查缓存是否已满
                if (map.size() == size) {
                    // 队尾node出队
                    map.remove(queue.removeLast().key);
                }
                Node node = new Node(key, val);
                queue.addFirst(node);
                map.put(key, node);
            }
        }

        private void moveToHead(Node node) {
            queue.remove(node);
            queue.addFirst(node);
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                moveToHead(map.get(key));
                return map.get(key).val;
            }
            return -1;
        }
    }

    public int[] LRU(int[][] operators, int k) {
        LRUCache cache = new LRUCache(k);
        List<Integer> l = new ArrayList<>();
        for (int[] operator : operators) {
            int op = operator[0];
            if (op == 1) { // set
                cache.set(operator[1], operator[2]);
            }
            if (op == 2) { // get
                l.add(cache.get(operator[1]));
            }
        }

        int[] arr = new int[l.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = l.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        cache.set(0, 1);
        cache.set(3, 1);
        cache.set(4, 1);
        cache.set(5, 1);
        cache.set(8, 1);
        cache.set(9, 1);
        cache.set(3, 1);
        cache.set(1, 1);
        cache.get(8);
        cache.get(3);

        for (Node n : cache.queue) {
            System.out.println(n);
        }
    }


}
