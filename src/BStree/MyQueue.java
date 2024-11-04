/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BStree;

import java.util.LinkedList;

/**
 *
 * @author ASUS
 */
public class MyQueue<T extends Comparable<T>> {
    LinkedList<Node<T>> queue;
    
    public MyQueue() {
        this.queue = new LinkedList<>();
    }
    
    public void clear() {
        this.queue.clear();
    }
    
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    
    public void enqueue(Node<T> x) {
        this.queue.addLast(x);
    }
    
    public Node<T> dequeue() {
        if (isEmpty()) {
            return null;
        }
        return this.queue.removeFirst();
    }
    
    public Node<T> front() {
        if (isEmpty()) {
            return null;
        }
        return this.queue.getFirst();
    }
}
