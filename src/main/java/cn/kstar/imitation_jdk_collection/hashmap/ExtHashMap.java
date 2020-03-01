package cn.kstar.imitation_jdk_collection.hashmap;

public class ExtHashMap<K, V> implements ExtMap<K, V> {

    private Node<K, V>[] table = null; // 存放数组元素，默认未初始化
    
    private int size; // 元素实际个数
    
    private float DEFAULT_LOAD_FACTOR = 0.75f; // 默认加载因子，加载因子越小，hash冲突率越低
    
    private static int DEFAULT_INITIAL_CAPACITY = 16; // table 默认大小
    
    @SuppressWarnings("unchecked")
    @Override
    public V put(K key, V value) {
        
        if (table == null) { // 判断数组大小是否为空，若为空，做初始化操作
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        
        // 如果size>12,就要扩容一倍数组
        if (size > (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR)) {
            this.resize();
        }
        
        int index = this.getIndex(key, DEFAULT_INITIAL_CAPACITY); // 获取下标位置
        Node<K, V> node = table[index];
        if (node == null) { // 未发生hash冲突
            node = new Node<K, V>(key, value, null);
            size++;
        } else { // 发生hash冲突
            Node<K, V> newNode = node;
            while (newNode != null) {
                if (newNode.getKey().equals(key) || (newNode.getKey() == key)) { // 是重复的key
                    return newNode.setValue(value); // 覆盖原先key对应的value
                } else {
                    if (newNode.next == null) { // 遍历到链表末尾向链表头添加新元素
                        node = new Node<K, V>(key, value, node);
                        size++;
                    }
                }
                newNode = newNode.next;
            }
        }
        table[index] = node;
        return value;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = this.getNode(table[getIndex(key, DEFAULT_INITIAL_CAPACITY)], key);
        return (node == null) ? null : node.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * 打印
     */
    public void print() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node =table[i];
            System.out.println("[index: "+ i + "]");
            while(node != null) {
                System.out.println("[key:" + node.getKey() + ",value:" + node.getValue() + "]");
                node = node.next;
            }
            System.out.println();
        }
    }
    
    /**
     * 对table进行扩容操作
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] newTable = new Node[DEFAULT_INITIAL_CAPACITY << 1]; // 新table大小是旧的两倍
        for (int i=0; i<table.length; i++) { // 遍历旧table
            Node<K, V> oldNode = table[i];
            while (oldNode != null) { // 遍历旧链表
                table[i] = null; // 删除原先table的值
                K oldKey = oldNode.getKey();
                int index = this.getIndex(oldKey, newTable.length); // 重新计算index
                Node<K, V> oldNext = oldNode.next;
                oldNode.next = newTable[index]; // 下标相同，原来的下一个存放为新Node
                newTable[index] = oldNode; // 将原Node赋值给新Node
                oldNode = oldNext; // 获取下一个节点
            }
        }
        table = newTable; // 将newtable赋值给table
        DEFAULT_INITIAL_CAPACITY = newTable.length;
        newTable = null; // newTable变为不可达
    }
    
    /**
     * 获取下标
     * 
     * @param key
     * @param length
     * @return
     */
    private int getIndex(K key, int length) {
        int hashCode = (key== null) ? 0 : key.hashCode();
//         int index = hashCode % length;
        int index = hashCode & (length-1); // JDK 源码算法
         return index;
    }
    
    /**
     * 获取制定数组上的节点
     * 
     * @return
     */
    private Node<K, V> getNode(Node<K, V> node, K key) {
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
    
    
    @SuppressWarnings("hiding")
    class Node<K, V> implements Entry<K, V> {

        private K key; // Map集合的key
        
        private V value; // Map集合的value
        
        private Node<K, V> next; // 下一个节点Node
        
        
        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        
    }
}
