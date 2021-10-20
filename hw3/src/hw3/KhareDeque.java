package hw3;

/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 * 
 * @author Khare, Acuna
 * @version 09/28/21
 */
import java.util.NoSuchElementException;
    
public class KhareDeque<Item> implements Deque<Item> {
	
	// Node attributes
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    /**
     * Program entry point for dequeue. 
     * @param args command line arguments
     */
    public KhareDeque() {
    	head= null;
    	tail=null;
    	size=0;
    }

    public static void main(String[] args) {
        KhareDeque<Integer> deque = new KhareDeque<>();

        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();        
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());   

        //deque features
        System.out.println(deque.dequeueFront());        
        deque.enqueueFront(1);
        deque.enqueueFront(11);                         
        deque.enqueueFront(3);                 
        deque.enqueueFront(5);         
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());        
        System.out.println(deque.last());                
        deque.dequeueFront();
        deque.dequeueFront();        
        System.out.println(deque.first());        
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());            
    }

	@Override
	public void enqueueFront(Item element) {
		Node<Item> node = new Node<Item>(element);
		
		if(head==null){
			head=node;
			tail=node;
		}
		else {
			node.setNext(head);
			node.setPrev(node);
			head= node;
		}
		size++;
	}

	@Override
	public void enqueueBack(Item element) {
		Node<Item> node = new Node<Item>(element);
		if(tail==null) {
			tail= node;
			head= node;
		}
		else {
			tail.setNext(node);
			node.setPrev(tail);
			tail=node;
		}
		
		size++;
		
	}

	@Override
	public Item dequeueFront() throws NoSuchElementException {
		if(head== null) {
			throw new NoSuchElementException("Deque is Empty.");
		}
		
		Item item= head.getItem();
		head=head.getNext();
		
		if(head==null) {
			tail =null;
		}
		
		else{
			head.setPrev(null);
		}
		
		size--;
		return item;
	}

	@Override
	public Item dequeueBack() throws NoSuchElementException {
		if(tail== null) {
			throw new NoSuchElementException("Deque is Empty.");
		}
		Item item = tail.getItem();
		
		tail = tail.getPrev();
		
		if(tail==null) {
			head=null;
		}
		else {
			tail.setNext(null);
		}
		
		return item;
	}

	@Override
	public Item first() throws NoSuchElementException {
		if(head==null) {
			throw new NoSuchElementException("Deque is empty.");
		}
		Item item = head.getItem();
		return item;
	}

	@Override
	public Item last() throws NoSuchElementException {
		if(tail==null) {
			throw new NoSuchElementException("Deque is empty.");
		}
		Item item = tail.getItem();
		return item;
	}

	@Override
	public boolean isEmpty() {
		return head==null||tail==null;
	}

	@Override
	public int size() {
		return size;
	}
	
	public String toString() {
		if(head==null|| tail==null) {
			return "empty";
		}
		String result="";
		
		Node<Item> node =head;
		
		while(node!=null) {
			result= result+ node.getItem()+" ";
			node=node.getNext();
		}
		
		return result;
	}
	
	class Node<Item> {
		
		Item element;
		Node next=null;
		Node prev=null;
		
		public Node(Item element) {
			this.element= element;
		}
		
		public void setNext(Node<Item> next) {
			this.next= next;
		}
		
		public void setPrev(Node<Item> prev) {
			this.prev = prev;
		}
		
		public Item getItem() {
			return element;
		}
		
		public Node<Item> getNext() {
			return next;
		}
		
		public Node<Item> getPrev() {
			return prev;
		}

	}

} 