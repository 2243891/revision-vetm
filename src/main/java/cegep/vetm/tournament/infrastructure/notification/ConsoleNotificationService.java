package cegep.vetm.tournament.infrastructure.notification;

import cegep.vetm.tournament.domain.notification.NotificationService;
import cegep.vetm.tournament.domain.player.Player;

public class ConsoleNotificationService implements NotificationService {

    @Override
    public void notifyRegistration(Player player, String tournamentName) {
        System.out.println("[NOTIFICATION] " + player.getPseudo() + " has been registered to tournament '"
                + tournamentName + "'.");
    }
}
