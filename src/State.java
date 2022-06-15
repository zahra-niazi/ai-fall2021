import java.util.ArrayList;
import java.util.LinkedList;

public class State {

    private Graph graph;
    private int selectedNodeId;
    private State parentState;


    public State(Graph graph, int selectedNodeId, State parentState){
        this.graph= graph.copy();
        this.selectedNodeId= selectedNodeId;
        if(parentState != null){
            this.parentState= parentState;
        }else{
            this.parentState = null;
        }
    }

    public ArrayList<State> successor(){
        ArrayList<State> children= new ArrayList<State>();
        for (int i = 0; i < this.graph.size(); i++) {
            int nodeId= this.graph.getNode(i).getId();
            if(nodeId != selectedNodeId){
                State newState = createChild(nodeId);
                children.add(newState);
            }
        }
        return children;
    }

    public State createChild(int nodeId){
        State newState = new State(this.graph.copy(), nodeId, this);
        LinkedList<Integer> nodeNeighbors = newState.getGraph().getNode(nodeId).getNeighborsIds();
        for (int j = 0; j < nodeNeighbors.size(); j++) {
            int neighborId=nodeNeighbors.get(j);
            newState.getGraph().getNode(neighborId).reverseNodeColor();
        }
        if(newState.getGraph().getNode(nodeId).getColor() == Color.Black){
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
        }else {
            newState.getGraph().getNode(nodeId).reverseNodeColor();
        }
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

    public State getParentState(){
        return parentState;
    }

    public  int getSelectedNodeId(){
        return selectedNodeId;
    }
}
