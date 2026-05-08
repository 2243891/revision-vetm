package cegep.vetm.tournament.domain.notification;

import cegep.vetm.tournament.domain.player.Player;

public interface NotificationService {

    void notifyRegistration(Player player, String tournamentName);
}
