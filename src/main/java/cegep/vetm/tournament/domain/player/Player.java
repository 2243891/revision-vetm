package cegep.vetm.tournament.domain.player;

import java.util.Objects;

public class Player {

    private final String id;
    private final String pseudo;

    public Player(String id, String pseudo) {
        this.id = Objects.requireNonNull(id);
        this.pseudo = Objects.requireNonNull(pseudo);
    }

    public String getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Player)) return false;
        Player player = (Player) other;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return pseudo + " (" + id + ")";
    }
}
