package com.flintore_0921.menues;

import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.managers.PlayerManager;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.flintore_0921.componentes.Constants.Menu.*;

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
                default -> println(DEFAULT_INVALID_OPTION_MESSAGE);
            }
        } while (validate(option, maxOptions));
    }

    private void addUserMenu() {
        String playerName;
        int option;
        PlayerIcon playerIcon = null;

        boolean flagNameValidation;
        flagNameValidation = false;

        do {
            boolean flagIconValidation = false;

            print("Ingrese el nombre del jugador: ");
            playerName = this.INPUT_RESPONSE.next();

            if (Objects.isNull(playerName) || this.playerManager.containsPlayer(playerName)) {
                println("Este nombre ya existe. Utilice otro");
                printLineSeparator();
                continue;
            }

            do {
                println("Seleccione un icono: ");
                List<PlayerIcon> avalilableIcons = this.playerManager.getAvailableIcons();

                for (int indx = 0; indx < avalilableIcons.size(); indx++) {
                    println(String.format("%d. %s", indx + 1, avalilableIcons.get(indx)));
                }
                option = this.INPUT_RESPONSE.nextInt();

                if (!validate(option, avalilableIcons.size())) {
                    println(DEFAULT_INVALID_OPTION_MESSAGE);
                    continue;
                }

//                label Get the selected icon
                playerIcon = avalilableIcons.get(option - 1);

                flagIconValidation = true;

            } while (flagIconValidation);

            flagNameValidation = true;

        } while (flagNameValidation);

        this.playerManager.addPlayer(playerName, playerIcon);
    }

    private void editUserMenu() {

    }

    private void removeUserMenu() {

    }

}
