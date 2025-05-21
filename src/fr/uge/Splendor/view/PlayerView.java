package fr.uge.Splendor.view;

import fr.uge.Splendor.player.Player;
import fr.uge.Splendor.cards.DevelopmentCard;
import fr.uge.Splendor.tokens.GemColor;

public class PlayerView {
    public static void displayPlayerInfo(Player player) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n=== Joueur : ").append(player.getName()).append(" ===\n");
        sb.append("Prestige : ").append(player.getPrestigePoints()).append("\n");

        sb.append("Jetons : ");
        for (GemColor color : GemColor.values()) {
            int count = player.getTokens().getOrDefault(color, 0);
            sb.append(color).append(" = ").append(count).append(" ");
        }
        sb.append("\n");

        sb.append("Jetons bonus : ");
        for (GemColor color : GemColor.values()) {
            int bonus = player.getBonus().getOrDefault(color, 0);
            sb.append(color).append(" = ").append(bonus).append(" ");
        }
        sb.append("\n");

        sb.append("Cartes possédées :\n");
        for (DevelopmentCard card : player.getCards()) {
            sb.append(card.toString()).append("\n");
        }

        System.out.println(sb);
    }
}
