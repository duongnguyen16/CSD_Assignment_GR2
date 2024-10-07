/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class MyList<T> {
   public Node<T> head = null;
   public Node<T> tail = null;

   public MyList() {
   }

   public Node<T> getHead() {
      return this.head;
   }

   public void setHead(Node<T> head) {
      this.head = head;
   }

   public Node<T> getTail() {
      return this.tail;
   }

   public void setTail(Node<T> tail) {
      this.tail = tail;
   }

   public boolean isEmpty() {
      return this.head == null;
   }

   public void clear() {
      this.head = null;
      this.tail = null;
   }

   public void addLast(T x) {
      Node p;
      if (this.isEmpty()) {
         p = new Node(x);
         this.head = p;
         this.tail = p;
      } else {
         p = new Node(x);
         this.tail.next = p;
         this.tail = p;
      }

   }

   public void visit(T p) {
      System.out.println(p.toString() + " ");
   }

   public void traverse() {
      for (Node<T> p = this.head; p != null; p = p.next) {
         this.visit(p.info);
      }

      System.out.println();
   }

   public void addManyLast(T[] a) {
      for (int i = 0; i < a.length; ++i) {
         this.addLast(a[i]);
      }

   }

   public void addFirst(T x) {
      Node p;
      if (this.isEmpty()) {
         p = new Node(x);
         this.head = p;
         this.tail = p;
      } else {
         p = new Node(x, this.head);
         this.head = p;
      }

   }

   public void removeFirst() {
      if (this.head != this.tail) {
         Node<T> p = this.head;
         this.head = this.head.next;
         p.next = null;
      } else {
         this.clear();
      }

   }

   public void removeLast() {
      if (this.isEmpty()) {
         this.clear();
      }

      Node p;
      for (p = this.head; p.next != this.tail; p = p.next) {
      }

      p.next = null;
      this.tail = p;
   }

   public Node<T> getFirst() {
      return this.head;
   }

   public Node<T> getLast() {
      return this.tail;
   }

   public Node<T> get(T x) {
      for (Node<T> p = this.head; p != null; p = p.next) {
         if (p.info == x) {
            return p;
         }
      }

      return null;
   }

   public Node<T> find(Node<T> p) {
      for (Node<T> m = this.head; m != null; m = m.next) {
         if (m.info.equals(p.info)) {
            return p;
         }
      }

      return null;
   }

   public Node<T> getNext(Node<T> p) {
      return p != null ? p.next : null;
   }

   public Node getPrev(Node<T> p) {
      if (p == this.head) {
         return null;
      } else {
         Node q;
         for (q = this.head; q != null && q.next != p; q = q.next) {
         }

         return q;
      }
   }

   public int size() {
      int count = 0;

      for (Node<T> p = this.head; p != null; p = p.next) {
         ++count;
      }

      return count;
   }

   public Node<T> getByIndex(int index) {
      Node<T> p = this.head;

      for (int count = 0; p != null; p = p.next) {
         if (count == index) {
            return p;
         }

         ++count;
      }

      return null;
   }

   public void insertAfter(int k, T x) {
      // Bước 1: Kiểm tra danh sách rỗng.
      if (head == null) {
         System.out.println("The list is empty.");
         return;
      }

      // Bước 2: Tìm Node tại vị trí k.
      Node<T> p = head;
      int count = 1;
      while (p != null && count < k) {
         p = p.next;
         count++;
      }

      // Bước 3: Kiểm tra nếu vị trí k hợp lệ (tìm thấy Node tại vị trí k).
      if (p == null) {
         System.out.println("Position " + k + " is out of bounds.");
      } else {
         // Bước 4: Tạo Node mới chứa giá trị x.
         Node<T> newNode = new Node<>(x);

         // Bước 5: Chèn Node mới vào sau vị trí k.
         newNode.next = p.next;
         p.next = newNode;

         // Nếu chèn vào cuối danh sách, cập nhật tail.
         if (p == tail) {
            tail = newNode;
         }
      }
   }

   public void insertBefore(Node<T> p, T x) {
      if (p == this.head) {
         this.addFirst(x);
      } else {
         Node<T> m = this.getPrev(p);
         Node<T> insert = new Node(x, p);
         m.next = insert;
         insert.next = p;
      }

   }

   public void insert(int index, T x) {
      Node<T> p = this.getByIndex(index);
      if (p != null) {
         this.insertBefore(p, x);
      }

   }

   public boolean remove(Node<T> p) {
      if (this.head != null && p != null) {
         if (p == this.head) {
            this.removeFirst();
            return true;
         } else if (p == this.tail) {
            this.removeLast();
            return true;
         } else {
            Node<T> m = this.getPrev(p);
            if (m == null) {
               return false;
            } else {
               m.next = p.next;
               p.next = null;
               return true;
            }
         }
      } else {
         return false;
      }
   }

   public boolean remove(int index) {
      Node<T> p = this.getByIndex(index);
      return this.remove(p);
   }

   public void removeAfter(Node<T> p) {
      Node<T> m = this.getNext(p);
      this.remove(m);
   }

   public void removeBefore(Node<T> p) {
      Node<T> m = this.getPrev(p);
      this.remove(m);
   }

   public void set(Node<T> p, T x) {
      if (p != null) {
         p.info = x;
      }

   }

   public Node<T> max() {
      Node<T> p = this.head;

      Node max;
      for (max = this.head; p != null; p = p.next) {
         if (p.info.equals(max.info)) {
            max = p;
         }
      }

      return max;
   }

   public void swap(Node<T> p, Node<T> q) {
      if (p != null && q != null) {
         T temp = p.info;
         p.info = q.info;
         q.info = temp;
      }
   }

   public void sort(Node p, Node q) {
      if (this.head != null) {
         for (Node b = p; b != q.next; b = b.next) {
            for (Node e = b.next; e != q.next; e = e.next) {
               if (b.info.equals(e.info)) {
                  this.swap(b, e);
               }
            }
         }

      }
   }

   public void sort(Comparator<T> c) {
      if (this.head != null) {
         for (Node<T> b = this.head; b != this.tail; b = b.next) {
            for (Node<T> e = b.next; e != null; e = e.next) {
               if (c.compare(b.info, e.info) > 0) {
                  this.swap(b, e);
               }
            }
         }

      }
   }

   public void reverse() {
      Node<T> current = this.head;

      Node next;
      Node previous;
      for (previous = null; current != null; current = next) {
         next = current.next;
         current.next = previous;
         previous = current;
      }

      this.head = previous;
      this.tail = current;
   }

   public boolean search(int x) {
      Node<T> p = this.head;

      for (int count = 0; p != null; p = p.next) {
         if (count == x) {
            return true;
         }

         ++count;
      }

      return false;
   }
}
