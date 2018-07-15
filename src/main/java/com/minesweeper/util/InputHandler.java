package com.minesweeper.util;

import com.minesweeper.domain.Command;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputHandler {

    public final static String commandOne = "f" ;
    public final static String commandTwo = "r" ;

    public final static Set setOfCommands = Collections.unmodifiableSet(new HashSet(){
        private static final long serialVersionUID = 1L;
        {
            add(commandOne);
            add(commandTwo);
        }
    });

    Scanner scanner;
    int tileBoundary;

    public InputHandler(Scanner scanner, int bounds) {
        this.scanner = scanner;
        this.tileBoundary = bounds - 1;
    }

    public Command promptForCommand(){
        System.out.println("[F]lag [R]eveal");
        String input = scanner.nextLine();
        while(!setOfCommands.contains(input)){
            System.out.println("Not a valid command.");
            System.out.println("[F]lag [R]eveal");
            input = scanner.nextLine().toLowerCase();
        }
        System.out.println("Input x: ");
        int x = scanner.nextInt();
        while ( x < 0 || x > tileBoundary){
            scanner.nextLine();
            System.out.println("Invalid value for x, valid values are [0-" + tileBoundary + "]");
            x = scanner.nextInt();
        }

        System.out.println("Input y: ");
        int y = scanner.nextInt();
        while ( y < 0 || y > tileBoundary){
            scanner.nextLine();
            System.out.println("Invalid value for y, valid values are [0-" + tileBoundary + "]");
            y = scanner.nextInt();
        }
        scanner.nextLine();
        return new Command(x,y,input);
    }



}
