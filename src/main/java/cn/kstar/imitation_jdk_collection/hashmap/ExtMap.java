package cn.kstar.imitation_jdk_collection.hashmap;

/**
 * ExtMap接口
 * @author maximum-wong
 *
 * @param <K>
 * @param <V>
 */
public interface ExtMap<K, V> {

    /**
     * 插入数据
     * 
     * @param key
     * @param vaue
     * @return
     */
    V put(K key, V vaue);
    
    /**
     * 查询元素
     * 
     * @param key
     * @return
     */
    V get(K key);
    
    /**
     * 获取元素个数
     * 
     * @return
     */
    int size();
    
    /**
     * Node节点
     * 
     * @author maximum-wong
     *
     * @param <K>
     * @param <V>
     */
    interface Entry<K, V> {
        
        K getKey();
        
        V getValue();
        
        V setValue(V value);
    }
    
}
