package com.minesweeper.domain;

public class Tile {

    boolean minePresent;
    int adjacentMines;

    public boolean isMinePresent() {
        return minePresent;
    }

    public void setMinePresent(boolean minePresent) {
        this.minePresent = minePresent;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
}
