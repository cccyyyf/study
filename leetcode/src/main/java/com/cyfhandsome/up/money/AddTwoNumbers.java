package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2021/12/5 22:14
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null,tail = null;

        int carryNum = 0;
        while (l1 != null || l2 != null) {
            int l1Num = l1 != null ? l1.val : 0;
            int l2Num = l2 != null ? l2.val : 0;

            int sum = l1Num + l2Num + carryNum;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carryNum = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if(carryNum > 0){
            tail = new ListNode(carryNum);
        }
        return head;
    }
}
