package com.flintore_0921.managers;

import com.flintore_0921.componentes.Constants;
import com.flintore_0921.componentes.Player;
import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.componentes.results.GameResult;
import com.flintore_0921.menus.GameMenu;
import com.flintore_0921.utils.TableChecker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class TicTacToeManager {

    private static TicTacToeManager manager;

    private PlayerIcon[][] table;

    private final PlayerManager playerManager;

    private final TableChecker tableCheckerManager;

    private TurnManager turns;

    public static void initiate(PlayerManager playerManager) {
        if (Objects.nonNull(manager)) {
            return;
        }

        manager = new TicTacToeManager(playerManager);
    }

    /**
     * <b>Label:</b> Use initiate methods before use it.
     */
    public static TicTacToeManager getInstance() {
        return manager;
    }

    private TicTacToeManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.tableCheckerManager = TableChecker.getInstance();
    }

    public boolean addNextMove(int xPos, int yPos, final PlayerIcon playerMove) {
        boolean added = false;

        if (isSpaceEmpty(xPos, yPos)) {
            this.table[xPos][yPos] = playerMove;
            this.turns.nextTurn();
            added = true;
        }

        return added;
    }

    public boolean isSpaceEmpty(int xPos, int yPos) {
        return this.table[xPos][yPos] == null;
    }

    public PlayerIcon[][] getTable() {
        return this.table.clone();
    }

    // TODO: 1/2/2023  
    public GameResult hasWon() {

        for (Player player : this.playerManager.getPlayers()) {
            if (checkWon(player.getPlayerIcon())) {
                return new GameResult(player.getPlayerName(), GameResult.StateResult.WIN);
            }
        }

        if (isDraw()) {
            return GameResult.DRAW_RESULT;
        }

        return GameResult.STILL_PLAYING_RESULT;
    }

    public boolean isDraw() {
        return Arrays.stream(this.table)
                .flatMap(Stream::of)
                .noneMatch(Objects::isNull);
    }


//    TURN methods

    /**
     * Requires GameMenu instance. Return null if is an Anonymous class
     */
    public Player getActualPlayer(GameMenu menu) {
        if (menu.getClass().isAnonymousClass()) {
            return null;
        }

        final int playerTurn = this.turns.getActualPlayerTurn();
        int counter = 0;

        Collection<Player> players = this.playerManager.getPlayers();

        for (Player player : players) {

            if (counter++ == playerTurn) {
                return player;
            }
        }

        return null;
    }

    public boolean hasEnoughPlayer() {
        int playersCount = playerManager.getTotalPlayers();
        return playersCount == Constants.TicTacGame.PLAYERS_EXPECTED;
    }

    public void resetTable() {

        this.table = Arrays.stream(Constants.Table.EMPTY_TABLE)
                .map(PlayerIcon[]::clone)
                .toArray(PlayerIcon[][]::new);

        /*Set the turns based the list*/
        this.turns = new TurnManager(playerManager.getTotalPlayers());
    }

    private boolean checkWon(PlayerIcon playerIcon) {
        return this.tableCheckerManager.checkIsMatchInARow(this.table, playerIcon);
    }
}
