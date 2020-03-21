package newImplementation.Test;
import newImplementation.* ;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals ;
import static org.junit.Assert.assertNotEquals;

public class TestBaseAlgorithm {

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


    @Test

    /**
     * graph: ( ',' represents disconnected components )
     * 0 - 1 - 2 - 3 ,
     * 4 - 5 - 6 - 7
     *
     * Worked
     * */
    public void TestThree(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        TestBaseAlgorithm.addVertex(sampleGraph , 8);
        for(int counter = 0 ; counter < 3 ; counter++){
            sampleGraph.addEdge(counter ,  counter+1) ;
        }
        for(int counter = 4 ; counter<7 ; counter++){
            sampleGraph.addEdge(counter , counter+1) ;
        }
        assertEquals(2 , Solution.computeSolution(sampleGraph , 2));
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
    public void varyComponentSizeUseOneInstanceOfConnectedGraphAndOrientation(){
        // make a simple linear graph
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
    public void connectedCompleteGraphTestVaryComponentSize(){
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
            assertEquals((int)preComputedSolutions.get(counter) , Solution.computeSolution(sample ,counter ));
        }

    }


    /**
     *
     *
     * */
    public void ConnectedBipartiteGraph(){

    }



}
