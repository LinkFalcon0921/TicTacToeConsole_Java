package com.flintore_0921.managers;

import com.flintore_0921.componentes.Constants;
import com.flintore_0921.componentes.Constants.ETablePosition;

@Deprecated
public class TablePosition {
    private Integer x, y;

    /*Initiate the value.*/
    public TablePosition() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Get the coordinate
     */
    public int get(ETablePosition coordinate) {
        return switch (coordinate) {
            case X -> this.x;
            case Y -> this.y;
        };
    }

    /**
     * First get the value then increase the coordinate.
     */
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

    /**
     * First get the value then decrease the coordinate.
     */
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

    private void set(ETablePosition position, int value) {
        switch (position) {
            case X -> this.x = value;
            case Y -> this.y = value;
        }
    }

    private void increment(ETablePosition coordinate) {
        int value = this.get(coordinate);

//        Switch to validations
        switch (coordinate) {
            case X -> {
                if (value > Constants.Table.TABLE_ROWS) {
                    return;
                }

            }
            case Y -> {
                if (value > Constants.Table.TABLE_COLUMNS) {
                    return;
                }
            }
        }
        set(coordinate, ++value);
    }

    private void decrease(ETablePosition coordinate) {
        int value = this.get(coordinate);
        if (value == 0) {
            return;
        }

        this.set(coordinate, --value);
    }

    public void reset() {
        this.x = 0;
        this.y = 0;
    }

}
