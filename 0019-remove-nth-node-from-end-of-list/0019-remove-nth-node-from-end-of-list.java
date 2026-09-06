class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) return null;
        
        ListNode ptr = head;
        ListNode temp = head;
        

        int i = 1;
        while (i <= n) {
            ptr = ptr.next; 
            i++;
        }
    
        if (ptr == null) return head.next;
        

        while (ptr.next != null) {
            ptr = ptr.next;
            temp = temp.next;
        } 
        
 
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
        
        return head;
    }
}
