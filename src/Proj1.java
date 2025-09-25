/***********************************************
 * @file Proj1.java
 * @description Tests BST operations with input.txt file
 * @author Alex Warren
 * @date September 24, 2025
 ***********************************************/

import java.io.FileNotFoundException;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 1){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser("C:\\Users\\Alext\\IdeaProjects\\project-1-part-2-warrat22\\src\\input.txt");
    }
}