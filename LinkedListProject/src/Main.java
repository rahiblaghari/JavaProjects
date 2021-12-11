//Name: Rahib Laghari
//Net ID: rrl190001
import java.io.*;
import java.util.Scanner;
import static java.lang.System.exit;

public class Main {


    //Calculate area using input points
    public static double calculateArea(double x, double y, double x2, double y2){
        return ((x2+x)*(y2-y));
    }

    //Writing necessary info formatted correctly to output file
    public static void writeToList(String Name, Double driverArea, LinkedList<Double,String> list){
        Node<Double,String> node = new Node<>(driverArea,Name);
        list.addNode(node);
    }

    public static boolean containsAlphabetic(String Line){
        return Line.contains("a") || Line.contains("A") || Line.contains("b") || Line.contains("B") || Line.contains("c") || Line.contains("C") || Line.contains("d") || Line.contains("D") || Line.contains("e") || Line.contains("E") || Line.contains("f") || Line.contains("F") || Line.contains("g") || Line.contains("G") || Line.contains("h") || Line.contains("H") || Line.contains("i") || Line.contains("I") || Line.contains("j") || Line.contains("J") || Line.contains("k") || Line.contains("K") || Line.contains("l") || Line.contains("L") || Line.contains("m") || Line.contains("M") || Line.contains("n") || Line.contains("N") || Line.contains("o") || Line.contains("O") || Line.contains("p") || Line.contains("P") || Line.contains("q") || Line.contains("Q") || Line.contains("r") || Line.contains("R") || Line.contains("s") || Line.contains("S") || Line.contains("t") || Line.contains("T") || Line.contains("u") || Line.contains("U") || Line.contains("v") || Line.contains("V") || Line.contains("w") || Line.contains("W") || Line.contains("x") || Line.contains("X") || Line.contains("y") || Line.contains("Y") || Line.contains("z") || Line.contains("Z");
    }

    public static boolean containsAlphabetic(char Character){
        return Character == 'a' || Character == 'A' || Character == 'b' || Character == 'B' || Character == 'c' || Character == 'C' || Character == 'd' || Character == 'D' || Character == 'e' || Character == 'E' || Character == 'f' || Character == 'F' || Character == 'g' || Character == 'G' || Character == 'h' || Character == 'H' || Character == 'i' || Character == 'I' || Character == 'j' || Character == 'J' || Character == 'k' || Character == 'K' || Character == 'l' || Character == 'L' || Character == 'm' || Character == 'M' || Character == 'n' || Character == 'N' || Character == 'o' || Character == 'O' || Character == 'p' || Character == 'P' || Character == 'q' || Character == 'Q' || Character == 'r' || Character == 'R' || Character == 's' || Character == 'S' || Character == 't' || Character == 'T' || Character == 'u' || Character == 'U' || Character == 'v' || Character == 'V' || Character == 'w' || Character == 'W' || Character == 'x' || Character == 'X' || Character == 'y' || Character == 'Y' || Character == 'z' || Character == 'Z';
    }

    public static boolean containsNum(String Line){
        return Line.contains("0") || Line.contains("1") || Line.contains("2") || Line.contains("3") || Line.contains("4") || Line.contains("5") || Line.contains("6") || Line.contains("7") || Line.contains("8") || Line.contains("9");
    }

