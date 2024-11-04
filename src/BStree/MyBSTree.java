/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BStree;

import LinkedList.MyList;

/**
 *
 * @author ASUS
 */
public class MyBSTree<T extends Comparable<T>> {

    public Node<T> root;

    public MyBSTree() {
        this.root = null;
    }

    public void clear() {
        this.root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }
    // insert when bstree is empty

    public void insert(T x) {
        if (isEmpty()) {
            // System.out.println("Insert root: " + x);
            Node<T> newNode = new Node<>(x);
            this.root = newNode;
            return;
        }

        Node<T> parentOfCurrent = null;
        Node<T> current = this.root;

        while (current != null) {
            if (current.info.equals(x)) { // Use equals for comparing values
                System.out.println("The key " + x + " already exists");
                return;
            }
            parentOfCurrent = current;
            if (x.compareTo(current.info) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        Node<T> newNode = new Node<>(x);
        if (x.compareTo(parentOfCurrent.info) < 0) {
            // System.out.println("Insert " + parentOfCurrent.info + ".left " + x);
            parentOfCurrent.left = newNode;
        } else {
            // System.out.println("Insert " + parentOfCurrent.info + ".right " + x);
            parentOfCurrent.right = newNode;
        }
    }

    void preOrder(Node<T> p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    void postOrder(Node<T> p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    void inOrder(Node<T> p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    void breadth() {
        if (root == null) {
            return;
        }
        MyQueue<T> q = new MyQueue<>();
        q.enqueue(root);
        Node<T> p;
        while (!q.isEmpty()) {
            p = q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            visit(p);
        }
    }

    public void visit(Node<T> p) {
        System.out.print(p.toString() + " ");
    }

    // public void insertMany(int[] a){
    // for(int i = 0; i<a.length;i++){
    // insert(a[i]);
    // }
    // }
    //
    public Node<T> search(T x) {
        Node<T> current = root;
        while (current != null) {
            if (current.info.equals(x)) {
                return current; // Found
            }
            current = (x.compareTo(current.info) < 0) ? current.left : current.right;
        }
        return null; // Not found
    }

    // merge deletion
    public void deleteByMerging(T x) {
        // Check if the BST is empty
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion");
            return;
        }

        // Search for the node to delete and its parent
        Node<T> deleteNode = root;
        Node<T> parentOfDeleteNode = null;

        // Searching for the node with value x
        while (deleteNode != null && !deleteNode.info.equals(x)) {
            parentOfDeleteNode = deleteNode;
            if (x.compareTo(deleteNode.info) < 0) {
                deleteNode = deleteNode.left;
            } else {
                deleteNode = deleteNode.right;
            }
        }

        // If the node to be deleted is not found
        if (deleteNode == null) {
            System.out.println("The key " + x + " does not exist, no deletion");
            return;
        }

        // Case 1: Deleting a leaf node (no children)
        if (deleteNode.left == null && deleteNode.right == null) {
            // If the node to delete is the root
            if (parentOfDeleteNode == null) {
                root = null; // Empty the tree
            } else if (parentOfDeleteNode.left == deleteNode) {
                parentOfDeleteNode.left = null;
            } else {
                parentOfDeleteNode.right = null;
            }
            return;
        }

        // Case 2: Deleting a node with only a left child
        if (deleteNode.left != null && deleteNode.right == null) {
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else if (parentOfDeleteNode.left == deleteNode) {
                parentOfDeleteNode.left = deleteNode.left;
            } else {
                parentOfDeleteNode.right = deleteNode.left;
            }
            return;
        }

        // Case 3: Deleting a node with only a right child
        if (deleteNode.left == null && deleteNode.right != null) {
            if (parentOfDeleteNode == null) {
                root = deleteNode.right;
            } else if (parentOfDeleteNode.left == deleteNode) {
                parentOfDeleteNode.left = deleteNode.right;
            } else {
                parentOfDeleteNode.right = deleteNode.right;
            }
            return;
        }

        // Case 4: Deleting a node with both left and right children
        if (deleteNode.left != null && deleteNode.right != null) {
            // Find the rightmost node in the left subtree
            Node<T> leftSubtree = deleteNode.left;
            Node<T> rightmostInLeft = leftSubtree;

            // Find the rightmost node in the left subtree
            while (rightmostInLeft.right != null) {
                rightmostInLeft = rightmostInLeft.right;
            }

            // Attach the right subtree of the node to delete to the rightmost node in the
            // left subtree
            rightmostInLeft.right = deleteNode.right;

            // If deleting the root, make the left subtree the new root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else if (parentOfDeleteNode.left == deleteNode) {
                parentOfDeleteNode.left = deleteNode.left;
            } else {
                parentOfDeleteNode.right = deleteNode.left;
            }
        }
    }

    public void deleteByCopy(T x) {
        // Check if the BST is empty
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion");
            return;
        }

        // Search for the node to delete and its parent
        Node<T> deleteNode = root;
        Node<T> parentOfDeleteNode = null;

        // Searching for the node with value x
        while (deleteNode != null && !deleteNode.info.equals(x)) {
            parentOfDeleteNode = deleteNode;
            if (x.compareTo(deleteNode.info) < 0) {
                deleteNode = deleteNode.left;
            } else {
                deleteNode = deleteNode.right;
            }
        }

        // If the node to be deleted is not found
        if (deleteNode == null) {
            System.out.println("The key " + x + " does not exist, no deletion");
            return;
        }

        // Case 1: Deleting a leaf node (no children)
        if (deleteNode.left == null && deleteNode.right == null) {
            if (parentOfDeleteNode == null) {
                root = null; // Empty the tree
            } else if (parentOfDeleteNode.left == deleteNode) {
                parentOfDeleteNode.left = null;
            } else {
                parentOfDeleteNode.right = null;
            }
            return;
        }

        // Case 2: Deleting a node with only a left child
        if (deleteNode.left != null && deleteNode.right == null) {
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else if (parentOfDeleteNode.left == deleteNode) {
                parentOfDeleteNode.left = deleteNode.left;
            } else {
                parentOfDeleteNode.right = deleteNode.left;
            }
            return;
        }

        // Case 3: Deleting a node with only a right child
        if (deleteNode.left == null && deleteNode.right != null) {
            if (parentOfDeleteNode == null) {
                root = deleteNode.right;
            } else if (parentOfDeleteNode.left == deleteNode) {
                parentOfDeleteNode.left = deleteNode.right;
            } else {
                parentOfDeleteNode.right = deleteNode.right;
            }
            return;
        }

        // Case 4: Deleting a node with both left and right children (Copy by replacing
        // with in-order predecessor)
        if (deleteNode.left != null && deleteNode.right != null) {
            Node<T> parentReplaceNode = null; // Parent of the replacement node
            Node<T> replaceNode = deleteNode.left; // The rightmost node in the left subtree (in-order predecessor)

            // Find the rightmost node in the left subtree
            while (replaceNode.right != null) {
                parentReplaceNode = replaceNode;
                replaceNode = replaceNode.right;
            }

            // Copy the value from the replace node to the delete node
            deleteNode.info = replaceNode.info;

            // If the replace node is a direct left child of the delete node
            if (parentReplaceNode == null) {
                deleteNode.left = replaceNode.left; // Replace deleteNode's left child with the replaceNode's left
                                                    // subtree
            } else {
                // If the replace node had any left subtree, connect it to its parent's right
                parentReplaceNode.right = replaceNode.left;
            }
            return;
        }
    }

    // convert BSTree to Ordered MyList
    public void inOrderToMyList(MyList<T> a, Node<T> from) {
        if (from == null) {
            return;
        }
        inOrderToMyList(a, from.left);
        a.addLast(from.info);
        inOrderToMyList(a, from.right);
    }

    public void postOrderToMyList(MyList<T> a, Node<T> from) {
        if (from == null) {
            return;
        }
        postOrderToMyList(a, from.left);
        postOrderToMyList(a, from.right);
        a.addLast(from.info);
    }

    public void balance(MyList<T> b, int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            insert(b.getByIndex(middle).info);
            balance(b, first, middle - 1);
            balance(b, middle + 1, last);
        }
    }

    public void balance() {
        MyList<T> n = new MyList<>();
        inOrderToMyList(n, root);
        clear();
        balance(n, 0, n.size() - 1);

    }

    public Node searchFirstBothChildByBreadth() {
        MyQueue q = new MyQueue<>();
        q.enqueue(root);
        Node<T> p;
        while (!q.isEmpty()) {
            p = q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            if (p.right != null && p.left != null) {
                return p;
            }
        }
        return null;
    }

    public Node<T> findParent(Node<T> p) {
        Node<T> current = root;
        Node<T> parent = null;

        while (current != null && current != p) {
            parent = current;
            if (p.info.compareTo(current.info) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return parent; // Will return null if p is the root or not found.
    }

    public Node rotateRight(Node p) {
        if (p == null) {
            return null;
        }
        Node leftNode = p.left;
        if (leftNode == null) {
            return p;
        }
        Node leftRightNode = leftNode.right;
        p.left = leftRightNode;
        leftNode.right = p;
        if (p == root) {
            root = leftNode;
        } else {
            Node parent = findParent(p);
            if (parent.left == p) {
                parent.left = leftNode;
            } else {
                parent.right = leftNode;
            }
        }
        return leftNode;
    }

    public Node rotateLeft(Node node) {
        if (node == null) {
            return null;
        }
        Node rightNode = node.right;
        if (rightNode == null) {
            return node;
        }
        Node rightLeftNode = rightNode.left;
        node.right = rightLeftNode;
        rightNode.left = node;
        if (node == root) {
            root = rightNode;
        } else {
            Node parent = findParent(node);
            if (parent.left == node) {
                parent.left = rightNode;
            } else {
                parent.right = rightNode;
            }
        }
        return rightNode;
    }

    public void breathFirstToMyList(MyList<T> a, Node<T> from) {
        if (from == null) {
            return;
        }

        MyQueue<T> q = new MyQueue<>();
        q.enqueue(from);
        Node<T> p;

        while (!q.isEmpty()) {
            p = q.dequeue();

            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }

            a.addLast(p.info);
        }
    }

}
