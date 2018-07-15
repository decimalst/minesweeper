package com.minesweeper.domain;

public class Command {
    int x;
    int y;
    String command;

    public Command(int x, int y, String command) {
        this.x = x;
        this.y = y;
        this.command = command;
    }

    @Override
    public String toString(){
        return command + " X = " + x + " Y= " + y;
    }
}
