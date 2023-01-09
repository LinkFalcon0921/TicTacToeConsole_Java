package com.flintore_0921.menus;

import com.flintore_0921.componentes.Player;
import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.componentes.results.GameResult;
import com.flintore_0921.managers.PlayerManager;
import com.flintore_0921.managers.TicTacToeManager;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Stream;

import static com.flintore_0921.componentes.Constants.*;

public class GameMenu extends Menu {

    private interface MenuKey {
    }

    private final TicTacToeManager gameManager;

    public GameMenu(PlayerManager players) {
        super();
        TicTacToeManager.initiate(players);
        this.gameManager = TicTacToeManager.getInstance();
    }

    public GameMenu(PrintStream PRINTER, Scanner responseReceiver, PlayerManager players) {
        super(PRINTER, responseReceiver);
        TicTacToeManager.initiate(players);
        this.gameManager = TicTacToeManager.getInstance();
    }

    @Override
    public void printMenu() {
        if (!this.gameManager.hasEnoughPlayer()) {
            /*Validate if there are players*/
            printMessageWithSeparators("Agregue jugadores para iniciar a jugar.");
            return;
        }

        this.gameManager.resetTable();

        printMessageWithSeparators("Iniciando juego...");

        GameResult result = GameResult.STILL_PLAYING_RESULT;

        while (result.equals(GameResult.STILL_PLAYING_RESULT)) {
            try {
                printTable();

                final Player actualPlayer = this.gameManager.getActualPlayer(this);
                println(String.format("Turno del jugador %s: ", actualPlayer.getPlayerName()));

                final int option = this.INPUT_RESPONSE.nextInt();

                if (isExit(option)) {
                    return;
                }

                if (!isValidInputTable(option)) {
                    callInvalidOptionException();
                }

                final int indexTableSelected = option - 1;
                int xPos = indexTableSelected / Table.TABLE_ROWS;
                int yPos = indexTableSelected % Table.TABLE_ROWS;

                if (!this.gameManager.addNextMove(xPos, yPos, actualPlayer.getPlayerIcon())) {
                    throw new Exception();
                }

                result = this.gameManager.hasWon();
            } catch (Exception e) {
                showDefaultMessageInvalidOption();
            }
        }

//        Print table
        printLineSeparator();
        printTable();

//        print result
        printMessageWithSeparators(Separators.EMPTY_SEPARATOR, result.printResult());
    }

    //    Between 1 and table length
    private boolean isValidInputTable(final int option) {
        return option > 0 && option <= Table.TABLE_LENGTH;
    }

    private void printTable() {
        PlayerIcon[][] table = this.gameManager.getTable();

        Iterator<PlayerIcon[]> rowsIter = Stream.of(table).iterator();

        while(rowsIter.hasNext()){
            PlayerIcon[] rowTable = rowsIter.next();
            if(rowsIter.hasNext()){
                printRowWithSeparator(rowTable);
                continue;
            }

            printRow(rowTable);
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
                print(Separators.SEPARATOR);
            }
        }
        println(Separators.EMPTY_SEPARATOR);
    }

    private void printRowWithSeparator(PlayerIcon[] rows) {
        printRow(rows);
        printRowSeparator();
    }


    private void printRowSeparator() {
        this.PRINTER.println(ROWS_SEPARATOR);
    }
}
