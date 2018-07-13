import com.minesweeper.domain.GameBoard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    public static void main(String[] args){
        printAsciiTitle();
        GameBoard gameBoard = gameSetup();
        gameBoard.printGameBoard();
    }
    public static GameBoard gameSetup(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String username = sc.nextLine();
        int boardSize = 0;
        do{
            System.out.println("Enter board size[4-16]:");
            try{
                boardSize = sc.nextInt();
            } catch (InputMismatchException e){
                sc.nextLine();
                continue;
            }
        } while(boardSize > 16 || boardSize < 4);
        System.out.println("Board size = " + boardSize);
        int numberOfMines = 0;
        do{
            System.out.println("Enter number of mines[1-" + (int)(Math.pow(boardSize,2)-1)+ "]");
            try{
                numberOfMines = sc.nextInt();
            } catch (InputMismatchException e){
                sc.nextLine();
                continue;
            }
        } while(numberOfMines > (Math.pow(boardSize,2)-1) || numberOfMines < 1);
        System.out.println("Number of mines = " + numberOfMines);

        sc.close();
        GameBoard gameBoard = new GameBoard(boardSize,numberOfMines);
        return gameBoard;
    }

    public static void printAsciiTitle(){
        System.out.println("############################");
        System.out.println("########MINESWEEPER#########");
        System.out.println("############################");
    }
}
