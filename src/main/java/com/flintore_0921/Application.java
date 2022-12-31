package com.flintore_0921;

import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.managers.TicTacToeManager;

public class Application {
    private static TicTacToeManager ticTacToeManager;

    private static PlayerIcon lastIconPlayed;

    public static void main(String[] args) {
//        Initialize variables.
        ticTacToeManager = new TicTacToeManager(System.out);

        printMenu();


    }

    private static void printMenu() {
        int option = -1;


    }


    //    Printers
    private static void println(Object value){
        System.out.println(value);
    }

    private static void print(Object value){
        System.out.print(value);
    }
}
