//Name: Rahib Laghari
//NetID: rrl190001
public class BinTree <A extends Comparable<A>> {
    // calling BinTree creates a binary tree object, which contains a node at the root and branches out into children
    // first get a root
    private Node<A> root;
    // construct blank binary tree:
    public BinTree() {}
    // get binary tree's root/set root
    public Node<A> getRoot() {
        return root;
    }
    public void setRoot(Node<A> root) {
        this.root = root;
    }
    // overloaded constructor to create tree and store node as root
    BinTree(Node<A> root){
        this.root = root;
    }
    // Insert a node into the tree
    public Node<A> Insert(Node<A> insNode){
        // if root is null, set root to node, otherwise go and see where to insert it
        if(getRoot() == null){
            setRoot(insNode);
            return getRoot();
        }
        else{
            return Insert(insNode, getRoot());
        }
    }
    // Find where to store node based on alphabetically ordered name, when found, use recursion to return to
    // previous node to store into its specified Node pointer
    public Node<A> Insert(Node<A> insNode, Node<A> traverse){//return node found as well
        if(insNode.compareTo(traverse)<1){
            if (traverse.getlNode() == null){
                traverse.setlNode(insNode);
                return insNode;
            }
            else {
                return Insert(insNode, traverse.getlNode());
            }
        }
        else if(insNode.compareTo(traverse)>0){
            if (traverse.getrNode() == null){
                traverse.setrNode(insNode);
                return insNode;
            }
            else {
                return Insert(insNode, traverse.getrNode());
            }
        }
        else{
            System.out.println("Something wrong in insert!");
            return null;
        }
    }
    // Searches and returns a node:
    public Node<A> Search(String name){
        // if root null, no need to search/return null
        if(getRoot() == null){
            return null;
        }
        else{
            //otherwise, search for name
            return Search(name, getRoot());
        }
    }
    public Node<A> Search(String name, Node<A> node){
        // based on if it's alphabetically larger, smaller, or equal, move accordingly
        String nodeName = node.toString();
        if(nodeName.compareTo(name)==0){
            return node;
        }
        else if(nodeName.compareTo(name)>0){
            return Search(name, node.getlNode());
        }
        else if(nodeName.compareTo(name)<0){
            return Search(name, node.getrNode());
        }
        else{
            System.out.println("Something went wrong: binTree Search function!");
            return null;
        }
    }//return node found
    // delete node
    public Node<A> Delete(String name){
        // if null, return null
        if(getRoot() == null){
            return null;
        }
        else if(getRoot().toString().equals(name)){
            // if node is equal, output node deleted and exit
            System.out.println("RECORD DELETED\nName: " + getRoot().toString());
            Node<A> returnNode = getRoot();
            setRoot(returnNode.getrNode());
            if(returnNode.getlNode()!=null){
                Insert(returnNode.getlNode());
            }
            return returnNode;
        }
        else{ // go to delete function, as somewhere in a valid tree and not in root
            return Delete(name, getRoot());
        }
    }
    public Node<A> Delete(String name, Node<A> node){
        // using Search and Insert functions, finds the node to delete and the position of it to the
        // previous node. Delete's node by replacing with child if not null,
        // then inserts the node not replaced if both aren't null
        String nodeName = node.toString();
        if(nodeName.compareTo(name)==0){
            System.out.println("RECORD DELETED\nName: " + node);
        }
        else if(nodeName.compareTo(name)>0){
            if(node.getlNode().toString().compareTo(name)==0) {
                Delete(name, node.getlNode());
                Node<A> deleted = node.getlNode();
                node.setlNode(deleted.getrNode());
                if(deleted.getlNode()!=null)
                    Insert(deleted.getlNode());
                return deleted;
            }
            else {
                return Delete(name, node.getlNode());
            }
        }
        else if(nodeName.compareTo(name)<0){
            if(node.getrNode().toString().compareTo(name)==0) {
                Delete(name, node.getrNode());
                Node<A> deleted = node.getrNode();
                node.setrNode(deleted.getrNode());
                if(deleted.getlNode()!=null)
                    Insert(deleted.getlNode());
                return deleted;
            }
            else {
                return Delete(name, node.getrNode());
            }
        }
        else{
            System.out.println("Something went wrong: binTree Delete function!");
            return null;
        }
        return null;
    }//return node found

    public String getAscending(){ // output the list in ascending order (inorder, alphebetically ascending output)
        ReturnName = "";
        getAscending(getRoot());
        String returnStr = ReturnName;
        ReturnName = "";
        return returnStr;
    }
    public void getAscending(Node<A> currentNode){
        if(currentNode!=null) {
            getAscending(currentNode.getlNode());
            ReturnName+=currentNode;
            ReturnName+=",";
            getAscending(currentNode.getrNode());
        }
    }

    public String getDescending(){// output the list in descending order (inorder, alphebetically descending output)
        ReturnName = "";
        getDescending(getRoot());
        String returnStr = ReturnName;
        ReturnName = "";
        return returnStr;
    }
    public void getDescending(Node<A> currentNode){
        if(currentNode!=null) {
            getDescending(currentNode.getrNode());
            ReturnName+=currentNode;
            ReturnName+=",";
            getDescending(currentNode.getlNode());
        }
    }
    private String ReturnName = "";

    public String getSearchName() {
        return ReturnName;
    }

    public void setSearchName(String searchName) {
        ReturnName = searchName;
    }
    // below is used for finding if a name contains a phrase given, and putting it into a list
    private String contains = "";

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public void addToContains(String contains){
        this.contains+=contains;
        this.contains+=",";
    }

    public String containSearch(String Name){// contain Search gets each name which contains a phrase
        setContains(""); //set i to one, while loop and every time "," is found, run for the name then set new name
        // Note: a name cant contain a comma since it is the search criteria to know when a name is done being searched for
        // hence, a comma is usable to form a new name list, which is used for output in main
        containSearch(getRoot(), Name);
        return getContains();
    }
    public void containSearch(Node<A> currentNode, String Name){
        if(currentNode!=null) {
            containSearch(currentNode.getlNode(), Name);
            if(currentNode.toString().contains(Name)){
                addToContains(currentNode.toString());
            }
            containSearch(currentNode.getrNode(), Name);
        }
    }
}
