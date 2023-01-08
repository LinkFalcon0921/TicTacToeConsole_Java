package com.flintore_0921.componentes;

public interface Constants {

    interface Separators {
        /*Lines separator*/
        String SEPARATOR = "|";
        String ROWS_SEPARATOR = "-+-+-";
    }

    interface Table {
        int TABLE_LENGTH = 9;
        int TABLE_ROWS = 3;
        int TABLE_COLUMNS = 3;
        String EMPTY_MOVE = " ";
        PlayerIcon[][] EMPTY_TABLE = new PlayerIcon[TABLE_ROWS][TABLE_COLUMNS];
    }

    interface Menu extends Separators {
        int EXIT_VALUE = 0;
        int FAIL_START_VALUE = -1;

        String LINE_SEPARATOR = "-";

        int COUNT_SEPARATOR_LENGTH = 20;

        int MIN_USERNAME_LENGTH = 3;

        // OPTIONS
        String OPTION_EXIT = "Salir";


        // MESSAGES
        String DEFAULT_INVALID_OPTION_MESSAGE = "Seleccione una opcion valida.";
        String NO_PLAYERS_REGISTERED_MESSAGE = "No hay jugadores registrados.";
        String MAX_PLAYERS_REACHED_MESSAGE = "No se pueden agregar mas jugadores.";

        String PLAYER_NAME_LENGTH_RULE_MESSAGE = "Nombre del jugador debe contener al menos 3 digitos.";

        String INVALID_USERNAME_MESSAGE = "Digite un nombre valido.";

        String USERNAME_ALREADY_USED_MESSAGE = "Este nombre ya existe. Utilice otro.";

        String PLAYER_ADDED_SUCCESSFULLY_MESSAGE = "Jugador agragado satisfactiamente.";
        String PLAYER_NOT_ADDED_MESSAGE = "No fue posible agragar al jugador.";
        String PLAYER_SAME_NAME_MESSAGE = "Ya cuenta con este nombre.";
        String ICONS_NOT_AVAILABLE_MESSAGE = "No hay iconos disponibles.";
        String NOT_ICON_EDITED = "No se pudo editar el icono.";
        String NOT_NAME_EDITED = "No se pudo editar el nombre.";
    }

    interface TicTacGame {
        int PLAYERS_EXPECTED = 2;
    }
}
