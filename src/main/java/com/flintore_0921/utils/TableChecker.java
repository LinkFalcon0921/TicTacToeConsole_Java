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

    public boolean checkIsMatchInARow(final PlayerIcon[][] table, final PlayerIcon playerIcon){
        return this.matchHorizontal(table, playerIcon) ||
                this.matchVertically(table, playerIcon) ||
                this.matchDiagonally(table, playerIcon);
    }

    public boolean matchHorizontal(PlayerIcon[][] table, PlayerIcon playerIcon) {
        boolean match = true;

        for (PlayerIcon[] iconRow : table) {
            match = true;

            for (PlayerIcon icon : iconRow) {
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

                /*End if false in a case*/
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

    /**Check if the table has the player icon in a row based in the principal and last possible diagonal. <br/>Return true
     * if last expression matches otherwise false. */
    public boolean matchDiagonally(final PlayerIcon[][] table, final PlayerIcon playerIcon) {
        boolean matchDiagonal = false;

        for (final PlayerIcon[] iconsRow : table) {
            matchDiagonal = matchDiagonallyLeft(iconsRow, playerIcon) || matchDiagonallyRight(iconsRow, playerIcon);

            /*If it does not match*/
            if (!matchDiagonal) {
                break;
            }

        }
        return matchDiagonal;
    }

    private boolean matchDiagonallyLeft(final PlayerIcon[] iconRow, final PlayerIcon playerIcon) {

        for (int columnLeftRight = 0; columnLeftRight < iconRow.length; columnLeftRight++) {
            int columnRightLeft = iconRow.length - (columnLeftRight + 1);

            if (iconRow[columnLeftRight].equals(playerIcon)) {
                return true;
            }

        }

        return false;
    }

    private boolean matchDiagonallyRight(final PlayerIcon[] iconRow, final PlayerIcon playerIcon) {

        for (int columnRightLeft = iconRow.length - 1; columnRightLeft >= 0; columnRightLeft--) {
            if (iconRow[columnRightLeft].equals(playerIcon)) {
                return true;
            }

        }

        return false;
    }
}
