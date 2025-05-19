package Splendor;

import java.util.List;
import java.util.Scanner;

public class Splendor {
	private final List<DevelopmentCard> drawDeck = null;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//initialisation des joueurs
		System.out.println("Veuillez choisir le nom du premier joueur");
		String user1 = sc.nextLine();
		Player player1 = new Player(user1);
		
		System.out.println("Veuillez choisir le nom du second joueur");
	//	sc = new Scanner(System.in);
		String user2 = sc.nextLine();
		Player player2 = new Player(user2);
		
		TokenBank bank = new TokenBank();
		//List<DevelopmentCard> 
		
		
		sc.close();
		
	}

}