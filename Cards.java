/**
 * Name         : YEO WEI TECK VICTOR
 * Matric No    : A0154004X
 * THIS PROGRAM WILL CREATE A LIST OF CARDS BASED ON THE CARDS AND CONDUCT A NUMBER OF OPERATIONS
 * BASED ON THE QUERIES
 */

import java.util.*;

public class Cards {
  
  public void run() {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt(); //N: number of cards, N[5,100]
    sc.nextLine();
    TailedLinkedList<String> cards = new TailedLinkedList<String>();
    
    for(int i = 0; i < N; i++)
      cards.addLast(sc.nextLine());
    
    int Q = sc.nextInt(); //Q: Number of Queries, Q[1,500]
    sc.nextLine();
    
    for(int i = 0; i < Q; i++){
      
      String query = sc.nextLine();
      String[] modQuery = query.split(" ");
      
      switch(modQuery[0]){
       
        case "details":{
          int curIndex = 1;
          int detailIndex = Integer.valueOf(modQuery[1]);
          
          for(ListNode<String> n = cards.getHead(); n != null; n = n.getNext()){
            if(detailIndex==curIndex){
              String[] details = n.getElement().split(" ");
              System.out.println(details[0]+ " " + details[1]);
              break;
            }
            curIndex++;
          }
          break;
        }
        
        case "position":{
          
          int index = 1;          
          for(ListNode<String> n = cards.getHead(); n!= null; n = n.getNext()){
            
            String temp = n.getElement();
            String[] tempArr = temp.split(" ");
            if(modQuery[1].equals(tempArr[0])){
              System.out.println(index);
              break;
            }
            index++;
          }
          break;
        }
        
        case "print":{
          
          for(ListNode<String> n = cards.getHead(); n!= null; n = n.getNext()){
            
            String temp = n.getElement();
            String[] tempArr = temp.split(" ");
            if(n.getNext() != null)
              System.out.print(tempArr[0] + " ");
            else
              System.out.println(tempArr[0]);
          }
          break;
        }
        
        case "swap":{
          
          int indexA = Integer.valueOf(modQuery[1]);
          int indexB = Integer.valueOf(modQuery[2]);
          int indexC = Integer.valueOf(modQuery[3]);
          int indexD = Integer.valueOf(modQuery[4]);
          int tempA = indexA;
          int tempC = indexC;
          int curIndex = 1;
          TailedLinkedList<String> temp1 = new TailedLinkedList<String>();
          TailedLinkedList<String> temp2 = new TailedLinkedList<String>();
          ListNode<String> temp1Prev = null;
          ListNode<String> temp1Next = null;
          ListNode<String> temp2Prev = null;
          boolean temp2PrevExists = false;
          boolean temp1PrevExists = false;
          
          for(ListNode<String> n = cards.getHead(); n != null; n=n.getNext()){
            
            if(curIndex == indexB+1 && curIndex != indexC){
              
              
              temp1Next = new ListNode<String>(n.getElement());
            }
            
            if(curIndex == indexA-1){
              temp1Prev = new ListNode<String>(n.getElement());
              temp1PrevExists = true;
            }            
            else if(curIndex == indexA){
              for(ListNode<String> x = n; x != null; x=x.getNext()){
                if(tempA != indexB+1){
                  temp1.addLast(x.getElement());
                  tempA++;
                }
                else
                  break;
              }
            }            
            else if(curIndex == indexC-1 && curIndex != indexB){
              
              temp2Prev = new ListNode<String>(n.getElement());
              temp2PrevExists = true;
            }              
            else if(curIndex == indexC){
              for(ListNode<String> x = n; x != null; x=x.getNext()){
                if(tempC != indexD+1){
                  temp2.addLast(x.getElement());
                  tempC++;
                }
                else
                  break;
              }
            }
            curIndex++;
          }
          //step1
          for(ListNode<String> n = cards.getHead(); n != null; n=n.getNext()){
            if(n.getElement().equals(temp2.getTail().getElement())){
              temp1.getTail().setNext(n.getNext());
              break;
            }
          }
          
          //step2
          if(temp2PrevExists){
            for(ListNode<String> n = cards.getHead(); n != null; n=n.getNext()){
              if(n.getElement().equals(temp2Prev.getElement())){
                n.setNext(temp1.getHead());
                break;
              }
            }
            if(!temp1PrevExists){ //if there is no element before first value to be swapped
              
              int k = 0;

              for(ListNode<String> n = cards.getHead(); n != null; n=n.getNext()){
                if(n.getElement().equals(temp1Next.getElement())){
                  temp2.getTail().setNext(n);
                  break;
                }
              }

              cards = new TailedLinkedList<String>(); //emptying cards
              
              for(ListNode<String> n = temp2.getHead(); n != null; n=n.getNext()){
                cards.addLast(n.getElement());
              }
            }
            else{
              for(ListNode<String> n = cards.getHead(); n != null; n=n.getNext()){
                if(n.getElement().equals(temp1Next.getElement())){
                  temp2.getTail().setNext(n);
                  break;
                }
              }
              for(ListNode<String> n = cards.getHead(); n != null; n=n.getNext()){
                if(n.getElement().equals(temp1Prev.getElement())){
                  n.setNext(temp2.getHead());
                  break;
                }
              }
            }
          }
          else{
            temp2.getTail().setNext(temp1.getHead());
            if(!temp1PrevExists){
              cards = new TailedLinkedList<String>();
              for(ListNode<String> n = temp2.getHead(); n != null; n=n.getNext()){
                cards.addLast(n.getElement());
              }
            }
            else{
              for(ListNode<String> n = cards.getHead(); n != null; n=n.getNext()){
                if(n.getElement().equals(temp1Prev.getElement())){
                  n.setNext(temp2.getHead());
                }
              }
            }
          }
          System.out.println("swap has been performed");
          break;
        }
        case "shuffle":{
          
          TailedLinkedList<String> deck1 = new TailedLinkedList<String>();
          TailedLinkedList<String> deck2 = new TailedLinkedList<String>();
          double counter = 1;
          int numCards = cards.size();
          for(ListNode<String> n = cards.getHead(); n!=null;n=n.getNext()){
            if(counter <= Math.ceil(numCards/2.0)){
              deck1.addLast(n.getElement());
            } 
            else{
              deck2.addLast(n.getElement());
            }
            counter++;
          }
          int ctr = 1;
          cards = new TailedLinkedList<String>();
          while(ctr <= numCards){
            if(ctr%2!=0 && deck1.size() != 0){
              cards.addLast(deck1.getHead().getElement());
              deck1.removeFirst();
            }
            else if(deck2.size() != 0){
              cards.addLast(deck2.getHead().getElement());
              deck2.removeFirst();
            }
            ctr++;
          }
          
          System.out.println("shuffle has been performed");
          break;
        }     
      }
    }
  }
  public static void main(String[] args) {
    Cards myCards = new Cards();
    myCards.run();
  }
}

