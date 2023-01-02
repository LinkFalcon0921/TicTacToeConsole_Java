package com.flintore_0921.managers;

public class TurnManager {
    private final int MAX_COUNTER;
    private int position;

    public TurnManager(int MAX_COUNTER) {
        this.MAX_COUNTER = MAX_COUNTER;
    }

    public int nextTurn(){
        return this.position++ % this.MAX_COUNTER;
    }

    public int getActualPlayerTurn(){
        return this.position;
    }

    public int getPreviousPlayerTurn(){
        if (this.position == 0){
            return this.MAX_COUNTER-1;
        }

        return this.position;
    }
}
