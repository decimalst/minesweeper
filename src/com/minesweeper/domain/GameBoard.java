package com.minesweeper.domain;

import java.util.*;

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

        //Finally, we need to calculate the adjacent mines for each tile
        //getAdjacents() -> map a lambda to the getAdjacents map, which returns 1 on mine present
        //-> reduce to sum the number of adjacent mines
        for(int i = 0; i < tileArray.length; i++){
            for(int j = 0; j < tileArray[0].length; j++){
                //Maps don't allow duplicate key value pairs - I want a list-like thing here or something that flattens
                //to a list-like thing
                MultiMap<Integer, Integer> listOfAdjacentTileCoordinates = getAdjacents(i,j);
                //System.out.println("I = " + i + " J = " + j);
                //System.out.println(listOfAdjacentTileCoordinates.entrySet());
                int adjacentMineCount = listOfAdjacentTileCoordinates.entrySet().stream()
                                                        .map(tile -> tileArray[tile.getKey()][tile.getValue()].minePresent?1:0)
                                                        .reduce(0,(a,b) -> a+b );
                //System.out.println(adjacentMineCount);
                tileArray[i][j].adjacentMines = adjacentMineCount;
            }
        }
    }

    public void printGameBoard(){
        for(int i = 0; i < tileArray.length; i++){
            for(int j = 0; j < tileArray[i].length; j++){
                System.out.print(" " + tileArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public MultiMap<Integer,Integer> getAdjacents(int i, int j){
        Map<Integer,Integer> adjacentPairs = new HashMap<Integer,Integer>();
        List<Integer> possibleX = new ArrayList<Integer>();
        List<Integer> possibleY = new ArrayList<Integer>();

        //I = 5 J = 4
        //[4=5, 5=4, 6=4]

        if((i - 1) > 0){
            possibleX.add(i-1);
        }
        if((i + 1) < this.boardSize){
            possibleX.add(i+1);
        }
        possibleX.add(i);
        System.out.println("i = " + i);
        System.out.println(possibleX);
        if((j - 1) > 0){
            possibleY.add(j-1);
        }
        if((j + 1) < this.boardSize ){
            possibleY.add(j+1);
        }
        possibleY.add(j);
        System.out.println("j = " + j);
        System.out.println(possibleY);

        for( int x : possibleX){
            for( int y : possibleY){
                if( x != i && y != j ){
                    adjacentPairs.put(x,y);
                }
            }
        }
        System.out.println(adjacentPairs.entrySet());
        return adjacentPairs;
    }
}
