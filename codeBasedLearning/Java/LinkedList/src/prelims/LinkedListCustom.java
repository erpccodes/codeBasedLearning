package prelims;

public class LinkedListCustom {
	
	Node head;
	int size;
	
    // Node class
	class Node {
		String data;
		Node next;
		
		public Node(String data) {
			this.data=data;
			this.next=null;
		}
	}
	
	public void addFirst(String data) {
		Node newNode=new Node(data);
		size++;
		if(head==null) {
			head=newNode;
		}else {
			newNode.next=head;
			head=newNode;
		}
		
	}
	
	public void addLast(String data) {
		Node newNode=new Node(data);
		size++;
		if(head==null) {
			head=newNode;
		}else {
			Node currNode=head;
			while(currNode.next!=null) {
				currNode=currNode.next;
			}
			currNode.next=newNode;
		}
	}
	
	public void printLL() {
		if(head==null) {
			System.out.print("Linked List is empty");
			return;
		}else {
			Node currNode=head;
			System.out.println("testing refernce: "+head);
			while(currNode!=null) {
				System.out.print(currNode.data+"-->");
				currNode=currNode.next;
			}
		}
	}
	
	public void deleteFirst()
	{
		if(head==null) {
			System.out.println("List is already empty");
			return;
		}else {
			head=head.next;
			size--;
		}
	}
	
	public void deleteLast() {

		if(head==null) {
			System.out.println("List is already empty");
			return;
		}else if(head.next==null) {
			head=null;
			size--;
			return;
		}
		else {
			Node currNode=head;
			while(currNode.next.next!=null) {
				currNode=currNode.next;
			}
			currNode.next=null;
			size--;
		}
	}
	
	public int size() {
		return size;
	}
	
	public void reverseLL() {
		if(head==null ||head.next==null) {
			return;
		}
		Node previous=null;
		Node curr=head;
		while(curr!=null) {
			Node nextNode=curr.next;
			curr.next=previous;
			previous=curr;
			curr=nextNode;
		}
			head=previous;
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListCustom ll = new LinkedListCustom();
		ll.addFirst("List");
		ll.printLL();
		ll.addFirst("Linked");
		ll.printLL();
		ll.deleteFirst();
		ll.printLL();
		ll.addLast("LastNode");
		ll.printLL();
		ll.deleteLast();
		ll.printLL();
		ll.addFirst("Linked");
		ll.printLL();
		System.out.println(ll.size());
		ll.reverseLL();
		ll.printLL();
	}
	
}
