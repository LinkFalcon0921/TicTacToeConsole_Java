package com.flintore_0921.menus;

import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.managers.PlayerManager;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class PlayerMenu extends Menu {

    private interface MenuKey {
        int KEY_ADD_PLAYER = 1;
        int KEY_EDIT_PLAYER = 2;
        int KEY_REMOVE_PLAYER = 3;
        int KEY_EDIT_NAME_PLAYER = 1;
        int KEY_EDIT_ICON_PLAYER = 2;

        String OPTION_EDIT_PLAYER = "Editar jugador";
        String OPTION_DELETE_PLAYER = "Eliminar jugador";
        String OPTION_ADD_PLAYER = "Agregar jugador";
    }


    private final PlayerManager playerManager;

    public PlayerMenu() {
        super();
        this.playerManager = new PlayerManager();
    }

    public PlayerMenu(PrintStream printer, Scanner scannerResponse) {
        super(printer, scannerResponse);
        this.playerManager = new PlayerManager();
    }

    public void printMenu() {
        int option = -1;

        do {
            try {
                printLineSeparator();
                println("Seleccione un usuario:");
                println(setOptionMenu(MenuKey.KEY_ADD_PLAYER, MenuKey.OPTION_ADD_PLAYER));
                println(setOptionMenu(MenuKey.KEY_EDIT_PLAYER, MenuKey.OPTION_EDIT_PLAYER));
                println(setOptionMenu(MenuKey.KEY_REMOVE_PLAYER, MenuKey.OPTION_DELETE_PLAYER));
                println(setOptionMenu(EXIT_VALUE, OPTION_EXIT));

                option = getOptionSelected();

                final int actionSelected = option;

                switch (actionSelected) {
                    case MenuKey.KEY_ADD_PLAYER -> addUserMenu();
                    case MenuKey.KEY_EDIT_PLAYER -> editUserMenu();
                    case MenuKey.KEY_REMOVE_PLAYER -> removeUserMenu();
                    case EXIT_VALUE -> {/*Do nothing*/}
                    default -> throw new Exception();
                }
            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }
        } while (!isExit(option));

        fullLineSeparatorSpace();
    }

    /**
     * Return true if are player equals or higher than the given amount
     */
    public boolean therePlayersToPlay(final int amount) {
        return this.playerManager.getTotalPlayers() >= amount;
    }

    //    Add user case menu
    private void addUserMenu() {

        boolean flagAddNew = false;
        String playerName = null;
        int option = -1;
        PlayerIcon playerIcon = null;

        List<PlayerIcon> availableIcons = this.playerManager.getAvailableIcons();

        if (!whereIcons(availableIcons)) {
            return;
        }

        do {
            try {
                printLineSeparator();
                playerName = getUserName(String.format("Ingrese el nombre del jugador. %d para salir.: ", EXIT_VALUE));

                /*label Continue asking if username not apply the rules.*/
                if (!isValidUserName(playerName)) {
                    continue;
                }

                /*label: exit if type 0*/
                if (isExit(option)) {
                    return;
                }

                /*label: Check if contains the typed username*/
                if (this.playerManager.containsPlayer(playerName)) {
                    printMessageWithSeparators(USERNAME_ALREADY_USED_MESSAGE);
                    continue;
                }

                do {
                    try {
                        printLineSeparator();
                        println("Seleccione un icono: ");
                        printAsList(availableIcons);
                        println(setOptionMenu(EXIT_VALUE, OPTION_EXIT));

                        //                label: Get the option from user
                        option = getOptionSelected();

                        /*label: Exit if the user type 0.*/
                        if (isExit(option)) {
                            return;
                        }

                        flagAddNew = validate(option, availableIcons.size());

                        if (!flagAddNew) {
                            throw new Exception();
                        }

                        // label Get the selected icon
                        playerIcon = availableIcons.get(option - 1);

                    } catch (Exception e) {
                        showDefaultMessageInvalidOption();
                    }

                    // label: end icon while if choose a icon
                } while (!flagAddNew);

            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }

            // label: end name while if choose a icon
        } while (!flagAddNew);

        //label: Add the player
        if (this.playerManager.addPlayer(playerName, playerIcon)) {
            printMessageWithSeparators(PLAYER_ADDED_SUCCESSFULLY_MESSAGE);
        } else {
            printMessageWithSeparators(PLAYER_NOT_ADDED_MESSAGE);
        }

        fullLineSeparatorSpace();
    }
    /*Edit menu method*/

    private void editUserMenu() {
        String userName = null;
        int option = -1;

        List<String> userNames = this.playerManager.getUserNames();

        if (!whereUsers(userNames)) {
            return;
        }

        do {
            try {
                println("Elija un jugador a editar:");
                printAsList(userNames);
                println(setOptionMenu(EXIT_VALUE, OPTION_EXIT));

                option = getOptionSelected();

                /*label: check if user cancel the action*/
                if (isExit(option)) {
                    return;
                }

                userName = userNames.get(option - 1);

                /*label: Max options for edit choose*/
                final int maxOptionsEdit = 2;
                do {
                    try {
                        println(String.format("Jugador %s:", userName));
                        printLineSeparator();

                        println("Que desea editar: ");
                        println(setOptionMenu(MenuKey.KEY_EDIT_NAME_PLAYER, "Nombre."));
                        println(setOptionMenu(MenuKey.KEY_EDIT_ICON_PLAYER, "Icono"));
                        println(setOptionMenu(EXIT_VALUE, OPTION_EXIT));

                        option = getOptionSelected();

                        switch (option) {
                            case MenuKey.KEY_ADD_PLAYER -> editNameMenu(userName);
                            case MenuKey.KEY_EDIT_PLAYER -> editIconMenu(userName);
                            case EXIT_VALUE -> {/*Do nothing*/}
                            default -> throw new Exception();
                        }
                    } catch (Exception e) {
                        showDefaultMessageInvalidOption();
                    }
                    // label end options edit loop
                } while (!validate(option, maxOptionsEdit) && !isExit(option));

            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }
            // label end start loop
        } while (!validate(option, userNames.size()) && !isExit(option));

        fullLineSeparatorSpace();
    }
    /*Edit action method*/

    private void editNameMenu(String name) {
        printLineSeparator();
        printLineSeparator();
        boolean flagNewName = false;

        String newName = null;

        do {
            try {
                newName = getUserName(String.format("Ingrese el nombre del jugador. %d para salir.: ", EXIT_VALUE));

                if (Objects.isNull(newName)) {
                    continue;
                }

                if (Objects.equals(name, newName)) {
                    println(PLAYER_SAME_NAME_MESSAGE);
                    fullLineSeparatorSpace();
                    continue;
                }

                /*label set the name as valid*/
                flagNewName = true;
            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }

        } while (!flagNewName);

        if (Objects.nonNull(newName) && this.playerManager.editUser(name, newName)) {
            printMessageWithSeparators("Nombre editado exitosamente: ".concat(newName));
        } else {
            printMessageWithSeparators("No se pudo editar el nombre");
        }

        fullLineSeparatorSpace();
    }
    /*Edit action method*/

    private void editIconMenu(String name) {
        fullLineSeparatorSpace();

        boolean flagEdit = false;
        PlayerIcon playerIcon = null;
        int iconSelected = -1;

        List<PlayerIcon> availableIcons = this.playerManager.getAvailableIcons();

        if (availableIcons.isEmpty()) {
            println(ICONS_NOT_AVAILABLE_MESSAGE);
            return;
        }

        do {
            try {
                print("Seleccione el nuevo icono: ");
                iconSelected = getOptionSelected();

                flagEdit = validate(iconSelected, availableIcons.size());

//                label: If the option is not valid.
                if (!flagEdit) {
                    throw new Exception();
                }

                playerIcon = availableIcons.get(iconSelected - 1);
            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }

        } while (!flagEdit);

        if (Objects.nonNull(playerIcon) && this.playerManager.editUser(name, playerIcon)) {
            printMessageWithSeparators(String.format("Icono editado exitosamente: %s : %s", name, playerIcon));
        } else {
            printMessageWithSeparators(NOT_ICON_EDITED);
        }

        fullLineSeparatorSpace();
    }

    /*Remove menu method*/
    private void removeUserMenu() {

        String userName = null;
        int nameSelected = -1;

        final List<String> userNames = this.playerManager.getUserNames();

        if (!whereUsers(userNames)) {
            return;
        }

        do {
            try {
                print("Seleccione el jugador a eliminar: ");
                printAsList(userNames);

                nameSelected = getOptionSelected();

                if (isExit(nameSelected)) {
                    return;
                }

                userName = userNames.get(nameSelected - 1);

            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }
        } while (!validate(nameSelected, userNames.size()));

        if (this.playerManager.removePlayer(userName)) {
            printMessageWithSeparators("Jugador eliminado exitosamente.");
        } else {
            printMessageWithSeparators("No se pudo eliminar el jugador.");
        }

        fullLineSeparatorSpace();
    }


    private boolean isValidUserName(final String playerName) {
        String trimmedPlayerName = playerName.trim();

        if (trimmedPlayerName.isBlank() || trimmedPlayerName.length() <= MIN_USERNAME_LENGTH) {
            printMessageWithSeparators(PLAYER_NAME_LENGTH_RULE_MESSAGE);
            return false;
        }

        return true;
    }

    private String getUserName(final String message) {
        String newName;
        boolean flagName = false;
        do {
            fullLineSeparatorSpace();
            print(message);
            newName = this.INPUT_RESPONSE.next();

            if (newName.isBlank()) {
                printMessageWithSeparators(INVALID_USERNAME_MESSAGE);
                continue;
            }

            flagName = true;
        } while (!flagName);

        return newName;
    }

    private String getUserName() {
        String newName = null;
        boolean flagName = false;
        do {
            try {
                newName = this.INPUT_RESPONSE.next();

                if (newName.isBlank()) {
                    printMessageWithSeparators(INVALID_USERNAME_MESSAGE);
                    continue;
                }

                flagName = true;
            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }
        } while (!flagName);

        return newName;
    }

    /*True is icon list is not empty*/

    private boolean whereIcons(List<PlayerIcon> availableIcons) {
        if (availableIcons.isEmpty()) {
            printMessageWithSeparators(MAX_PLAYERS_REACHED_MESSAGE);
            return false;
        }
        return true;
    }
    /*True is user list is not empty*/

    private boolean whereUsers(List<String> userNames) {
        if (userNames.isEmpty()) {
            printMessageWithSeparators(NO_PLAYERS_REGISTERED_MESSAGE);
            return false;
        }
        return true;
    }


}
