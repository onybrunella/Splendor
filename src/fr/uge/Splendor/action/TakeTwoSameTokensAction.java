package fr.uge.Splendor.action;

import fr.uge.Splendor.player.Player;
import fr.uge.Splendor.tokens.GemColor;
import fr.uge.Splendor.tokens.TokenBank;


import java.util.Scanner;

public class TakeTwoSameTokensAction {

    public static void takeTwoSameTokens(Player player, TokenBank bank, Scanner sc) {
        System.out.println("Choisissez une couleur pour prendre 2 jetons (il faut qu’il en reste au moins 4) :");
        String colorStr = sc.nextLine().toUpperCase();

        try {
            GemColor color = GemColor.valueOf(colorStr);
            int available = bank.getTokens().getOrDefault(color, 0);

            if (available < 4) {
                System.out.println("Pas assez de jetons " + color + " (il en faut au moins 4).");
                return;
            }

            bank.getTokens().put(color, available - 2);
            player.getTokens().put(color, player.getTokens().getOrDefault(color, 0) + 2);
            System.out.println("2 jetons " + color + " pris avec succès.");
        } catch (IllegalArgumentException e) {
            System.out.println("Couleur invalide.");
        }
    }

}
