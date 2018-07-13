package com.minesweeper.domain;

import java.util.Random;

public class GameBoard {
    Tile[][] tileArray;
    int boardSize;
    int numberOfMines;

    public GameBoard(int boardSize, int numberOfMines){
        this.boardSize = boardSize;
        this.numberOfMines = numberOfMines;
        this.init();
    }

    private void init(){
        //Generate our boardSize x boardSize array:
        tileArray = new Tile[boardSize][boardSize];
        for(int i = 0; i < tileArray.length; i++){
            for(int j = 0; j < tileArray[0].length; j++){
                tileArray[i][j] = new Tile();
            }
        }

        //Now, we need to pick random tiles to place mines in
        Random minePlacer = new Random();
        int minesPlaced = 0;
        while(minesPlaced<numberOfMines){
            int xToPlace = minePlacer.nextInt(boardSize-1);
            int yToPlace = minePlacer.nextInt(boardSize-1);
            if(tileArray[xToPlace][yToPlace].hasMinePresent()){
               continue;
            }
            else{
                tileArray[xToPlace][yToPlace].setMinePresent(true);
                tileArray[xToPlace][yToPlace].reveal();
                minesPlaced++;
            }
        }

        //Finally, we need to calculate the adjacent mines for the other tiles
    }

    public void printGameBoard(){
        for(int i = 0; i < tileArray.length; i++){
            for(int j = 0; j < tileArray[i].length; j++){
                System.out.print(" " + tileArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}
