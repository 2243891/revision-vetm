package cegep.vetm.tournament.domain.player;

import cegep.vetm.tournament.domain.player.exception.InvalidPseudoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PseudoValidatorTest {
    private final String TOO_SHORT_PASSWORD = "AB";
    private final String TOO_LONG_PASSWORD = "ABC1231414123213213123214512";
    private final String START_WITH_NUMBER = "1AVCWADa";
    private final String VALID_PASSWORD = "AVCWA321Da_";

    @Test
    void givenPasswordWithATooShortLength_WhenValidate_ThenInvalidPseudoException() {
        assertThrows(InvalidPseudoException.class, () -> {
            PseudoValidator.validate(TOO_SHORT_PASSWORD);
        });
    }

    @Test
    void givenPasswordWithATooLongtLength_WhenValidate_ThenInvalidPseudoException() {
        assertThrows(InvalidPseudoException.class, () -> {
            PseudoValidator.validate(TOO_LONG_PASSWORD);
        });
    }

    @Test
    void givenPasswordStartingWithNumber_WhenValidate_ThenInvalidPseudoException() {
        assertThrows(InvalidPseudoException.class, () -> {
            PseudoValidator.validate(START_WITH_NUMBER);
        });
    }

    @Test
    void givenValidPassword_WhenValidate_ThenInvalidPseudoExceptionNotThrown() {
        assertDoesNotThrow(() -> {
            PseudoValidator.validate(VALID_PASSWORD);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "Alice", "bob_42", "Player_1", "abc" })
    void givenValidPassword_WhenValidate_ThenInvalidPseudoExceptionNotThrown(String value) {
        assertDoesNotThrow(() -> {
            PseudoValidator.validate(VALID_PASSWORD);
        });
    }
}