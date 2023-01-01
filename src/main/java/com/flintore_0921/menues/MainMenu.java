package com.flintore_0921.menues;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.flintore_0921.componentes.Constants.Menu.*;

public class MainMenu extends Menu {

    public static final int PLAYERS_EXPECTED = 2;

    /*Key values for each menu.*/
    interface MenuKey {
        int KEY_GAME_MENU = 1;
        int KEY_PLAYER_MENU = 2;
    }

    private final Map<Integer, Menu> mapMenu;

    public MainMenu() {
        super();
        this.mapMenu = new HashMap<>();
        this.mapMenu.put(MenuKey.KEY_PLAYER_MENU, new PlayerMenu());
    }

    public MainMenu(PrintStream printer, Scanner scannerResponse) {
        super(printer, scannerResponse);
        this.mapMenu = new HashMap<>();
        this.mapMenu.put(MenuKey.KEY_PLAYER_MENU, new PlayerMenu(printer, scannerResponse));
    }

    @Override
    void printMenu() {
        int option = -1;

        do {
            try {
                println("Elija una opcion:");
//                println("1. Iniciar partida.");
                println("2. Menu Jugadores.");
                println("0. Salir");

                option = this.INPUT_RESPONSE.nextInt();

                if (!validate(option, this.mapMenu.size())) {
                    throw new Exception();
                }

                switch (option) {
                    case MenuKey.KEY_GAME_MENU -> startGame();
                    default -> callMenu(option);
                }

            } catch (Exception ex) {
                println(DEFAULT_INVALID_OPTION_MESSAGE);
            }
        } while (!isExit(option));
    }

    private void startGame() {
        /*Validate if there are players*/
        if (!isTherePlayersToPlay()) {
            printMessageWithSeparators("Agregue jugadores para iniciar a jugar");
            return;
        }

        // Logic to start game

    }

    private boolean isTherePlayersToPlay() {
        final PlayerMenu playerMenu = (PlayerMenu) this.mapMenu.get(MenuKey.KEY_GAME_MENU);
        return playerMenu.therePlayersToPlay(PLAYERS_EXPECTED);
    }

    private void callMenu(int option) {
        this.mapMenu.get(option - 1).printMenu();
    }
}
