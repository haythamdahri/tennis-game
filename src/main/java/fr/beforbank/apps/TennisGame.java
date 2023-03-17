package fr.beforbank.apps;

public class TennisGame {
    private int playerOneScore;
    private int playerTwoScore;
    private boolean gameOver;
    private Player winner;

    public TennisGame() {
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.gameOver = false;
    }

    public void play(final String players) {
        if (this.gameOver) {
            System.out.printf("Game over, Player %s already won the game%n", winner);
            return;
        }
        for (int i = 0; i < players.length(); i++) {
            if (players.charAt(i) == 'A') {
                this.playerOneScores();
            } else {
                this.playerTwoScores();
            }
            checkWinner();
            if (winner != null) {
                System.out.printf("Player %s wins the game%n", winner);
                return;
            }
            System.out.printf(
                    "Player A : %s / Player B : %s%n",
                    translateScore(this.playerOneScore),
                    translateScore(this.playerTwoScore)
            );
        }
    }

    public Player getWinner() {
        return this.winner;
    }

    private void playerOneScores() {
        if (playerOneScore >= 3 && playerTwoScore >= 3) {
            if (playerOneScore == playerTwoScore) {
                playerOneScore++;
            } else if (playerOneScore == playerTwoScore + 1) {
                playerOneScore++;
            } else {
                playerOneScore = 3;
                playerTwoScore = 3;
            }
        } else {
            playerOneScore++;
        }
    }

    private void playerTwoScores() {
        if (playerOneScore >= 3 && playerTwoScore >= 3) {
            if (playerTwoScore == playerOneScore) {
                playerTwoScore++;
            } else if (playerTwoScore == playerOneScore + 1) {
                playerTwoScore++;
            } else {
                playerOneScore = 3;
                playerTwoScore = 3;
            }
        } else {
            playerTwoScore++;
        }
    }

    private void checkWinner() {
        if (playerOneScore >= 4 && playerOneScore >= playerTwoScore + 2) {
            this.winner = Player.A;
            this.gameOver = true;
        } else if (playerTwoScore >= 4 && playerTwoScore >= playerOneScore + 2) {
            this.winner = Player.B;
            this.gameOver = true;
        }
    }

    private String translateScore(int score) {
        return switch (score) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            case 4 -> "AD";
            default -> "";
        };
    }

}
