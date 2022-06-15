import java.util.ArrayList;
import java.util.LinkedList;

public class InformedState {

    private Graph graph;
    private int selectedNodeId;
    private InformedState parentState;
    public double g;
    public double h;
    public double f;

    public InformedState(Graph graph, int selectedNodeId, InformedState parentState){
        this.graph= graph.copy();
        this.selectedNodeId= selectedNodeId;
        if(parentState != null){
            this.parentState= parentState;
        }else{
            this.parentState = null;
            this.g=0;
        }
    }

    public ArrayList<InformedState> successor(){
        ArrayList<InformedState> children= new ArrayList<>();
        for (int i = 0; i < this.graph.size(); i++) {
            int nodeId= this.graph.getNode(i).getId();
            if(nodeId != selectedNodeId){
                InformedState newState = createChild(nodeId);
                children.add(newState);
            }
        }
        return children;
    }

    public InformedState createChild(int nodeId){
        InformedState newState = new InformedState(this.graph.copy(), nodeId, this);
        LinkedList<Integer> nodeNeighbors = newState.getGraph().getNode(nodeId).getNeighborsIds();

        int gCount=0,rCount=0,bCount=0;
        for (int i=0;i<newState.getGraph().size();i++){
            if (newState.getGraph().getNode(i).getColor() == Color.Red) rCount++;
            else if (newState.getGraph().getNode(i).getColor() == Color.Green) gCount++;
            else bCount++;
        }
        newState.h=(rCount*3 + bCount*2)/newState.getGraph().maxNeighbor;

        for (int j = 0; j < nodeNeighbors.size(); j++) {
            int neighborId=nodeNeighbors.get(j);
            newState.getGraph().getNode(neighborId).reverseNodeColor();
        }
        double pg=0;
        if (parentState!=null) {
            pg=newState.parentState.g;
        }
        System.out.println("pg = " + pg);
        if(newState.getGraph().getNode(nodeId).getColor() == Color.Black){
            newState.g = pg + 2;
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
                newState.g = pg + 1;
            } else if (newState.getGraph().getNode(nodeId).getColor() == Color.Green) {
                newState.g = pg + 3;
            }
            newState.getGraph().getNode(nodeId).reverseNodeColor();
        }

        newState.f= newState.g + newState.h;
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

    public InformedState getParentState(){
        return parentState;
    }

    public  int getSelectedNodeId(){
        return selectedNodeId;
    }
}
