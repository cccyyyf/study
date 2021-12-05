package com.cyfhandsome.up.money;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cyf
 * @date 2021/12/4 20:00
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DetectCycleList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * hash
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        Set<ListNode> statisticalMap = new HashSet<>();
        while (head != null) {
            if (statisticalMap.contains(head)) {
                return head;
            }
            statisticalMap.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * 快慢指针，当快慢指针相遇时，确定此链表有回环
     * 将快指针恢复到头节点，之后让快指针按照一个节点速度移动，快慢指针相遇的地方为开始回环节点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode) {
                break;
            }
        }
        if(fastNode== null){
            return null;
        }
        fastNode = head;
        while (fastNode != slowNode){
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }

        return fastNode;
    }
}
