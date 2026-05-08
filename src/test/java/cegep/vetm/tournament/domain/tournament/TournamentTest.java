package cegep.vetm.tournament.domain.tournament;

import cegep.vetm.tournament.domain.player.Player;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class TournamentTest {

    @Test
    void testNewTournamentState() {
        Tournament tournament = new Tournament("t1", "Spring Cup", Instant.now().plusSeconds(3600));
        assertThat(tournament.getState()).isEqualTo(TournamentState.REGISTRATION_OPEN);
    }

    @Test
    void testRegisterPlayer() {
        Tournament tournament = new Tournament("t1", "Spring Cup", Instant.now().plusSeconds(3600));
        Player player = new Player("p1", "Alice");
        tournament.register(player);
        assertThat(tournament.getPlayers()).containsExactly(player);
    }
}
