//Name: Rahib Laghari
//Net ID: rrl190001
public class Node<A,N>{
    private Node<A,N> next;
    private Node<A,N> prev;
    private Driver<A,N> currentDriver;//This will hold area and name data
    private A area;// it will hold this
    private N name;// and this
    public Node(){};
    public Node(A area, N name) {
        this.area = area;
        this.name = name;
        next = null;
        prev = null;
    }

    public Node<A,N> getNext() {
        return next;
    }

    public void setNext(Node<A,N> next) {
        this.next = next;
    }

    public Node<A,N> getPrev() {
        return prev;
    }

    public void setPrev(Node<A,N> prev) {
        this.prev = prev;
    }

    public A getArea() {
        return area;
    }

    public void setArea(A area) {
        this.area = area;
    }

    public N getName() {
        return name;
    }

    public void setName(N name) {
        this.name = name;
    }


    @Override
    public String toString() {
        double checkArea = Math.round((double)area*100.0);
        if(0==(checkArea%10)){
            return "" + name + " " + area + "0";
        }
        return "" + name + " " + area;
    }
}
