package site.zhaoyangjue.basecode.algorithm.list;


/**
 * 算法-链表相关实现
 */
public class ListUnitPart1 {
    /**
     * 反转链表（206）
     * 1.递归处理当前节点的下一个节点：ListNode cur = reverseList(head.next);
     * 2.将递归中的当前节点的next的next指向当前节点，即可实现当前节点和下一个节点之间的反转：head.next.next = head;
     * 3.将当前节点与下一个节点之间的关系去掉：head.next = null;
     * 4.返回当前节点给上一层级的递归；
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    /**
     * 相交链表 160
     * 1.边界判断，如果存在为null的链表，则直接返回null;
     * 2.新增pA、pB，分别对应两个链表，两指针开始移动，直到两个指针相同，相同即为相交节点；
     * 3.如果其中一个走到链表最后，那么将该指针指向另一条链表的头节点；另一条链表也是如此操作；
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }
            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }
        return pA;
    }



}


