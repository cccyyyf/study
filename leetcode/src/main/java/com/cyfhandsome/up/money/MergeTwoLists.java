package com.cyfhandsome.up.money;

import java.util.*;

/**
 * @author cyf
 * @date 2022/2/22 11:19
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoLists {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode perhard = new ListNode(-1);
        ListNode perv = perhard;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                perv.next = list2;
                list2 = list2.next;
            } else {
                perv.next = list1;
                list1 = list1.next;
            }
            perv = perv.next;
        }
        perv.next = list1 == null ? list2 : list1;

        return perhard.next;

    }

    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return  list1;
        }
        if(list1.val > list2.val){
            list2.next = mergeTwoLists1(list1,list2.next);
            return list2;
        }else {
            list2.next = mergeTwoLists1(list1.next,list2);
            return list1;
        }
    }



    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode2 = new ListNode(1, new ListNode(2, new ListNode(4)));
        mergeTwoLists1(listNode1, listNode2);
    }


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
}
