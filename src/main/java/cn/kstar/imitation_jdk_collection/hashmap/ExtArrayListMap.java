package cn.kstar.imitation_jdk_collection.hashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于ArrayList实现HashMap
 * 缺点：效率低
 * 
 * @author maximum-wong
 *
 */
public class ExtArrayListMap<K, V> {

    private List<Entry<K, V>> table = new ArrayList<>();
    
    /**
     * 添加元素
     * 
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        Entry<K, V> existEntry = this.getEntry(key);
        if (existEntry != null) { // 判断key是否重复
            existEntry.setValue(value);
            return;
        }
        Entry<K, V> entry = new Entry<>(key, value);
        table.add(entry);
    }
    
    /**
     * 删除元素
     * 
     * @param key
     */
    public void remove(K key) {
        Entry<K, V> existEntry = this.getEntry(key);
        if (existEntry != null) {
            table.remove(existEntry);
        }
    }
    
    /**
     * 获取value
     * 
     * @param key
     * @return
     */
    public V get(K key) {
        Entry<K, V> entry = this.getEntry(key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }
    
    /**
     * 获取Entry对象
     * 
     * @param key
     * @return
     */
    public Entry<K, V> getEntry(K key) {
        for (Entry<K, V> entry : table) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
        }
        return null;       
    }
    
    /**
     * 内部类Entry
     * 
     * @author maximum-wong
     *
     * @param <K>
     * @param <V>
     */
    @SuppressWarnings({ "hiding" })
    public class Entry<K, V> {
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        private K key;
        
        private V value;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
        
    }
}
