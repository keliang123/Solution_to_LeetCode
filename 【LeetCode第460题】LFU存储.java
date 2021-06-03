/*
    请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。它应该支持以下操作：get 和 put。
get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近 最少使用的键。
「项的使用次数」就是该项自插入以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。

    常规的get和put方法好说，时间复杂度要求O(1)，使用HashMap。
    这里的关键问题时，put时刚好到了容量时候，此时删除里面出现频率少的结点。
    如果出现频率最低的只有一个，删除这一个就可，最低频率有多个时，删除时间上最远出现那个.
	
	示例：
		LFUCache cache = new LFUCache( 2  );
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);        //返回 1
cache.put(3, 3);     //去除 key 2
cache.get(2);        //返回 -1 (未找到key 2)
cache.get(3);        //返回 3
cache.put(4, 4);     //去除 key 1
cache.get(1);        //返回 -1 (未找到 key 1)
cache.get(3);       // 返回 3
cache.get(4);       // 返回 4

 */

/*
	我自己第一次的想法，用HashMap实现put 和get是很方便的，时间复杂度都是O(1)。问题关键是当容量已经满了时，要删除出现频率小的数据。
	当最小频率只有一个是，删除就是了，但是最小频率有多个时，这时候要考虑优先级，即时间上最远才出现的数据删除，所以我的思路是用一个HashMap存储键值，用一个ArrayList存放优先级顺序。
	上面方法时间复杂度是O(n)，结果超时了。
	
	第二种方法参考别人的思想，然后自己实现的，利用双向链表。先定义两个HashMap，一个存放键值KEY和结点，另一个存放使用频率和双向链表。
	用双向链表原因：首先需要比较访问频率，在访问频率相等情况下再比较最后访问的时间前后。而且时间复杂度为O(1)，
	设置双向链表可以头部是只存入结点，尾部就是只负责删除时间靠后的结点。
*/


//我自己第一次的解答
class LFUCache {

    private int size;
    private int maxSize;
    private HashMap<Integer , Integer> map;  //存放数据 key value
    private HashMap<Integer , Integer> time; //key的使用频率
    private ArrayList<Integer> list;         //key的优先级顺序
    
    public LFUCache(int capacity) {
        size = 0;
        maxSize = capacity;
        map = new HashMap<>(capacity);
        time = new HashMap<>(capacity);
        list = new ArrayList<>(capacity);
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            time.put(key , time.get(key) + 1);
            list.remove(new Integer(key));
            list.add(key);
            return map.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (maxSize == 0) 
            return;
        if (map.containsKey(key)) {
            map.put(key , value);
            list.remove(new Integer(key));
            list.add(key);
            time.put(key , time.get(key) + 1);
            return;
        }

        if (size == maxSize) {
            int min = Integer.MAX_VALUE;
            int minKey = -1 , minIndex = maxSize;
            for (Map.Entry<Integer , Integer> e : time.entrySet()) {
                Integer eKey = e.getKey();
                Integer eValue = e.getValue();
                if (eValue < min) {
                    min = eValue;
                    minKey = eKey;
                    minIndex = list.indexOf(eKey);
                }
                if (eValue == min) {
                    minIndex = Math.min(minIndex , list.indexOf(eKey));
                    minKey = list.get(minIndex);
                }
            }
            map.remove(minKey);
            list.remove(minIndex);
            time.remove(minKey);
            size--;
        }
        map.put(key , value);
        list.add(key);
        time.put(key , 1);
        size++;
    }
}

//第二种
import java.util.HashMap;
public class LFUCache {

    public int capacity;
    //第一个HashMap存放键值和结点
    public HashMap<Integer , Node> keyNode;
    //第二个存放出现频率和双向链表
    public HashMap<Integer , DoubleLinkedList> freqNode;

    public class Node {
        public int key;
        public int value;
        public int freq;
        public Node next;
        public Node pre;

        public Node() {
        }

