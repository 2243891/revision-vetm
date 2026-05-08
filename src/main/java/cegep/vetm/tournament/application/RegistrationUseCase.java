package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.player.Player;
import cegep.vetm.tournament.domain.player.PlayerRepository;
import cegep.vetm.tournament.domain.player.exception.PlayerAlreadyExistsException;
import cegep.vetm.tournament.domain.tournament.Tournament;
import cegep.vetm.tournament.domain.tournament.TournamentRepository;
import cegep.vetm.tournament.domain.tournament.exception.TournamentNotFoundException;

public class RegistrationUseCase {

    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;

    public RegistrationUseCase(PlayerRepository playerRepository, TournamentRepository tournamentRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public Player register(String tournamentId, String playerId, String pseudo) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));

        if (playerRepository.findByPseudo(pseudo).isPresent()) {
            throw new PlayerAlreadyExistsException(pseudo);
        }

        Player player = new Player(playerId, pseudo);
        playerRepository.save(player);
        tournament.register(player);
        tournamentRepository.save(tournament);
        return player;
    }
}
