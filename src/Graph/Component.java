package Graph;
/*
* incpmplete
*
* */
import java.util.LinkedList;

public class Component {
    public LinkedList<Node> listOfNodes ;
    public LinkedList<Integer> vertexNumbers ;
    int componentSize;
    int componentNumber ;
    public Component(){}
    public Component(LinkedList<Node> nodes, LinkedList<Edge> edges) {
        this.listOfNodes = nodes ;
        if(nodes!= null){
            this.componentSize = nodes.size() ;
        }
    }




}
