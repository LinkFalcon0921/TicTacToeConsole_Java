package com.flintore_0921.componentes;

public interface Constants {

    interface AppManager {
        String SEPARATOR = "|";

        /*Lines separator*/
        String ROWS_SEPARATOR = "-+-+-";
    }

    interface Table {
        int TABLE_LENGTH = 9;
        int TABLE_ROWS = 3;
        int TABLE_COLUMNS = 3;
    }

    interface Menu {
        int EXIT_VALUE = 0;
        int FAIL_START_VALUE = -1;

        String LINE_SEPARATOR = "-";

        int COUNT_SEPARATOR_LENGTH = 12;

        //        messages
        String DEFAULT_INVALID_OPTION_MESSAGE = "Seleccione una opcion valida";
    }

    /**
     * Enum of table position.
     */
    @Deprecated
    enum ETablePosition {
        X, Y
    }
}
