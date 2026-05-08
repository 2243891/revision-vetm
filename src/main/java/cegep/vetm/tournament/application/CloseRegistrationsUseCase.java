package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.tournament.Match;
import cegep.vetm.tournament.domain.tournament.Tournament;
import cegep.vetm.tournament.domain.tournament.TournamentRepository;
import cegep.vetm.tournament.domain.tournament.exception.TournamentNotFoundException;
import cegep.vetm.tournament.domain.player.Player;

import java.util.List;

public class CloseRegistrationsUseCase {

    private final TournamentRepository tournamentRepository;

    public CloseRegistrationsUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public void close(String tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));

        tournament.closeRegistrations();
        generateFirstRoundMatches(tournament);
        tournamentRepository.save(tournament);
    }

    private void generateFirstRoundMatches(Tournament tournament) {
        List<Player> players = tournament.getPlayers();
        for (int i = 0; i + 1 < players.size(); i += 2) {
            String matchId = tournament.getId() + "-M" + (i / 2 + 1);
            tournament.addMatch(new Match(matchId, players.get(i), players.get(i + 1)));
        }
    }
}
