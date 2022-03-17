package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2022/2/22 21:25
 *https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
160. 相交链表
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
图示两个链表在节点 c1 开始相交：
题目数据 保证 整个链式结构中不存在环。
*/
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null){
            return null;
        }
        ListNode stepA = headA;
        ListNode stepB = headB;
        //当一个链表走完时，将当前链表指针指向另一个链表的头节点，则两个指针会走过相同步长的路径
        //当没有交点时 null==null 结束
        //当有节点时，节点地址相同 结束
        while (stepA != stepB){
            stepA = stepA==null? headB:stepA.next;
            stepB = stepB==null?headA:stepB.next;
        }
        return stepA;
    }


      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
      }
}
