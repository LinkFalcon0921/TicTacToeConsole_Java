package com.flintore_0921.menus;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.flintore_0921.componentes.Constants.*;

public class MainMenu extends Menu {

    /*Key values for each menu.*/
    private interface MenuKey {
        int KEY_GAME_MENU = 1;
        int KEY_PLAYER_MENU = 2;

        String OPTION_START_GAME = "Iniciar partida";
        String OPTION_PLAYER_MENU = "Jugadores";
    }

    private final Map<Integer, Menu> mapMenu;

    public MainMenu() {
        super();
        this.mapMenu = new HashMap<>();
        // TODO: 1/2/2023 remove it
        this.mapMenu.put(MenuKey.KEY_GAME_MENU, new PlayerMenu());
        this.mapMenu.put(MenuKey.KEY_PLAYER_MENU, new PlayerMenu());
    }

    public MainMenu(PrintStream printer, Scanner scannerResponse) {
        super(printer, scannerResponse);
        this.mapMenu = new HashMap<>();
        // TODO: 1/2/2023 remove it
        this.mapMenu.put(MenuKey.KEY_GAME_MENU, new PlayerMenu(printer, scannerResponse));
        this.mapMenu.put(MenuKey.KEY_PLAYER_MENU, new PlayerMenu(printer, scannerResponse));
    }

    @Override
    public void printMenu() {
        int option = -1;

        do {
            try {
                println("Elija una opcion:");
                println(setOptionMenu(MenuKey.KEY_GAME_MENU, MenuKey.OPTION_START_GAME));
                println(setOptionMenu(MenuKey.KEY_PLAYER_MENU, MenuKey.OPTION_PLAYER_MENU));
                println(setOptionMenu(EXIT_VALUE, OPTION_EXIT));

                option = this.INPUT_RESPONSE.nextInt();

                if (!validate(option, this.mapMenu.size())) {
                    callInvalidOptionException();
                }

                final int menuSelected = option;

                switch (menuSelected) {
                    case MenuKey.KEY_GAME_MENU -> startGame();
                    case EXIT_VALUE -> {/*do nothing*/}
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
        final PlayerMenu playerMenu = getMenu(MenuKey.KEY_PLAYER_MENU);
        return playerMenu.therePlayersToPlay(TicTacGame.PLAYERS_EXPECTED);
    }

    private void callMenu(int selected) {
        if (isExit(selected)) {
            return;
        }

        getMenu(selected).printMenu();
    }

    //    Method to map menus
    @SuppressWarnings("unused")
    private <M extends Menu> M getMenu(Class<M> cExpected, final int keyMenu) {
        return cExpected.cast(this.mapMenu.get(keyMenu));
    }

    /*Cast the */
    @SuppressWarnings("unchecked")
    private <M extends Menu> M getMenu(final int keyMenu) {
        return (M) this.mapMenu.get(keyMenu);
    }
}