    public static void main(String[] args) {
        LinkedList<Double, String> list = new LinkedList<>();
//        Driver<Double,String> driver;
        //get user input for file name
        Scanner scnr = new Scanner(System.in);
        String fileName;
        String searchFileName;
        System.out.print("What is the driver route file name: ");
        fileName = scnr.next();
        System.out.print("What is the search file name: ");
        searchFileName = scnr.next();

        //open input file, and set up arrays to store necessary data
        File inputFile = new File(fileName);
        File searchFile = new File(searchFileName);

        // Use variables below to store nums and calculate area doing so
        double x = 0.0, y = 0.0, x2 = 0.0, y2 = 0.0;
        // Use original variable to find when to exit
        int currVar = 1;
        double var, area = 0.0;
        String currentLine;
        String Name = "";
        if(inputFile.exists() && searchFile.exists()) {
            BufferedReader br;
            BufferedReader search;
            try {
                //Read names and points from file
                search = new BufferedReader(new FileReader(searchFileName));
                br = new BufferedReader(new FileReader(fileName));
                int i = 0;
                double multiplier;
                currentLine = br.readLine();
                while(currentLine != null){
                    //Store names and points in Linked list one by one into linked list
                    while(containsAlphabetic(currentLine.charAt(i)) || currentLine.charAt(i)==' ') {// first get name
                        Name += currentLine.charAt(i);
                        i++;
                    }
                    try {
                    while(i<(currentLine.length())) {
                            if (currentLine.charAt(i) != ',' && currentLine.charAt(i) != ' ') {
                                //Store points, translate to negative if necessary
                                if (currentLine.charAt(i) == '-') {
                                    i++;
                                    multiplier = -1.0;
                                } else {
                                    multiplier = 1.0;
                                }
                                StringBuilder wholeNum = new StringBuilder();
                                while (currentLine.length() != i && currentLine.charAt(i) != ',' && currentLine.charAt(i) != ' ') {
                                    if(containsAlphabetic(currentLine.charAt(i)) || currentLine.charAt(i)<46 || currentLine.charAt(i)>57 || currentLine.charAt(i)==47){
                                        throw new Exception();
                                    }
                                    wholeNum.append(currentLine.charAt(i));
                                    i++;
                                }
                                var = (multiplier * Double.parseDouble(wholeNum.toString()));
                                switch (currVar) {
                                    case 1:
                                        x = var;
                                        currVar = 2;
                                        break;
                                    case 2:
                                        y = var;
                                        currVar = 3;
                                        break;
                                    case 3:
                                        if(currentLine.charAt(i)!=','){
                                            throw new Exception();
                                        }
                                        x2 = var;
                                        currVar = 4;
                                        break;
                                    case 4:
                                        if(i<currentLine.length() && currentLine.charAt(i)==','){
                                            throw new Exception();
                                        }
                                        y2 = var;
                                        area += calculateArea(x, y, x2, y2);
                                        y = y2;
                                        x = x2;
                                        currVar = 3;
                                        break;
                                    default:
                                        break;
                                }
//                            multiplier = 1.0;
                            }
                            i++;
                        }
                    if(y!=y2 || x!=x2){
                        throw new Exception();
                    }
                        area = Math.round(Math.abs(area) * 50.0) / 100.0;
                        writeToList(Name, area, list);
                    }
                    catch (Exception E){
                        //next line
//                            while (currentLine.length() != i && currentLine.charAt(i) != ',' && currentLine.charAt(i) != ' ') {
//                                i++;
//                            }
                    }
                        currVar = 1;
//                        area = Math.round(Math.abs(area) * 50.0) / 100.0;
                        //variable "Name" contains names
                        //driver = new Driver(Name, area);
//                        writeToList(Name, area, list);
                        Name = "";
                        area = 0;
                        currentLine = br.readLine();
                        //Finally, reset index counter i
                        i = 0;
                }
//                list.sortList("name", "asc");
                currentLine = search.readLine();
                while(currentLine!=null) {
                    if(currentLine.equals("sort area asc")){
                        list.sortList("area", "asc");
                        System.out.println(list);
                        System.out.println();
                    } else if(currentLine.equals("sort area des")){
                        list.sortList("area", "des");
                        System.out.println(list);
                        System.out.println();
                    } else if(currentLine.equals("sort name asc") || currentLine.equals("sort driver asc")){
                        list.sortList("name", "asc");
                        System.out.println(list);
                        System.out.println();
                    } else if(currentLine.equals("sort name des") || currentLine.equals("sort driver des")){
                        list.sortList("name", "des");
                        System.out.println(list);
                        System.out.println();
                    } else if(containsAlphabetic(currentLine) && !containsNum(currentLine)){
                        list.searchList(currentLine);
                    } else if(containsNum(currentLine) && !containsAlphabetic(currentLine)){
                        list.searchList(Double.parseDouble(currentLine));
                    }
                    currentLine = search.readLine();
                }
                //Close File:
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                exit(0);
            }
        }
        else{
            System.out.println("File couldn't be found");
        }
    }
}
