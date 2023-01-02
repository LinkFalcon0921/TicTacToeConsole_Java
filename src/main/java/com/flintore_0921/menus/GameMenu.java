package com.flintore_0921.menus;

import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.managers.TicTacToeManager;

import java.io.PrintStream;
import java.util.*;

import static com.flintore_0921.componentes.Constants.*;

public class GameMenu extends Menu {

    private interface MenuKey {
    }

    private final TicTacToeManager gameManager;

    public GameMenu(Collection<String> players) {
        super();
        TicTacToeManager.initiate(players);
        this.gameManager = TicTacToeManager.getInstance();
    }

    public GameMenu(PrintStream PRINTER, Scanner responseReceiver, Collection<String> players) {
        super(PRINTER, responseReceiver);
        TicTacToeManager.initiate(players);
        this.gameManager = TicTacToeManager.getInstance();
    }

    @Override
    public void printMenu() {
        printMessageWithSeparators("Iniciando juego...");
    }

    private void printTable() {
        PlayerIcon[][] table = this.gameManager.getTable();

        for (PlayerIcon[] columns : table) {
            printRowWithSeparator(columns);
        }
    }

    private void printRow(PlayerIcon[] columns) {
        Iterator<PlayerIcon> iterator = Arrays.asList(columns).iterator();

        while (iterator.hasNext()) {
            PlayerIcon move = iterator.next();
            Object fieldValue = Objects.nonNull(move) ? move : Table.EMPTY_MOVE;

            print(fieldValue);

//            Add separator
            if (iterator.hasNext()) {
                print(Separators.ROWS_SEPARATOR);
            }
        }
    }

    private void printRowWithSeparator(PlayerIcon[] rows) {
        printRow(rows);
        printRowSeparator();
    }


    private void printRowSeparator() {
        this.PRINTER.println(ROWS_SEPARATOR);
    }
}
