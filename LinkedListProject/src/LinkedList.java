//Name: Rahib Laghari
//Net ID: rrl190001
public class LinkedList<A,N>{
    public LinkedList() {}
    private Node<A,N> head;
    private Node<A,N> tail;
//    private Driver<A,N> currDriver;
//    private Driver<A,N> nextDriver;
    private int listLength = 0;

    public void addNode(Node<A,N> newNode){
        Node<A,N> currentNode = head;
        this.listLength+=1;
        if(currentNode == null){ // The list is empty
            head = newNode;     // set the head and tail equal to the newNode as this is the first element
        }else{
            tail.setNext(newNode); // add the newNode to the end of the tail
            newNode.setPrev(tail);
        }
        tail = newNode; // update tail pointer
    }

    public void searchList(double Num){
        Node<A,N> node = head;
        Num = Math.round(Num*100)/100.0;
        for(; node.getNext()!=null; node = node.getNext()){
            if ((Double) node.getArea() == Num){
                System.out.println(node);
                return;
            }
        }
        if ((Double) node.getArea() == Num){
            System.out.println(node);

        }
        else{
            System.out.println("" + Num + " not found");
        }
    }

    public void searchList(String Name){
        Node<A,N> node = head;
        String N = " ";
        for(; node.getNext()!=null; node = node.getNext()){
            N = (String) node.getName();
            if (N.contains(Name)){
                System.out.println(node);
                return;
            }
        }
        if (N.contains(Name)){
            System.out.println(node);
        }
        else{
            System.out.println("" + Name + " not found");
        }
    }

