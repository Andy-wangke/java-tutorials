/**
 * 
 */
package com.it.binaryTree.huffman;

import java.util.PriorityQueue;

/**
 * @author K.Andy Wang
 */
class HuffTree implements Comparable<HuffTree> {

    double frequncy;
    char ch;
    HuffTree left, right;
    String code = "";

    public HuffTree() {
    }

    public HuffTree(double frequncy, char ch) {
        this.frequncy = frequncy;
        this.ch = ch;
    }

    // merge two nodes and create a new node with added frequencies
    public static HuffTree merge(HuffTree h1, HuffTree h2) {
        HuffTree parent = new HuffTree();
        parent.left = h1;
        parent.right = h2;
        parent.frequncy = h1.frequncy + h2.frequncy;
        return parent;
    }

    // assign code and print the code for each character
    public static void assignCode(HuffTree root) {
        if (root.left != null || root.right != null) {
            root.left.code = root.code + "0";
            assignCode(root.left);
            root.right.code = root.code + '1';
            assignCode(root.right);
        } else {// print
            System.out.println("Code for " + root.code + " is " + root.ch);
        }
    }

    @Override
    public int compareTo(HuffTree o) {
        if (this.frequncy > o.frequncy)
            return 1;
        else if (this.frequncy < o.frequncy)
            return -1;
        else
            return 0;
    }

}

public class HuffmanCoding {

    public static void main(String[] args) {
        PriorityQueue<HuffTree> pq=new PriorityQueue<>();
        
        //build up data
        pq.add(new HuffTree(5,'A'));
        pq.add(new HuffTree(25,'B'));
        pq.add(new HuffTree(10,'C'));
        pq.add(new HuffTree(15,'D'));
        pq.add(new HuffTree(20,'E'));
        pq.add(new HuffTree(20,'F'));
        
        while(pq.size()>1){
            //find top two min
            HuffTree t1=pq.remove();
            HuffTree t2=pq.remove();
            HuffTree mergeNode = HuffTree.merge(t1, t2);
            pq.add(mergeNode);
        }
        
        
        //while the size of pq is 1,then getting the node
        HuffTree root=pq.remove();
        
        HuffTree.assignCode(root);
    }
}
