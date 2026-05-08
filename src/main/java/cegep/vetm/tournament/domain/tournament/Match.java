package cegep.vetm.tournament.domain.tournament;

import cegep.vetm.tournament.domain.player.Player;

public class Match {

    private final String id;
    private final Player playerOne;
    private final Player playerTwo;
    private Score score;

    public Match(String id, Player playerOne, Player playerTwo) {
        this.id = id;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public String getId() {
        return id;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Score getScore() {
        return score;
    }

    public boolean hasResult() {
        return score != null;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Player getWinner() {
        if (score == null) {
            return null;
        }
        return score.playerOneWins() ? playerOne : playerTwo;
    }
}
