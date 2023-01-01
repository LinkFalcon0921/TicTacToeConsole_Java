package com.flintore_0921.menues;

import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.managers.PlayerManager;

import java.io.PrintStream;
import java.util.*;

import static com.flintore_0921.componentes.Constants.Menu.DEFAULT_INVALID_OPTION_MESSAGE;
import static com.flintore_0921.componentes.Constants.Menu.EXIT_VALUE;

public class PlayerMenu extends Menu {

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
        final int maxOptions = 0;
        int option;

        do {
            println("Seleccione un usuario:");
            println("1. Agregar jugador.");
            println("2. Editar jugador.");
            println("3. Eliminar jugador.");
            println("0. Salir");

            switch (option = this.INPUT_RESPONSE.nextInt()) {
                case 1 -> addUserMenu();
                case 2 -> editUserMenu();
                case 3 -> removeUserMenu();
                case 0 -> {/*Exit case*/}
                default -> println(DEFAULT_INVALID_OPTION_MESSAGE);
            }
        } while (validate(option, maxOptions));
    }

    //    Add user case menu
    private void addUserMenu() {
        String playerName;
        int option;
        PlayerIcon playerIcon = null;

        List<PlayerIcon> availableIcons = this.playerManager.getAvailableIcons();

        if (whereIcons(availableIcons)) {
            return;
        }

        boolean flagNameValidation;
        flagNameValidation = false;

        do {
            boolean flagIconValidation = false;

            print("Ingrese el nombre del jugador: ");
            printLineSeparator();

            playerName = this.INPUT_RESPONSE.next();

            /*label Exit if the user type 0 or empty.*/
            if (!isValidUserName(playerName)) {
                return;
            }

            if (this.playerManager.containsPlayer(playerName)) {
                println("Este nombre ya existe. Utilice otro");
                printLineSeparator();
                continue;
            }

            do {
                println("Seleccione un icono: ");
                printListCollection(availableIcons);
                println("0. Salir");
                printLineSeparator();

//                label: Get the option from user
                option = this.INPUT_RESPONSE.nextInt();

                /*label: Exit if the user type 0 or empty.*/
                if (exit(option)) {
                    return;
                }

                if (!validate(option, availableIcons.size())) {
                    println(DEFAULT_INVALID_OPTION_MESSAGE);
                    continue;
                }

//                label Get the selected icon
                playerIcon = availableIcons.get(option - 1);

                flagIconValidation = true;

//                label: end icon while if choose a icon
            } while (flagIconValidation);

            flagNameValidation = true;

//                label: end name while if choose a icon
        } while (flagNameValidation);

        //label: Add the player
        this.playerManager.addPlayer(playerName, playerIcon);
    }

    private void editUserMenu() {
        String userName;
        int option;

        List<String> userNames = new ArrayList<>(this.playerManager.getUserNames());

        if (whereUsers(userNames)) {
            return;
        }

        do {
            println("Elija el nombre del usuario a editar:");
            printListCollection(userNames);
            println("0. Salir.");

            option = this.INPUT_RESPONSE.nextInt();

            /*label: check if user cancel the action*/
            if (exit(option)) {
                return;
            }

            userName = userNames.get(option - 1);

            /*Max options for */
            final int maxOptionsEdit = 2;
            do {
                println(String.format("Usuario %s:", userName));
                println("Que desea editar: ");
                println("1. Nombre.");
                println("2. Icono.");
                println("0. Salir.");

                option = this.INPUT_RESPONSE.nextInt();

                switch (option) {
                    case 1 -> editNameMenu(userName);
                    case 2 -> editIconMenu(userName);
                    case 0 -> {/*Do nothing*/}
                }

            } while (validate(option, maxOptionsEdit));

        } while (option != EXIT_VALUE);

    }

    private boolean whereUsers(List<String> userNames) {
        if (userNames.isEmpty()) {
            println("No existen usuarios");
            return true;
        }
        return false;
    }

    //    Inner menu to edit name

    private void editNameMenu(String name) {
        printLineSeparator();
        printLineSeparator();
        boolean flagNewName = false;

        String newName;

        do {
            newName = getUserName("Ingrese el nuevo nombre. 0 para salir: ");

            if (Objects.isNull(newName)) {
                continue;
            }

            if (Objects.equals(name, newName)) {
                println("Ya cuenta con este nombre.");
                fullLineSeparatorSpace();

                continue;
            }

            /*label set the name as valid*/
            flagNewName = true;

        } while (flagNewName);

        this.playerManager.editUser(name, newName);
    }

    private String getUserName() {
        String newName;
        boolean flagName = false;
        do {
            newName = this.INPUT_RESPONSE.next();

            if (newName.isBlank()) {
                println("Digite un nombre valido.");
                fullLineSeparatorSpace();

                continue;
            }

            flagName = true;
        } while (flagName);

        return newName;
    }

    private String getUserName(String message) {
        String newName;
        boolean flagName = false;
        do {
            print(message);
            newName = this.INPUT_RESPONSE.next();

            if (newName.isBlank()) {
                println("Digite un nombre valido.");
                fullLineSeparatorSpace();

                continue;
            }

            flagName = true;
        } while (flagName);

        return newName;
    }

    private void editIconMenu(String name) {
        fullLineSeparatorSpace();

        boolean flagIcon = false;
        PlayerIcon playerIcon = null;
        int iconSelected = -1;

        List<PlayerIcon> availableIcons = this.playerManager.getAvailableIcons();

        if (availableIcons.isEmpty()) {
            println("No hay iconos disponibles.");
            return;
        }

        do {
            print("Seleccione el nuevo icono: ");
            iconSelected = this.INPUT_RESPONSE.nextInt();

            if (!validate(iconSelected, availableIcons.size())) {
                println("Ingrese un opcion valida.");
                continue;
            }

            playerIcon = availableIcons.get(iconSelected - 1);

            /*label set the icon as valid*/
            flagIcon = true;

        } while (flagIcon);

        this.playerManager.editUser(name, playerIcon);

    }

    private void removeUserMenu() {
        int optionName;
        String userName = null;
        boolean flagIcon = false;

        List<String> userNames = new ArrayList<>(this.playerManager.getUserNames());

        if (whereUsers(userNames)) {
            return;
        }

        do {
            println("Ingrese el usuario a eliminar: ");
            printListCollection(userNames);


            optionName = this.INPUT_RESPONSE.nextInt();

            if (exit(optionName)) {

            }

            if (!validate(optionName, userNames.size())) {
                continue;
            }

            userName = userNames.get(optionName - 1);

            flagIcon = true;

        } while (flagIcon);

        this.playerManager.removePlayer(userName);
    }

    private boolean exit(int optionName) {
        return optionName == EXIT_VALUE;
    }

    private boolean isValidUserName(String playerName) {
        playerName = playerName.trim();

        return !playerName.isBlank() &&
                playerName.length() > 0 && !playerName.startsWith(String.valueOf(EXIT_VALUE));
    }

    private boolean whereIcons(List<PlayerIcon> availableIcons) {
        if (availableIcons.isEmpty()) {
            printLineSeparator();
            println("No se puede agregar mas usuarios.");
            printLineSeparator("");
            printLineSeparator("");
            return true;
        }
        return false;
    }

}
