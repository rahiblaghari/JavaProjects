
// Name: Rahib Laghari
// NetID: rrl190001
public class Graph {
    Graph() { // default contructor

    }

    Graph(int size) { // overloaded constructor to store in size and set adjacency list
        setSize(size);
        locationList = new String[getSize()][getSize()];

    }

    private int Size = 0; // initialize size to 0
    private String[][] locationList; // adjacency list, uses null or String of place

    public void setSize(int size) {
        Size = size;
    }

    public int getSize() {
        return Size;
    }

    public void insertVertex(String currVert) { // inserts vertex in next available spot
        int i = 0;
        while (locationList[i][0] != null) {
            i++;
        }
        locationList[i][0] = currVert; // set vertex to input (in graph)
    }

    public void insertEdge(String currEdge) {
        int i = 0, j = 0; // find current vertex, then insert edge into first open spot of vertex
        while (i < getSize() && locationList[i][0] != null) {
            i++;
        }
        i -= 1;
        while (locationList[i][j] != null) {// find next available spot in vertex's matrix
            j++;
        }
        locationList[i][j] = currEdge; // set edge to input (in graph)
    }

    public void deleteVertex(String vert) {
        int i;
        for (i = 0; i < Size; i++) {// find vertex
            for (int j = 0; j < locationList[i].length; j++) {
                if (locationList[i][j].contains(vert)) {
                    locationList[i][j] = null; // set to null where-ever vertex is found
                }
            }
        }
    }

    public void deleteEdge(String vertex, String edge) {
        int i, j;
        for (i = 0; i < Size; i++) { // locate vertex
            if (locationList[i][0].equals(vertex)) {// locate vertex
                break;
            }
        }
        if (i == Size) {
            System.out.println("Vertex not found");
            return;
        }
        for (j = 0; j < locationList[i].length; j++) { // locate edge
            try {
                if (locationList[i][j].contains(edge)) {
                    break;
                }
            } catch (NullPointerException E) {
                //if null, check next element
            }
        }
        if (j == locationList[i].length) {
            System.out.println("Edge not found");
        } else {
            locationList[i][j] = null; // if exists, set edge to null (hence, removing it)
        }
    }

    public int findCost(String currVert, String nextVert, int weight) {
        if (weight == -1) { // if invalid
            return weight;
        }
        int i = 0, j = 0;
        for (i = 0; i < getSize(); i++) {
            try {
                if (locationList[i][0].equals(currVert)) {
                    break;
                }
            } catch (Exception E) {
                // read every element to work with delete method
            }
        }
        if (i == getSize() || !locationList[i][0].equals(currVert)) {
            weight = -1;
            return weight;// if invalid vertex
        }
        for (j = 1; j < locationList[i].length; j++) {
            try {
                String vert = locationList[i][j];
                int index = vert.indexOf(",");
                vert = vert.substring(0, index);
                if (vert.equals(nextVert)) {
                    break;
                }
            } catch (Exception E) {
                // read every element to work with delete method
            }
        }
        if (j == getSize() || !locationList[i][j].contains(nextVert)) {
            weight = -1;
            return weight;// if edge doesn't exist
        } else {
            // if found, find the weight and add it onto current weight
            String edge = locationList[i][j];
            int index = edge.indexOf(",");
            edge = edge.substring(1 + index);
            weight += Integer.parseInt(edge);
        }
        return weight; // return final value for weight
    }
}