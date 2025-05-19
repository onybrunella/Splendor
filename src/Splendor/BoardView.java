package Splendor;

import java.util.List;
import java.util.Map;

public class BoardView {

    public static void display(Board board) {
        Map<Integer, List<DevelopmentCard>> rows = board.getVisibleRows();

        System.out.println("=== Board ===");

        for (int row = 1; row <= 3; row++) {
            System.out.println("Line " + row + " :");
            List<DevelopmentCard> cards = rows.get(row);
            displayCardRow(cards);
            System.out.println();
        }

        System.out.println("Remaining cards : " + board.getDrawDeck().size());
    }

    public static void displayCardRow(List<DevelopmentCard> cards) {
        if (cards == null || cards.isEmpty()) {
            System.out.println("(No card)");
            return;
        }

        List<String[]> cardLinesList = new java.util.ArrayList<>();

        for (DevelopmentCard card : cards) {
            cardLinesList.add(card.toCardLines());
        }

        for (int i = 0; i < cardLinesList.get(0).length; i++) {
            for (String[] cardLines : cardLinesList) {
                System.out.print(cardLines[i] + "  ");
            }
            System.out.println();
        }
    }
}
