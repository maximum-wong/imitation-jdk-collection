package cn.kstar.imitation_jdk_collection.arraylist;

import java.util.Arrays;

public class ExtArrayList<E> implements ExtList<E> {

    private transient Object[] elementData; // 保存ArrayList数据的数组
    
    private int size; // 元素的数量
    
    private static final int DEFAULT_CAPACITY = 10; // 默认数组容量
    
    public ExtArrayList() { // 默认初始化容量是10
        this(DEFAULT_CAPACITY);
    }
    
    public ExtArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始容量不能小于0");
        }
        elementData = new Object[initialCapacity];
    }
    
    @Override
    public void add(E e) {
        this.ensureExplicitCapacity(size+1); // 是否需要扩容
        elementData[size++] = e;
    }

    @Override
    public void add(int index, E e) {
        this.ensureExplicitCapacity(size+1); // 是否需要扩容
        System.arraycopy(elementData, index, elementData, index + 1, size-index); // 把index及以后的元素往后挪一位
        elementData[index] = e; // 将index设为插入的值
        size++;
    }

    @Override
    public E remove(int index) {
        E e = get(index);
        int numMoved = size - index -1; // 删除制定元素后需要挪动元素的个数
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            elementData[--size] = null; // 将最后一个元素置为空
        }
        return e;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(E e) { // 相同元素只删除一个
        if (e == null) {
            for (int i = 0; i < elementData.length; i++) {
                E obj = (E) elementData[i];
                if (obj == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < elementData.length; i++) {
                E obj = (E) elementData[i];
                if (obj.equals(e)) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        this.rangeCheck(index);
        return (E) elementData[index];
    }

    /**
     * 数组扩容
     * 
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        if (size == elementData.length) { // 元素个数与数组长度相同时需要进行扩容
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1); // 扩容0.5倍
            if (newCapacity - minCapacity < 0) { // 如果扩容后长度小于当前元素个数+1，选择当前元素个数+1
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity); // 将元素内容拷贝到新数组上去
        }
    }
    
    /**
     * 检查是否越界
     * 
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + "越界");
        }
    }
}
