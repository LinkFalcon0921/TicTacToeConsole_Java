package com.flintore_0921.managers;

import com.flintore_0921.componentes.PlayerIcon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private Map<String, PlayerIcon> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

    public boolean addPlayer(String playerName, PlayerIcon icon) {
        if (containsPlayer(playerName)) {
            return false;
        }

        return this.players.put(playerName, icon) != null;
    }

    public List<PlayerIcon> getAvailableIcons() {
        List<PlayerIcon> availableIcons = Arrays.asList(PlayerIcon.values());
        availableIcons.retainAll(this.players.values());

        return availableIcons;
    }

    public boolean containsPlayer(String name) {
        return this.players.containsKey(name);
    }

    public boolean removePlayer(String playerName) {
        return this.players.remove(playerName) != null;
    }

    public int getTotalPlayers() {
        return this.players.size();
    }
}
