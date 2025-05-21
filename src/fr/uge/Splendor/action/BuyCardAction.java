package fr.uge.Splendor.action;

import fr.uge.Splendor.board.Board;
import fr.uge.Splendor.cards.DevelopmentCard;
import fr.uge.Splendor.player.Player;
import fr.uge.Splendor.tokens.GemColor;
import fr.uge.Splendor.tokens.TokenBank;

import java.util.*;

public class BuyCardAction {

    public static void buyCard(Player player, TokenBank bank, Board board, Scanner sc) {
        Map<Integer, List<DevelopmentCard>> visibleRows = board.getVisibleRows();

        System.out.println("Choisissez le niveau de la carte à acheter :");
        for (Integer level : visibleRows.keySet()) {
            System.out.println(level + ": Niveau " + level + " (" + visibleRows.get(level).size() + " cartes)");
        }

        int level;
        try {
            level = Integer.parseInt(sc.nextLine());
            if (!visibleRows.containsKey(level)) {
                System.out.println("Niveau invalide.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide.");
            return;
        }

        List<DevelopmentCard> cards = visibleRows.get(level);
        if (cards.isEmpty()) {
            System.out.println("Aucune carte disponible à ce niveau.");
            return;
        }

        System.out.println("Cartes disponibles au niveau " + level + " :");
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(i + ": " + cards.get(i));
        }

        System.out.println("Entrez l’indice de la carte à acheter :");
        int index;
        try {
            index = Integer.parseInt(sc.nextLine());
            if (index < 0 || index >= cards.size()) {
                System.out.println("Indice invalide.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide.");
            return;
        }

        DevelopmentCard card = cards.get(index);
        if (!player.canBuy(card)) {
            System.out.println("Vous ne pouvez pas acheter cette carte (pas assez de jetons).");
            return;
        }

        // Effectuer la transaction (suppose que player.buyCard gère le retrait des jetons du joueur)
        player.buyCard(card);

        // Remettre les jetons payés dans la banque (en tenant compte des bonus)
        GemColor color = card.bonus();
        int bonuses = (int) player.getCards().stream().filter(c -> c.bonus() == color).count();
        int cost = Math.max(0, 3 - bonuses);
        bank.getTokens().put(color, bank.getTokens().getOrDefault(color, 0) + cost);

        cards.remove(index);

        if (!board.getDrawDeck().isEmpty()) {
            cards.add(board.getDrawDeck().remove(0));
        }
        System.out.println("Carte achetée avec succès !");
    }

    public static boolean canAffordAnyCard(Player player, Board board) {
        for (var cards : board.getVisibleRows().values()) {
            for (var card : cards) {
                if (player.canBuy(card)) {
                    return true;
                }
            }
        }
        return false;
    }
}
