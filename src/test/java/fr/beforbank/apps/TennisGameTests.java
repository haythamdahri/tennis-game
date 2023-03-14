package fr.beforbank.apps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TennisGameTests {

    @Test
    void playGame_shouldPickPlayerA_asWinner() {
        // Given
        final String players = "AAAA";

        // Then
        final TennisGame tennisGame = new TennisGame();
        tennisGame.play(players);
        assertEquals(Player.A, tennisGame.getWinner());
    }

    @Test
    void playGame_shouldPickPlayerB_asWinner() {
        // Given
        final String players = "BBBB";

        // Then
        final TennisGame tennisGame = new TennisGame();
        tennisGame.play(players);
        assertEquals(Player.B, tennisGame.getWinner());
    }

    @ParameterizedTest
    @MethodSource("unfinishedGames")
    void playGame_shouldNotPickAnyPlayerWhileTheGameIsNotOverYet(final String players) {
        final TennisGame tennisGame = new TennisGame();
        tennisGame.play(players);
        assertNull(tennisGame.getWinner());
    }

    @ParameterizedTest
    @MethodSource("finishedGames")
    void playGame_shouldPickPlayerAfterExchanges(final String players, final Player winner) {
        final TennisGame tennisGame = new TennisGame();
        tennisGame.play(players);
        assertEquals(winner, tennisGame.getWinner());
    }

    private static Stream<Arguments> unfinishedGames() {
        return Stream.of(
                Arguments.of("A"),
                Arguments.of("B"),
                Arguments.of("AA"),
                Arguments.of("BB"),
                Arguments.of("AAB"),
                Arguments.of("ABB"),
                Arguments.of("AABB"),
                Arguments.of("BBAA"),
                Arguments.of("AAA"),
                Arguments.of("BBB"),
                Arguments.of("AAAB"),
                Arguments.of("AAABB"),
                Arguments.of("AAABBB"),
                Arguments.of("BBBA"),
                Arguments.of("BBBAA"),
                Arguments.of("BBBAAA"),
                Arguments.of("ABABABABABABABABABABABABAB")
        );
    }

    private static Stream<Arguments> finishedGames() {
        return Stream.of(
                Arguments.of("AAAA", Player.A),
                Arguments.of("BBBB", Player.B),
                Arguments.of("AAABBBAA", Player.A),
                Arguments.of("AAABBBABAA", Player.A),
                Arguments.of("AAABBBABBB", Player.B)
        );
    }

}
