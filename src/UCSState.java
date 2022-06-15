import java.util.ArrayList;
import java.util.LinkedList;

public class UCSState {

    private Graph graph;
    private int selectedNodeId;
    private UCSState parentState;
    public int cost;

    public UCSState(Graph graph, int selectedNodeId, UCSState parentState){
        this.graph= graph.copy();
        this.selectedNodeId= selectedNodeId;
        if(parentState != null){
            this.parentState= parentState;
        }else{
            this.parentState = null;
            this.cost=0;
        }
    }

    public ArrayList<UCSState> successor(){
        ArrayList<UCSState> children= new ArrayList<>();
        for (int i = 0; i < this.graph.size(); i++) {
            int nodeId= this.graph.getNode(i).getId();
            if(nodeId != selectedNodeId){
                UCSState newState = createChild(nodeId);
                children.add(newState);
            }
        }
        return children;
    }

    public UCSState createChild(int nodeId){
        UCSState newState = new UCSState(this.graph.copy(), nodeId, this);
        LinkedList<Integer> nodeNeighbors = newState.getGraph().getNode(nodeId).getNeighborsIds();
        for (int j = 0; j < nodeNeighbors.size(); j++) {
            int neighborId=nodeNeighbors.get(j);
            newState.getGraph().getNode(neighborId).reverseNodeColor();
        }
        int pcost=0;
        if (parentState!=null) pcost=newState.parentState.cost;
        System.out.println("pcost = " + pcost);
        if(newState.getGraph().getNode(nodeId).getColor() == Color.Black){
            newState.cost = pcost + 2;
            int greenNeighborsCount=0;
            int redNeighborsCount=0;
            int blackNeighborcount=0;
            for (int j = 0; j < nodeNeighbors.size(); j++) {
                int neighborId=nodeNeighbors.get(j);
                switch (newState.getGraph().getNode(neighborId).getColor()) {
                    case Green : greenNeighborsCount++;
                        break;
                    case Red : redNeighborsCount++;
                        break;
                    case Black : blackNeighborcount++;
                        break;
                }
            }
            if(greenNeighborsCount > redNeighborsCount && greenNeighborsCount > blackNeighborcount){
                newState.getGraph().getNode(nodeId).changeColorTo(Color.Green);
            }else if(redNeighborsCount > greenNeighborsCount && redNeighborsCount > blackNeighborcount){
                newState.getGraph().getNode(nodeId).changeColorTo(Color.Red);
            }
        }
        else {
                if (newState.getGraph().getNode(nodeId).getColor() == Color.Red) {
                    newState.cost = pcost + 1;
                    System.out.println("newState.parentSatet.cost = " +newState.parentState.cost);

                    System.out.println("nodeID + newState.cost = " +  nodeId + ","+newState.cost);
                }
                else if (newState.getGraph().getNode(nodeId).getColor() == Color.Green) {
                    newState.cost = pcost + 3;
                    System.out.println("newState.parentSatet.cost = " +newState.parentState.cost);

                    System.out.println("nodeID + newState.cost = " + nodeId + ","+ newState.cost);

                }
                newState.getGraph().getNode(nodeId).reverseNodeColor();
        }
        System.out.println();
        return newState;
    }

    public String hash(){
        String result= "";
        for (int i = 0; i < graph.size(); i++) {
            switch (graph.getNode(i).getColor()) {
                case Green : result += "g";
                    break;
                case Red : result += "r";
                    break;
                case Black : result += "b";
                    break;
            }
        }
        return result;
    }

    public String outputGenerator(){
        String result= "";
        for (int i = 0; i < graph.size(); i++) {
            String color=null;
            switch (graph.getNode(i).getColor()) {
                case Red: color="R";
                    break;
                case Green : color="G";
                    break;
                case Black : color="B";
                    break;
            }
            result += graph.getNode(i).getId()+color+" ";
            for (int j = 0; j < graph.getNode(i).getNeighborsIds().size(); j++) {
                int neighborId=graph.getNode(i).getNeighborsId(j);
                String neighborColor=null;
                switch (graph.getNode(neighborId).getColor()) {
                    case Red :neighborColor="R";
                        break;
                    case Green :neighborColor= "G";
                        break;
                    case Black :neighborColor= "B";
                        break;
                }
                result += neighborId+neighborColor+" ";
            }
            result += ",";
        }
        return result;
    }

    public Graph getGraph(){
        return graph;
    }

    public UCSState getParentState(){
        return parentState;
    }

    public  int getSelectedNodeId(){
        return selectedNodeId;
    }
}
