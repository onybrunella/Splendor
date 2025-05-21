package fr.uge.Splendor;

import fr.uge.Splendor.action.BuyCardAction;
import fr.uge.Splendor.action.TakeThreeDifferentTokensAction;
import fr.uge.Splendor.action.TakeTwoSameTokensAction;
import fr.uge.Splendor.board.Board;
import fr.uge.Splendor.board.Deck;
import fr.uge.Splendor.player.Player;
import fr.uge.Splendor.end.End;
import fr.uge.Splendor.view.BoardView;
import fr.uge.Splendor.tokens.TokenBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Splendor {

	public static void showHelp() {
		System.out.println("""
            Commandes disponibles :
            help   → Affiche cette aide
            bank   → Affiche les jetons disponibles dans la banque
            mine   → Affiche vos cartes, bonus et points
            exit   → Quitte la partie
            """);
	}

	//            1      → Prendre 3 jetons différents
	//            2      → Prendre 2 jetons identiques
	//            3      → Acheter une carte
	public static void playerTurn(Player player, TokenBank bank, Scanner sc, Board board) {
		boolean validAction = false;

		System.out.println("\nTour de " + player.getName() + " :");
		System.out.println("Vos jetons : " + player.getPlayerTokens());
		System.out.println("Vos points de prestige : " + player.getPrestigePoints() + "\n");

		while (!validAction) {
			System.out.println("""
                Que voulez-vous faire ?
                1	-> Prendre 3 jetons de couleurs différentes
                2	-> Prendre 2 jetons de la même couleur
                3	-> Acheter une carte
                (Tapez help pour voir les autres commandes disponibles)
                """);

			var input = sc.nextLine().trim().toLowerCase();

			switch (input) {
				case "1" -> {
					TakeThreeDifferentTokensAction.takeThreeDifferentTokens(player, bank, sc);
					validAction = true;
				}
				case "2" -> {
					TakeTwoSameTokensAction.takeTwoSameTokens(player, bank, sc);
					validAction = true;
				}
				case "3" -> {
					if (!BuyCardAction.canAffordAnyCard(player, board)) {
						System.out.println("Vous ne pouvez acheter aucune carte actuellement.");
					} else {
						BuyCardAction.buyCard(player, bank, board, sc);
						validAction = true;
					}
				}
				case "help" -> showHelp();
				case "bank" -> System.out.println(bank + "\n");
				case "mine" -> {
					System.out.println("Cartes : " + player.getCards());
					System.out.println("Bonus : " + player.getBonus());
					System.out.println("Points de prestique : " + player.getPrestigePoints());
					System.out.println("Jetons : " + player.getPlayerTokens() + "\n");
				}
				case "exit" -> {
					System.out.println("Partie quittée.");
					System.exit(0);
				}
				default -> System.out.println("Commande invalide.");
			}
		}

		System.out.println("Jetons après action : " + player.getPlayerTokens());
		System.out.println("Points de prestige : " + player.getPrestigePoints() + "\n");
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);

		System.out.print("Nom du premier joueur : ");
		Player player1 = new Player(sc.nextLine().toUpperCase());

		System.out.print("Nom du second joueur : ");
		Player player2 = new Player(sc.nextLine().toUpperCase());

		Deck deck = new Deck();
		TokenBank totalBank = new TokenBank();
		Board board = new Board(deck.getCards());

		Player[] players = { player1, player2 };
		List<Player> allPlayers = new ArrayList<>(List.of(players));

		int currentPlayerIndex = 0;
		boolean end = false;
		Player endPlayer = null;

		while (true) {
			BoardView.display(board);

			Player currentPlayer = players[currentPlayerIndex];

			playerTurn(currentPlayer, totalBank, sc, board);

			// Affichage des scores à la fin du tour
			System.out.println("\n--- Scores après ce tour ---");
			for (Player p : allPlayers) {
				System.out.println(p.getName() + " : " + p.getPrestigePoints() + " points");
			}

			// Vérifie si la fin doit être déclenchée
			if (!end && End.isEnd(currentPlayer)) {
				end = true;
				endPlayer = currentPlayer;
				System.out.println("\n" + currentPlayer.getName() + " a atteint 15 points. Dernier tour en cours !");
			}

			// Si fin déclenchée et tout le monde a joué un tour complet, on termine
			if (end && currentPlayer == endPlayer) {
				break;
			}

			currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
			TimeUnit.SECONDS.sleep(1);

		}

		End.displayFinalScores(allPlayers);
		Player winner = End.determineWinner(allPlayers);
		End.announceWinner(winner);

		sc.close();
	}
}

