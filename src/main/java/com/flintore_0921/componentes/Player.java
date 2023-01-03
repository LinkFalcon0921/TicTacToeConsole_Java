package com.flintore_0921.componentes;

import java.util.Objects;

public class Player {

    protected String name;
    protected PlayerIcon icon;

    public Player() {
    }

    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public PlayerIcon getPlayerIcon() {
        return icon;
    }

    public void setPlayerIcon(PlayerIcon icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.icon);
    }
}
