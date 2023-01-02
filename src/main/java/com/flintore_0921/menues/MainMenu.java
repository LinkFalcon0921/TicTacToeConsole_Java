package com.flintore_0921.menues;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu extends Menu {

    private static final int PLAYERS_EXPECTED = 2;

    /*Key values for each menu.*/
    private interface MenuKey {
        int KEY_GAME_MENU = 1;
        int KEY_PLAYER_MENU = 2;
    }

    private final Map<Integer, Menu> mapMenu;

    public MainMenu() {
        super();
        this.mapMenu = new HashMap<>();
        this.mapMenu.put(MenuKey.KEY_GAME_MENU, new PlayerMenu());
        this.mapMenu.put(MenuKey.KEY_PLAYER_MENU, new PlayerMenu());
    }

    public MainMenu(PrintStream printer, Scanner scannerResponse) {
        super(printer, scannerResponse);
        this.mapMenu = new HashMap<>();
        this.mapMenu.put(MenuKey.KEY_GAME_MENU, new PlayerMenu(printer, scannerResponse));
        this.mapMenu.put(MenuKey.KEY_PLAYER_MENU, new PlayerMenu(printer, scannerResponse));
    }

    @Override
    public void printMenu() {
        int option = -1;

        do {
            try {
                println("Elija una opcion:");
                println("1. Iniciar partida.");
                println("2. Menu Jugadores.");
                println(setOptionMenu(EXIT_VALUE, OPTION_EXIT));

                option = this.INPUT_RESPONSE.nextInt();

                if (!validate(option, this.mapMenu.size())) {
                    throw new Exception();
                }

                final int menuSelected = option;

                switch (menuSelected) {
                    case MenuKey.KEY_GAME_MENU -> startGame();
                    case EXIT_VALUE -> {}
                    default -> callMenu(menuSelected);
                }

            } catch (Exception ex) {
                showDefaultMessageInvalidOption();
            }
        } while (!isExit(option));
    }

    // TODO: 1/1/2023 Complete logic 
    private void startGame() {
        /*Validate if there are players*/
        if (!isTherePlayersToPlay()) {
            printMessageWithSeparators("Agregue jugadores para iniciar a jugar.");
            return;
        }

        // Logic to start game
        printMessageWithSeparators("No esta disponible");
        fullLineSeparatorSpace();
    }

    private boolean isTherePlayersToPlay() {
        final PlayerMenu playerMenu = (PlayerMenu) this.mapMenu.get(MenuKey.KEY_PLAYER_MENU);
        return playerMenu.therePlayersToPlay(PLAYERS_EXPECTED);
    }

    private void callMenu(int selected) {
        if (isExit(selected)) {
            return;
        }

        this.mapMenu.get(selected).printMenu();
    }
}
