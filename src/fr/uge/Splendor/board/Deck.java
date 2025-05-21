package fr.uge.Splendor.board;

import fr.uge.Splendor.cards.DevelopmentCard;
import fr.uge.Splendor.tokens.GemColor;

import java.util.*;

public class Deck {
    private final List<DevelopmentCard> cards;

    public Deck() {
        cards = new ArrayList<>();
        // Initialisation des cartes dès la création du deck
        for (GemColor color : GemColor.values()) {
            for (int i = 0; i < 8; i++) {
                Map<GemColor, Integer> cost = new HashMap<>();
                cost.put(color, 3);
                cards.add(new DevelopmentCard(1, color, cost, 1));
            }
        }
        Collections.shuffle(cards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public DevelopmentCard drawCard() {
        if (isEmpty()) return null;
        return cards.remove(0);
    }

    public List<DevelopmentCard> getCards() {
        return cards;
    }
}
