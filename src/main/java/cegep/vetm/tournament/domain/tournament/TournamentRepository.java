package cegep.vetm.tournament.domain.tournament;

import java.util.List;
import java.util.Optional;

public interface TournamentRepository {

    void save(Tournament tournament);

    Optional<Tournament> findById(String id);

    List<Tournament> findAll();
}
