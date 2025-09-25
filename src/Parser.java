/***********************************************
 * @file Parser.java
 * @description Parses commands from a file and performs operations on a BST.
 *              Commands include search, remove, and print. Results are
 *              written to an output file
 * @author Alex Warren
 * @date September 24, 2025
 ***********************************************/

import java.io.*;
import java.util.Scanner;

public class Parser {

    // Create a BST of SP500 type instead of Integer
    private BST<SP500> mybst = new BST<>();

    // constructor for Parser
    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Process the input file line by line
    // Normalize spaces and split into commands
    public void process(File input) throws FileNotFoundException {
        Scanner scnr = new Scanner(input);
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            // split by spaces for command + arguments
            String[] command = line.split(" ");
            operate_BST(command);
        }
    }

    // Execute BST operations based on commands
    public void operate_BST(String[] command) {
        switch (command[0].toLowerCase()) {
            // if insert, then split symbol and price then perform insert BST function
            case "insert" -> {
                if (command.length == 2) {
                    // command[1] looks like "AAPL,254.49"
                    String[] parts = command[1].split(",");
                    if (parts.length == 2) {
                        String symbol = parts[0];
                        double price = Double.parseDouble(parts[1]);
                        SP500 stock = new SP500(symbol, price);
                        mybst.insert(stock);
                        writeToFile("insert " + stock, "./result.txt");
                    } else {
                        writeToFile("Invalid insert", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }

            // Search for element in BST and write if found or if failed
            case "search" -> {
                if (command.length == 2) {
                    String symbol = command[1];
                    SP500 temp = new SP500(symbol, 0.0); // price doesn't matter for search
                    SP500 found = mybst.search(temp);
                    if (found != null) {
                        writeToFile("found " + found, "./result.txt");
                    } else {
                        writeToFile("search failed", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }

            // Perform BST remove function, write error message if not valid
            case "remove" -> {
                if (command.length == 2) {
                    String symbol = command[1];
                    SP500 temp = new SP500(symbol, 0.0);
                    SP500 removed = mybst.remove(temp);
                    if (removed != null) {
                        writeToFile("removed " + removed, "./result.txt");
                    } else {
                        writeToFile("remove failed", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }

            // Print the stocks in alphabetical order of symbol
            case "print" -> {
                String result = "";
                for (SP500 stock : mybst) {
                    result += stock + ", ";
                }
                writeToFile(result.trim(), "./result.txt");
            }

            // Default case
            default -> writeToFile("Invalid Command", "./result.txt");
        }
    }

    // Write result to file
    public void writeToFile(String data, String filePath) {
        try (FileOutputStream output = new FileOutputStream(filePath, true);
             PrintWriter out = new PrintWriter(output)) {
            out.println(data);
        }
        catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}