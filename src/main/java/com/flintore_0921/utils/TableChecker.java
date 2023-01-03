package com.flintore_0921.utils;


import com.flintore_0921.componentes.PlayerIcon;

/**
 * Class to validate if a {@link com.flintore_0921.componentes.PlayerIcon} match in a specific succession.
 */
public class TableChecker {
    private static TableChecker tableChecker;

    public static TableChecker getInstance() {
        if (tableChecker == null) {
            tableChecker = new TableChecker();
        }

        return tableChecker;
    }

    private TableChecker() {
    }

    public boolean matchHorizontal(PlayerIcon[][] table, final int EXPECTED_SUCCESSION) {
        int countSuccession = 0;

        while (countSuccession < EXPECTED_SUCCESSION) {

        }

        return countSuccession == EXPECTED_SUCCESSION;
    }
}
