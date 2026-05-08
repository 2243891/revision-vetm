package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.player.Player;
import cegep.vetm.tournament.domain.tournament.Match;
import cegep.vetm.tournament.domain.tournament.Score;
import cegep.vetm.tournament.domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingService {

    public List<RankingEntry> computeRanking(Tournament tournament) {
        Map<Player, Integer> wins = new HashMap<>();
        Map<Player, Integer> pointsScored = new HashMap<>();
        for (Player player : tournament.getPlayers()) {
            wins.put(player, 0);
            pointsScored.put(player, 0);
        }
        for (Match match : tournament.getMatches()) {
            if (match.hasResult()) {
                Player winner = match.getWinner();
                wins.merge(winner, 1, Integer::sum);
                Score score = match.getScore();
                pointsScored.merge(match.getPlayerOne(), score.getPlayerOneScore(), Integer::sum);
                pointsScored.merge(match.getPlayerTwo(), score.getPlayerTwoScore(), Integer::sum);
            }
        }
        List<RankingEntry> ranking = new ArrayList<>();
        for (Player player : tournament.getPlayers()) {
            ranking.add(new RankingEntry(player, wins.get(player), pointsScored.get(player)));
        }
        ranking.sort(Comparator
                .comparingInt(RankingEntry::getWins).reversed()
                .thenComparing(Comparator.comparingInt(RankingEntry::getPointsScored).reversed()));
        return ranking;
    }

    public static class RankingEntry {
        private final Player player;
        private final int wins;
        private final int pointsScored;

        public RankingEntry(Player player, int wins, int pointsScored) {
            this.player = player;
            this.wins = wins;
            this.pointsScored = pointsScored;
        }

        public Player getPlayer() {
            return player;
        }

        public int getWins() {
            return wins;
        }

        public int getPointsScored() {
            return pointsScored;
        }
    }
}
