package cegep.vetm.tournament.domain.tournament;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    void testWinningScore() {
        Score score = new Score(3, 1);
        assertThat(score.playerOneWins()).isTrue();
    }

    @Test
    void testTieScoreIsRejected() {
        assertThatThrownBy(() -> new Score(2, 2)).isInstanceOf(IllegalArgumentException.class);
    }
}
