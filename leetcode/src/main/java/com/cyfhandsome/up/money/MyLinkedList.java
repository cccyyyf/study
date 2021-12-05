package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2021/12/4 20:16
 * 707. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值
 * ，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 */
public class MyLinkedList {


    class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 链表大小
     */
    int size;

    /**
     * 链表头部
     */
    ListNode head;

    /**
     * 初始化链表
     */
    public MyLinkedList() {
        this.size = 0;
        head = new ListNode();
    }

    public int get(int index) {
        if(index < 0 || index >= size){
            return  -1;
        }
        ListNode cur = head ;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        this.addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        this.addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if(index > size){
            return;
        }
        if(index < 0){
            index = 0;
        }
        size++;
        //获取当前节点的前面一个节点
        ListNode perNode = head;
        for (int i = 0; i < index; i++) {
            perNode = perNode.next;
        }
        ListNode curNode = new ListNode(val);
        curNode.next = perNode.next;
        perNode.next = curNode;
    }

    public void deleteAtIndex(int index) {
        if(index >= size || index <0){
            return;
        }
        size --;
        ListNode perNode = head;
        for (int i = 0; i < index; i++) {
            perNode = perNode.next;
        }
        perNode.next = perNode.next.next;

    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
