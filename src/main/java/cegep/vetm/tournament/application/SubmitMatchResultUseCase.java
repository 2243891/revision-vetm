package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.tournament.Match;
import cegep.vetm.tournament.domain.tournament.Score;
import cegep.vetm.tournament.domain.tournament.Tournament;
import cegep.vetm.tournament.domain.tournament.TournamentRepository;
import cegep.vetm.tournament.domain.tournament.exception.MatchNotFoundException;
import cegep.vetm.tournament.domain.tournament.exception.TournamentNotFoundException;

public class SubmitMatchResultUseCase {

    private final TournamentRepository tournamentRepository;

    public SubmitMatchResultUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public void submit(String tournamentId, String matchId, int playerOneScore, int playerTwoScore) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));

        Match match = tournament.getMatches().stream()
                .filter(m -> m.getId().equals(matchId))
                .findFirst()
                .orElseThrow(() -> new MatchNotFoundException(matchId));

        match.setScore(new Score(playerOneScore, playerTwoScore));
        tournamentRepository.save(tournament);
    }
}
