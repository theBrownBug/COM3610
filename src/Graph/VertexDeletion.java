package Graph;

public class VertexDeletion {
    private Graph graph ;
    private boolean isConnected ;
    private int numberOfComponents ;
    private int maximumComponentSize ;
    boolean solutionExists;

    public VertexDeletion(Graph graph , int componentSize) throws NullPointerException, IndexOutOfBoundsException{
        if(componentSize<1){
            throw new IndexOutOfBoundsException();
        }

        if(graph==null){ throw new NullPointerException() ; }
        this.solutionExists = false ; // by default
        this.maximumComponentSize = componentSize ;
        this.graph = graph ;
        graph.DFS();
        this.isConnected = graph.isConnected() ;
        this.numberOfComponents = graph.getCurrentNumberOfComponents() ;
    }


    public void trivialCheck(){
        if(this.graph.getV()<= getComponentSize()){
            this.solutionExists = true ;
        }
    }
    public void solution() throws NullPointerException{
        if(this.graph == null){
            throw new NullPointerException("The graph is empty");
        }

        /*
        run a trivial check
        * the maximumComponentSize > the number of vertices, the solution already exists
        *
        */
        trivialCheck();
        if(this.solutionExists){ return; }

        if(!this.isConnected) {
            for (int counter = 1; counter <= this.numberOfComponents; counter++) {
                // compute for different components

            }
        }
        else{
            // component for only one connected component

        }

    }

    public Graph getGraph() { return graph; }
    public void setGraph(Graph graph) { this.graph = graph; }
    public boolean isConnected() {return isConnected; }
    public void setConnected(boolean connected) { isConnected = connected; }
    public int getNumberOfComponents() { return numberOfComponents; }
    public void setNumberOfComponents(int numberOfComponents) { this.numberOfComponents = numberOfComponents; }
    public int getComponentSize() { return maximumComponentSize; }
    public void setComponentSize(int componentSize) { this.maximumComponentSize = componentSize; }
    public boolean isSolutionExists() { return solutionExists; }
    public void setSolutionExists(boolean solutionExists) { this.solutionExists = solutionExists; }
}
