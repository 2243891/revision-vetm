package cegep.vetm.tournament.domain.player;

import cegep.vetm.tournament.domain.player.exception.InvalidPseudoException;

public class PseudoValidator {
/**
 *•	Le pseudo doit avoir entre 3 et 20 caractères (inclusif).
 * •	Il ne doit contenir que des lettres (a-z, A-Z), des chiffres (0-9) et des underscores (_).
 * •	Il ne doit pas commencer par un chiffre.
 * •	Si une règle est violée, lever une InvalidPseudoException (à créer).
 *
 * */


    public static void validate(String pseudo){
        String regex = "[a-zA-Z][a-zA-Z0-9\\_]{2,19}";
        if(!pseudo.matches(regex)) throw new InvalidPseudoException("Le pseudo viole les regles de password");
    }
}
