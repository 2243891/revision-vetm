package cegep.vetm.tournament.infrastructure.memory;

import cegep.vetm.tournament.domain.tournament.Tournament;
import cegep.vetm.tournament.domain.tournament.TournamentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTournamentRepository implements TournamentRepository {

    private final Map<String, Tournament> tournamentsById = new HashMap<>();

    @Override
    public void save(Tournament tournament) {
        tournamentsById.put(tournament.getId(), tournament);
    }

    @Override
    public Optional<Tournament> findById(String id) {
        return Optional.ofNullable(tournamentsById.get(id));
    }

    @Override
    public List<Tournament> findAll() {
        return new ArrayList<>(tournamentsById.values());
    }
}
