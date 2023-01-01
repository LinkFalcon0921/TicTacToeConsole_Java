package com.flintore_0921.managers;

import com.flintore_0921.componentes.PlayerIcon;

import java.util.*;

public class PlayerManager {
    private final Map<String, PlayerIcon> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

    public boolean addPlayer(String playerName, PlayerIcon icon) {
        if (containsPlayer(playerName)) {
            return false;
        }

        return this.players.put(playerName, icon) != null;
    }

    public Collection<String> getUserNames() {
        return this.players.keySet();
    }

    public List<PlayerIcon> getAvailableIcons() {
        List<PlayerIcon> availableIcons = Arrays.asList(PlayerIcon.values());
        availableIcons.retainAll(this.players.values());

        return availableIcons;
    }

    public boolean editUser(String name, String newName) {
        if (!containsPlayer(name)) {
            return false;
        }

        PlayerIcon playerIcon = this.players.remove(name);
        return this.players.put(newName, playerIcon) != null;
    }

    public boolean editUser(String name, PlayerIcon newIcon) {
        if (!containsPlayer(name)) {
            return false;
        }

        PlayerIcon previousPlayerIcon = this.players.get(name);
        return this.players.replace(name, previousPlayerIcon, newIcon);
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
