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

    /**
     * 合并两个升序列表 21
     * 1.构建一个虚拟节点，用于标记链表头位置；
     * 2.创建一个指针，指向当前比较的链表；
     * 3.比较两个链表的节点大小，将小的节点挂载在当前的指针下，同时移动小节点上的原有指针；
     * 注意，先移动pre指针，再移动原链表指针。
     * 4.比较之后，判断原链表上是否还存在节点，如果存在，则挂载在创建的指针上；
     * 5.返回虚拟节点的next，即为排序之后的结果。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else{
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        if (list1!=null) {
            pre.next = list1;
        }
        if (list2!=null) {
            pre.next = list2;
        }
        return dummy.next;
    }

    /**
     * 分隔链表 86
     * 1.构造大小节点列表，首尾指针均指向当前节点；
     * 2.遍历原链表，比较节点值与指定值的大小关系；
     *  如果当前节点比指定值大，那么将当前节点挂载在大节点列表上；
     *  如果当前节点比指定值小，那么将当前节点挂载在小节点列表上；
     * 3.将大节点列表挂载在小节点列表上；
     * 4.返回小节点列表的next；
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x){
        ListNode bigHead = new ListNode(-1);
        ListNode bigTail = bigHead;
        ListNode smallHead = new ListNode(-1);
        ListNode smallTail = smallHead;
        while (head != null) {
            if (head.val < x){
                smallTail.next = head;
                smallTail = head;
            } else {
                bigTail.next = head;
                bigTail = head;
            }
            head = head.next;
        }
        smallTail.next = bigHead.next;
        bigTail.next = null;
        return smallHead.next;
    }

    /**
     * 环形链表2 142
     * 1.利用快慢指针找到环形链表入口节点；
     * 2.在1中节点位置和头节点间同步向前移动对应的指针，直到两指针重合；
     * 3.在1中判断的时候需要注意遍历的跳出条件：fast != null && fast.next != null
     * 4.在2中需要注意的是当前环形入口节点和头节点。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode tempA = head;
                ListNode tempB = fast;
                while (tempA != tempB) {
                    tempA = tempA.next;
                    tempB = tempB.next;
                }
                return tempA;
            }
        }
        return null;
    }

    /**
     * 反转链表2 92
     * 1.构建虚拟头节点，并与原链表产生关联；
     * 2.寻找到指定区间的左边界，开始遍历；
     * 3.遍历逻辑为将当前节点与后一个节点进行交换，实现部分反转；
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        for (int i = 0; i < right-left; i++) {
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }


}


