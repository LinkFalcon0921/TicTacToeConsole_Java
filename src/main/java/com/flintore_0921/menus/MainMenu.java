package com.flintore_0921.menus;

import com.flintore_0921.managers.PlayerManager;

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
        this.setMenus(this.PRINTER, this.INPUT_RESPONSE);
    }

    public MainMenu(PrintStream printer, Scanner scannerResponse) {
        super(printer, scannerResponse);
        this.mapMenu = new HashMap<>();

        setMenus(printer, scannerResponse);
    }

    private void setMenus(PrintStream printer, Scanner scannerResponse) {
        PlayerManager playerManager = PlayerManager.getInstance();
        this.mapMenu.put(MenuKey.KEY_GAME_MENU, new GameMenu(printer, scannerResponse, playerManager));

        this.mapMenu.put(MenuKey.KEY_PLAYER_MENU, new PlayerMenu(printer, scannerResponse));
    }

    @Override
    public void printMenu() {
        int option;

        do {
            try {
                println("Elija una opcion:");
                println(setOptionMenu(MenuKey.KEY_GAME_MENU, MenuKey.OPTION_START_GAME));
                println(setOptionMenu(MenuKey.KEY_PLAYER_MENU, MenuKey.OPTION_PLAYER_MENU));
                println(setOptionMenu(EXIT_VALUE, OPTION_EXIT));

                option = this.INPUT_RESPONSE.nextInt();

                if (isExit(option)) {
                    return;
                }

                if (!validate(option, this.mapMenu.size())) {
                    callInvalidOptionException();
                }

                callMenu(option);

            } catch (Exception ex) {
                showDefaultMessageInvalidOption();
            }
        } while (true);
    }

    // TODO: 1/1/2023 Complete logic 
    private void startGame() {
        this.mapMenu.get(MenuKey.KEY_GAME_MENU).printMenu();
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
