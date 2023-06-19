import java.util.*;

public class demo {

     static class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode end=head;
        ListNode start=head;
        ListNode beforeStart=null;
        ListNode prev=head;
        for(int i=1;i<=right;i++){
            System.out.println("end"+end.val);
            if(i==left-1){
                beforeStart=end;
                end=end.next;
            }
            else if(i==left){
                start=end;
                prev=end;
                end=end.next;

            }
            else if(i>left){
                ListNode next=end.next;
                end.next=prev;
                prev=end;
                end=next;
            }
            else{
                end=end.next;
            }
            System.out.println("prev"+prev.val);
            System.out.println("end"+end.val);
            System.out.println("start"+start.val);
            System.out.println(i);
        }
        // System.out.println("prev"+prev.val);
        // System.out.println("end"+end.val);
        // System.out.println("start"+start.val);

        start.next=end;
        if(beforeStart==null){
            head=prev;
        }
        else{
            beforeStart.next=prev;
        }
        return head;
    }
    public static void main(String[] args) {
        ListNode head5=new ListNode(5);
        ListNode head4=new ListNode(4,head5);
        ListNode head3=new ListNode(3,head4);
        ListNode head2=new ListNode(2,head3);
        ListNode head=new ListNode(1,head2);
        ListNode node=reverseBetween(head,2,4);



    }
}








//   "<div>abc</div><p><em><i>test test test</b></em></p>"
//    <div><div><b></b></div></p>