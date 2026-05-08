package cegep.vetm.tournament.infrastructure.clock;

import cegep.vetm.tournament.domain.clock.Clock;

import java.time.Instant;

public class SystemClock implements Clock {

    @Override
    public Instant now() {
        return Instant.now();
    }
}
