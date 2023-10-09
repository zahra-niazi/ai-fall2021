import java.io.*;
import java.util.Scanner;

public class Main2 {

    static int magnetCount,b;
    static int[] rowPositive, columnPositive, rowNegative, columnNegative;

    public static void main(String[] args) throws IOException {
        File input = new File("input1_method2.txt");

        Scanner scanner = new Scanner(input);
        int rowCount = scanner.nextInt();
        int columnCount = scanner.nextInt();

        magnetCount =0;

        rowPositive = new int[rowCount];
        for (int i=0;i<rowCount ; i++){
            rowPositive[i] = scanner.nextInt();
            magnetCount+=rowPositive[i];
        }

        rowNegative = new int[rowCount];
        for (int i=0;i<rowCount ; i++){
            rowNegative[i] = scanner.nextInt();
        }

        columnPositive = new int[columnCount];
        for (int i=0;i<columnCount ; i++){
            columnPositive[i] = scanner.nextInt();
        }


        columnNegative = new int[columnCount];
        for (int i=0;i<columnCount ; i++){
            columnNegative[i] = scanner.nextInt();
        }

        int[][] board = new int[rowCount][columnCount];
        for (int i=0; i<rowCount; i++){
            for (int j=0; j<columnCount; j++){
                board[i][j]= scanner.nextInt();
            }
        }

        for (int i=0; i<rowCount; i++){
            for (int j=0; j<columnCount; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

        b=(rowCount*columnCount)/2;
        int[][] magnet = new int[b][5];

        for (int i =0; i<b; i++){
            magnet[i][0] = 0;
        }

        for ( int i=0; i<rowCount; i++){
            for (int j=0; j<columnCount; j++){
                if (magnet[board[i][j]-1][0] == 0) {
                    magnet[board[i][j] - 1][1] = i;
                    magnet[board[i][j] - 1][2] = j;
                    magnet[board[i][j] - 1][0] = 1;
                }
                else {
                    magnet[board[i][j] - 1][3] = i;
                    magnet[board[i][j] - 1][4] = j;
                }

            }
        }
        for (int i =0; i<b; i++){
            magnet[i][0] = -1;
        }

        magnet[0][0]=0;
        backtracking_search(magnet,0);

        magnet[0][0]=1;
        backtracking_search(magnet,0);

        magnet[0][0]=1;
        int temp = magnet[0][1];
        magnet[0][1]=magnet[0][3];
        magnet[0][3]=temp;

        temp = magnet[0][2];
        magnet[0][2]=magnet[0][4];
        magnet[0][4]=temp;

        backtracking_search(magnet,0);

    }


    public static void backtracking_search(int[][] magnet2, int num2){
        int[][] magnet = new int[b+1][5];
        for (int i=0; i<b;i++){
            for (int j=0;j<5;j++){
                magnet[i][j] = magnet2[i][j];
            }
        }
        int num = num2;


        if (num>=b) return;

        if(magnet[num][0] ==1)
            if (!check_neighbor_constraint(magnet,num)) {
                return;
            }

        if (!check_cons(magnet,num)){
            return;
        }
        if (is_complete_assignment(magnet) ) {
            return;
        }

        //// magnet
        magnet[num+1][0] =1;
        backtracking_search(magnet,num+1);

        //// magnet reverse
        magnet[num+1][0] = 1;

        int temp = magnet[num+1][1];
        magnet[num+1][1]=magnet[num+1][3];
        magnet[num+1][3]=temp;

        temp = magnet[num+1][2];
        magnet[num+1][2]=magnet[num+1][4];
        magnet[num+1][4]=temp;

        backtracking_search(magnet,num+1);

        //// empty
        magnet[num+1][0] =0;
        backtracking_search(magnet,num+1);

    }




    public static boolean is_complete_assignment(int[][] magnet){
        int c=0;
        for (int i =0; i<b; i++){
            if (magnet[i][0] == 1) {
                c++;
            }
        }
        if (c==magnetCount) {

            System.out.println("RES:");
            for (int i=0;i<b ; i++){
                for (int j=0; j<5; j++){
                    System.out.print(magnet[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            return true;
        }
        else return false;
    }


    public static boolean check_neighbor_constraint (int[][] magnet, int num) {
        boolean a=true,b=true,c=true,d=true;
        for(int i=0; i<num ; i++) {
            if (magnet[i][0] == 1) {

                // xpos equal
                if (magnet[i][1] == magnet[num][1]) {
                    if (Math.abs(magnet[i][2] - magnet[num][2]) == 1) {
                        a= false;
                    }
                }

                // ypos equal
                if (magnet[i][2] == magnet[num][2]) {
                    if (Math.abs(magnet[i][1] - magnet[num][1]) == 1) {
                        b= false;
                    }
                }

                // xneg equal
                if (magnet[i][3] == magnet[num][3]) {
                    if (Math.abs(magnet[i][4] - magnet[num][4]) == 1) {
                        c= false;
                    }
                }

                // yneg equal
                if (magnet[i][4] == magnet[num][4]) {
                    if (Math.abs(magnet[i][3] - magnet[num][3]) == 1) {
                        d= false;
                    }
                }

            }


        }
        if (a && b && c && d){
            return true;
        }
        return false;

    }


    public static boolean check_cons(int[][] magnet,int num){
        int[] xPositive = new int[rowPositive.length];
        int[] yPositive = new int[columnPositive.length];
        int[] xNegative = new int[rowNegative.length];
        int[] yNegative = new int[columnNegative.length];

        for (int i=0 ; i<rowPositive.length ; i++){
            xPositive[i] = 0;
        }
        for (int i=0 ; i<columnPositive.length ; i++){
            yPositive[i] = 0;
        }
        for (int i=0 ; i<rowNegative.length ; i++){
            xNegative[i] = 0;
        }
        for (int i=0 ; i<columnNegative.length ; i++){
            yNegative[i] = 0;
        }


        for (int i=0 ; i<=num ; i++){
            if (magnet[i][0] ==1){
                xPositive[magnet[i][1]]++;
                yPositive[magnet[i][2]]++;
                xNegative[magnet[i][3]]++;
                yNegative[magnet[i][4]]++;
            }
        }

        boolean a=true,b=true,c=true,d=true;


        for (int i=0 ; i<rowPositive.length ; i++){
            if (xPositive[i] > rowPositive[i]) {
                a=false;
            }
        }
        for (int i=0 ; i<columnPositive.length ; i++){
            if (yPositive[i] > columnPositive[i]) {
                b=false;
            }
        }
        for (int i=0 ; i<rowNegative.length ; i++){
            if (xNegative[i] > rowNegative[i]) {
                c=false;
            }
        }
        for (int i=0 ; i<columnNegative.length ; i++){
            if (yNegative[i] > columnNegative[i]) {
                d=false;
            }
        }

        if (a && b && c && d){
            return true;
        }
        return false;
    }


}
