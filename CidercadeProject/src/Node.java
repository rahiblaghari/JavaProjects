//Name: Rahib Laghari
//NetID: rrl190001
public class Node <A extends Comparable <A>>{
    // Node objects are used as elements in the binary tree
    // First, create a nodes varaibles, one left, one right, and a payload for the game
    private Node<A> lNode = null;
    private Node<A> rNode = null;
    private A game;
    Node(){}
    Node(A game){
        this.game = game;
    }
    // setters and getters will find/ pull information desired
    public Node<A> getlNode() {
        return lNode;
    }

    public void setlNode(Node<A> lNode) {
        this.lNode = lNode;
    }

    public Node<A> getrNode() {
        return rNode;
    }

    public void setrNode(Node<A> rNode) {
        this.rNode = rNode;
    }

    public A getGame() {
        return game;
    }

    public void setGame(A game) {
        this.game = game;
    }

    // toString takes the payload (game) and calls its toString method
    @Override
    public String toString() {
        return game.toString();
    }

    public Integer compareTo(Node<A> input){
        //compare to calls getGame's compare To function and uses it to output the sorted list
        return getGame().compareTo(input.getGame());
    }
}
