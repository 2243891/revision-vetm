package cegep.vetm.tournament.domain.tournament;

public class Score {

    private final int playerOneScore;
    private final int playerTwoScore;

    public Score(int playerOneScore, int playerTwoScore) {
        if (playerOneScore < 0 || playerTwoScore < 0) {
            throw new IllegalArgumentException("Scores must be positive.");
        }
        if (playerOneScore == playerTwoScore) {
            throw new IllegalArgumentException("Scores cannot be a tie.");
        }
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public boolean playerOneWins() {
        return playerOneScore > playerTwoScore;
    }
}
