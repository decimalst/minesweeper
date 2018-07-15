package com.minesweeper.domain;

public class Tile {
    boolean revealed;
    boolean flagged;
    boolean minePresent;
    int adjacentMines;

    public Tile(){
        this.revealed = false;
        this.minePresent = false;
        this.adjacentMines = 0;
    }

    public void reveal(){
        if(minePresent){
            //TODO: should end the game
            ;
        }
        this.revealed = true;
    }
    public boolean hasMinePresent() {
        return minePresent;
    }

    public void setMinePresent(boolean minePresent) {
        this.minePresent = minePresent;
    }

    public boolean isFlagged() { return flagged; }

    public void setFlagged(boolean flagged) { this.flagged = flagged; }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    @Override
    public String toString(){
        if ( revealed ){
            if(minePresent){
                return "M";
            }
            else {
                return Integer.toString(adjacentMines);
            }
        }
        else if (flagged){
            return "F";
        }
        else {
            return "O";
        }

    }
}
