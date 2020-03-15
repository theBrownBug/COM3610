package Graph;

public class Edge implements Cloneable {
    Node node ;
    private int source ;
    private int destination ;
    public Edge(int source , int destination){
        this.source = source ;
        this.destination = destination ;
        this.node = null ;
    }

    public Edge(Node node , int destination) {
        try {
            if(node== null){ throw new NullPointerException(); }
            this.node = node;
            this.source = node.getVertexNumber();
            this.destination = destination;
        }
        catch (NullPointerException e){
            System.out.println(e);
        }
    }

    public int getSource() { return source; }
    public void setSource(int source) { this.source = source; }
    public int getDestination() { return destination; }
    public void setDestination(int destination) { this.destination = destination;}
    public Node getNode() { return node; }
    public void setNode(Node node) { this.node = node; }
    public Edge clone() throws CloneNotSupportedException{
        return (Edge) super.clone() ;
    }

    boolean equals(Edge edge){
        if(edge.getSource()==this.source && edge.getDestination()== this.destination){
            return true ;
        }
        return false ;
    }
}
