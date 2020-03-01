package cn.kstar.imitation_jdk_collection.arraylist;

public interface ExtList<E> {

    /**
     * 添加元素
     * 
     * @param e
     */
    void add(E e);
    
    /**
     * 在指定位置上添加元素
     * 
     * @param index
     * @param e
     */
    void add(int index, E e);
    
    /**
     * 删除指定位置上元素
     * 
     * @param index
     * @return
     */
    E remove(int index);
    
    /**
     * 删除元素
     * 
     * @param e
     * @return
     */
    boolean remove(E e);
    
    /**
     * 获取元素个数
     * 
     * @return
     */
    int getSize();
    
    /**
     * 获取指定索引上的元素
     * 
     * @param index
     * @return
     */
    E get(int index);
}
