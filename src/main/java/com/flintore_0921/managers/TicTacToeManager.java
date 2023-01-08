package com.flintore_0921.managers;

import com.flintore_0921.componentes.Constants;
import com.flintore_0921.componentes.Constants.TicTacGame;
import com.flintore_0921.componentes.Player;
import com.flintore_0921.componentes.PlayerIcon;
import com.flintore_0921.componentes.results.GameResult;
import com.flintore_0921.utils.TableChecker;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import static com.flintore_0921.componentes.Constants.Table.TABLE_COLUMNS;
import static com.flintore_0921.componentes.Constants.Table.TABLE_ROWS;

public class TicTacToeManager {

    private static TicTacToeManager manager;

    private final PlayerIcon[][] table;

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
        this.turns = new TurnManager(playerManager.getTotalPlayers());
        this.table = new PlayerIcon[TABLE_ROWS][TABLE_COLUMNS];
        this.tableCheckerManager = TableChecker.getInstance();
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

    public PlayerIcon[][] getTable() {
        final int START_POS = 0;
        PlayerIcon[][] copyTable = new PlayerIcon[TABLE_ROWS][TABLE_COLUMNS];

        System.arraycopy(this.table, START_POS, copyTable, 0, this.table.length);

        return copyTable;
    }

    // TODO: 1/2/2023  
    public GameResult hasWon() {
        GameResult result = null;

        Iterator<Player> playerIconIterator = this.playerManager.getPLayers().iterator();
        int cursor = 0;

        while (playerIconIterator.hasNext()) {
            Player player = playerIconIterator.next();

            if (checkWon(player.getPlayerIcon())) {
                result = new GameResult(player.getPlayerName(), GameResult.Result.WIN);
                break;
            }

            if (isDraw()) {
                result = GameResult.DRAW_RESULT;
                break;
            }
        }

        return result;
    }

    public boolean isDraw() {
        return Arrays.stream(this.table).noneMatch(Objects::isNull);
    }

    private boolean checkWon(PlayerIcon playerIcon) {
        return this.tableCheckerManager.checkIsMatchInARow(this.table, playerIcon);
    }
}
