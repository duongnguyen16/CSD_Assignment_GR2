/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BStree;

/**
 *
 * @author ASUS
 */
public class Node<T extends Comparable<T>> {
    //data
    public T info;
    public Node<T> left;
    public Node<T> right;
    
    //constructors
    Node(){
    }
    Node(T x, Node<T> left, Node<T> right){
        this.info = x;
        this.left = left;
        this.right = right;
    }
    public Node(T x){
        this(x,null, null);
    }

    @Override
    public String toString() {
        return "Node{" + "info=" + info + ", left=" + left + ", right=" + right + '}';
    }
    
    
}
