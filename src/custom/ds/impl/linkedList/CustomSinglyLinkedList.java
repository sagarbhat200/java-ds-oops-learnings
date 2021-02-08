package custom.ds.impl.linkedList;

public class CustomSinglyLinkedList<T> {
    private int size;
    private ListNode<T> head;

    public CustomSinglyLinkedList(){
        this.size=0;
        ListNode dummyNode = new ListNode();
        this.head=dummyNode;
    }

    public void addFirst(T data){
        ListNode newNode = new ListNode(data);
        newNode.next=head.next;
        head.next= newNode;
        this.size+=1;
    }

    public void addLast(T data){
        ListNode newNode = new ListNode(data);
        ListNode cur=head.next;
        while(cur.next!=null){
            cur=cur.next;
        }
        cur.next=newNode;
        size+=1;
    }

    public T removeFirst(){
      if(size<=0)
          return null;
      T data = (T)head.next.data;
      head.next=head.next.next;
      this.size--;
      return data;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("List size is "+this.size+". Contents of the list are:{ \n");
        ListNode cur=head.next;
        while(cur!=null){
            sb.append("   "+cur.data.toString()+"\n");
            cur=cur.next;
        }
        sb.append("}");
        return sb.toString();
    }

    static class ListNode<T>{
        ListNode next;
        T data;
        public ListNode(){
            this.next=null;
            this.data=null;
        }
        public ListNode(T data){
            this.next=null;
            this.data=data;
        }
    }

//    public static void main(String[] args) {
//        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList<>();
//        list.addFirst(1);
//        list.addLast(2);
//        list.addFirst(3);
//        System.out.println(list.removeFirst());
//        System.out.println(list.toString());
//    }

}
