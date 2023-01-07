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

    public boolean matchHorizontal(PlayerIcon[][] table, PlayerIcon playerIcon) {
        boolean match = true;

        for (int row = 0; row < table.length; row++) {
            PlayerIcon[] iconRow = table[row];
            match = true;

            for (int column = 0; column < iconRow.length; column++) {
                PlayerIcon icon = iconRow[column];

                match = playerIcon.equals(icon);

                if (!match) {
                    break;
                }
            }

//            label: End when some row match
            if (match) {
                break;
            }
        }

        return match;
    }

    public boolean matchVertically(PlayerIcon[][] table, PlayerIcon playerIcon) {
        boolean match = true;

        for (int column = 0; column < table.length; column++) {
            match = true;

            for (int row = 0; row < table[row].length; row++) {
                PlayerIcon icon = table[row][column];

                match = playerIcon.equals(icon);

                if (!match) {
                    break;
                }
            }

//            label: End when some row match
            if (match) {
                break;
            }
        }

        return match;
    }
}
