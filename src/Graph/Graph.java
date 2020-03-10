package Graph;
import java.util.LinkedList;

public class Graph {
    private int V ;
    private LinkedList<Node>[] adjecencyList ;
    private int currentNumberOfComponents ;
    private boolean isConnected ;


    // Constructor
    Graph(int v) {
        try {
            V = v;
            this.isConnected = true ;// by default set true
            this.currentNumberOfComponents  = 0 ;
            this.adjecencyList = new LinkedList[v];
            for(int c = 0 ; c<this.adjecencyList.length ; c++){
                this.adjecencyList[c]= new LinkedList<Node>() ;
            }
        }
        catch (NumberFormatException e){

        }
    }

    public int getV() { return V; }
    public void setV(int v) { V = v; }

    public LinkedList<Node>[] getAdjecencyList() { return adjecencyList; }
    public void setAdjecencyList(LinkedList<Node>[] adjecencyList) {
        this.adjecencyList = adjecencyList;
    }

    /*
    * the component number is 0 by default
    * */
    public void addEdge(int source , int destination , int componentNumber){
         this.adjecencyList[source].add(new Node(source , destination , componentNumber)) ;
         this.adjecencyList[destination].add(new Node(source , source, componentNumber)) ;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void DFSUtil(Boolean[] visited , int vertex){
        visited[vertex] = true ;
        System.out.print(vertex +  " ");
        for(Node i : adjecencyList[vertex]){
            if(!visited[i.getDestination()]){
                DFSUtil(visited , i.getDestination());
                i.setComponentNumber(currentNumberOfComponents + 1);

            }
        }
    }

    public void DFS(){
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

    public static void main(String args[]){
        Graph graph = new Graph(5) ;
        graph.addEdge(1,  0 , 0);
        graph.addEdge(2, 3 , 0);
        graph.addEdge(3, 4 , 0);

        graph.DFS();
        System.out.println(graph.getCurrentNumberOfComponents());
        System.out.println(graph.isConnected()) ;

    }


}