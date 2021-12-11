//Name: Rahib Laghari
//NetID: rrl190001
import java.io.*;
import java.util.*;

public class Main {

    // Edit called when editing command given, it takes in the input and changes based on what field number is given
    public static void Edit(BinTree<Game> bin, String name, Character fieldNum, String newVal){
        Node<Game> editNode = bin.Search(name);
        String field = "";
        switch (fieldNum){
            case '1': // Edit high score
                try {
                    field = "high score";
                    editNode.getGame().setHighScore(Integer.parseInt(newVal));
                }catch (NumberFormatException E){
                    System.out.println("Error in edit");
                    return;
                }
                break;
            case '2': // Edit initials
                field = "initials";
                editNode.getGame().setInitials(newVal);
                break;
            case '3': // Edit number of plays (and edit the revenue to 0.25*plays as well)
                try {
                    field = "plays";
                    editNode.getGame().setPlays(Integer.parseInt(newVal));
                    editNode.getGame().setRevenue(editNode.getGame().getPlays()*0.25);
                }catch (NumberFormatException E){
                    System.out.println("Error in edit");
                    return;
                }
                break;
            default:
                return;
        }// after edits are made, print out what was edit, what value was added, and the entire game's info
        System.out.println(name + " UPDATED");
        System.out.println("UPDATE TO " + field + " - VALUE " + newVal);
        editNode.getGame().output();
    }

    // breadth write prints the tree's nodes left to right for each row
    public static String breadthWrite(BinTree<Game> tree){
        String finalStr = "";
        if(tree.getRoot()!=null){ // check to make sure tree exists
            // if tree exists, create a queue to hold each node. Every run, remove this and print it out, then store it's
            // children in the queue as well (from left to right)
            Queue<Node<Game>> queue = new LinkedList<>();
            queue.add(tree.getRoot());
            while(!(queue.isEmpty())){
                Node<Game> node = queue.remove();
                finalStr += node.getGame().writeOutput();
                breadthWrite(node, queue);
            }
        }
        return finalStr;
    }
    public static void breadthWrite(Node<Game> node, Queue<Node<Game>> queue){
        //store left and right child in queue
        if((node.getlNode())!=null){
            queue.add(node.getlNode());
        }
        if(node.getrNode()!=null){
            queue.add(node.getrNode());
        }
    }

