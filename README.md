# Esport Tournament Manager

A simple Java application to manage esport tournaments, used as a final exam revision exercise for the *Validation et Maintenance des Applications* (420-4C6-LI) course.

## Domain

- **Player**: registers to a tournament with a unique pseudo
- **Tournament**: has a name, a registration deadline, a list of registered players, and a state (`REGISTRATION_OPEN`, `IN_PROGRESS`, `COMPLETED`)
- **Match**: opposes two players and stores the score and the winner
- **NotificationService**: sends a notification when a player registers
- **Clock**: provides the current time (abstracted to be mockable)

## How to run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="cegep.vetm.tournament.Main"
```

Or simply run `Main.java` from your IDE.

## How to test

```bash
mvn test
```

## Project structure

```
src/main/java/cegep/vetm/tournament/
├── Main.java                 CLI entry point
├── domain/                   Pure business logic
│   ├── player/
│   ├── tournament/
│   ├── notification/
│   └── clock/
├── application/              Use cases orchestrating the domain
└── infrastructure/           Concrete implementations (in-memory, console)
```

## Features (branches)

The `develop` branch contains the base code. Features are developed on dedicated branches following Gitflow:

- `feature/pseudo-validation` — pseudo validation rules and tests
- `feature/notification-on-registration` — notification when a player registers
- `feature/match-results` — submitting match results
- `feature/ranking-improvements` — improved ranking logic with tiebreakers

Refer to the exam document for detailed instructions.
