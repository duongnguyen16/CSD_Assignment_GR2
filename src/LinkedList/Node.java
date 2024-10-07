/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

/**
 *
 * @author ASUS
 */
public class Node<T> {
   public T info;
   public Node<T> next;

   public Node() {
   }

   public Node(T x, Node<T> p) {
      this.info = x;
      this.next = p;
   }

   public Node(T x) {
      this(x, (Node)null);
   }
}
