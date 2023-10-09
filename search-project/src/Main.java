public class Main {

    public static void main(String[] args) {

        //-----------------------------------------------> test1 :
//        Graph initialGraph= new Graph(5);
//        initialGraph.addNode(new Node(0, Color.Red));
//        initialGraph.addNode(new Node(1, Color.Red));
//        initialGraph.addNode(new Node(2, Color.Red));
//        initialGraph.addNode(new Node(3, Color.Green));
//        initialGraph.addNode(new Node(4, Color.Red));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(3));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(4));

        //-----------------------------------------------> test2 :
      /*  Graph initialGraph= new Graph(7);
        initialGraph.maxNeighbor =  3;
        initialGraph.addNode(new Node(0, Color.Red));
        initialGraph.addNode(new Node(1, Color.Black));
        initialGraph.addNode(new Node(2, Color.Green));
        initialGraph.addNode(new Node(3, Color.Red));
        initialGraph.addNode(new Node(4, Color.Red));
        initialGraph.addNode(new Node(5, Color.Green));
        initialGraph.addNode(new Node(6, Color.Red));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(4));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(3));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(4));
        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(5));
        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(5));
        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(6));
*/
        //-----------------------------------------------> test3 :
     /*   Graph initialGraph= new Graph(15);
        initialGraph.maxNeighbor =  5;
        initialGraph.addNode(new Node(0, Color.Red));
        initialGraph.addNode(new Node(1, Color.Black));
        initialGraph.addNode(new Node(2, Color.Black));
        initialGraph.addNode(new Node(3, Color.Black));
        initialGraph.addNode(new Node(4, Color.Red));
        initialGraph.addNode(new Node(5, Color.Green));
        initialGraph.addNode(new Node(6, Color.Green));
        initialGraph.addNode(new Node(7, Color.Red));
        initialGraph.addNode(new Node(8, Color.Red));
        initialGraph.addNode(new Node(9, Color.Green));
        initialGraph.addNode(new Node(10, Color.Red));
        initialGraph.addNode(new Node(11, Color.Red));
        initialGraph.addNode(new Node(12, Color.Red));
        initialGraph.addNode(new Node(13, Color.Green));
        initialGraph.addNode(new Node(14, Color.Red));

        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(2));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(14));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(3));
        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(5));
        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(6));
        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(7));
        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(13));
        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(14));
        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(7));
        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(6));
        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(11));
        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(10));
        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(12));
        initialGraph.addLinkBetween(initialGraph.getNode(6), initialGraph.getNode(11));
        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(8));
        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(9));
        initialGraph.addLinkBetween(initialGraph.getNode(8), initialGraph.getNode(14));*/



        Graph initialGraph= new Graph(10);
        initialGraph.addNode(new Node(0, Color.Red));
        initialGraph.addNode(new Node(1, Color.Red));
        initialGraph.addNode(new Node(2, Color.Red));
        initialGraph.addNode(new Node(3, Color.Green));
        initialGraph.addNode(new Node(4, Color.Red));
        initialGraph.addNode(new Node(5, Color.Green));
        initialGraph.addNode(new Node(6, Color.Green));
        initialGraph.addNode(new Node(7, Color.Red));
        initialGraph.addNode(new Node(8, Color.Black));
        initialGraph.addNode(new Node(9, Color.Black));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(3));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(9));
        initialGraph.addLinkBetween(initialGraph.getNode(6), initialGraph.getNode(5));
        initialGraph.addLinkBetween(initialGraph.getNode(8), initialGraph.getNode(0));
        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(8));
        initialGraph.addLinkBetween(initialGraph.getNode(9), initialGraph.getNode(8));
        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(8));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(5));







        State initialState= new State(initialGraph, -1, null);

         //BFS.search(initialState);
        // DFS.search(initialState, -1, 25);

        /// IDS
       for (int k=0 ; k<10 ; k++){
         DFS.search(initialState,k,25);
       }

         //UCSState initialState1= new UCSState(initialGraph, -1, null);
         //UCS.search(initialState1);


        /// ###################################################################################################### ///



        // InformedState initialState2= new InformedState(initialGraph, -1, null);
       //  AStar.search(initialState2);
        // GBFS.search(initialState2);



        //  IDA*

       /* double threshold=initialState2.h;
        while (true){
            double temp = IDAStar.search(initialState2,0,threshold);
            if (temp==-1){
                break;
            }
            if (temp >100) break;
            threshold=temp;
        }*/

    }
}


