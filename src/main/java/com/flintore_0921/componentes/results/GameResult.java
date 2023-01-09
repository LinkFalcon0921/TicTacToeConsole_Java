package com.flintore_0921.componentes.results;

import java.util.Set;

public final class GameResult {

    public static final GameResult DRAW_RESULT = new GameResult(null, StateResult.DRAW);
    public static final GameResult STILL_PLAYING_RESULT = new GameResult(null, StateResult.PLAYING);

    interface Helpers {
        Set<StateResult> DEFAULT_RESULTS = Set.of(
                StateResult.DRAW
        );
    }

    public enum StateResult {
        WIN,
        DRAW,
        PLAYING
    }

    private final String additionalText;
    private final StateResult result;

    public GameResult(String text, StateResult result) {
        this.additionalText = text;
        this.result = result;
    }

    public boolean isDefaultResult() {
        return Helpers.DEFAULT_RESULTS.contains(this.result);
    }

    public String printResult() {
        return switch (this.result) {
            case WIN -> String.format("El ganador es: %s", this.additionalText);
            case DRAW -> String.format("El resultado es un %s", this.result);
            case PLAYING -> "Aun no hay ganador.";
        };
    }

}
