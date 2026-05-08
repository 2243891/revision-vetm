package cegep.vetm.tournament.domain.player.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String id) {
        super("Player not found: " + id);
    }
}
