package com.minesweeper.domain;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

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
                Multimap<Integer, Integer> listOfAdjacentTileCoordinates = getAdjacents(i,j);
                //System.out.println("I = " + i + " J = " + j);
                //System.out.println(listOfAdjacentTileCoordinates.entrySet());
                int adjacentMineCount = listOfAdjacentTileCoordinates.entries()
                                                        .stream()
                                                        .map(tile -> tileArray[tile.getValue()][tile.getKey()].minePresent?1:0)
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

    public Multimap<Integer,Integer> getAdjacents(int i, int j){
        Multimap<Integer,Integer> adjacentPairs = MultimapBuilder.treeKeys().arrayListValues().build();
        List<Integer> possibleX = new ArrayList<Integer>();
        List<Integer> possibleY = new ArrayList<Integer>();

        //I = 5 J = 4
        //[4=5, 5=4, 6=4]

        if((i - 1) >= 0){
            possibleY.add(i-1);
        }
        if((i + 1) < this.boardSize){
            possibleY.add(i+1);
        }
        possibleY.add(i);

        if((j - 1) >= 0){
            possibleX.add(j-1);
        }
        if((j + 1) < this.boardSize ){
            possibleX.add(j+1);
        }
        possibleX.add(j);


        for( int x : possibleX){
            for( int y : possibleY){
                    adjacentPairs.put(x,y);
            }
        }
        adjacentPairs.remove(i,j);
        return adjacentPairs;
    }
}
