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
	public String getName() {
		return name;
	}
	public Map<GemColor, Integer> getTokens() {
		return tokens;
	}
	public List<DevelopmentCard> getCards() {
		return cards;
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
		Objects.requireNonNull(card); //les requireNonNUll jsp si je dois les garder
		return tokens.getOrDefault(card.bonus(), 0) >= 3;
	}

//	public int getToken(GemColor gemColor) {
//		Objects.requireNonNull(gemColor);
//		return tokens.getOrDefault(gemColor, 0);
//	}


	//achète une carte (retire 3 jetons de la bonne couleur et ensuite ajt la carte de la collection du joeur)
	public void buyCard(DevelopmentCard card) {
		Objects.requireNonNull(card);
		if(!canBuy(card)) {
			throw new IllegalArgumentException("pas assez de jetons pour acheter la carte");
		}
		//retire 3 jetons de la bonne couleur (pour l'instant dans la version simplifiée)
		tokens.put(card.bonus(), tokens.get(card.bonus()) - 3);
		//ajoute la carte à la collection du joueur
		cards.add(card);
	}

	//player prend un jeton de la banque
	public void takeToken(GemColor gemColor) {
		Objects.requireNonNull(gemColor);
		tokens.put(gemColor, tokens.getOrDefault(gemColor, 0) + 1); //là on fait +1 pour la verison simplifiée
		//par la suite faudra faire + un montant passé en paramètre
	}
	
	@Override
	public String toString() {
        return name +  " -> Jetons: " + tokens;
      //  -> Prestige: " + prestigePoints +
	}
}