    public static void main(String[] args) {
        // create tree to hold node objects
        BinTree<Game> binTree = new BinTree<>();
        //open file and start reading using user input to get
        Scanner input = new Scanner(System.in);
        System.out.print("What is the name for the database file: ");
        String Database = input.next();
        System.out.print("What is the name for the commands file: ");
        String Commands = input.next();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{// this tree reads the Database, and builds the initial tree
            br = new BufferedReader(new FileReader(new File(Database)));
            String currLine = br.readLine();
            while(currLine!=null){// read every line until null line
                String name = "", holder = "", initials = "";// set/reset values
                int highScore = 0, plays = 0;
                double revenue = 0.0;
                int length = currLine.length(), i = 0;
                try {// read in a line, catch if error
                    for(int index = 0; index<5; index++) {
                        while (i < length && currLine.charAt(i) != ',') { // loop reads in the next word
                            if(currLine.charAt(i) != '$') {
                                holder += currLine.charAt(i);
                            }
                            i++;
                        }
                        i += 2;
                        switch (index){// holder will have the next element stored in
                            case 0:
                                name = holder;
                                break;
                            case 1:
                                highScore = Integer.parseInt(holder);
                                break;
                            case 2:
                                initials = holder;
                                break;
                            case 3:
                                plays = Integer.parseInt(holder);
                                break;
                            case 4:
                                revenue = Double.parseDouble(holder);
                                break;
                            default:
                                break;
                        }
                        holder = ""; // reset holder to get next element
                    }
                    revenue = Math.round(100.0 * revenue) / 100.0;
                    // finally, store into tree
                    binTree.Insert(new Node<Game>(new Game(name, highScore, initials, plays, revenue)));
                } catch (NumberFormatException E){
                    // if invalid input, skips the entire line and jumps here
                    System.out.println("Invalid");
                }
                // do same process for each line
                currLine = br.readLine();
            }
        }
        catch(IOException E){ // check if file valid
            System.out.println("Error opening Database file");
        }
        Node<Game> getNode;
        try{//now that tree is built, read and follow commands file
            br = new BufferedReader(new FileReader(new File(Commands)));
            bw = new BufferedWriter(new FileWriter(new File("cidercade.dat")));
            String currLine = br.readLine();
            while(currLine!=null){
                System.out.println(currLine);
                int length = currLine.length(), i = 0;
                switch (currLine.charAt(i)){
                    case '1'://Add record "name" high_score initials plays $revenue
                        try {
                            // create initial variables for the add function
                            String name = "", holder = "", initials = "";
                            int highScore = 0, plays = 0;
                            double revenue = 0.0;
                            i += 3;
                            while (currLine.charAt(i) != '"') {
                                name += currLine.charAt(i);
                                i++;
                            }
                            i += 2;
                            while (currLine.charAt(i) != ' ') {
                                holder += currLine.charAt(i);
                                i++;
                            }
                            highScore = Integer.parseInt(holder);
                            holder = "";
                            i++;
                            while (currLine.charAt(i) != ' ') {
                                initials += currLine.charAt(i);
                                i++;
                            }
                            i++;
                            while (currLine.charAt(i) != ' ') {
                                holder += currLine.charAt(i);
                                i++;
                            }
                            plays = Integer.parseInt(holder);
                            holder = "";
                            i += 2;
                            while (i < length) {
                                holder += currLine.charAt(i);
                                i++;
                            }
                            revenue = Double.parseDouble(holder);
                            revenue = Math.round(100.0 * revenue) / 100.0;
                            Node<Game> inputNode = new Node<Game>(new Game(name, highScore, initials, plays, revenue));
                            binTree.Insert(inputNode);
                            System.out.println("RECORD ADDED");
                            System.out.println("Name: " + name);
                            inputNode.getGame().output();
                        } catch (NumberFormatException E){
                            System.out.println("Invalid input for insert");
                            System.out.println();
                        }
                        break;
                    case '2'://Search record
                        String phrase = currLine.replace("2 ", "");
                        try {
                            String names = binTree.containSearch(phrase);
                            String name = "";
                            if(names.length() < 1){
                                throw new NullPointerException();
                            }
                            for(i = 0; i < (names.length()); i++){
                                if(names.charAt(i)==','){
                                    name = binTree.Search(name).toString();
                                    System.out.println(name + " FOUND");
                                    getNode = binTree.Search(name);
                                    getNode.getGame().output();
                                    name = "";
                                }
                                else {
                                    name += names.charAt(i);
                                }
                            }
                        }catch (NullPointerException E){
                            System.out.println(phrase + " NOT FOUND");
                            System.out.println();
                        }
                        break;
                    case '3'://Edit record
                        String line = currLine.replace("3 \"", "");
                        String name = "";
                        String newVal = "";
                        for (i = 0; line.charAt(i)!='"'; i++){
                            name+=line.charAt(i);
                        }
                        i+=2;
                        Character fieldNum = line.charAt(i);
                        i+=2;
                        while ((i<line.length())){
                            newVal+=line.charAt(i);
                            i++;
                        }
                        Edit(binTree, name, fieldNum, newVal);
                        break;
                    case '4'://Delete record
                        i+=2;
                        name = "";
                        for(; i<length; i++){
                            name += currLine.charAt(i);
                        }
                        try {
                            binTree.Delete(name).getGame().output();
                        }
                        catch (NullPointerException E){
                            System.out.println(name + " was not found");
                        }
                        break;
                    case '5'://Print sorted list
                        String nameList = "";
                        if(currLine.contains("asc")){
                            System.out.println("RECORDS SORTED ASCENDING");
                            nameList = binTree.getAscending();
                            name = "";
                            int ind = 0;
                            while(ind < nameList.length()) {
                                for (; ind < nameList.length() && nameList.charAt(ind) != ','; ind++) {
                                    name += nameList.charAt(ind);
                                }
                                ind++;
                                try {
                                    binTree.Search(name).getGame().sortOutput();
                                }catch (NullPointerException E){
                                }
                                name = "";
                                i++;
                            }
                            System.out.println();
                        }
                        else if (currLine.contains("des")){
                            System.out.println("RECORDS SORTED DESCENDING");
                            nameList = binTree.getDescending();
                            name = "";
                            int ind = 0;
                            while(ind < nameList.length()) {
                                for (; ind < nameList.length() && nameList.charAt(ind) != ','; ind++) {
                                    name += nameList.charAt(ind);
                                }
                                ind++;
                                try {
                                    binTree.Search(name).getGame().sortOutput();
                                }catch (NullPointerException E){
                                }
                                name = "";
                                i++;
                            }
                            System.out.println();
                        }
                        break;
                    default:
                        break;
                }
                // do for every line in commands file:
                currLine = br.readLine();
            }
            bw.write(breadthWrite(binTree)); // write to file using breadth write function written earlier in the file
            bw.close(); // close the file writer
            br.close(); // close the file reader
        }
        catch (IOException E){ // couldn't open second file
            System.out.println("Error creating cidercade or opening command file");
        }
    }
}
