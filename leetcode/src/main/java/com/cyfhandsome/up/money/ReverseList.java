package com.cyfhandsome.up.money;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author cyf
 * @date 2021/12/5 20:41
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseList {
    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    /**
     * 栈反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode node = stack.pop();
        ListNode result = node;
        while (!stack.isEmpty()) {
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;
        return result;
    }

    /**
     * 双链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        //head 1->2->3->4->5
        ListNode newNode = null;
        while (head != null) {
            //使用临时节点为了重置head
            //2->3->4->5
            ListNode temp = head.next;
            //将新链表赋值到head的下一个节点
            //1->null
            head.next = newNode;
            newNode = head;
            head = temp;
        }

        return newNode;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverse = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
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
        ListNode listNode = reverseList(head);
        while (listNode != null) {

            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}
