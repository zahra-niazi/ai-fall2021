import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class IDAStar {

    public static double search(InformedState currState, double g, double threshold){
        double f = currState.g + currState.h;
        if (f>threshold) return f;
        if(isGoal(currState)){
            result(currState);
            return -1;
        }
        double min = Double.MAX_VALUE;

        for (int i = currState.getGraph().size()-1; i>= 0; i--) {
            int nodeId= currState.getGraph().getNode(i).getId();
            InformedState tempState = currState.createChild(nodeId);
            double temp = search(tempState, tempState.g ,threshold);
            if (temp == -1) return -1;
            if (temp < min) min=temp;
        }
        return min;
    }

    private static boolean isGoal(InformedState state){
        for (int i = 0; i < state.getGraph().size(); i++) {
            if(state.getGraph().getNode(i).getColor() == Color.Red
                    || state.getGraph().getNode(i).getColor() == Color.Black){
                return false;
            }
        }
        return true;
    }

    private static void result(InformedState state){
        Stack<InformedState>  states = new Stack<InformedState>();
        while (true){
            states.push(state);
            if(state.getParentState() == null){
                break;
            }
            else {
                state = state.getParentState();
            }
        }
        try {
            FileWriter myWriter = new FileWriter("idastarResult.txt");
            System.out.println("initial state : ");
            while (!states.empty()){
                InformedState tempState = states.pop();
                if(tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();

                myWriter.write(tempState.getSelectedNodeId()+" ,");
                myWriter.write(tempState.outputGenerator()+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


