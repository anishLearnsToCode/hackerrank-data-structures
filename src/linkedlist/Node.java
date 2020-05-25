package linkedlist;

import java.util.Scanner;

public class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
    }
    Node(){ }

    public void input(int dat){
        this.data = dat;
        Node temp = this;
        Scanner s = new Scanner(System.in);
        while (data != -1){
            int data = s.nextInt();
            if(data == -1) {
                break;
            }

            Node newNode = new Node(data);
            temp.next = newNode;
            temp = temp.next;
        }
    }

    public void print(){
        Node temp = this;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public int size(){
        int count;
        Node temp = this;
        for(count=0 ; temp != null; count++){
            temp = temp.next;
        }
        return count;
    }
}


class DoublyLL{
    int data;
    DoublyLL next;
    DoublyLL prev;

    public void print(){
        DoublyLL temp = this;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public void input(){
        DoublyLL temp = this;
        Scanner s = new Scanner(System.in);
        while (data != -1){

            int data = s.nextInt();

            if(data == -1) {
                temp = null;
                break;
            }

            temp.data = data;
            temp.next = new DoublyLL();
            temp.next.prev = temp;
            temp = temp.next;
        }
    }

    DoublyLL(int data){
        this.data = data;
    }
    DoublyLL(DoublyLL node){
        this.data = node.data;
    }
    DoublyLL(Node node){
        this.data = node.data;
    }
    DoublyLL(){}
}
