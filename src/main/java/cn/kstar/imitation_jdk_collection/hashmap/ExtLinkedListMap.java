package cn.kstar.imitation_jdk_collection.hashmap;

import java.util.LinkedList;

/**
 * 基于LinkedList实现HashMap
 * @author maximum-wong
 *
 * @param <K>
 * @param <V>
 */
public class ExtLinkedListMap {

    @SuppressWarnings("unchecked")
    private LinkedList<Entry>[] table = new LinkedList[16];
    
    @SuppressWarnings("unused")
    private int size; // 实际大小
    
    /**
     * 添加元素
     * 
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        Entry newEntry = new Entry(key, value);
        int index = getIndex(key);
        LinkedList<Entry> entryList = table[index];
        if (entryList == null) {
            LinkedList<Entry> list = new LinkedList<>();
            list.add(newEntry);
            table[index] = list;
        } else {
            for (Entry entry : entryList) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
            }
            entryList.add(newEntry);
        }
        size++;
    }
    
    /**
     * 删除元素
     * 
     * @param key
     */
    public void remove(Object key) {
        Entry entry = this.getEntry(key);
        if (entry != null) {
            int index = getIndex(key);
            LinkedList<Entry> entryList = table[index];
            entryList.remove(entry);
            size--;
        }
    }
    
    /**
     * 获取元素的索引
     * 
     * @param key
     * @return
     */
    public int getIndex(Object key) {
        int hashCode = key.hashCode();
        int index =hashCode % table.length;
        return index;
    }
    
    /**
     * 获取value
     * 
     * @param key
     * @return
     */
    public Object get(Object key) {
        Entry entry = this.getEntry(key);
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
    public Entry getEntry(Object key) {
        int index = this.getIndex(key);
        LinkedList<Entry> entryList = table[index];
        if (entryList != null) {
            for (Entry entry : entryList) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
            }
        }
        return null;
    }
    
    /**
     * 内部类Entry
     * 
     * @author maximum-wong
     *
     */
    public class Entry {
        
        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
        
        private Object key;
        
        private Object value;

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

    }
}
