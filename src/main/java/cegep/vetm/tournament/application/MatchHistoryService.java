package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.player.Player;
import cegep.vetm.tournament.domain.tournament.Match;
import cegep.vetm.tournament.domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.List;

public class MatchHistoryService {

    public List<Match> findCompletedMatchesFor(Tournament tournament, Player player) {
        List<Match> matches = new ArrayList<>();
        for (Match match : tournament.getMatches()) {
            if (match.hasResult()
                    && (match.getPlayerOne().equals(player) || match.getPlayerTwo().equals(player))) {
                matches.add(match);
            }
        }
        return matches;
    }
}
