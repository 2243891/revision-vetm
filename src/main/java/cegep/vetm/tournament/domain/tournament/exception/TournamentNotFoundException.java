package cegep.vetm.tournament.domain.tournament.exception;

public class TournamentNotFoundException extends RuntimeException {

    public TournamentNotFoundException(String id) {
        super("Tournament not found: " + id);
    }
}
