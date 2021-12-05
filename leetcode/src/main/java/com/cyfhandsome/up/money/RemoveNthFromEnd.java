package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2021/11/29 8:59
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 19. 删除链表的倒数第 N 个结点
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class RemoveNthFromEnd {

    static class ListNode {
        int val;
        ListNode next;

        public void setVal(int val) {
            this.val = val;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode resultNode = new ListNode(-1, head);

        //使用快慢指针
        //1、先定义快指针像前走n步
        //2、定义慢指针和快指针一块走
        //3、快指针走完了，慢指针到倒数n+1位置
        ListNode fastNode = resultNode;
        ListNode slowNode = resultNode;
        while (n-- > 0) {
            fastNode = fastNode.next;
        }
        ListNode slowPevNode = null;
        while (fastNode != null) {
            slowPevNode = slowNode;
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        slowPevNode.next = slowNode.next;

        return resultNode.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        head3.setNext(head4);
        head2.setNext(head3);
        head1.setNext(head2);
        head.setNext(head1);
        ListNode listNode = removeNthFromEnd(head, 1);
        while (listNode != null) {

            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}
