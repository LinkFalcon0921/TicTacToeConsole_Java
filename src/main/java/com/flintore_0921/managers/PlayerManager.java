package com.flintore_0921.managers;

import com.flintore_0921.componentes.Player;
import com.flintore_0921.componentes.PlayerIcon;

import java.util.*;
import java.util.function.Consumer;

public class PlayerManager {

    private static PlayerManager manager;
    private final Set<Player> players;

    private PlayerManager() {
        this.players = new HashSet<>();
    }

    public static PlayerManager getInstance(){
        if(Objects.isNull(manager)){
            manager = new PlayerManager();
        }

        return manager;
    }

    /**
     * Return true if player not exists.
     */
    public boolean addPlayer(Player player) {
        if (containsPlayer(player)) {
            return false;
        }

        return this.players.add(player);
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream().map(Player::getPlayerName).toList();
    }

    public List<PlayerIcon> getAvailableIcons() {
        List<PlayerIcon> availableIcons = new ArrayList<>(Arrays.asList(PlayerIcon.values()));

        if (getPlayers().isEmpty()) {
            return availableIcons;
        }

        List<PlayerIcon> playerIcons = getPlayers().stream()
                .map(Player::getPlayerIcon)
                .toList();

        availableIcons.removeAll(playerIcons);

        return availableIcons;
    }

    public boolean editPlayer(String playerName, Player player) {
        if (Objects.isNull(playerName) || !containsPlayer(player)) {
            return false;
        }

        Optional<Player> optionalPlayer = Optional.empty();

        try {
            optionalPlayer = getPlayers().stream()
                    .filter(pl -> pl.getPlayerName().equals(playerName))
                    .findFirst();

            optionalPlayer.ifPresent(editPlayerPresent(player));

        } catch (Exception ignored) {
        }

        return optionalPlayer.isPresent();
    }

    private Consumer<Player> editPlayerPresent(Player player) {
        return pl -> {
            String newPlayerName = player.getPlayerName();
            if (Objects.nonNull(newPlayerName)) {
                pl.setPlayerName(newPlayerName);
            }

            PlayerIcon playerIcon = player.getPlayerIcon();
            if (Objects.nonNull(playerIcon)) {
                pl.setPlayerIcon(playerIcon);
            }
        };
    }

    public boolean containsPlayer(Player player) {
        return Objects.nonNull(player) && getPlayers().contains(player);
    }

    public boolean removePlayer(Player player) {
        return Objects.nonNull(player) && this.players.remove(player);
    }

    public int getTotalPlayers() {
        return getPlayers().size();
    }

    public Collection<Player> getPlayers() {
        return new HashSet<>(this.players);
    }
}
