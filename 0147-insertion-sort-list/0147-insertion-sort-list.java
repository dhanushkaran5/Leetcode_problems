class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode curr = head,
            pHead = new ListNode(0);
            
        while(curr != null){
            ListNode c = pHead.next,
                p = pHead;

            while(c != null && c.val <= curr.val) {
                p = p.next;
                c = c.next; 
            }

            ListNode next = curr.next;
            curr.next = c;
            p.next = curr;
            curr = next; 
        }  
        return pHead.next;
    }
}