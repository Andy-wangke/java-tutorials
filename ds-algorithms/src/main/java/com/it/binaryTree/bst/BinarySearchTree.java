package com.it.binaryTree.bst;

import java.util.Iterator;


/**
 * 
 *         <pre>
 * A BST is a binary tree where nodes are ordered in the following way:
 *          each node contains one key(also known as data)
 *          the keys in the left subtree are less than the key in its parent node,in short L<P,
 *          the keys in the right subtree are more than the key in its parent node,in short P>R
 *          duplicate keys are not allowed.
 * </pre>
 * 
 *         <pre>
 * 1.get height
 * 2.get size
 * 3.insert a node
 * 4.find a node
 * 5.delete a node
 * 6.display the entire tree
 * 
 * </pre>
 */
public class BinarySearchTree<T> implements Iterable<T> {

    // root Node
    private TreeNode<?> root = null;

    /**
     * 
     */
    public BinarySearchTree() {
        root = new TreeNode<Object>("A", "100");
    }

    // TODO: initial by random
    public void createBinaryTree() {
        TreeNode<Object> nodeB = new TreeNode<Object>("B", "50");
        TreeNode<Object> nodeC = new TreeNode<Object>("C", "150");
        TreeNode<Object> nodeD = new TreeNode<Object>("D", "200");
        TreeNode<Object> nodeE = new TreeNode<Object>("E", "250");
        TreeNode<Object> nodeF = new TreeNode<Object>("F", "300");
        TreeNode<Object> nodeG = new TreeNode<Object>("G", "350");
        TreeNode<Object> nodeH = new TreeNode<Object>("H", "400");
        root.leftNode = nodeB;
        root.rightNode = nodeC;
        nodeB.leftNode = nodeD;
        nodeB.rightNode = nodeE;
        nodeC.leftNode = nodeF;
        nodeC.rightNode = nodeG;
        nodeD.leftNode = nodeH;

    }

    public void traversalPreOrder(TreeNode<?> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData());
        traversalPreOrder(node.leftNode);
        traversalPreOrder(node.rightNode);
    }

    public void traversalInOrder(TreeNode<?> node) {
        if (node == null) {
            return;
        }
        traversalInOrder(node.leftNode);
        System.out.print(node.getData());
        traversalInOrder(node.rightNode);
    }

    public void traversalPostOrder(TreeNode<?> node) {
        if (node == null) {
            return;
        }
        traversalPostOrder(node.leftNode);
        traversalPostOrder(node.rightNode);
        System.out.print(node.getData());
    }

    /**
     * @return
     */

    public int getHeight() {
        return this.getHeight(root);
    }

    /**
     * @return
     */
    public int getHeight(TreeNode<?> node) {
        if (node == null) {
            return 0;
        }
        // int leftHeight = this.getHeight(node.leftNode);
        // int rightHeight = this.getHeight(node.rightNode);
        // return (leftHeight > rightHeight) ? (leftHeight + 1) : (rightHeight + 1);
        return Math.max(this.getHeight(node.leftNode), this.getHeight(node.rightNode)) + 1;
    }

    public int getSize() {
        return this.getSize(root);
    }

    /**
     * @param node
     * @return
     */
    public int getSize(TreeNode<?> node) {
        if (node == null) {
            return 0;
        }
        int leftSize = getSize(node.leftNode);
        int rightSize = getSize(node.rightNode);
        return leftSize + rightSize + 1;
    }

    public int countLeaves() {
        return this.countLeaves(root);
    }

    /**
     * recursive
     * @param node
     * @return
     */
    public int countLeaves(TreeNode<?> node) {
        if (node == null) {
            return 0;
        } else if (node.leftNode == null && node.rightNode == null) {
            return 1;
        } else
            return this.countLeaves(node.leftNode) + this.countLeaves(node.rightNode);
    }

    /**
     * @param node
     */
    public void insert(TreeNode<?> node) {

    }

    /**
     * @param node
     */
    public void update(TreeNode<?> node) {

    }

    /**
     * find a specified node
     */
    public void find(String name) {

    }

    /**
     * find a node by name
     * 
     * @param <T>
     */
    public <T> boolean findByName(TreeNode<T> node,String name) {
        if(root ==null){
            return false;
        }else{
            return root.
        }
    }

    /**
     * delete a node by name
     */
    public void delete(String name) {

    }
    
    /**
     * 
     */
    public Iterator<T> iterator() {
        
        return null;

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.createBinaryTree();

        int height = bst.getHeight();
        int size = bst.getSize();
        System.out.println("the total height is " + height + ",total size is " + size);
        bst.displayBeforeOrder(bst.root);
        bst.displayMiddleOrder(bst.root);
        bst.displayafterOrder(bst.root);

    }
}

class TreeNode<T> {

    private String name;
    private T data;
    TreeNode<?> leftNode;
    TreeNode<?> rightNode;

    /**
     * @param index
     * @param data
     */
    public TreeNode(String name, T data) {
        super();
        this.name = name;
        this.data = data;
        leftNode = null;
        rightNode = null;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the leftNode
     */
    public TreeNode<?> getLeftNode() {
        return leftNode;
    }

    /**
     * @param leftNode
     *            the leftNode to set
     */
    public void setLeftNode(TreeNode<?> leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * @return the rightNode
     */
    public TreeNode<?> getRightNode() {
        return rightNode;
    }

    /**
     * @param rightNode
     *            the rightNode to set
     */
    public void setRightNode(TreeNode<?> rightNode) {
        this.rightNode = rightNode;
    }

}

// class StaticDemo {
//
// public static void main(String[] args) {
// Demo obj1 = new Demo();
// Demo.number++;
// Demo obj2 = new Demo();
// System.out.println(obj2.getX());
// }
// }
//
// class Demo {
//
// private int x;
// static int number = 0;
//
// public Demo() {
// number++;
// }
//
// public int getX() {
// x = number;
// return x;
// }
// }
