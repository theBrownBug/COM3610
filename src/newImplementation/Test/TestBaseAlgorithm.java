package newImplementation.Test;
import newImplementation.* ;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static org.junit.Assert.assertEquals ;
import static org.junit.Assert.assertNotEquals;

public class TestBaseAlgorithm {

    String pathName = "/Users/god_tm/Documents/GitHub/COM3610/sampleGraphs";

    /**
     * Same graph for test case 1 and 2
     * Graph  for test 1 and test 2 +tive and -tive:
     *  8 vertices
     *  6 edges
     *
     *  0 - 1 ,
     *  2 - 3 - 4 - 5 - 6 - 7
     *
     * */
    // test 1
    @Test
    public void testOnePositiveBaseAlgo(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        TestBaseAlgorithm.addVertex(sampleGraph , 8);
        sampleGraph.addEdge(0 , 1) ;
        for(int counter = 2 ; counter< sampleGraph.vertexSet().size()  - 1; counter++){
            sampleGraph.addEdge(counter , counter + 1) ;
        }
        assertEquals(2 ,  Solution.computeSolution(sampleGraph , 2));

    }


    @Test
    public void TestOneNegative(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        TestBaseAlgorithm.addVertex(sampleGraph , 8);
        sampleGraph.addEdge(0 , 1) ;
        for(int counter = 2 ; counter< sampleGraph.vertexSet().size()  - 1; counter++){
            sampleGraph.addEdge(counter , counter + 1) ;
        }
        assertNotEquals(1 ,  Solution.computeSolution(sampleGraph , 2));
    }


    /**
     * same graph for test case 3 and 4
     * 3 vertices
     *  0 - 1 - 2
     *  when c = 2 , only one element should be deleted
     *
     * */
    @Test
    public void TestTwoPositive(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        TestBaseAlgorithm.addVertex(sampleGraph , 3);
        for(int counter = 0 ; counter < sampleGraph.vertexSet().size() - 1 ;counter++){
            sampleGraph.addEdge(counter , counter +1) ;
        }
        assertEquals(1, Solution.computeSolution(sampleGraph, 2));
    }

    /**
     * Graph :
     * 0-1-2
     *
     * for c = 1 (for each component to have size = 1 (c=1) , only vertex (1) has to be deleted)
     *  */
    @Test
    public void TestTwoPositiveTwo(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        for(int counter = 0 ; counter< 3 ; counter++){
            addVertex(sampleGraph , counter);
        }
        //TestBaseAlgorithm.addVertex(sampleGraph , 3);
        for(int counter = 0 ; counter < sampleGraph.vertexSet().size() - 1 ;counter++){
            sampleGraph.addEdge(counter , counter +1) ;
        }


        assertEquals(1, Solution.computeSolution(sampleGraph, 1));
    }


    public static void addVertex(Graph<Integer, DefaultEdge> graph , int limit){
        for(int counter =0 ; counter< limit ; counter++){
            graph.addVertex(counter) ;
        }
    }
    /**
     * vary graphs,
     * keep c constant ,
     * compute min beforehand
     */
   // public void varyGraphsTest(int){ }



    /***
     * I have taken small sample undirected graph for this test ( 0-1-2-3 )
     * For this test, graph instance size and orientation(the way edges are connected for a fixed sized graph) have been kept constant
     *
     * The component sizes have been varied
     *
     * For example
     * When
     * c = 0 , D = 4 (if component size= 0 , then all the vertices should be deleted from the graph)
     * c = 1 , D = 2
     * c = 2 , D = 1
     * c = 3 , D = 1
     * c = 4 , D = 0 (if c = vertex set size(in this case 4) , the deletion set should be empty)
     * */
    @Test
    public void varyComponentSizeConnectedLinearGraph(){
        // make a simple connected linear graph
        Graph<Integer , DefaultEdge> sample = new SimpleGraph<>(DefaultEdge.class) ;
        /*
        * graph sample :
        * 0-1-2-3
        * */
        for(int counter = 0 ; counter < 4 ; counter++){ sample.addVertex(counter) ; }
        for(int counter = 0 ; counter<3 ; counter++){sample.addEdge(counter , counter+1) ; }
        ArrayList<Integer> preComputedMinDeletionSets = new ArrayList<>() ;
        preComputedMinDeletionSets.add(4);
        preComputedMinDeletionSets.add(2) ;
        preComputedMinDeletionSets.add(1);
        preComputedMinDeletionSets.add(1);
        preComputedMinDeletionSets.add(0) ;

        for(int counter = 0 ; counter<=sample.vertexSet().size() ; counter++){
            assertEquals((int)preComputedMinDeletionSets.get(counter) , Solution.computeSolution(sample , counter));
        }
    }