class TailedLinkedList<E> {
  
  // Data attributes
  private ListNode<E> head;
  private ListNode<E> tail;
  private int num_nodes;
  
  public TailedLinkedList() {
    this.head = null;
    this.tail = null;
    this.num_nodes = 0;
  }

  // Return true if list is empty; otherwise return false.
  public boolean isEmpty() {
    return (num_nodes == 0);
  }
  
  // Return number of nodes in list.
  public int size() {
    return num_nodes;
  }
  
  // Return value in the first node.
  public E getFirst() throws NoSuchElementException {
    if (head == null)
      throw new NoSuchElementException("can't get from an empty list");
    else
      return head.getElement();
  }
  
  // Return true if list contains item, otherwise return false.
  public boolean contains(E item) {
    for (ListNode<E> n = head; n != null; n = n.getNext())
      if (n.getElement().equals(item))
      return true;
    
    return false;
  }

  // Add item to front of list.
  public void addFirst(E item) {
    head = new ListNode<E>(item, head);
    num_nodes++;
    if (num_nodes == 1) tail = head;
  }
  
  // Return reference to first node.
  public ListNode<E> getHead() {
    return head;
  }
  
  // Return reference to last node of list.
  public ListNode<E> getTail() {
    return tail;
  }
  
  // Add item to end of list.
  public void addLast(E item) {
    if (head != null) {
      tail.setNext(new ListNode<E>(item));
      tail = tail.getNext();
    } else {
      tail = new ListNode<E>(item);
      head = tail;
    }
    num_nodes++;
  }
  
  // Remove node after node referenced by current
  public E removeAfter(ListNode<E> current) throws NoSuchElementException {
    E temp;
    if (current != null) {
      ListNode<E> nextPtr = current.getNext();
      if (nextPtr != null) {
        temp = nextPtr.getElement();
        current.setNext(nextPtr.getNext());
        num_nodes--;
        if (nextPtr.getNext() == null) // last node is removed
          tail = current;
        return temp;
      } else
        throw new NoSuchElementException("No next node to remove");
    } else { // if current is null, we want to remove head
      if (head != null) {
        temp = head.getElement();
        head = head.getNext();
        num_nodes--;
        if (head == null)
          tail = null;
        return temp;
      } else
        throw new NoSuchElementException("No next node to remove");
    }
  }
  
  public void addAfter(ListNode<E> current, E item){
    if(current != null){
      current.next = new ListNode<E>(item,current.next);
    }else{
      head = new ListNode<E>(item,head);
    }
    num_nodes++;
  }
  
  // Remove first node of list.
  public E removeFirst() throws NoSuchElementException {
    return removeAfter(null);
  }
  
  // Remove item from list
  public E remove(E item) throws NoSuchElementException {
    ListNode<E> current = head;
    if (current == null) {
      throw new NoSuchElementException("No node to remove");
    }
    if (current.getElement().equals(item)) {
      return removeAfter(null);
    }
    while (current.getNext().getElement() != null) {
      if (current.getNext().getElement().equals(item)) {
        return removeAfter(current);
      }
      current = current.getNext();
    }
    throw new NoSuchElementException("No node to remove");
  }
}

class ListNode<E> {
  protected E element;
  protected ListNode<E> next;
  
  /* constructors */
  public ListNode(E item) {
    this.element = item;
    this.next = null;
  }
  
  public ListNode(E item, ListNode<E> n) {
    element = item;
    next = n;
  }
  
  /* get the next ListNode */
  public ListNode<E> getNext() {
    return this.next;
  }
  
  /* get the element of the ListNode */
  public E getElement() {
    return this.element;
  }
  
  public void setNext(ListNode<E> item) {
    this.next = item;
  }
  
  public void setElement(E item) {
    this.element = item;
  }
}

