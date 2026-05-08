package cegep.vetm.tournament.domain.tournament.exception;

public class PlayerAlreadyRegisteredException extends RuntimeException {

    public PlayerAlreadyRegisteredException(String pseudo) {
        super("Player already registered: " + pseudo);
    }
}
