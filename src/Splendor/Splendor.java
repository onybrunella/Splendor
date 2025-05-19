package Splendor;

import java.util.List;
import java.util.Scanner;

public class Splendor {
	private final List<DevelopmentCard> drawDeck = null;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//initialisation des joueurs
		System.out.println("Veuillez choisir le nom du premier joueur : ");
//		String user1 = sc.nextLine();
//		Player player1 = new Player(user1);
		Player player1 = new Player(sc.nextLine());
		
		System.out.println("Veuillez choisir le nom du second joueur : ");
		Player player2 = new Player(sc.nextLine());
		
		TokenBank bank = new TokenBank();
		//List<DevelopmentCard> 
		
		
		sc.close();
		
	}

}