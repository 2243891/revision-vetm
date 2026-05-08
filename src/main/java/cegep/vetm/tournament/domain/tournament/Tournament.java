package cegep.vetm.tournament.domain.tournament;

import cegep.vetm.tournament.domain.player.Player;
import cegep.vetm.tournament.domain.tournament.exception.PlayerAlreadyRegisteredException;
import cegep.vetm.tournament.domain.tournament.exception.RegistrationsClosedException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {

    private final String id;
    private final String name;
    private final Instant registrationDeadline;
    private final List<Player> players = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();
    private TournamentState state;

    public Tournament(String id, String name, Instant registrationDeadline) {
        this.id = id;
        this.name = name;
        this.registrationDeadline = registrationDeadline;
        this.state = TournamentState.REGISTRATION_OPEN;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getRegistrationDeadline() {
        return registrationDeadline;
    }

    public TournamentState getState() {
        return state;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    public void register(Player player) {
        if (state != TournamentState.REGISTRATION_OPEN) {
            throw new RegistrationsClosedException(name);
        }
        if (players.contains(player)) {
            throw new PlayerAlreadyRegisteredException(player.getPseudo());
        }
        players.add(player);
    }

    public void closeRegistrations() {
        this.state = TournamentState.IN_PROGRESS;
    }

    public void complete() {
        this.state = TournamentState.COMPLETED;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }
}