        public Node(int key , int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    public class DoubleLinkedList {
        public Node dummyHead;
        public Node dummyTail;
        public int size;

        public DoubleLinkedList() {
            this.dummyHead = new Node(-1 , -1);
            this.dummyTail = new Node(-1 , -1);
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
            this.size = 0;
        }

        //双向链表表头添加结点
        private void addNodeHead(Node node) {
            if (node == null)
                return ;
            Node temp = dummyHead.next;

            dummyHead.next = node;
            node.next = temp;
            temp.pre = node;
            node.pre = dummyHead;
            size++;
        }

        //双向链表删除dummy结点的下一个结点
        private Node deleNodeMid(Node dummy) {
            if (dummy == null)
                return null;
            Node deleNode = dummy.next;
            Node deleNodeNext = deleNode.next;
            dummy.next = deleNodeNext;
            deleNodeNext.pre = dummy;
            deleNode.next = null;
            deleNode.pre = null;
            return deleNode;
        }

        //双向链表表尾删除结点
        private Node deleNodeTail() {
            if (dummyTail == null || dummyTail.pre == null || dummyTail.pre.pre == null)
                return null;
            Node temp = dummyTail.pre.pre;
            Node deleNode = dummyTail.pre;

            temp.next = dummyTail;
            dummyTail.pre = temp;
            deleNode.next = null;
            deleNode.pre = null;
            size--;

            return deleNode;
        }
    }


    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyNode = new HashMap<>(capacity);
        this.freqNode = new HashMap<>();
    }

    public int get(int key) {
        if (keyNode.containsKey(key)) {

            int oldFreq = keyNode.get(key).freq;
            //删除原来的频率上的结点
            DoubleLinkedList oldDouble = freqNode.get(oldFreq);
            Node dummy = oldDouble.dummyHead;
            while(dummy.next.key != key) {
                dummy = dummy.next;
            }
            Node deleNode = oldDouble.deleNodeMid(dummy);
            oldDouble.size--;
            if (oldDouble.size == 0)
                freqNode.remove(oldFreq);
            int newFreq = oldFreq + 1;
            deleNode.freq++;
            //增加下一个频率上的结点
            if (!freqNode.containsKey(newFreq)) {
                DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
                doubleLinkedList.addNodeHead(deleNode);
                freqNode.put(newFreq , doubleLinkedList);
            } else {
                DoubleLinkedList newDouble = freqNode.get(newFreq);
                newDouble.addNodeHead(deleNode);
                freqNode.put(newFreq , newDouble);
            }
            return keyNode.get(key).value;
        }
        return -1;
    }

    public void put(int key , int value) {
        if (capacity == 0)
            return;

        if (keyNode.containsKey(key)) {
            int freq = keyNode.get(key).freq;
            keyNode.get(key).value = value;

            DoubleLinkedList oldDouble = freqNode.get(freq);
            Node dummy = oldDouble.dummyHead;
            while(dummy.next.key != key) {
                dummy = dummy.next;
            }
            Node deleNode = oldDouble.deleNodeMid(dummy);
            oldDouble.size--;
            if (oldDouble.size == 0)
                freqNode.remove(freq);
            int newFreq = freq + 1;
            deleNode.freq++;
            deleNode.value = value;
            //增加下一个频率上的结点
            if (!freqNode.containsKey(newFreq)) {
                DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
                doubleLinkedList.addNodeHead(deleNode);
                freqNode.put(newFreq , doubleLinkedList);
            } else {
                DoubleLinkedList newDouble = freqNode.get(newFreq);
                newDouble.addNodeHead(deleNode);
                freqNode.put(newFreq , newDouble);
            }
            return;
        }

        if (keyNode.size() == capacity) {
            int freqTime = 0;
            while (!freqNode.containsKey(freqTime)) {
                freqTime++;
            }
            Node deleNode = freqNode.get(freqTime).deleNodeTail();
            if (freqNode.get(freqTime).size == 0)
                freqNode.remove(freqTime);
            int deleKey = deleNode.key;
            keyNode.remove(deleKey);
        }
        Node addNode = new Node(key , value);
        keyNode.put(key , addNode);
        if (!freqNode.containsKey(1)) {
            DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
            doubleLinkedList.addNodeHead(addNode);
            freqNode.put(1 , doubleLinkedList);
        } else {
            DoubleLinkedList  doubleLinkedList  = freqNode.get(1);
            doubleLinkedList.addNodeHead(addNode);
            freqNode.put(1 , doubleLinkedList);
        }
    }

    //测试
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1 , 1);
        cache.put(2 , 2);
        System.out.println("开始测试：");
        System.out.println("是否有1: " + cache.get(1));
        System.out.println("是否有2: " + cache.get(2));

        cache.put(2 , 4);
        System.out.println("添加(3 , 3)后测试是否有没有1 或者 2：");
        System.out.println("是否有1: " + cache.get(1));
        System.out.println("是否有2：" + cache.get(2));
        System.out.println("是否有3：" + cache.get(3));

        cache.put(4 , 5);
        System.out.println("添加(4 , 5)后测试是否有没有2 或者 3：");
        System.out.println("是否有2: " + cache.get(2));
        System.out.println("是否有1：" + cache.get(1));
    }
}

/*
	get和put操作时间复杂度是O(1)。
*/


