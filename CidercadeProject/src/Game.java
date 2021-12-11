//Name: Rahib Laghari
//NetID: rrl190001
public class Game implements Comparable<Game>{
    // create variables which are stored for each game
    private String name;
    private Integer highScore;
    private String initials;
    private Integer plays;
    private Double revenue;
    // overloaded constructor wasn't necessary, but helps make code look cleaner in main
    // since Game object can completely be created at once
    Game(String name, Integer highScore, String initials, Integer plays, Double revenue){
        this.name = name;
        this.highScore = highScore;
        this.initials = initials;
        this.plays = plays;
        this.revenue = revenue;
    }
    // if just a single string was to be created, helps set it up/ makes it easier
    Game(String name){
        this.name = name;
    }
    //end test code

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Integer getPlays() {
        return plays;
    }

    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @Override
    public int compareTo(Game inserted){ // compares the games based on name (alphabetical order)
        if(getName().length()>inserted.getName().length()){
            for(int i = 0; i < inserted.getName().length(); i++){
                if(getName().charAt(i)>inserted.getName().charAt(i)){
                    return 1;// true when the number should come ahead alphabetically, hence the larger number
                }else if (inserted.getName().charAt(i)>getName().charAt(i)){
                    return 0;// false when the name should come before alphabetically, hence the lower number
                }
            }
            return 0; // if whole word traversed through, will return the first word as smaller alphabetically
        }
        else{
            for(int i = 0; i < getName().length(); i++){
                if(getName().charAt(i)>inserted.getName().charAt(i)){
                    return 1;// true when the number should come ahead alphabetically, hence the larger number
                }else if (inserted.getName().charAt(i)>getName().charAt(i)){
                    return 0;// false when the name should come before alphabetically, hence the lower number
                }
            }
            return 1; // if whole word traversed through, will return the first word as larger alphabetically
        }
    }

    @Override
    public String toString(){ // gets the name of the game
        return name;
    }
    public void output() { // output format's all the information line by line
        System.out.println("High Score: " + getHighScore() + '\n' + "Initials: " + getInitials()  + '\n' + "Plays: " + getPlays());
        System.out.print("Revenue: $");
        System.out.printf("%.2f\n", revenue);
        System.out.println();
    }
    public void sortOutput(){ // sort output prints the output based on commas instead of lines, prints it out
        System.out.print(name + ", " + highScore + ", " + initials + ", " + plays + ", $");
        System.out.printf("%.2f\n", revenue);
    }
    public String writeOutput(){ // writeOutput gets node outputs a String formatted with commas instead of lines
        String output = name + ", " + highScore + ", " + initials + ", " + plays + ", $";
        output+=revenue.toString();
        String check = revenue.toString();
        for(int ind = (check.length() - (check.indexOf("."))); ind <= 2; ind++){
            output+="0";
        }
        output+='\n';
        return output;
    }
}
