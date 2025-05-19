package Splendor;

import java.util.*;

public class Splendor {
	private static final List<DevelopmentCard> drawDeck = new ArrayList<>();

	public static void initDeck() {
		for (GemColor color : GemColor.values()) {
			for (int i = 0; i < 8; i++) {
				Map<GemColor, Integer> cost = new HashMap<>();
				cost.put(color, 3);
				drawDeck.add(new DevelopmentCard(1, color, cost, 1));
			}
		}
		Collections.shuffle(drawDeck);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Nom du premier joueur : ");
		Player player1 = new Player(sc.nextLine());

		System.out.print("Nom du second joueur : ");
		Player player2 = new Player(sc.nextLine());

		initDeck();

		Board board = new Board(drawDeck);

		BoardView.display(board);

		sc.close();
	}
}
