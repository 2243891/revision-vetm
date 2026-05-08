package cegep.vetm.tournament.infrastructure.memory;

import cegep.vetm.tournament.domain.player.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryPlayerRepositoryTest {

    @Test
    void testFindByPseudo() {
        InMemoryPlayerRepository repository = new InMemoryPlayerRepository();
        Player player = new Player("p1", "Alice");
        repository.save(player);
        assertThat(repository.findByPseudo("Alice")).contains(player);
    }
}
