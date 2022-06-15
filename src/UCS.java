import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UCS {

    public static void search(UCSState initialState){
        PriorityQueue<UCSState> frontier = new PriorityQueue<UCSState>(100, new Comparator<UCSState>() {
            @Override
            public int compare(UCSState ucsState, UCSState t1) {
                if (ucsState.cost>t1.cost) return 1;
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
            UCSState tempState = frontier.poll();
            inFrontier.remove(tempState.hash());
            explored.put(tempState.hash(),true);
            ArrayList<UCSState> children = tempState.successor();

            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        result(children.get(i));
                        System.out.println("cost when goal found =     "+children.get(i).cost);
                        return;
                    }
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
               /* else if (inFrontier.containsKey(children.get(i).hash()) ){
                    Iterator<UCSState> it = frontier.iterator();
                    int sw=0;
                    UCSState st = null;
                    while (it.hasNext()){
                        UCSState temp=it.next();
                        if (temp.hash().equals(children.get(i).hash()) && temp.cost > children.get(i).cost){
                            //frontier.remove(temp);
                            //it.remove();
                            st = temp;
                            sw=1;
                            break;
                           // frontier.add(children.get(i));
                        }

                   }
                    if (sw==1){
                        frontier.remove(st);
                        frontier.add(children.get(i));
                    }
                }*/
            }
        }
    }

    private static boolean isGoal(UCSState state){
        for (int i = 0; i < state.getGraph().size(); i++) {
            if(state.getGraph().getNode(i).getColor() == Color.Red
                    || state.getGraph().getNode(i).getColor() == Color.Black){
                return false;
            }
        }
        return true;
    }

    private static void result(UCSState state){
        Stack<UCSState>  states = new Stack<UCSState>();
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
            FileWriter myWriter = new FileWriter("ucsResult.txt");
            System.out.println("initial state : ");
            while (!states.empty()){
                UCSState tempState = states.pop();
                if(tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();
                System.out.println(tempState.cost);
                if (tempState.getParentState()!=null) System.out.println("tempState.getParentState().cost = " + tempState.getParentState().cost);
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


