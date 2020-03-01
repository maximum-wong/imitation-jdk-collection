package cn.kstar.imitation_jdk_collection.linkedlist;

public class ExtLinkedList<E> {

    private int size; // 链表的长度
    
    private Node first; // 头结点
    
    private Node last; // 尾节点
    
    /**
     * 添加节点
     * 
     * @param e
     */
    public void add (E e) {
        Node node = new Node();
        node.object = e;
        if (first == null) { // 头节点为空
            first = node; // first节点指向新增节点
        } else { //头结点不为空
            node.prev = last; // 新增节点的prev指针指向last节点
            last.next = node; // last节点的next指针指向新增节点
        }
        last = node; // 将新增节点置未last节点
        size++; // 链表长度加一
    }
    
    /**
     * 在指定位置上添加节点
     * 
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        this.checkElementIndex(index); // 检验下标
       Node oldNode = this.getNode(index); // 获取原来下标上的节点
       if (oldNode != null) {
           Node oldPrevNode = oldNode.prev; // 获取原来下标上的上一个节点
           Node newNode = new Node();
           newNode.object = e;
           newNode.next = oldNode; // 新增节点的next指针指向原来下标上的节点
           if (oldPrevNode == null) { // 原来下标上的上一个节点为空
               first = newNode; // 将新增节点置为first节点
           } else { // 原来下标上的上一个节点不为空
               newNode.prev = oldPrevNode; // 新增节点的prev指针指向原来下标上的上一个节点
               oldPrevNode.next = newNode; // 原来下标上的上一个节点的next指针指向新增节点
           }
           oldNode.prev = newNode; // 原来下标上的节点的prev指针指向新增节点
           size++; // 链表长度加一
       }
    }
    
    /**
     * 获取指定下标上的元素
     * 
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        this.checkElementIndex(index);
        Node node = this.getNode(index);
        return (node == null) ? null : (E) node.object;
    }
    
    /**
     * 删除指定下表上的元素
     * 
     * @param index
     */
    public void remove(int index) {
        this.checkElementIndex(index);
        Node oldNode = this.getNode(index);
        if (oldNode != null) {
            Node oldNextNode = oldNode.next; // 被删除节点的下一个节点
            Node oldPrevNode = oldNode.prev; // 被删除节点的上一个节点
            if (oldPrevNode == null) { // 被删除的节点是first节点
                first = oldNextNode; // 将被删除节点的下一个节点置为first节点
            } else {
                oldPrevNode.next = oldNextNode; // 被删除节点的上一个节点的next指针指向被删除节点的下一个节点
                oldNode.prev = null; // 被删除节点的prev指针置为空
            }
            if (oldNextNode == null) { // 删除的节点是last节点
                last = oldPrevNode; // 将被删除节点的上一个节点置为last节点
            } else {
                oldNextNode.prev = oldPrevNode; // 被删除节点的下一个节点的prev指针指向被删除节点的上一个节点
                oldNode.next = null; // 被删除节点的next指针置为空
            }
            oldNode.object = null; // 清空被删除节点的值
            size--; // 链表长度减一
        }
    }
    
    /**
     * 获取链表元素个数
     * 
     * @return
     */
    public int getSize() {
        return size;
    }
    
    /**
     * 检查元素索引是否越界
     * 
     * @param index
     */
    private void checkElementIndex(int index) {
        if (!this.isElementIndex(index)) {
            throw new IndexOutOfBoundsException("index=" + index + "越界");
        }
    }
    
    /**
     * 索引是否越界
     * 
     * @param index
     * @return
     */
    private boolean isElementIndex(int index) {
        return (index >= 0) && (index < size);
    }
    
    /**
     * 根据索引获取链表节点
     * 
     * @param index
     * @return
     */
    private Node getNode(int index) {
        this.checkElementIndex(index);
        Node node = null;
        if (first != null) {
            node = first;
            for (int i = 0; i < index; i++) { // 可以用二分法遍历
                node = node.next;
            }
        }
        return node;
    }
    
    private class Node { // 链表节点
        
        Object object; //存放元素的值
        
        Node prev; // 上一个Node
        
        Node next; // 下一个Node
    }
    
}
