package Graph;
/*
* incpmplete
*
* */
import java.util.LinkedList;

public class Component {
    public LinkedList<Node> listOfNodes ;
    public LinkedList<Integer> vertexNumbers ;
    int componentSize ;
    int componentNumber ;
    public Component(){}
    public Component(LinkedList<Node> nodes) {
        this.listOfNodes = nodes ;
        if(nodes!= null){
            this.componentSize = nodes.size() ;
        }
    }
    public void setVertexNumbers(){
        for(int counter = 0 ; counter<listOfNodes.size() ; counter++){
            vertexNumbers.add(listOfNodes.get(counter).getSource()) ;
        }
    }



}
