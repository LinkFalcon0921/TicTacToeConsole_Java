package com.flintore_0921.componentes;

import com.flintore_0921.managers.TablePosition;

public interface Constants {

    interface AppManager{
        int TABLE_LENGTH = 9;
        int TABLE_ROWS = 3;
        int TABLE_COLUMNS = 3;

        String SEPARATOR = "|";

        /*Lines separator*/
        String ROWS_SEPARATOR = "-+-+-";
    }

    /** Enum of table position. */
    enum ETablePosition{
        X,Y
    }

}
