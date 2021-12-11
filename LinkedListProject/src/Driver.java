//Name: Rahib Laghari
//Net ID: rrl190001
public class Driver<A,N> {
    private Node<A,N> Driver;
    private A area;
    private N name;
    private String howToSort;
    Driver(){
        //default
    }
    Driver(A area, N name){
        this.name = name;
        this.area = area;

    }
    public void setArea(A area) {
        this.area = area;
    }
    public void setCompare(String howToSort) {
        this.howToSort = howToSort;
    }
    public void setName(N name) {
        this.name = name;
    }
    public A getArea() {
        return area;
    }
    public String getHowToSort() {
        return howToSort;
    }
    public void setHowToSort(String howToSort) {
        this.howToSort = howToSort;
    }
    public N getName() {
        return name;
    }

    public Node<A, N> getDriver() {
        return Driver;
    }

    public void setDriver(Node<A, N> driver) {
        Driver = driver;
    }

    public void compareTo(){

    }

    public boolean compareTo(double a1, double a2){
        if (a1>a2){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Driver{" + "Name='" + name + '\'' +  ", area=" + area + ", howToSort='" + howToSort + '\'' + '}';
    }
}
