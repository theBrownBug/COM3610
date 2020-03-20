package newImplementation.Test;
import newImplementation.* ;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals ;
import static org.junit.Assert.assertNotEquals;

public class TestBaseAlgorithm {

    /**
     * Same graph for test case 1 and 2
     * Graph  for test 1 and test 2 +tive and -tive:
     *  8 vertices
     *  6 edges
     *
     *  0 --- 1
     *  2-3-4-5-6-7
     *
     * */

    // test 1
    @Test
    public void testOnePositiveBaseAlgo(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        addVertex(sampleGraph , 8);
        sampleGraph.addEdge(0 , 1) ;
        for(int counter = 2 ; counter< sampleGraph.vertexSet().size()  - 1; counter++){
            sampleGraph.addEdge(counter , counter + 1) ;
        }
        assertEquals(2 ,  Solution.computeSolution(sampleGraph , 2));

    }


    @Test
    public void TestOneNegative(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        addVertex(sampleGraph , 8);
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
     *
     *  when c = 2 , only one element should be deleted
     *  when c = 1 ,
     * */
    @Test
    public void TestTwoPositive(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        addVertex(sampleGraph , 3);
        for(int counter = 0 ; counter < sampleGraph.vertexSet().size() - 1 ;counter++){
            sampleGraph.addEdge(counter , counter +1) ;
        }
        assertEquals(1, Solution.computeSolution(sampleGraph, 2));
    }

    /**
     * Test case 4 -- Not working?? Figure out
     * change c while keeping the graph same
     * 0-1-2
     *
     *  */
    @Test
    public void TestTwoPositiveTwo(){
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        addVertex(sampleGraph , 3);
        for(int counter = 0 ; counter < sampleGraph.vertexSet().size() - 1 ;counter++){
            sampleGraph.addEdge(counter , counter +1) ;
        }

        // should be 2
        assertEquals(1, Solution.computeSolution(sampleGraph, 1));
    }





    public void addVertex(Graph<Integer, DefaultEdge> graph , int limit){
        for(int counter =0 ; counter< limit ; counter++){
            graph.addVertex(counter) ;
        }
    }



}
