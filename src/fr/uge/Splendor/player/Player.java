package fr.uge.Splendor.player;
import fr.uge.Splendor.cards.DevelopmentCard;
import fr.uge.Splendor.tokens.GemColor;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Player {
	private final String name;
	private final Map<GemColor, Integer> tokens;
	private final List<DevelopmentCard> cards;

	public Player(String name) {
		this.name = name;
		this.tokens = new HashMap<>();
		for (GemColor gemColor : GemColor.values()) {
			this.tokens.put(gemColor, 0);
		}
		this.cards = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getPlayerTokens(){
		return tokens.entrySet().stream()
				.map(entry -> entry.getKey() + ": " + entry.getValue())
				.collect(Collectors.joining(", "));
	}

	public Map<GemColor, Integer> getTokens(){
		return tokens;
	}

	public List<DevelopmentCard> getCards() {
		String[] lines = new String[6];
		StringBuilder cardLines = new StringBuilder();
		for(DevelopmentCard card : cards){
			//afficher carte
			String[] cardLine = card.toCardLines();
			for(String line: cardLine){
				cardLines.append(line).append("\n");
			}
		}
		return cards;
		}

	//afficher bonus player
	public String getBonus() {
		StringBuilder bonus = new StringBuilder();
		for (var card : cards) {
			bonus.append(card.bonus()).append(" ");
		}
		return bonus.toString();
	}


	//Calcule le nb de points de prestige du joueur
	//version simplifiée mais après on pourra utilisé un stream je pense
	public int getPrestigePoints() {
		int prestigePoints = 0;
		for (DevelopmentCard card : cards) {
			prestigePoints += card.prestige();
		}
		return prestigePoints;
	}

	//vérifie si le joueur a au moins 3 jetons (dans notre version simplifiée) de la couleur de la carte à acheter
	//	on pourrait aussi faire un stream pour ça mais c une version simplifiée
	public boolean canBuy(DevelopmentCard card) {
		Objects.requireNonNull(card);
		GemColor color = card.bonus();

		int tokensOwned = tokens.getOrDefault(color, 0);
		int bonusesOwned = 0;

		for (DevelopmentCard playerCard : cards) {
			if (playerCard.bonus() == color) {
				bonusesOwned++;
			}
		}

		return (tokensOwned + bonusesOwned) >= 3;
	}


//	public int getToken(GemColor gemColor) {
//		Objects.requireNonNull(gemColor);
//		return tokens.getOrDefault(gemColor, 0);
//	}


	//achète une carte (retire 3 jetons de la bonne couleur et ensuite ajt la carte de la collection du joeur)
	public void buyCard(DevelopmentCard card) {
		Objects.requireNonNull(card);

		if (!canBuy(card)) {
			throw new IllegalArgumentException("Pas assez de ressources pour acheter la carte");
		}

		GemColor color = card.bonus();

		int bonusesOwned = 0;
		for (DevelopmentCard playerCard : cards) {
			if (playerCard.bonus() == color) {
				bonusesOwned++;
			}
		}

		int toPay = Math.max(0, 3 - bonusesOwned);

		int currentTokens = tokens.getOrDefault(color, 0);
		if (currentTokens < toPay) {
			throw new IllegalStateException("Le joueur n'a pas assez de jetons pour payer la carte");
		}

		tokens.put(color, currentTokens - toPay);

		cards.add(card);
	}


	//player prend un jeton de la banque
//	public void takeToken(GemColor gemColor) {
//		Objects.requireNonNull(gemColor);
//		tokens.put(gemColor, tokens.getOrDefault(gemColor, 0) + 1); //là on fait +1 pour la verison simplifiée
//		//par la suite faudra faire + un montant passé en paramètre
//	}

	@Override
	public String toString() {
		return name + " -> Jetons: " + tokens;
		//  -> Prestige: " + prestigePoints +
	}
}