    /**
     * make a simple linear disconnected graph
     * Graph instance presented : ( ',' represents disconnected components )
     *  0 - 1 - 2 - 3,
     *  4 - 5 - 6 - 7
     *
     * Minimum Deletion sets for different component sizes:
     * Precomputed minimum Deletion Set sizes for various component sizes
     *
     * c = 0 , D = 8
     * c = 1 , D = 4
     * c = 2 , D = 2
     * c = 3 , D = 2
     * c = 4 = D = 0
     * */
    @Test
    public void varyComponentSizeDisconnectedLinearGraph(){
        Graph<Integer , DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class) ;
        addVertex(graph , 8);
        for(int counter = 0 ; counter < 3 ; counter++){ graph.addEdge(counter , counter+1) ; }
        for(int counter = 4 ; counter< 7 ; counter++){ graph.addEdge(counter , counter+1) ; }
        ArrayList<Integer> precomputedMinimumDeletionSets = new ArrayList<>() ;
        precomputedMinimumDeletionSets.add(8) ;
        precomputedMinimumDeletionSets.add(4) ;
        precomputedMinimumDeletionSets.add(2) ;
        precomputedMinimumDeletionSets.add(2) ;
        precomputedMinimumDeletionSets.add(0) ;
        for(int counter = 0 ; counter< precomputedMinimumDeletionSets.size()  ; counter++){
            assertEquals((int)precomputedMinimumDeletionSets.get(counter) , Solution.computeSolution(graph, counter));
        }


    }

    /**
     * In this case, the graph is complete, connected and contains vertices(0 , 1 , 2 , 3)
     * The component size has been varied on this instance
     * Key:
     *  Observationally, the component sizes are inversely linearly proportional to the Deletion set size
     *
     * Here are the precomputed minimum deletion set sizes for varied values of c(component sizes)
     * c = 0 , D = 4
     * c = 1 , D = 3
     * c = 2 , D = 2
     * c = 3 , D = 1
     * c = 4 , D = 0
     * */
    @Test
    public void varyComponentSizeConnectedCompleteGraphTest(){
        Graph<Integer, DefaultEdge> sample = new SimpleGraph<>(DefaultEdge.class) ;
        for(int counter = 0 ; counter<4 ; counter++){ sample.addVertex(counter); }

        // make a Complete graph
        for(int counter = 0 ; counter< sample.vertexSet().size() ; counter++){
            for(int counter2 = 0; counter2<sample.vertexSet().size() ; counter2++){
                if(counter!=counter2){
                    sample.addEdge(counter , counter2) ;
                }
            }
        }

        /* precomputed values of minimum deletion sets */
        ArrayList<Integer> preComputedSolutions = new ArrayList<>() ;
        preComputedSolutions.add(4) ;
        preComputedSolutions.add(3) ;
        preComputedSolutions.add(2) ;
        preComputedSolutions.add(1) ;
        preComputedSolutions.add(0) ;

        for(int counter = 0 ; counter<=sample.vertexSet().size() ; counter++){
            assertEquals((int)preComputedSolutions.get(counter) , Solution.computeSolution(sample ,counter));
        }

    }


    /**
     * BiClique = a complete bipartite graph
     * In this case, the graph is composed of 6 vertices labelled between (0 - 3) inclusive
     * of which first 2 vertices and last 2 vertices represent the two sides of the graph
     * Both set sizes in the bipartite graph are equal
     *
     *
     * */
    @Test
    public void varyComponentSizeBiCliqueWithEqualSetSizes(){
        SimpleGraph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        addVertex(graph , 4) ;
        for(int counter = 0 ; counter< 2 ; counter++){
            for(int counter2 = 2 ; counter2<4 ; counter2++){
                graph.addEdge(counter , counter2);
            }
        }
        ArrayList<Integer> precomputedMinimumDeletionSets = new ArrayList<>() ;
        precomputedMinimumDeletionSets.add(4);
        precomputedMinimumDeletionSets.add(2);
        precomputedMinimumDeletionSets.add(2);
        precomputedMinimumDeletionSets.add(1);
        precomputedMinimumDeletionSets.add(0);

        for(int counter = 0 ; counter< precomputedMinimumDeletionSets.size() ; counter++){
            assertEquals((int)precomputedMinimumDeletionSets.get(counter) , Solution.computeSolution(graph , counter));
        }
    }



    @Test
    public void masterSimulation() throws IOException {
        File folder = new File(pathName);
        File[] listOfFiles = folder.listFiles();
        ArrayList<Graph<Integer , DefaultEdge>> allGraphs = new ArrayList<>();
        HashMap<Graph , String> map = new HashMap<>() ;

        for (int i = 0; i < listOfFiles.length; i++) {
            // only consider .edge files
            if (listOfFiles[i].isFile() &&listOfFiles[i].getName().endsWith(".edge")) {
                BufferedReader reader = new BufferedReader(new FileReader(pathName + "/"+listOfFiles[i].getName())) ;
                String r ;
                int index = 0 ;
                Graph<Integer , DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class) ;
                while((r = reader.readLine())!= null){
                    if (index==0){
                        String[] graphDetails = r.split("\\s+");
                        for(int counter = 1 ; counter<= Integer.parseInt(graphDetails[2]) ; counter++){
                            graph.addVertex(counter);
                        }
                    } else{
                        String[] edgeData = r.split("\\s+");
                        try{
                            graph.addEdge(Integer.parseInt(edgeData[1]), Integer.parseInt(edgeData[2]));
                        }catch (Exception e){}
                    }
                    index++ ;
                }
                map.put(graph , listOfFiles[i].getName()) ;
                allGraphs.add(graph) ;
                reader.close();
            }
        }

        // sort graphs by number of vertices
        Collections.sort(allGraphs , new SortByVertexSetSize());

        // check if graphs are sorted
        /*
        for(Graph graph : allGraphs){
            System.out.println(graph.vertexSet().size() + " =  " +map.get(graph) );
            System.out.println();
        }
        */


        // create a new File and output the entries
        File output = new File(pathName+"/"+"output.txt") ;
        BufferedWriter writer = new BufferedWriter(new FileWriter(output)) ;
        String head = "GraphName            Vertices    Edges       ComponentSize   DeletionSetSize    TimeTaken(MicroSeconds)" ;
        writer.write(head);
        writer.newLine();


        for(int counter = 0 ; counter< allGraphs.size(); counter++){
            Graph<Integer , DefaultEdge> graph = allGraphs.get(counter);
            int numberOfVertices = graph.vertexSet().size();
            int numberOfEdges = graph.edgeSet().size() ;
            //System.out.println(listOfFiles[counter].getName());
            // get the graph name
            String graphName = map.get(graph).split("\\.")[0];
            int line = 0 ;
            writer.write(graphName + "              "+numberOfVertices+"            "+numberOfEdges+"           ");

            for(int componentSizes = 0 ; componentSizes<= numberOfVertices ; componentSizes++){
                double startTime = System.nanoTime() ;
                int minDeletionSet = Solution.computeSolution(graph , componentSizes) ;
                double timeTaken = (System.nanoTime() - startTime)/1000 ;
                if(line==0) {
                    writer.write("      "+componentSizes + "           " + minDeletionSet + "               " + timeTaken);
                }else {
                    writer.write("                                                      "
                            +componentSizes + "           " + minDeletionSet + "               " + timeTaken);
                }
                line++ ;
                writer.newLine();
            }

            writer.newLine();

        }
        writer.close();


    }


     @Test
     public void sampleSimulation() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(pathName+"/"+"3x3-grid.edge")) ;
        String r ;
        int index = 0 ;
        Graph<Integer , DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class) ;
        while((r = reader.readLine())!= null){
                if (index==0){
                    String[] graphDetails = r.split("\\s+");
                    for(int counter = 1 ; counter<= Integer.parseInt(graphDetails[2]) ; counter++){
                        graph.addVertex(counter);
                    }
                } else{
                    String[] edgeData = r.split("\\s+");
                    try{
                        graph.addEdge(Integer.parseInt(edgeData[1]), Integer.parseInt(edgeData[2]));
                    }catch (Exception e){}
                }
                index++ ;
        }
        reader.close();
        File output = new File(pathName+"/"+"sampleoutput.txt") ;
        BufferedWriter writer = new BufferedWriter(new FileWriter(output)) ;
        String head = "GraphName            Vertices    Edges       ComponentSize   DeletionSetSize    TimeTaken(MicroSeconds)" ;
        writer.write(head);
        writer.newLine();

        int numberOfVertices = graph.vertexSet().size();
        int numberOfEdges = graph.edgeSet().size() ;
        String graphName = "3x3-grid.egde".split("\\.")[0] ;
        writer.write(graphName + "              "+numberOfVertices+"            "+numberOfEdges+"           ");

        int line = 0 ;
        for(int componentSizes = 0 ; componentSizes<= numberOfVertices ; componentSizes++){
            double startTime = System.nanoTime() ;
            int minDeletionSet = Solution.computeSolution(graph , componentSizes) ;
            double timeTaken = (System.nanoTime() - startTime)/1000 ;
            if(line==0) {
                writer.write("      "+componentSizes + "           " + minDeletionSet + "               " + timeTaken);
            }else{
                writer.write("                                                      "
                        +componentSizes + "           " + minDeletionSet + "               " + timeTaken);
            }
            line++ ;
            writer.newLine();
        }
        writer.close();

    }

}
class SortByVertexSetSize implements Comparator<Graph> {
    @Override
    public int compare(Graph o1, Graph o2) {
        return o1.vertexSet().size() - o2.vertexSet().size() ;
    }
}
