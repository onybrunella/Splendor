package fr.uge.Splendor.action;

import fr.uge.Splendor.player.Player;
import fr.uge.Splendor.tokens.GemColor;
import fr.uge.Splendor.tokens.TokenBank;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TakeThreeDifferentTokensAction {
    public static void takeThreeDifferentTokens(Player player, TokenBank bank, Scanner sc) {
        while (true) {
            System.out.println("Entrez trois couleurs différentes (ex: WHITE RED BLUE) :");
            var line = sc.nextLine();
            String[] colors = line.trim().split("\\s+");

            if (colors.length != 3) {
                System.out.println("Erreur : vous devez entrer exactement trois couleurs.");
                continue;
            }

            Set<GemColor> chosenColors = new HashSet<>();
            boolean valid = true;

            for (var colorStr : colors) {
                try {
                    var gemColor = GemColor.valueOf(colorStr.toUpperCase());
                    if (chosenColors.contains(gemColor)) {
                        System.out.println("Erreur : les couleurs doivent être différentes.");
                        valid = false;
                        break;
                    }
                    int available = bank.getTokens().getOrDefault(gemColor, 0);
                    if (available < 1) {
                        System.out.println("Erreur : pas assez de jetons " + gemColor + ".");
                        valid = false;
                        break;
                    }
                    chosenColors.add(gemColor);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur : couleur invalide '" + colorStr + "'.");
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                continue;
            }

            for (GemColor gemColor : chosenColors) {
                bank.getTokens().put(gemColor, bank.getTokens().get(gemColor) - 1);
                player.getTokens().put(gemColor, player.getTokens().getOrDefault(gemColor, 0) + 1);
            }
            System.out.println("Jetons pris avec succès !");
            break;
        }
    }

}


