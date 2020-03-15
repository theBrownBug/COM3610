package Graph;
import java.util.*;

public class Graph implements Cloneable{
    private int V ;
    private LinkedList<Node>[] adjecencyList ;
    private int currentNumberOfComponents ;
    private boolean isConnected ;
    LinkedList<Node> nodes = new LinkedList<Node>();
    LinkedList<Edge> edges = new LinkedList<Edge>() ;
    // Constructor
    Graph(int v) throws IndexOutOfBoundsException {
        if(v<0){ throw  new IndexOutOfBoundsException() ;   }
        try {
            V = v;
            this.isConnected = true ;// by default set true
            this.currentNumberOfComponents  = 0 ;
            for(int i =0 ; i < V ; i++){ nodes.add(new Node(i, 0) ); }
            this.adjecencyList = new LinkedList[v];
            for(int c = 0 ; c<this.adjecencyList.length ; c++){
                this.adjecencyList[c]= new LinkedList<Node>() ;
            }
        }
        catch (NumberFormatException e){

        }
    }


    /**
     * the constructor is used to create a subgraph of a given graph
     *
     *
     * */
    public Graph(int V , int startNode , int endNode , int componentNumber){
        try {
            if(endNode-startNode<0){
                throw new Exception() ;
            }
            this.V = (endNode - startNode ) + 1;
            this.adjecencyList = new LinkedList[V] ;
            for(int counter = 0 ; counter< V ;counter++){
                if(counter>= startNode && counter<=endNode){
                    this.adjecencyList[counter] = new LinkedList<Node>() ;
                }
            }
//            for(int counter= startNode ; counter<=endNode ; counter++){
//                nodes.add(new Node(counter , componentNumber)) ;
//            }

        }

        catch (Exception e){
            System.out.println(e);
        }


    }
    public int getV() { return V; }


