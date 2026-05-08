package cegep.vetm.tournament.domain.tournament.exception;

public class RegistrationsClosedException extends RuntimeException {

    public RegistrationsClosedException(String tournamentName) {
        super("Registrations are closed for tournament: " + tournamentName);
    }
}
