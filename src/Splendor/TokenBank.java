package Splendor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenBank {
	private final Map<GemColor, Integer> tokens;
	public TokenBank() {
		tokens = new HashMap<>();
		//initialisation de la banque avec 4 jetons de chaque couleur
		for(var gemcolor : GemColor.values()) {
			tokens.put(gemcolor, 4);
		}
	}

	public Map<GemColor, Integer> getTokens() {
		return tokens;
	}



	@Override
	//j'ai fais un stream
	public String toString() {
		return "Banque: " + tokens.entrySet().stream()
				.map(entry -> entry.getKey() + ": " + entry.getValue())
				.collect(Collectors.joining(", "));
	}
}