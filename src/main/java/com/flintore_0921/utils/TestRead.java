package com.flintore_0921.utils;

import com.flintore_0921.componentes.PlayerIcon;

import java.util.List;
import java.util.stream.Stream;

public class TestRead {

    private static int sCase = 0;

    public static void main(String[] args) {

        final PlayerIcon iconPlayer = PlayerIcon.X;

        final PlayerIcon[][] moves = {
                {PlayerIcon.X, PlayerIcon.X, PlayerIcon.X},
                {PlayerIcon.X, PlayerIcon.X, PlayerIcon.X}
        };

//        4 ms
        Runnable runnerStream = () -> {
            Stream<PlayerIcon> iconsRead = Stream.of(moves[0]);

            List<PlayerIcon> playerIcons = iconsRead.toList();

            playerIcons.forEach(System.out::println);
        };

//        0 ms
        final Runnable runnerForEach = () -> {
            boolean match;

            for (int row = 0; row < 1; row++) {
                PlayerIcon[] rowMove = moves[row];

                for (int column = 0; column < rowMove.length; column++) {
                    PlayerIcon icon = rowMove[column];

                    match = iconPlayer.equals(icon);

                    if (!match) {
                        System.out.println(String.format("Case #%d: fail.", sCase));
                    }
                }
            }

        };

        testCase(runnerStream);
        testCase(runnerForEach);
    }
    private static void testCase(Runnable r) {
        final long currentTimeStart, currentTimeEnd;

        currentTimeStart = System.currentTimeMillis();

        r.run();

        currentTimeEnd = System.currentTimeMillis();

        System.out.println(
                String.format("Caso #%d: %d ms para finalizar.", ++sCase, currentTimeEnd - currentTimeStart)
        );
    }
}
