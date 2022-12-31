package com.flintore_0921.managers;

import com.flintore_0921.componentes.PlayerIcon;

import java.io.PrintStream;

import static com.flintore_0921.componentes.Constants.AppManager.ROWS_SEPARATOR;
import static com.flintore_0921.componentes.Constants.AppManager.SEPARATOR;
import static com.flintore_0921.componentes.Constants.Table.TABLE_COLUMNS;
import static com.flintore_0921.componentes.Constants.Table.TABLE_ROWS;

public class TicTacToeManager {
//    Printer of the app
    private final PrintStream PRINTER;
    //    Table of the app.
    private final PlayerIcon[][] table;

    public TicTacToeManager(PrintStream printer) {
        this.PRINTER = printer;
        this.table = new PlayerIcon[TABLE_ROWS][TABLE_COLUMNS];
    }

    public void addNextMove(int xPos, int yPos, PlayerIcon playerMove) {
        if (!isSpaceEmpty(xPos, yPos)) {
            return;
        }

        this.table[xPos][yPos] = playerMove;
    }

    public boolean isSpaceEmpty(int xPos, int yPos) {
        return this.table[xPos][yPos] == null;
    }

    public void printTable() {
        for (int xPos = 0; xPos < TABLE_ROWS; xPos++) {
            PlayerIcon[] columns = this.table[xPos];
            printRowWithSeparator(columns);
        }
    }

    private void printRow(PlayerIcon[] columns) {
        for (PlayerIcon field : columns) {
            Object fieldValue = field != null ? field : " ";
            PRINTER.print(fieldValue);
//            Add separator
            PRINTER.print(SEPARATOR);
        }
    }

    private void printRowWithSeparator(PlayerIcon[] rows) {
        printRow(rows);
        printRowSeparator(PRINTER);
    }

    private void printRowSeparator(PrintStream printer) {
        printer.println(ROWS_SEPARATOR);
    }
}
