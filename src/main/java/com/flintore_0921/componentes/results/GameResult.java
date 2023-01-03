package com.flintore_0921.componentes.results;

import java.util.Set;

public final class GameResult {

    public static final GameResult DRAW_RESULT = new GameResult(null, Result.DRAW);

    interface Helpers {
        Set<Result> DEFAULT_RESULTS = Set.of(
                Result.DRAW
        );
    }

    public enum Result {
        WIN,
        DRAW
    }

    private final String additionalText;
    private final Result result;

    public GameResult(String text, Result result) {
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
        };
    }

}
