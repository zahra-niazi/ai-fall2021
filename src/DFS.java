import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class DFS {
    public static int search(State currState, int selectedNodeId, int level){
        if(isGoal(currState)){
            result(currState);
            return 1;
        }
        else if (level==0) return 0;
        else {
            for (int i = currState.getGraph().size()-1; i>= 0; i--) {
                int nodeId= currState.getGraph().getNode(i).getId();
                if (i!=selectedNodeId) {
                    if (search(currState.createChild(nodeId), nodeId, level - 1) == 1) {
                        return 1;
                    }
                }
            }
            return 0 ;
        }
    }

    private static boolean isGoal(State state){
        for (int i = 0; i < state.getGraph().size(); i++) {
            if(state.getGraph().getNode(i).getColor() == Color.Red
                    || state.getGraph().getNode(i).getColor() == Color.Black){
                return false;
            }
        }
        return true;
    }

    private static void result(State state){
        Stack<State>  states = new Stack<State>();
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
            FileWriter myWriter = new FileWriter("dfsResult.txt");
            System.out.println("initial state : ");
            while (!states.empty()){
                State tempState = states.pop();
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


