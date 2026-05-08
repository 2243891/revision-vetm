package cegep.vetm.tournament.infrastructure.memory;

import cegep.vetm.tournament.domain.player.Player;
import cegep.vetm.tournament.domain.player.PlayerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryPlayerRepository implements PlayerRepository {

    private final Map<String, Player> playersById = new HashMap<>();

    @Override
    public void save(Player player) {
        playersById.put(player.getId(), player);
    }

    @Override
    public Optional<Player> findById(String id) {
        return Optional.ofNullable(playersById.get(id));
    }

    @Override
    public Optional<Player> findByPseudo(String pseudo) {
        return playersById.values().stream()
                .filter(p -> p.getPseudo().equals(pseudo))
                .findFirst();
    }

    @Override
    public List<Player> findAll() {
        return new ArrayList<>(playersById.values());
    }
}
