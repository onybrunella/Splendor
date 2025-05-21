package fr.uge.Splendor.end;

import fr.uge.Splendor.player.Player;

import java.util.List;

public class End {

    public static boolean isEnd(Player player) {
        return player.getPrestigePoints() >= 15;
    }

    public static Player determineWinner(List<Player> players) {
        Player winner = players.get(0);

        for (Player player : players) {
            if (player.getPrestigePoints() > winner.getPrestigePoints()) {
                winner = player;
            } else if (player.getPrestigePoints() == winner.getPrestigePoints()) {
                // En cas d'égalité : le joueur avec le moins de cartes gagne
                if (player.getCards().size() < winner.getCards().size()) {
                    winner = player;
                }
            }
        }

        return winner;
    }

    public static void displayFinalScores(List<Player> players) {
        System.out.println("\n=== Scores finaux ===");
        for (Player player : players) {
            System.out.println(player.getName() + " : " + player.getPrestigePoints() + " points (" + player.getCards().size() + " cartes)");
        }
    }

    public static void announceWinner(Player winner) {
        System.out.println("\nVictoire de " + winner.getName() + " avec " + winner.getPrestigePoints() + " points de prestige !!!!!!!");
    }
}
