package cegep.vetm.tournament;

import cegep.vetm.tournament.application.CloseRegistrationsUseCase;
import cegep.vetm.tournament.application.CreateTournamentUseCase;
import cegep.vetm.tournament.application.RankingService;
import cegep.vetm.tournament.application.RegistrationUseCase;
import cegep.vetm.tournament.application.SubmitMatchResultUseCase;
import cegep.vetm.tournament.domain.clock.Clock;
import cegep.vetm.tournament.domain.notification.NotificationService;
import cegep.vetm.tournament.domain.player.PlayerRepository;
import cegep.vetm.tournament.domain.tournament.Match;
import cegep.vetm.tournament.domain.tournament.Tournament;
import cegep.vetm.tournament.domain.tournament.TournamentRepository;
import cegep.vetm.tournament.infrastructure.clock.SystemClock;
import cegep.vetm.tournament.infrastructure.memory.InMemoryPlayerRepository;
import cegep.vetm.tournament.infrastructure.memory.InMemoryTournamentRepository;
import cegep.vetm.tournament.infrastructure.notification.ConsoleNotificationService;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final PlayerRepository playerRepository = new InMemoryPlayerRepository();
    private final TournamentRepository tournamentRepository = new InMemoryTournamentRepository();
    private final Clock clock = new SystemClock();
    private final NotificationService notificationService = new ConsoleNotificationService();

    private final CreateTournamentUseCase createTournament = new CreateTournamentUseCase(tournamentRepository, clock);
    private final RegistrationUseCase registration = new RegistrationUseCase(playerRepository, tournamentRepository, notificationService);
    private final CloseRegistrationsUseCase closeRegistrations = new CloseRegistrationsUseCase(tournamentRepository);
    private final SubmitMatchResultUseCase submitResult = new SubmitMatchResultUseCase(tournamentRepository);
    private final RankingService rankingService = new RankingService();

    public static void main(String[] args) {
        new Main().run();
    }

    public Main() {
        seedSampleData();
    }

    private void seedSampleData() {
        Tournament tournament = createTournament.create("Spring Cup 2026", Duration.ofHours(2));
        registerSamplePlayer(tournament.getId(), "Alice");
        registerSamplePlayer(tournament.getId(), "Bob");
        registerSamplePlayer(tournament.getId(), "Carol");
        registerSamplePlayer(tournament.getId(), "Dave");
    }

    private void registerSamplePlayer(String tournamentId, String pseudo) {
        registration.register(tournamentId, UUID.randomUUID().toString().substring(0, 8), pseudo);
    }

    public void run() {
        System.out.println("=== Esport Tournament Manager ===");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> createTournamentInteractive();
                    case "2" -> registerPlayerInteractive();
                    case "3" -> closeRegistrationsInteractive();
                    case "4" -> submitResultInteractive();
                    case "5" -> showRankingInteractive();
                    case "6" -> listTournaments();
                    case "0" -> running = false;
                    default -> System.out.println("Unknown choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Kachow.");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Create tournament");
        System.out.println("2. Register player");
        System.out.println("3. Close registrations");
        System.out.println("4. Submit match result");
        System.out.println("5. Show ranking");
        System.out.println("6. List tournaments");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private String askTournamentId() {
        listTournaments();
        System.out.print("Tournament id: ");
        return scanner.nextLine().trim();
    }

    private String askMatchId(Tournament tournament) {
        System.out.println("Available matches:");
        for (Match match : tournament.getMatches()) {
            String status = match.hasResult() ? "[done]" : "[pending]";
            System.out.println("  " + match.getId() + " " + status + ": "
                    + match.getPlayerOne().getPseudo() + " vs " + match.getPlayerTwo().getPseudo());
        }
        System.out.print("Match id: ");
        return scanner.nextLine().trim();
    }

    private void createTournamentInteractive() {
        System.out.print("Tournament name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Registration window (in minutes): ");
        long minutes = Long.parseLong(scanner.nextLine().trim());
        Tournament tournament = createTournament.create(name, Duration.ofMinutes(minutes));
        System.out.println("Created tournament " + tournament.getId() + " - deadline: " + tournament.getRegistrationDeadline());
    }

    private void registerPlayerInteractive() {
        String tournamentId = askTournamentId();
        System.out.print("Pseudo: ");
        String pseudo = scanner.nextLine().trim();
        registration.register(tournamentId, UUID.randomUUID().toString().substring(0, 8), pseudo);
        System.out.println(pseudo + " registered.");
    }

    private void closeRegistrationsInteractive() {
        String tournamentId = askTournamentId();
        closeRegistrations.close(tournamentId);
        System.out.println("Registrations closed. First-round matches generated.");
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();
        for (Match match : tournament.getMatches()) {
            System.out.println("  " + match.getId() + ": " + match.getPlayerOne().getPseudo()
                    + " vs " + match.getPlayerTwo().getPseudo());
        }
    }

    private void submitResultInteractive() {
        String tournamentId = askTournamentId();
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();
        String matchId = askMatchId(tournament);
        System.out.print("Player 1 score: ");
        int s1 = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Player 2 score: ");
        int s2 = Integer.parseInt(scanner.nextLine().trim());
        submitResult.submit(tournamentId, matchId, s1, s2);
        System.out.println("Result recorded.");
    }

    private void showRankingInteractive() {
        String tournamentId = askTournamentId();
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();
        List<RankingService.RankingEntry> ranking = rankingService.computeRanking(tournament);
        int position = 1;
        for (RankingService.RankingEntry entry : ranking) {
            System.out.println(position++ + ". " + entry.getPlayer().getPseudo() + " - " + entry.getWins() + " win(s)");
        }
    }

    private void listTournaments() {
        System.out.println("Available tournaments:");
        for (Tournament tournament : tournamentRepository.findAll()) {
            System.out.println("  " + tournament.getId() + " - " + tournament.getName() + " [" + tournament.getState() + "]");
        }
    }
}
