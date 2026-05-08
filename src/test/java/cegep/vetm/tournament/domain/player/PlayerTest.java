package cegep.vetm.tournament.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void testPlayerEquality() {
        Player playerA = new Player("p1", "Alice");
        Player playerB = new Player("p1", "Bob");
        assertThat(playerA).isEqualTo(playerB);
    }
}
