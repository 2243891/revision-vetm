package cegep.vetm.tournament.domain.player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {

    void save(Player player);

    Optional<Player> findById(String id);

    Optional<Player> findByPseudo(String pseudo);

    List<Player> findAll();
}
