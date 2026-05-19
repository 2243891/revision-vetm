package cegep.vetm.tournament.domain.player.exception;

public class InvalidPseudoException extends RuntimeException {
    public InvalidPseudoException(String message) {
        super(message);
    }
}
