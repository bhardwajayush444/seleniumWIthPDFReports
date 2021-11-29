package DSA;

import io.cucumber.java.mk_latn.No;

public class linkedList {
    Node head;
    static class Node{
        int data;
        Node next;
        Node(int d){
            this.data=d;
            next=null;
        }
    }
    public void printList(){
        Node n=head;
        while(n!=null){
            System.out.println(n.data+" ");
            n=n.next;
        }
    }
    public void push(int newData){
        Node new_node=new Node(newData);
        new_node.next=head;
        head=new_node;

    }
    public void insert_after(Node prev_node,int data){
        if (prev_node == null)
        {
            System.out.println("The given previous node cannot be null");
            return;
        }
        Node new_node = new Node(data);
        new_node.next=prev_node.next;
        prev_node.next=new_node;

    }
    public void append(int new_data) {
        Node new_node = new Node(new_data);
        if(head==null){
            head=new Node(new_data);
            return;
        }
        new_node.next=null;
        Node last = head;
        while (last.next != null)
            last = last.next;

        /* 6. Change the next of last node */
        last.next = new_node;
        return;
    }
    public void delete(int key){
        Node temp=head,prev=null;
        if(temp!=null && temp.data==key){
            head=temp.next;
            return;
        }
        while(temp!=null && temp.data!=key){
            prev=temp;
            temp=temp.next;
        }
        prev.next=temp.next;
    }


    public static void main(String[] args){
        linkedList list=new linkedList();
        list.head=new Node(1);
        Node second=new Node(2);
        Node third=new Node(4);
        list.head.next=second;
        second.next=third;
        list.printList();
    }
}