    /*
    * the component number is 0 by default
    * */
    public void addEdge(int source , int destination , int componentNumber) {
        try {
            if (destination > V || source > V) {
                throw new Exception();
            }
            boolean sourceToDestinationAlreadyExists = false;
            boolean destinationToSourceAlreadyExists = false;
            for(Node node:this.nodes){
                if(node.getVertexNumber()==source || node.getVertexNumber()== destination){
                    LinkedList<Edge> edges = node.getEdges() ;
                    for(Edge edge: edges){
                        // if edge from source to destination already exists and (from dest -> source) return
                        //
                     if(edge.getSource() == source && edge.getDestination()==destination){
                         sourceToDestinationAlreadyExists = true ;
                     }
                     else if(edge.getSource()== destination && edge.getDestination() == source){
                         destinationToSourceAlreadyExists = true ;
                     }
                    }
                }
            }

            if(sourceToDestinationAlreadyExists && destinationToSourceAlreadyExists){
                return;
            }
            else{
                this.nodes.get(source).addEdge(destination);
                this.nodes.get(destination).addEdge(source);
                this.edges.add(new Edge(source , destination));
                this.edges.add(new Edge(destination , source)) ;

                // create a new deep copy of the node to be added into the adjacency list
                this.adjecencyList[source].add(new Node(destination ,
                        this.nodes.get(destination).getComponentNumber() ,
                        new LinkedList<Edge>(this.nodes.get(destination).getEdges())
                )) ;

                this.adjecencyList[destination].add(
                        new Node(source,
                                this.nodes.get(source).getComponentNumber() ,
                                new LinkedList<Edge>(this.nodes.get(source).getEdges())
                                )) ;
            }

        }catch (Exception e){
            System.out.println("The destination node cannot be greater than number of vertices");
        }
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void DFSUtil(Boolean[] visited , int vertex){
        visited[vertex] = true ;
        System.out.print(vertex +  " ");
        for(Node i : adjecencyList[vertex]){
            if(!visited[i.getVertexNumber()]){
                DFSUtil(visited , i.getVertexNumber());
                i.setComponentNumber(currentNumberOfComponents + 1);

            }
        }
    }

    public void initialiseDFS(){
        Boolean[] visited = new Boolean[this.V] ;
        for(int v = 0 ; v< visited.length ; v++){
            visited[v] = false ;
        }
        for(int vertex = 0 ; vertex<V ; vertex++){
            if(!visited[vertex]){
                DFSUtil(visited , vertex);
                System.out.println();
                setCurrentNumberOfComponents(++currentNumberOfComponents);
            }

        }

    }

    boolean isConnected(){
        //DFS();
        if(currentNumberOfComponents==1){ setConnected(true); }
        else{ setConnected(false); }
        return this.isConnected ;
    }

    int getCurrentNumberOfComponents(){
        //DFS() ;
        return this.currentNumberOfComponents ;
    }

    public void setCurrentNumberOfComponents(int currentNumberOfComponents) {
        this.currentNumberOfComponents = currentNumberOfComponents;
    }









    /**
     * creates a subgraph of this current graph
     *
     *
     * */
    public Graph createSubGraph(int startNode , int endNode , int componentNumber) {
        try{
            if(startNode<0){
                throw new IndexOutOfBoundsException() ;
            }
            if(endNode>V){
                throw new IndexOutOfBoundsException();
            }
            if(componentNumber<1 || componentNumber>currentNumberOfComponents){
                throw new IndexOutOfBoundsException() ;
            }
            Graph graph = new Graph(this.V , startNode ,  endNode , componentNumber);
            for(int counter = 0 ; counter< this.adjecencyList.length ; counter++){

                // create a new clone adjecency list with required node objects
                if(graph.adjecencyList[counter]!=null){
                    Iterator<Node> iterator  = this.adjecencyList[counter].listIterator() ;
                    while(iterator.hasNext()){
                        Node next = iterator.next() ;
                        next.computeDegree();
                        graph.adjecencyList[counter].add(next.clone()) ;
                        graph.nodes.add(next.clone()) ;
                    }
                }

            }
            for(Edge edge:this.edges){
                if(edge.getSource()>=startNode && edge.getDestination()<=endNode){
                    graph.edges.add(edge.clone()) ;
                }
            }

            return graph ;
        }catch (IndexOutOfBoundsException e){
            System.out.println("index out of bounds" + e);
        }
        catch (CloneNotSupportedException e){
            System.out.println("Cannot clone" + e);
        }
        return null ;

    }


    public void removeVertex(int vertexNumber){
        try {
            if (this.nodes.size() < vertexNumber) {
                throw new Exception() ;
            }
            this.adjecencyList[vertexNumber].clear();
            this.adjecencyList[vertexNumber] = null ;
            for(LinkedList<Node> list:adjecencyList){
                Iterator<Node> iterator = list.listIterator() ;
                while(iterator.hasNext()){
                    Node next= iterator.next() ;
                    if(next.getVertexNumber()==vertexNumber){
                        list.remove(next) ;
                    }
                }
            }
            for(LinkedList<Node> list : adjecencyList){
                for(Node n: list){
                    LinkedList<Edge> edges = n.getEdges() ;
                    for(Edge e: edges){
                        if(e.getDestination() == vertexNumber){
                            edges.remove(e) ;
                        }
                    }
                }
            }
            for(Node n : nodes){
                if(n.getVertexNumber()==vertexNumber){
                    n.setEdges(null);
                    nodes.remove(n) ;
                }

                LinkedList<Edge> edges = n.getEdges() ;
                for(Edge edge : n.getEdges()){
                    if(edge.getDestination()==vertexNumber){
                        edges.remove(edge) ;
                    }
                }
            }

        }catch (Exception e){
            System.out.println("The  vertex number is out of bounds");
        }
    }


    public Object clone() throws CloneNotSupportedException{
        return super.clone() ;
    }

    public static void main(String args[]){
        Graph graph = new Graph(5) ;
        graph.addEdge(1,  0 , 0);
        //graph.addEdge(1 ,2 , 0);
        graph.addEdge(2, 3 , 0);
        graph.addEdge(3, 4 , 0);

        graph.initialiseDFS();

        Graph subgraph = graph.createSubGraph(0 , 1 , 1);

        System.out.println(graph.getCurrentNumberOfComponents());
        System.out.println(graph.isConnected()) ;

    }

    public LinkedList<Node>[] getAdjecencyList() {
        return adjecencyList;
    }

    public void setAdjecencyList(LinkedList<Node>[] adjecencyList) {
        this.adjecencyList = adjecencyList;
    }
}