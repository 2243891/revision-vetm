package cegep.vetm.tournament.application;

import cegep.vetm.tournament.domain.notification.NotificationService;
import cegep.vetm.tournament.domain.player.Player;
import cegep.vetm.tournament.domain.player.PlayerRepository;
import cegep.vetm.tournament.domain.player.exception.PlayerAlreadyExistsException;
import cegep.vetm.tournament.domain.tournament.Tournament;
import cegep.vetm.tournament.domain.tournament.TournamentRepository;
import cegep.vetm.tournament.domain.tournament.exception.TournamentNotFoundException;
import cegep.vetm.tournament.infrastructure.memory.InMemoryPlayerRepository;
import cegep.vetm.tournament.infrastructure.memory.InMemoryTournamentRepository;
import cegep.vetm.tournament.infrastructure.notification.ConsoleNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationUseCaseTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @Mock
    private TournamentRepository tournamentRepositoryMock;

    @Mock
    private NotificationService notificationServiceMock;

    private RegistrationUseCase registrationUseCase;

    @BeforeEach
    void setup() {
        registrationUseCase = new RegistrationUseCase(playerRepositoryMock, tournamentRepositoryMock,
                notificationServiceMock);

    }

    @Test
    void testInscription1(){
        when(tournamentRepositoryMock.findById("1")).thenReturn(Optional.of(new Tournament("1", "tournament 1", Instant.now())));
        when(playerRepositoryMock.findByPseudo("user12")).thenReturn(Optional.empty());

        registrationUseCase.register("1","user12", "user12");

        verify(notificationServiceMock).notifyRegistration(any(Player.class), any(String.class));

    }

    @Test
    void testInscription2(){
        when(tournamentRepositoryMock.findById("1")).thenReturn(Optional.of(new Tournament("1", "tournament 1", Instant.now())));
        when(playerRepositoryMock.findByPseudo("bent")).thenReturn(Optional.of(new Player("1","bent")));


        try {
            registrationUseCase.register("1","user12", "bent");
        } catch (PlayerAlreadyExistsException e) {
        }

        verify(notificationServiceMock, never()).notifyRegistration(any(Player.class), any(String.class));

    }

    @Test
    void testInscription3(){
        when(tournamentRepositoryMock.findById("1")).thenThrow(TournamentNotFoundException.class);
       // when(playerRepositoryMock.findByPseudo("bent")).thenReturn(Optional.of(new Player("1","bent")));


        try {
            registrationUseCase.register("1","user12", "bent");
        } catch (TournamentNotFoundException e) {
        }

        verify(notificationServiceMock, never()).notifyRegistration(any(Player.class), any(String.class));

    }

}