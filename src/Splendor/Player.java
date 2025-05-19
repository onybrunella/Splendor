package Splendor;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;


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
	
	//Calcule le nb de points de prestige du joueur
	//version simplifiée mais après il faudra utilisé un stream je pense
	public int getPrestigePoints() {
		var allPoints = 0;
		for (var card : cards) {
			allPoints += card.prestige();
		}
		return allPoints;
	}
	
	//vérifie si le joueur a au moins 3 jetons (dans notre version simplifiée) de la couleur de la carte à acheter
	public boolean canBuy(DevelopmentCard card) {
		Objects.requireNonNull(card);
		return tokens.getOrDefault(card.bonus(), 0) >= 3;
	}

	public int getToken(GemColor gemColor) {
		Objects.requireNonNull(gemColor);
		return tokens.getOrDefault(gemColor, 0);
	}

	//achète une carte (retire 3 jetons de la bonne couleur et ensuite ajt la carte de la collection du joeur)
	public void buyCard(DevelopmentCard card) {
		Objects.requireNonNull(card);
		//if (!cards.contains(card)) {
			//throw new IllegalArgumentException("You don't have this card");
	//	}
		tokens.put(card.bonus(), tokens.get(card.bonus()) - 3);
		cards.add(card);
	}

	//player prend un jeton de la banque
	public void takeToken(GemColor gemColor) {
		tokens.put(gemColor, tokens.getOrDefault(gemColor, 0) + 1);
	}
	
	@Override
	public String toString() {
        return name +  " -> Jetons: " + tokens;
      //  -> Prestige: " + prestigePoints +
	}
}
