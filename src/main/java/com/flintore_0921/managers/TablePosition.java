package com.flintore_0921.managers;

import com.flintore_0921.componentes.Constants;
import com.flintore_0921.componentes.Constants.ETablePosition;

public class TablePosition {
    private int x, y;

    /*Initiate the value.*/
    public TablePosition() {
        this.x = 0;
        this.y = 0;
    }

    /**Get the coordinate*/
    public int get(ETablePosition coordinate) {
        return switch (coordinate) {
            case X -> this.x;
            case Y -> this.y;
        };
    }

    /**First get the value then increase the coordinate.*/
    public int getAndIncrease(ETablePosition coordinate) {
        try {
            return switch (coordinate) {
                case X -> this.x;
                case Y -> this.y;
            };
        } finally {
            increment(coordinate);
        }
    }

    /**First get the value then decrease the coordinate.*/
    public int getAndDecrease(ETablePosition coordinate) {
        try {
            return switch (coordinate) {
                case X -> this.x;
                case Y -> this.y;
            };
        } finally {
            decrease(coordinate);
        }
    }

    private void increment(ETablePosition coordinate) {
        switch (coordinate) {
            case X -> this.x++;
            case Y -> this.y++;
        }
    }

    private void decrease(ETablePosition coordinate) {
        switch (coordinate) {
            case X -> this.x--;
            case Y -> this.y--;
        }
    }

}
