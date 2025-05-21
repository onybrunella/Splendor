package fr.uge.Splendor.board;

import fr.uge.Splendor.cards.DevelopmentCard;

import java.util.*;

public class Board {
    private List<DevelopmentCard> drawDeck;
    private Map<Integer, List<DevelopmentCard>> visibleRows;

    public Board(List<DevelopmentCard> initialDeck) {
        this.drawDeck = new ArrayList<>(initialDeck);
        this.visibleRows = new HashMap<>();

        for (int i = 1; i <= 3; i++) {
            List<DevelopmentCard> rowCards = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                rowCards.add(this.drawDeck.remove(0));
            }
            visibleRows.put(i, rowCards);
        }
    }

    public Map<Integer, List<DevelopmentCard>> getVisibleRows() {
        return visibleRows;
    }

    public List<DevelopmentCard> getDrawDeck() {
        return drawDeck;
    }
}
