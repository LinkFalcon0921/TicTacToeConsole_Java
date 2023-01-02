package com.flintore_0921;

import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.menues.MainMenu;

public class Application {
//    Main Menu class
    private static MainMenu menuApp;

    private static PlayerIcon lastIconPlayed;

    public static void main(String[] args) {
//        Initialize variables.
        menuApp = new MainMenu();

//        label start app
        menuApp.printMenu();
    }
}
