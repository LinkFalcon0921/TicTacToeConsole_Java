package com.flintore_0921.managers;

import com.flintore_0921.componentes.Constants;
import com.flintore_0921.componentes.PlayerIcons;

import java.io.PrintStream;

import static com.flintore_0921.componentes.Constants.AppManager.*;

public class AppManager {


    //    Table of the app.
    private final PlayerIcons[][] table;

    private TablePosition position;

    public AppManager() {
        this.table = new PlayerIcons[TABLE_ROWS][TABLE_COLUMNS];
    }

    public void addNextMove(PlayerIcons playerMove) {
        final int row = this.position.get(Constants.ETablePosition.X);
        final int column = this.position.get(Constants.ETablePosition.Y);

        this.table[row][column] = playerMove;
    }

    public void printTable(PrintStream printer) {
        for (int xPos = 0; xPos < TABLE_ROWS; xPos++) {
            PlayerIcons[] rows = this.table[xPos];
            printRowWithSeparator(printer, rows);
        }
    }

    private void printRow(PrintStream printer, PlayerIcons[] rows) {
        for (PlayerIcons field : rows) {
            printer.print(field);
//            Add separator
            printer.print(SEPARATOR);
        }
    }

    private void printRowWithSeparator(PrintStream printer, PlayerIcons[] rows) {
        printRow(printer, rows);
        printRowSeparator(printer);
    }

    private void printRowSeparator(PrintStream printer) {
        printer.println(ROWS_SEPARATOR);
    }
}
