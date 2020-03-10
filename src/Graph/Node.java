package Graph;

public class Node {
    private int source ;
    private int destination ;
    private int nodeDegree ;

    // the component number is >1
    private int componentNumber ;
    public Node(int source , int destination , int number){
        this.source = source;
        this.componentNumber = number ;
        this.destination = destination ;
        this.nodeDegree = 0 ;  // by default ;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getComponentNumber() {
        return componentNumber;
    }

    public void setComponentNumber(int componentNumber) {
        this.componentNumber = componentNumber;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getNodeDegree() {
        return nodeDegree;
    }

    public void setNodeDegree(int nodeDegree) {
        this.nodeDegree = nodeDegree;
    }
}
