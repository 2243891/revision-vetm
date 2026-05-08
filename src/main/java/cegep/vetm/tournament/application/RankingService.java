package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.player.Player;
import cegep.vetm.tournament.domain.tournament.Match;
import cegep.vetm.tournament.domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingService {

    public List<RankingEntry> computeRanking(Tournament tournament) {
        Map<Player, Integer> wins = new HashMap<>();
        for (Player player : tournament.getPlayers()) {
            wins.put(player, 0);
        }
        for (Match match : tournament.getMatches()) {
            if (match.hasResult()) {
                Player winner = match.getWinner();
                wins.merge(winner, 1, Integer::sum);
            }
        }
        List<RankingEntry> ranking = new ArrayList<>();
        for (Map.Entry<Player, Integer> entry : wins.entrySet()) {
            ranking.add(new RankingEntry(entry.getKey(), entry.getValue()));
        }
        ranking.sort(Comparator.comparingInt(RankingEntry::getWins).reversed());
        return ranking;
    }

    public static class RankingEntry {
        private final Player player;
        private final int wins;

        public RankingEntry(Player player, int wins) {
            this.player = player;
            this.wins = wins;
        }

        public Player getPlayer() {
            return player;
        }

        public int getWins() {
            return wins;
        }
    }
}
