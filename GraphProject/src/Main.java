import java.lang.*;
import java.util.*;
import java.io.*;

//Name: Rahib Laghari
//Net ID: rrl190001

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        int i, j, k; // used as indexes for loops
        int size; // used to find appropriate size for matrix list
        Scanner input = new Scanner(System.in);
        String[] list;
        System.out.print("What is the name of your graph file: ");
        String graphFile = input.nextLine(); //Say "TestCases/galaxy1.txt" to enter galaxy 1 from text cases for example
        try {
            // read file to create size variable
            BufferedReader br = new BufferedReader(new FileReader(graphFile));
            String currLine = br.readLine();
            size = 0;
            while (currLine != null) {
                currLine = br.readLine();
                size++;
            }
            graph = new Graph(size);
            br.close();
            // read variable to store values into the list
            br = new BufferedReader(new FileReader(graphFile));
            currLine = br.readLine();
            while (currLine != null) {
                currLine += " "; // concatenate space at end to make reading easier
                //insert location (comes before the space)
                int index = currLine.indexOf(" ");
                String currentLocation = currLine.substring(0, index);
                graph.insertVertex(currentLocation);
                currLine = currLine.substring(index);
                while (currLine.length() > 1) {
                    // continue inserting all locations into the graph
                    currLine = currLine.substring(1);
                    index = currLine.indexOf(" ");
                    currentLocation = currLine.substring(0, index);
                    graph.insertEdge(currentLocation);
                    currLine = currLine.substring(index);
                }
                currLine = br.readLine();
            }
            br.close();
        } catch (Exception E) { // if error detected or unable to open graph file, jumps here
            System.out.println("Graph file not found or invalid");
            System.exit(0);
        }
        System.out.print("What is the name of your route file: ");
        String routeFile = input.nextLine();
        //initialize variables:
        size = 0;
        i = 0;
        try { // read file, here to find appropriate size for the list to store the names and weights for end
            BufferedReader br = new BufferedReader(new FileReader(routeFile));
            String currLine = br.readLine();
            while (currLine != null) {
                currLine = br.readLine();
                size++;
            }
            br.close();
        } catch (Exception E) {
            // error in reading file, will be caught in next try catch though for print to console purpose
        }
        list = new String[size];
        try {
            // read file
            BufferedReader br = new BufferedReader(new FileReader(routeFile));
            String currLine = br.readLine();
            while (currLine != null) {
                currLine += " ";
                // store name first, then weight, and if valid or invalid after
                int weight = 0;
                int index = currLine.indexOf(" ");
                String name = currLine.substring(0, index);
                currLine = currLine.substring(1 + index);
                index = currLine.indexOf(" ");
                String currentLocation = currLine.substring(0, index);
                currLine = currLine.substring(index);
                while (currLine.length() > 1) {
                    // get current & next location, then search graph to find weight
                    currLine = currLine.substring(1);
                    index = currLine.indexOf(" ");
                    String nextLocation = currLine.substring(0, index);
                    weight = graph.findCost(currentLocation, nextLocation, weight);
                    currLine = currLine.substring(index);
                    currentLocation = nextLocation;
                }

                if (weight != -1) { // if valid path
                    list[i] = ("" + weight + " " + name + " valid");
                } else { // if invalid path
                    weight = 0;
                    list[i] = ("" + weight + " " + name + " invalid");
                }
                i++; // go to next position in list for next iteration
                currLine = br.readLine();
            }
            br.close();
        } catch (Exception E) {
            System.out.println("Route file not found or invalid");
            System.exit(0);
        }
        // length and holder used to sort and print list
        int length;
        String holder;
        // sort list:
        for (j = 0; j < (list.length); j++) {
            for (i = 0; i < (list.length - 1); i++) {
                length = list[i].length();
                if (list[i].length() > list[1 + i].length()) {
                    length = list[1 + i].length();
                }
                for (k = 0; k < length; k++) {
                    if (list[i].charAt(k) > list[1 + i].charAt(k)) {
                        holder = list[i];
                        list[i] = list[1 + i];
                        list[1 + i] = holder;
                        break;
                    } else if (list[i].charAt(k) < list[1 + i].charAt(k)) {
                        break;
                    }
                }
            }
        }
        // print list:
        for (i = 0; i < list.length; i++) {
            String element = list[i];
            int index = element.indexOf(" ");
            String weight = element.substring(0, index);
            element = element.substring(1 + index);
            index = element.indexOf(" ");
            String name = element.substring(0, index);
            element = element.substring(1 + index);
            String valid = element;
            System.out.println(name + "    " + weight + "    " + valid);
        }
    }
}