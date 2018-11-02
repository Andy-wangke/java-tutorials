/**
 * 
 */
package com.it.binaryTree.huffman;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author K.Andy Wang
 *
 */
public interface HuffmanCodingInterface {
    //take a file as input and create a Huffman tree
    public HuffTree buildTree(File inputFile) throws FileNotFoundException,Exception;
    
    public HuffTree buildTree(String inputStr) throws Exception;
    
    //take a file as input and encode the file
    //output a string of 1's and 0's representing the file content
    public String encode(File inputFile,HuffTree huffTree) throws FileNotFoundException;
    
    public String encode(String inputStr,HuffTree huffTree) throws FileNotFoundException;
    
    //take a string of code and output the decoded words
    public String decode(String code,HuffTree huffTree) throws Exception;
    
    //print the characters and their corresponding codes
    public String traverseHuffmanTree(HuffTree huffTree) throws Exception;
    
    
    //take a file as input and create a table with characters and frequencies
    //print the characters and frequencies
    public String getFrequencies(File inputFile) throws FileNotFoundException;
    
    public String getFrequencies(String inputStr);
}