    public void sortList(String valueToSortBy, String order) {
        Node<A,N> selectNode = head; //set select and next node in order to traverse through linked list
        Node<A,N> nextNode = selectNode.getNext();
        if (order.equals("asc")) {
            if (valueToSortBy.equals("name")) {
                for (int i = 0; i < listLength - 1; i++) {
                    for (int j = 0; j < listLength - 1; j++) {
                        String current = (String) selectNode.getName();
                        String next = (String) nextNode.getName();
                        if (greaterThan(current, next)) {
                            if (selectNode.getPrev() == null) {
                                //first node, set head pointer
                                head = nextNode;
                                nextNode.setPrev(null);
                            } else {
                                (selectNode.getPrev()).setNext(nextNode);
                                nextNode.setPrev(selectNode.getPrev());
                            }
                            if (nextNode.getNext() == null) {
                                //final node, set tail pointer
                                tail = selectNode;
                                selectNode.setNext(null);
                            } else {
                                (nextNode.getNext()).setPrev(selectNode);
                                selectNode.setNext(nextNode.getNext());
                            }
                            selectNode.setPrev(nextNode);
                            nextNode.setNext(selectNode);
                            nextNode = selectNode.getNext();
                        } else {
                            //compare selectnode and next node, then reassign for next comparission
                            selectNode = selectNode.getNext();
                            nextNode = nextNode.getNext();
                        }
                    }
                    //reset node pointers and complete again
                    selectNode = head;
                    nextNode = selectNode.getNext();
                }
            } else if (valueToSortBy.equals("area")) {
                for (int i = 0; i < listLength - 1; i++) {
                    for (int j = 0; j < listLength - 1; j++) {
                        Double current = (Double) selectNode.getArea();
                        Double next = (Double) nextNode.getArea();
                        if (current > next) {
                            if (selectNode.getPrev() == null) {
                                //first node, set head pointer
                                head = nextNode;
                                nextNode.setPrev(null);
                            } else {
                                (selectNode.getPrev()).setNext(nextNode);
                                nextNode.setPrev(selectNode.getPrev());
                            }
                            if (nextNode.getNext() == null) {
                                //final node, set tail pointer
                                tail = selectNode;
                                selectNode.setNext(null);
                            } else {
                                (nextNode.getNext()).setPrev(selectNode);
                                selectNode.setNext(nextNode.getNext());
                            }
                            selectNode.setPrev(nextNode);
                            nextNode.setNext(selectNode);
                            nextNode = selectNode.getNext();
                        } else {
                            //compare selectnode and next node, then reassign for next comparission
                            selectNode = selectNode.getNext();
                            nextNode = nextNode.getNext();
                        }
                    }
                    //reset node pointers and complete again
                    selectNode = head;
                    nextNode = selectNode.getNext();
                }
            }
        } else if (order.equals("des")) {
            if (valueToSortBy.equals("name")) {
                for (int i = 0; i < listLength - 1; i++) {
                    for (int j = 0; j < listLength - 1; j++) {
                        String current = (String) selectNode.getName();
                        String next = (String) nextNode.getName();
                        if (greaterThan(next, current)) {
                            if (selectNode.getPrev() == null) {
                                //first node, set head pointer
                                head = nextNode;
                                nextNode.setPrev(null);
                            } else {
                                (selectNode.getPrev()).setNext(nextNode);
                                nextNode.setPrev(selectNode.getPrev());
                            }
                            if (nextNode.getNext() == null) {
                                //final node, set tail pointer
                                tail = selectNode;
                                selectNode.setNext(null);
                            } else {
                                (nextNode.getNext()).setPrev(selectNode);
                                selectNode.setNext(nextNode.getNext());
                            }
                            selectNode.setPrev(nextNode);
                            nextNode.setNext(selectNode);
                            nextNode = selectNode.getNext();
                        } else {
                            //compare selectnode and next node, then reassign for next comparission
                            selectNode = selectNode.getNext();
                            nextNode = nextNode.getNext();
                            //System.out.println(toString());
                        }
                    }
                    //reset node pointers and complete again
                    selectNode = head;
                    nextNode = selectNode.getNext();
                }
            } else if (valueToSortBy.equals("area")) {
                for (int i = 0; i < listLength - 1; i++) {
                    for (int j = 0; j < listLength - 1; j++) {
                        Double current = (Double) selectNode.getArea();
                        Double next = (Double) nextNode.getArea();
                        if (current < next) {
                            if (selectNode.getPrev() == null) {
                                //first node, set head pointer
                                head = nextNode;
                                nextNode.setPrev(null);
                            } else {
                                (selectNode.getPrev()).setNext(nextNode);
                                nextNode.setPrev(selectNode.getPrev());
                            }
                            if (nextNode.getNext() == null) {
                                //final node, set tail pointer
                                tail = selectNode;
                                selectNode.setNext(null);
                            } else {
                                (nextNode.getNext()).setPrev(selectNode);
                                selectNode.setNext(nextNode.getNext());
                            }
                            selectNode.setPrev(nextNode);
                            nextNode.setNext(selectNode);
                            nextNode = selectNode.getNext();
                        } else {
                            //compare selectnode and next node, then reassign for next comparission
                            selectNode = selectNode.getNext();
                            nextNode = nextNode.getNext();
                        }
                    }
                    //reset node pointers and complete again
                    selectNode = head;
                    nextNode = selectNode.getNext();
                }
            }
        }
    }
    public String toString(){
        Node<A,N> node = new Node<>();
        StringBuilder returnStatement = new StringBuilder();
        for(node = head; node.getNext()!=null; node = node.getNext()){
            returnStatement.append(node).append("\n");
        }
        returnStatement.append(node.getName()).append(" ").append(node.getArea());
        return returnStatement.toString();
    }

    public boolean greaterThan(String currentName, String nextName){
        int lengthUsed = currentName.length();
        if(lengthUsed<nextName.length()){
            lengthUsed = nextName.length();
        }
        if(currentName.equals(nextName)){
            return false;
        }
        for(int i = 0; i<lengthUsed; i++){
            if(currentName.charAt(i) > nextName.charAt(i)){
                return true;
            } else if (currentName.charAt(i) < nextName.charAt(i)) {
                return false;
            }
        }
        return false;
    }
    public Node<A, N> getHead() {
        return head;
    }

    public void setHead(Node<A, N> head) {
        this.head = head;
    }

    public Node<A, N> getTail() {
        return tail;
    }

    public void setTail(Node<A, N> tail) {
        this.tail = tail;
    }

    public int getListLength() {
        return listLength;
    }
}
