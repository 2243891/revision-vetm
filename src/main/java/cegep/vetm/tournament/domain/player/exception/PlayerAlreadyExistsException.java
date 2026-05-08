package cegep.vetm.tournament.domain.player.exception;

public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException(String pseudo) {
        super("A player with the pseudo '" + pseudo + "' already exists.");
    }
}
