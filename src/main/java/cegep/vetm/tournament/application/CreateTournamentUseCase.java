package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.clock.Clock;
import cegep.vetm.tournament.domain.tournament.Tournament;
import cegep.vetm.tournament.domain.tournament.TournamentRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class CreateTournamentUseCase {

    private final TournamentRepository tournamentRepository;
    private final Clock clock;

    public CreateTournamentUseCase(TournamentRepository tournamentRepository, Clock clock) {
        this.tournamentRepository = tournamentRepository;
        this.clock = clock;
    }

    public Tournament create(String name, Duration registrationWindow) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Instant deadline = clock.now().plus(registrationWindow);
        Tournament tournament = new Tournament(id, name, deadline);
        tournamentRepository.save(tournament);
        return tournament;
    }
}
