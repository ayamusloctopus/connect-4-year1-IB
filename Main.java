import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String[][] board = new String[6][7];
        System.out.print("Hello and welcome to Connect 4 :) Would you like to see the instructions?");
        System.out.println();
        instructions();
        newGame(board);
    }
        public static void printGrid(String[][] board){
        System.out.println();
        System.out.print("0|1|2|3|4|5|6|");
            for (int row = 0; row <= 5; row++) {
                System.out.println();
                for (int column = 0; column < 7; column++) {
                    if (board[row][column] == null) {
                        board[row][column] = " ";
                    }
                    System.out.print(board[row][column] + "|");
                }
            }
            System.out.println();
        }

        public static void newGame(String [][] board){
            printGrid(board);
            Boolean userIsYellow = userGoesFirst();
            if(userIsYellow==true){
                System.out.print("The game has decided you are yellow (represented by the pawn X), you play first!");
                while(checkWin(board,"O") == false && checkWin(board,"X") == false){
                    userTurn(board);
                    if(gameEnd(board) == true){
                        break;
                    }
                    computerTurn(board);
                    gameEnd(board);
                }
            } else if (userIsYellow==false){
                System.out.print("The game has decided you are red (represented by the pawn X), the computer plays first!");
                while(checkWin(board,"O") == false && checkWin(board, "X") == false) {
                    computerTurn(board);
                    if(gameEnd(board) == true){
                        break;
                    }
                    userTurn(board);
                    gameEnd(board);
                }
            }
        }

        public static boolean userGoesFirst(){ //https://stackoverflow.com/questions/10918350/using-random-with-strings-in-java
        String [] assignPawns = new String[]{"yellow","red"};
        Random randomObject = new Random();
        int playercolour = randomObject.nextInt(assignPawns.length);
        if(playercolour == 0){
            return true;
        }
        return false;
    }

        public static void userTurn(String [][] board){
        int usersChoice = inputFromUser();
        Boolean columnFull = checkColumnFull(usersChoice,board);
            if(columnFull == false){
                placePawn(usersChoice, board, "X");
            } else if (columnFull == true) {
                userTurn(board);
            }
            printGrid(board);
        }

        public static void computerTurn(String [][] board){
        int computersChoice = inputFromComputer();
            if(checkColumnFull(computersChoice, board) == false){
                placePawn(computersChoice, board, "O");
                printGrid(board);
            } else if (checkColumnFull(computersChoice, board) == true) {
                computerTurn(board);
            }
        }

        public static String [][] placePawn(int inputColumn, String [][] board, String pawn){
                for (int row = 5; row >= 0; row--) {
                    if (board[row][inputColumn] == " ") {
                        int rowToBeFound = row;
                        board[rowToBeFound][inputColumn] = pawn;
                        break;
                    }
                }
                return board;
            }

        public static boolean checkColumnFull(int inputColumn, String [][] board){
            if (board[0][inputColumn] != " ") {
                System.out.println("This column is full, please pick another one.");
                return true;
            }
        return false;
        }

        public static boolean checkUserInput(String userInputColumn){
        if (userInputColumn.equals("0") ||userInputColumn.equals("1") || userInputColumn.equals("2") || userInputColumn.equals("3") || userInputColumn.equals("4") || userInputColumn.equals("5") || userInputColumn.equals("6")) {
        return true;
        } else {
            return false;}
        }

        public static int inputFromUser(){
        System.out.println();
        System.out.print("Enter which column you want to place your pawn in, from 0 to 6. ");
        Scanner userInputObject = new Scanner(System.in);
        String userInputColumnAsString = userInputObject.next();
            if (checkUserInput(userInputColumnAsString)==true){
                int userInputColumnAsInt = Integer.parseInt(userInputColumnAsString);
                return userInputColumnAsInt;
            } else if(checkUserInput(userInputColumnAsString)==false){
                System.out.println("You should enter a number from 0 to 6");
                inputFromUser();}
            return 6;
        }

        public static int inputFromComputer(){
        Random randomObject = new Random(); //creating a random object
        int computerInputColumn = randomObject.nextInt(7); //the computer will pick an integer between 0 and 6
        return computerInputColumn;}

        public static boolean checkHorizontalWin(String [][] board, String pawn){
        for (int row = 0; row < 6; row ++){
            for (int column = 0; column < 4; column ++){
                if (board[row][column].equals(pawn) && board[row][column+1].equals(pawn) && board[row][column+2].equals(pawn) && board[row][column+3].equals(pawn)){
                    return true;
                }
            }
        }
        return false;
        }

        public static boolean checkVerticalWin(String [][] board, String pawn){
        for (int column = 0; column < 7; column ++){
            for (int row = 0; row < 3; row ++){
                if (board[row][column].equals(pawn) && board[row+1][column].equals(pawn) && board[row+2][column].equals(pawn) && board[row+3][column].equals(pawn)){
                    return true;
                }
            }
        }
        return false;
        }

        public static boolean checkDiagonalWin1(String [][] board, String pawn){
        for(int row = 0; row < 3; row ++){
            for (int column = 0; column < 4; column ++){
                if (board[row][column].equals(pawn) && board[row+1][column+1].equals(pawn) && board[row+2][column+2].equals(pawn) && board[row+3][column+3].equals(pawn)){
                    return true;
                }
            }
        }
        return false;
        }

        public static boolean checkDiagonalWin2(String [][]board, String pawn){
        for(int row = 0; row < 3; row ++){
            for(int column = 6; column > 2; column --){
                if (board[row][column].equals(pawn) && board[row+1][column-1].equals(pawn) && board[row+2][column-2].equals(pawn) && board[row+3][column-3].equals(pawn)){
                    return true;
                }
            }
        }
        return false;
        }

        public static boolean checkWin(String [][] board, String pawn){
        if(checkHorizontalWin(board, pawn) || checkVerticalWin(board, pawn) || checkDiagonalWin1(board, pawn) || checkDiagonalWin2(board, pawn) == true){
            return true;}
        return false;
        }

        public static boolean checkTie(String [][] board){
            for (int row = 0; row <= 5; row++) {
                for (int column = 0; column < 7; column++) {
                    if (board[row][column] == " ") {
                        return false;
                    }
                }
            }
            System.out.println("The game was a tie! No one has won.");
            return true;
        }

        public static void instructions(){
        System.out.println();
        System.out.print("Enter Y if yes and N if no.");
        Scanner userInputObject = new Scanner(System.in);
        String yesOrNo = userInputObject.next();
        if(yesOrNo.equals("Y") || yesOrNo.equals("y")){
            System.out.println("1) You are player X that will be playing against the computer which is player 0.");
            System.out.println("2) When it is your turn, you can select which column you want to place your pawn in, and it will fall down to the bottom of the column." );
            System.out.println("3) Your goal is to have four of your pawns in a row: horizontally, vertically or diagonally!");
            System.out.println("4) Beware!! If your opponent is in the process of having 4 pawns in a row make sure to block them!");
            System.out.println("Good Luck");
        } else if (yesOrNo.equals("N") || yesOrNo.equals("n")) {
            System.out.println();
            System.out.println("Good Luck");
        } else {
            instructions();
        }
        }

        public static boolean gameEnd(String [][] board){
            if(checkWin(board,"O") == true){
                computerWin(board);
                return true;
            } else if(checkWin(board, "X") == true){
                playerWin(board);
                return true;
            }
            return false;
        }

        public static void computerWin(String [][] board){
            System.out.print("The computer has won");
            System.out.println();
            System.out.println("The game is over, would you like to play again?");
            newGameChoice(board);
        }

        public static void playerWin(String [][] board){
            System.out.print("You have won!");
            System.out.println();
            System.out.println("The game is over, would you like to play again?");
            newGameChoice(board);
        }

        public static void newGameChoice(String [][]board){
        System.out.print("Enter Y if yes and N if no.");
        Scanner userInputObject = new Scanner(System.in);
        String yesOrNo = userInputObject.next();
            if(yesOrNo.equals("Y") || yesOrNo.equals("y")){
                for (int row = 0; row <= 5; row++) {
                    for (int column = 0; column < 7; column++) {
                        if (board[row][column].equals("O")) {
                            board[row][column] = " ";
                        } else if(board[row][column].equals("X")) {
                            board[row][column] = " ";
                        }
                        }
                    }
                newGame(board);
            } else if (yesOrNo.equals("N") || yesOrNo.equals("n")){
                System.out.println("Bye!");
            } else {
                newGameChoice(board);
            }
        }

}


