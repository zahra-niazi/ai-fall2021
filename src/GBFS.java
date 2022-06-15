import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GBFS {

    public static void search(InformedState initialState){

        PriorityQueue<InformedState> frontier = new PriorityQueue<>(100, new Comparator<InformedState>() {
            @Override
            public int compare(InformedState state1, InformedState state2) {
                if (state1.h > state2.h) return 1;
                else return -1;
            }
        });
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        if(isGoal(initialState)){
            result(initialState);
            return;
        }
        frontier.add(initialState);
        inFrontier.put(initialState.hash(),true);
        while (!frontier.isEmpty()){
            InformedState tempState = frontier.poll();
            inFrontier.remove(tempState.hash());
            explored.put(tempState.hash(),true);
            ArrayList<InformedState> children = tempState.successor();

            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        result(children.get(i));
                        return;
                    }
                    frontier.add(children.get(i));
                    children.get(i).outputGenerator();
                    inFrontier.put(children.get(i).hash(), true);
                }
            }
        }
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
            FileWriter myWriter = new FileWriter("gbfsResult.txt");
            System.out.println("initial state : ");
            while (!states.empty()){
                InformedState tempState = states.pop();
                if(tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();
                System.out.println(tempState.f);
                if (tempState.getParentState()!=null) System.out.println("tempState.getParentState().cost = " + tempState.getParentState().f);
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


