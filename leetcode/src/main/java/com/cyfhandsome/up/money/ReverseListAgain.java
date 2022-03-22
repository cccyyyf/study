package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2022/3/20 18:15
 */
public class ReverseListAgain {

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
    }

    /**
     * 反转链表,递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head== null || head.next==null){
            return head;
        }
        ListNode reverseList  = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverseList;
    }

    public static void main(String[] args) {
       ListNode listNode = new ListNode(1,new ListNode(2
               ,new ListNode(3,new ListNode(4,new ListNode(5)))));
       ReverseListAgain reverseListAgain = new ReverseListAgain();
       reverseListAgain.reverseList(listNode);

    }
}
